package com.mohammedfakham.tdm_eff_theorique_2018_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button calculer, effacer;
    EditText vitesse, vitesseDownload, vitesseUpload, vitesseSociaux;
    ImageView aiguille;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vitesse = findViewById(R.id.vitesse);
        vitesseDownload = findViewById(R.id.vitesse_download);
        vitesseUpload = findViewById(R.id.vitesse_upload);
        vitesseSociaux = findViewById(R.id.vitesse_socieux);
        aiguille = findViewById(R.id.aiguille);

        calculer = findViewById(R.id.calculer);
        effacer = findViewById(R.id.effacer);

        effacer.setOnClickListener((View v) -> {
            effacer();
        });

        calculer.setOnClickListener((View v) -> {
            calculer(Integer.parseInt(vitesse.getText().toString()));
        });

    }

    void effacer() {
        vitesse.setText("");
        vitesseDownload.setText("");
        vitesseUpload.setText("");
        vitesseSociaux.setText("");
        aiguille.setRotation(-90f);
    }

    void calculer(int debit) {
        if (debit < 0) {
            rotation(0, -90f);
            Toast.makeText(MainActivity.this, "Erreur! la vitesse de la connexion doit être entre 0 et 100 Mb/s", Toast.LENGTH_LONG).show();
        } else if (debit > 100) {
            rotation(0, 90f);
            Toast.makeText(MainActivity.this, "Erreur! la vitesse de la connexion doit être entre 0 et 100 Mb/s", Toast.LENGTH_LONG).show();
        } else {
            vitesseDownload.setText(String.format("%.2f", debit * 0.75));
            vitesseUpload.setText(String.format("%.2f", debit * 0.15));
            vitesseSociaux.setText(String.format("%.2f", debit * 0.1f));
            rotation(0, -90f + debit);
        }
    }

    void rotation(float ang1, float ang2) {
        RotateAnimation rotate = new RotateAnimation(ang2, ang1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setInterpolator(new LinearInterpolator());

        aiguille.startAnimation(rotate);
        aiguille.setRotation(ang2);
    }
}
