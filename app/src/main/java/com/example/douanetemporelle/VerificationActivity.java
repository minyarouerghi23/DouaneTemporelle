package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    TextView tvNom, tvAge, tvTrajet, tvMotif;
    Button btnApprouver, btnRefuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // 1 norbtou les elts mta xml bel id mtehom
        tvNom = findViewById(R.id.tv_nom_xx);
        tvAge = findViewById(R.id.tv_age_xx);
        tvTrajet = findViewById(R.id.tv_trajet_xx);
        tvMotif = findViewById(R.id.tv_motif_xx);
        btnApprouver = findViewById(R.id.btn_approuver_xx);
        btnRefuser = findViewById(R.id.btn_refuser_xx);

        // 2 nrecuperiw data mel activ 1
        Intent intent = getIntent();
        String nom = intent.getStringExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"));
        int naissance = intent.getIntExtra(Cles.cle(Cles.MATRICULE, "ANNEE_NAISSANCE"), 0);
        int destination = intent.getIntExtra(Cles.cle(Cles.MATRICULE, "ANNEE_DESTINATION"), 0);
        String motif = intent.getStringExtra(Cles.cle(Cles.MATRICULE, "MOTIF"));

        // 3 3mor = an dest-an naiss
        int ageTemporel = destination - naissance;

        // 4 recap
        tvNom.setText("Nom : " + nom);
        tvAge.setText("Âge temporel : " + ageTemporel + " ans");
        tvTrajet.setText("Trajet : " + naissance + " -> " + destination);
        tvMotif.setText("Motif : " + motif);

        //5 btn approuv ssi modif passé+ diff nb 1er
        if (motif.equals("Modification du passé") && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }

        // when clicked nbaathoulou res + esmou
        btnApprouver.setOnClickListener(v -> ouvrirVerdict(true, nom));
        btnRefuser.setOnClickListener(v -> ouvrirVerdict(false, nom));
    }

    // 7 chn7elou activ3
    private void ouvrirVerdict(boolean approuve, String nom) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "VERDICT"), approuve);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"), nom);
        startActivity(intent);
    }

    // 8 fct verif 1er ou pas
    private boolean estPremier(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
