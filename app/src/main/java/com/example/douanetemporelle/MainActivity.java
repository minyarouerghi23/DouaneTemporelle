package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // declarina les champs
    EditText etNom, etNaissance, etDestination;
    Spinner spMotif;
    Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // rbatna les elements bl XML atravers el ID
        etNom = findViewById(R.id.et_nom_am);
        etNaissance = findViewById(R.id.et_naissance_am);
        etDestination = findViewById(R.id.et_destination_am);
        spMotif = findViewById(R.id.sp_motif_am);
        btnInspecter = findViewById(R.id.btn_inspecter_am);

        // n3amrou el liste
        String[] motifs = {"Tourisme", "Modification du passé", "Fuite"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, motifs);
        spMotif.setAdapter(adapter);

        // kif tenzel chneya ysir
        btnInspecter.setOnClickListener(v -> {
            String nom = etNom.getText().toString().trim();
            String naissance = etNaissance.getText().toString().trim();
            String destination = etDestination.getText().toString().trim();
            String motif = spMotif.getSelectedItem().toString();

            // tthabet ma fma hata case fergha
            if (nom.isEmpty() || naissance.isEmpty() || destination.isEmpty()) {
                Toast.makeText(this, "Remplis tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            int n = Integer.parseInt(naissance);
            int d = Integer.parseInt(destination);
            if (d <= n) {
                Toast.makeText(this, "L'année de destination doit être après la naissance", Toast.LENGTH_SHORT).show();
                return;
            }

            // teb3ath les coordonnees ll activity eli baadha
            Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
            intent.putExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"), nom);
            intent.putExtra(Cles.cle(Cles.MATRICULE, "ANNEE_NAISSANCE"), n);
            intent.putExtra(Cles.cle(Cles.MATRICULE, "ANNEE_DESTINATION"), d);
            intent.putExtra(Cles.cle(Cles.MATRICULE, "MOTIF"), motif);
            startActivity(intent);
        });
    }
}