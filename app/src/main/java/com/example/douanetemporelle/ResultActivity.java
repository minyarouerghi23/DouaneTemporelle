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

        // rbatna les elements
        LinearLayout racine = findViewById(R.id.layout_racine_xx);
        TextView tvMessage = findViewById(R.id.tv_message_xx);
        Button btnNouveau = findViewById(R.id.btn_nouveau_xx);

        // bch ne9bel esmou z yet3ada wela ma yet3addech
        Intent intent = getIntent();
        boolean approuve = intent.getBooleanExtra(Cles.cle(Cles.MATRICULE, "VERDICT"), false);
        String nom = intent.getStringExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"));

        // 7asb ena ch9arart yetbadel loun el background akhdher wela a7mer
        if (approuve) {
            racine.setBackgroundColor(Color.parseColor("#2E7D32"));
            tvMessage.setText("Bon voyage " + nom + " !");
        } else {
            racine.setBackgroundColor(Color.parseColor("#C62828"));
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        // traja3ni ll mainactivity w yetsakrou les previous activities
        btnNouveau.setOnClickListener(v -> {
            Intent retour = new Intent(ResultActivity.this, MainActivity.class);
            retour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(retour);
            finish();
        });
    }
}
