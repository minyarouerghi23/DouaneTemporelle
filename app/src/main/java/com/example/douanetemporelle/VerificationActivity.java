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

        // norbot fl khyout
        tvNom = findViewById(R.id.tv_nom_am);
        tvAge = findViewById(R.id.tv_age_am);
        tvTrajet = findViewById(R.id.tv_trajet_am);
        tvMotif = findViewById(R.id.tv_motif_am);
        btnApprouver = findViewById(R.id.btn_approuver_am);
        btnRefuser = findViewById(R.id.btn_refuser_am);

        // ne9bel les coordonnees ml activity loula
        Intent intent = getIntent();
        String nom = intent.getStringExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"));
        int naissance = intent.getIntExtra(Cles.cle(Cles.MATRICULE, "ANNEE_NAISSANCE"), 0);
        int destination = intent.getIntExtra(Cles.cle(Cles.MATRICULE, "ANNEE_DESTINATION"), 0);
        String motif = intent.getStringExtra(Cles.cle(Cles.MATRICULE, "MOTIF"));

        // ne7seblou el 3am eli bch ysefer fih na7i menou l3ameli touled fih
        int ageTemporel = destination - naissance;

        // na3tik el fichaaaaa
        tvNom.setText("Nom : " + nom);
        tvAge.setText("Âge temporel : " + ageTemporel + " ans");
        tvTrajet.setText("Trajet : " + naissance + " -> " + destination);
        tvMotif.setText("Motif : " + motif);

        // nehlek el bouton idha ken el conditionet el zouz yet7a9e9ou
        if (motif.equals("Modification du passé") && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }

        // ki nenzel ala el fles yebaathli T wela F w esm el sayed
        btnApprouver.setOnClickListener(v -> ouvrirVerdict(true, nom));
        btnRefuser.setOnClickListener(v -> ouvrirVerdict(false, nom));
    }

    // sbab y7eli el activity el theltha
    private void ouvrirVerdict(boolean approuve, String nom) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "VERDICT"), approuve);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"), nom);
        startActivity(intent);
    }

    // tchouf nbr premier wela le
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
