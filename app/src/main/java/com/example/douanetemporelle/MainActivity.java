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

    // 1) تعريف عناصر الواجهة
    EditText etNom, etNaissance, etDestination;
    Spinner spMotif;
    Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2) ربط العناصر بالـ XML عن طريق الـ id
        etNom = findViewById(R.id.et_nom_xx);
        etNaissance = findViewById(R.id.et_naissance_xx);
        etDestination = findViewById(R.id.et_destination_xx);
        spMotif = findViewById(R.id.sp_motif_xx);
        btnInspecter = findViewById(R.id.btn_inspecter_xx);

        // 3) ملء القائمة المنسدلة بالأسباب الثلاثة
        String[] motifs = {"Tourisme", "Modification du passé", "Fuite"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, motifs);
        spMotif.setAdapter(adapter);

        // 4) عند الضغط على الزر
        btnInspecter.setOnClickListener(v -> {
            String nom = etNom.getText().toString().trim();
            String naissance = etNaissance.getText().toString().trim();
            String destination = etDestination.getText().toString().trim();
            String motif = spMotif.getSelectedItem().toString();

            // 5) التحقق من أن كل الحقول مملوءة
            if (nom.isEmpty() || naissance.isEmpty() || destination.isEmpty()) {
                Toast.makeText(this, "Remplis tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            // 6) إرسال المعطيات إلى النشاط الثاني (Intent صريح)
            Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
            intent.putExtra(Cles.cle(Cles.MATRICULE, "NOM_VOYAGEUR"), nom);
            intent.putExtra(Cles.cle(Cles.MATRICULE, "ANNEE_NAISSANCE"), Integer.parseInt(naissance));
            intent.putExtra(Cles.cle(Cles.MATRICULE, "ANNEE_DESTINATION"), Integer.parseInt(destination));
            intent.putExtra(Cles.cle(Cles.MATRICULE, "MOTIF"), motif);
            startActivity(intent);
        });
    }
}
