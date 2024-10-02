package com.example.parcialpreguntados;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ActividadResultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_resultados);

        TextView tvResultado = findViewById(R.id.tv_resultado);
        Button btnVolver = findViewById(R.id.btn_volver);

        Intent intent = getIntent();
        int puntaje = intent.getIntExtra("puntaje", 0);
        String nombre = intent.getStringExtra("nombre");

        tvResultado.setText("¡Felicitaciones " + nombre + "! Tu puntaje es: " + puntaje);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverIntent = new Intent(ActividadResultados.this, MainActivity.class);
                startActivity(volverIntent);
            }
        });
    }
}
