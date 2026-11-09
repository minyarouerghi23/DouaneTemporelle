package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    TextView tvNom, tvAge, tvTrajet, tvMotif, tvParadoxe;
    Button btnApprouver, btnRefuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // 1) norbtou el 3anaser mte3 el XML bel id mte3hom
        tvNom = findViewById(R.id.tv_nom_am);
        tvAge = findViewById(R.id.tv_age_am);
        tvTrajet = findViewById(R.id.tv_trajet_am);
        tvMotif = findViewById(R.id.tv_motif_am);
        tvParadoxe = findViewById(R.id.tv_paradoxe_am);
        btnApprouver = findViewById(R.id.btn_approuver_am);
        btnRefuser = findViewById(R.id.btn_refuser_am);

        // 2) nesta9blou el ma3loumet mel activity el loula
        Intent intent = getIntent();
        String nom = intent.getStringExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"));
        int naissance = intent.getIntExtra(Cles.cle(Cles.MATRICULE, "ANNEE_NAISSANCE"), 0);
        int destination = intent.getIntExtra(Cles.cle(Cles.MATRICULE, "ANNEE_DESTINATION"), 0);
        String motif = intent.getStringExtra(Cles.cle(Cles.MATRICULE, "MOTIF"));

        // 3) n7esbou el 3omr ezzamani = 3am el wejha - 3am el wilada
        int ageTemporel = destination - naissance;

        // 4) na3rdhou el molakhas fi el passeport
        tvNom.setText("Nom : " + nom);
        tvAge.setText("Âge temporel : " + ageTemporel + " ans");
        tvTrajet.setText("Trajet : " + naissance + " -> " + destination);
        tvMotif.setText("Motif : " + motif);

        // 5) ken el sabab "Modification du passé" w el far9 3adad awwali: n3attlou zorr Approuver w nodhherou el message
        if (motif.equals("Modification du passé") && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
            btnApprouver.setAlpha(0.4f);
            tvParadoxe.setVisibility(View.VISIBLE);
            tvParadoxe.setText("Paradoxe détecté : " + ageTemporel + " est un nombre premier");
        }

        // 6) ki nodghtou 3al zorr: nab3thou el 7okm (true/false) w esm el msafer
        btnApprouver.setOnClickListener(v -> ouvrirVerdict(true, nom));
        btnRefuser.setOnClickListener(v -> ouvrirVerdict(false, nom));
    }

    // 7) fonction tefta7 el activity ethalitha w tab3eth el 7okm w el esm
    private void ouvrirVerdict(boolean approuve, String nom) {
        Intent intent = new Intent(VerificationActivity.this, ResultActivity.class);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "VERDICT"), approuve);
        intent.putExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"), nom);
        startActivity(intent);
    }

    // 8) fonction tet2akked ken el 3adad awwali
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