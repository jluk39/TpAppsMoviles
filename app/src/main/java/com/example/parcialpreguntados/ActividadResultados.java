package com.example.parcialpreguntados;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActividadResultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_resultados);

        TextView tvResultado = findViewById(R.id.tv_resultado);
        Button btnVolver = findViewById(R.id.btn_volver);
        ImageView imgGif = findViewById(R.id.img_gif);  // ref img

        Intent intent = getIntent();
        int puntaje = intent.getIntExtra("puntaje", 0);
        String nombre = intent.getStringExtra("nombre");

        // mostrar img
        imgGif.setImageResource(R.drawable.gif_auto);

        String mensajeFinal;
        if (puntaje <= 1) {
            mensajeFinal = "¡" + nombre + ", tu puntaje es: " + puntaje + "!\nNecesitas seguir practicando.";
        } else if (puntaje == 2 || puntaje == 3) {
            mensajeFinal = "¡" + nombre + ", tu puntaje es: " + puntaje + "!\nTe fue bien, pero sigue esforzándote y serás un experto.";
        } else {
            mensajeFinal = "¡" + nombre + ", tu puntaje es: " + puntaje + "!\n¡Tuviste un muy buen desempeño!";
        }

        tvResultado.setText(mensajeFinal);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverIntent = new Intent(ActividadResultados.this, MainActivity.class);
                startActivity(volverIntent);
                finish();
            }
        });
    }
}

