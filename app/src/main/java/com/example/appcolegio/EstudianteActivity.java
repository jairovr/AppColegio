package com.example.appcolegio;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appcolegio.modelo.estudiante;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class EstudianteActivity extends AppCompatActivity {
    private EditText docuE;
    private EditText nomE;
    private EditText apeE;
    private EditText direcE;
    private EditText telE;
    private ListView listv_personas;

    //Declaracion de las variabes para la lista
    private List<estudiante> listEstudiante = new ArrayList<estudiante>();
    ArrayAdapter<estudiante> arrayAdapterEstudiante;
    estudiante estudianteSelected;
    //Variables para manejar la Base de Datos
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante);
        nomE = (EditText) findViewById(R.id.txtnombreEstudiante);
        apeE = (EditText) findViewById(R.id.txtapellidoEstudiante);
        direcE = (EditText) findViewById(R.id.txtdireccionEstudiante);
        docuE = (EditText) findViewById(R.id.txtdocumentoEstudiante);
        telE = (EditText) findViewById(R.id.txttelefonoEstudiante);
        listv_personas = (ListView) findViewById(R.id.lvdatosPersonales);
        inicializarfirebase();
        listarDatos();
        listv_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                estudianteSelected = (estudiante) parent.getItemAtPosition(position);
                docuE.setText(estudianteSelected.getDocEst());
                nomE.setText(estudianteSelected.getNomEst());
                apeE.setText(estudianteSelected.getApeEst());
                direcE.setText(estudianteSelected.getDirEst());
                telE.setText(estudianteSelected.getTelEst());
            }
        });
    }

    private void listarDatos() {
        databaseReference.child("estudiante").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                listEstudiante.clear();
                for (DataSnapshot objSnaptshot : datasnapshot.getChildren()) {
                    estudiante p = objSnaptshot.getValue(estudiante.class);
                    listEstudiante.add(p);
                    arrayAdapterEstudiante = new ArrayAdapter<estudiante>(EstudianteActivity.this, android.R.layout.simple_list_item_1, listEstudiante);
                    listv_personas.setAdapter(arrayAdapterEstudiante);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

   private void inicializarfirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_estudiante, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void limpiarCajas() {
        docuE.setText("");
        nomE.setText("");
        apeE.setText("");
        direcE.setText("");
        telE.setText("");
    }
    private void validacion() {
        String documento = docuE.getText().toString();
        String nombre = nomE.getText().toString();
        String apellido = apeE.getText().toString();
        String direccion = direcE.getText().toString();
        String telefono = telE.getText().toString();
        if (documento.isEmpty()) {
            docuE.setError("Requerido");
        } else if (nombre.isEmpty()) {
            nomE.setError("Requerido");
        } else if (apellido.isEmpty()) {
            apeE.setError("Requerido");
        } else if (direccion.isEmpty()) {
            direcE.setError("Requerido");
        } else if (telefono.isEmpty()) {
            telE.setError("Requerido");
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String documento = docuE.getText().toString();
        String nombre = nomE.getText().toString();
        String apellido = apeE.getText().toString();
        String direccion = direcE.getText().toString();
        String telefono = telE.getText().toString();
        switch (item.getItemId()) {
            case R.id.icon_nuevo:
                if (nombre.isEmpty() || (apellido.isEmpty()) || (documento.isEmpty()) || (telefono.isEmpty()) || (direccion.isEmpty())) {
                    validacion();
                } else {
                    estudiante p = new estudiante();
                    p.setuId(UUID.randomUUID().toString());
                    p.setDocEst(documento);
                    p.setNomEst(nombre);
                    p.setApeEst(apellido);
                    p.setDirEst(direccion);
                    p.setTelEst(telefono);
                    databaseReference.child("estudiante").child(p.getuId()).setValue(p);
                    Toast.makeText(this, "Registro Guardado", Toast.LENGTH_SHORT).show();
                    limpiarCajas();
                    break;
                }
            case R.id.icon_guardar:
                estudiante p = new estudiante();
                p.setuId(estudianteSelected.getuId());
                p.setDocEst(docuE.getText().toString().trim());
                p.setNomEst(nomE.getText().toString().trim());
                p.setApeEst(apeE.getText().toString().trim());
                p.setDirEst(direcE.getText().toString().trim());
                p.setTelEst(telE.getText().toString().trim());

                databaseReference.child("estudiante").child(p.getuId()).setValue(p);
                Toast.makeText(this, "Registro Actualizado", Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            case R.id.icon_eliminar:
                p = new estudiante();
                p.setuId(estudianteSelected.getuId());
                databaseReference.child("estudiante").child(p.getuId()).removeValue();
                Toast.makeText(this, "Registro Eliminado", Toast.LENGTH_SHORT).show();
                limpiarCajas();
                break;
            default:
                break;
        }
        return true;
    }
}