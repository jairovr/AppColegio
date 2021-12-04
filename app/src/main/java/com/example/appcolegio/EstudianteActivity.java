package com.example.appcolegio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EstudianteActivity extends AppCompatActivity {
    private EditText txtDocEstu;
    private EditText txtNomEstu;
    private EditText txtApeEstu;
    private EditText txtDirEstu;
    private EditText txtTelEstu;
    private Spinner spOpcionesEstu;
    private Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante);
        btnRegresar = (Button) findViewById(R.id.btnRegresarEstudiante);
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent btnRegresar = new Intent(EstudianteActivity.this, MainActivity.class);
                startActivity(btnRegresar);
            }
        });
    }
}