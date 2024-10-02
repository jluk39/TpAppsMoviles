package com.example.parcialpreguntados;

public class Pregunta {
    private String pregunta;
    private String[] opciones;
    private int respuestaCorrecta;
    private int imagen;

    // Constructor para inicializar la pregunta, las opciones, la respuesta correcta y la imagen
    public Pregunta(String pregunta, String[] opciones, int respuestaCorrecta, int imagen) {
        this.pregunta = pregunta;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
        this.imagen = imagen;
    }

    // Métodos getters para acceder a los datos de la pregunta
    public String getPregunta() {
        return pregunta;
    }

    public String[] getOpciones() {
        return opciones;
    }

    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public int getImagen() {
        return imagen;
    }
}

