package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // norbtou les elts bel id mtehom
        LinearLayout racine = findViewById(R.id.layout_racine_xx);
        TextView tvMessage = findViewById(R.id.tv_message_xx);
        Button btnNouveau = findViewById(R.id.btn_nouveau_xx);

        // 2 nrecuperiw data
        Intent intent = getIntent();
        boolean approuve = intent.getBooleanExtra(Cles.cle(Cles.MATRICULE, "VERDICT"), false);
        String nom = intent.getStringExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"));

        //3 nzaynou selon el jugement final
        if (approuve) {
            racine.setBackgroundColor(Color.parseColor("#2E7D32")); // أخضر
            tvMessage.setText("Bon voyage " + nom + " !");
        } else {
            racine.setBackgroundColor(Color.parseColor("#C62828")); // أحمر
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        // back lel main activ w nfaskhou lokhrina
        btnNouveau.setOnClickListener(v -> {
            Intent retour = new Intent(ResultActivity.this, MainActivity.class);
            retour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(retour);
            finish();
        });
    }
}
