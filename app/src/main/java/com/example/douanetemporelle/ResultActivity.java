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

        // 1) norbtou el 3anaser mte3 el XML bel id mte3hom
        LinearLayout racine = findViewById(R.id.layout_racine_am);
        TextView tvTampon = findViewById(R.id.tv_tampon_am);
        TextView tvMessage = findViewById(R.id.tv_message_am);
        Button btnNouveau = findViewById(R.id.btn_nouveau_am);

        // 2) nesta9blou el 7okm (true/false) w esm el msafer
        Intent intent = getIntent();
        boolean approuve = intent.getBooleanExtra(Cles.cle(Cles.MATRICULE, "VERDICT"), false);
        String nom = intent.getStringExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"));

        // 3) 7asb el 7okm yetbaddel loun el background (khdher wala a7mer), el tampon w el message
        if (approuve) {
            racine.setBackgroundColor(Color.parseColor("#2E7D32"));
            tvTampon.setText("APPROUVÉ");
            tvMessage.setText("Bon voyage " + nom + " !");
        } else {
            racine.setBackgroundColor(Color.parseColor("#C62828"));
            tvTampon.setText("REFUSÉ");
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        // 4) zorr "Nouveau voyageur" yrajja3na lel MainActivity w yesker el activities el 9dima
        btnNouveau.setOnClickListener(v -> {
            Intent retour = new Intent(ResultActivity.this, MainActivity.class);
            retour.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(retour);
            finish();
        });
    }
}