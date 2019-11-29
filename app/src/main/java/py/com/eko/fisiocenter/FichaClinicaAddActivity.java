package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import py.com.eko.fisiocenter.Modelos.FichaClinica;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.Modelos.PersonaShort;
import py.com.eko.fisiocenter.Modelos.TipoProducto;
import py.com.eko.fisiocenter.WebService.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FichaClinicaAddActivity extends AppCompatActivity {

    py.com.eko.fisiocenter.Modelos.PersonaShort[] medicos;
    py.com.eko.fisiocenter.Modelos.PersonaShort[] pacientes;
    py.com.eko.fisiocenter.Modelos.TipoProducto[] tipoProductos;

    EditText paciente;
    EditText medico;
    EditText tipoProducto;
    EditText motivo;
    EditText diagnostico;
    EditText observacion;

    PersonaShort selectedMedico = null;
    PersonaShort selectedPaciente = null;
    TipoProducto selectedTipoProducto = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_clinica_add);

        paciente = findViewById(R.id.addFichaClinicaCliente);
        medico = findViewById(R.id.addFichaClinicaMedico);
        tipoProducto = findViewById(R.id.addFichaClinicaTipoProducto);
        motivo = findViewById(R.id.addFichaClinicaMotivo);
        diagnostico = findViewById(R.id.addFichaClinicaDiagnostico);
        observacion = findViewById(R.id.addFichaClinicaObservacion);

        paciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dialogPaciente();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        medico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dialogMedico();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        tipoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dialogTipoProducto();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnAdd = findViewById(R.id.btnAddFichaClinica);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFichaClinica();
            }
        });
    }

    public void dialogMedico() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("soloUsuariosDelSistema", true);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FichaClinicaAddActivity.this, android.R.layout.select_dialog_singlechoice);
        Call<Lista<PersonaShort>> callMedicos = Servicios.getServicio().obtenerPersonas(obj);
        callMedicos.enqueue(new Callback<Lista<PersonaShort>>() {
            @Override
            public void onResponse(Call<Lista<PersonaShort>> call, Response<Lista<PersonaShort>> response) {
                medicos = response.body().getLista();
                for(PersonaShort p:medicos){
                    arrayAdapter.add(p.getNombreCompleto());
                }
            }

            @Override
            public void onFailure(Call<Lista<PersonaShort>> call, Throwable t) {
                Log.w("warning", t);
            }
        });
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(FichaClinicaAddActivity.this);
        builderSingle.setTitle("Seleccione el medico");

        builderSingle.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                selectedMedico = medicos[which];
                medico.setText(strName);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(FichaClinicaAddActivity.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Has seleccionado a: ");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();
    }

    public void dialogPaciente() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("soloUsuariosDelSistema", false);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FichaClinicaAddActivity.this, android.R.layout.select_dialog_singlechoice);
        Call<Lista<PersonaShort>> callPacientes = Servicios.getServicio().obtenerPersonas(obj);
        callPacientes.enqueue(new Callback<Lista<PersonaShort>>() {
            @Override
            public void onResponse(Call<Lista<PersonaShort>> call, Response<Lista<PersonaShort>> response) {
                pacientes = response.body().getLista();
                for(PersonaShort p:pacientes){
                    arrayAdapter.add(p.getNombreCompleto());
                }
            }

            @Override
            public void onFailure(Call<Lista<PersonaShort>> call, Throwable t) {
                Log.w("warning", t);
            }
        });
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(FichaClinicaAddActivity.this);
        builderSingle.setTitle("Seleccione el paciente");

        builderSingle.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                selectedPaciente = pacientes[which];
                paciente.setText(strName);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(FichaClinicaAddActivity.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Has seleccionado a: ");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();
    }

    public void dialogTipoProducto() throws JSONException {

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FichaClinicaAddActivity.this, android.R.layout.select_dialog_singlechoice);
        Call<Lista<TipoProducto>> callTipoProductos = Servicios.getServicio().obtenerTipoProductos();
        callTipoProductos.enqueue(new Callback<Lista<TipoProducto>>() {
            @Override
            public void onResponse(Call<Lista<TipoProducto>> call, Response<Lista<TipoProducto>> response) {
                tipoProductos = response.body().getLista();
                for(TipoProducto p:tipoProductos){
                    arrayAdapter.add(p.getNombreCategoria());
                }
            }

            @Override
            public void onFailure(Call<Lista<TipoProducto>> call, Throwable t) {
                Log.w("warning", t);
            }
        });

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(FichaClinicaAddActivity.this);
        builderSingle.setTitle("Seleccione tipo de producto");


        builderSingle.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                selectedTipoProducto = tipoProductos[which];
                tipoProducto.setText(strName);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(FichaClinicaAddActivity.this);
                builderInner.setMessage(strName);
                builderInner.setTitle("Has seleccionado a: ");
                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();
    }

    public void addFichaClinica(){
        FichaClinica f = new FichaClinica();

        String motivoText = motivo.getText().toString();
        String observacionText = observacion.getText().toString();
        String diagnosticoText = diagnostico.getText().toString();


        if(motivoText != null && observacionText != null && diagnosticoText != null && selectedMedico != null
        && selectedPaciente != null && selectedTipoProducto != null){
            f.setObservacion(observacionText);
            f.setMotivo(motivoText);
            f.setDiagnostico(diagnosticoText);
            f.setMedico(selectedMedico);
            f.setCliente(selectedPaciente);
            f.setIdTipoProducto(selectedTipoProducto);

            Call<FichaClinica> createFicha = Servicios.getServicio().guardarFichaClinica(f);
            createFicha.enqueue(new Callback<FichaClinica>() {
                @Override
                public void onResponse(Call<FichaClinica> call, Response<FichaClinica> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Exito al guardar",Toast.LENGTH_LONG).show();
                        Intent intentNewActivity = new Intent(FichaClinicaAddActivity.this,
                                FichaClinicaActivity.class);
                        startActivity(intentNewActivity);
                    }else{
                        Toast.makeText(getApplicationContext(),"Error al guardar",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<FichaClinica> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Error al guardar",Toast.LENGTH_LONG).show();
                    Log.w("warning", t);
                }
            });
        }else {
            Toast.makeText(getApplicationContext(),"Complete el/los campo/s",Toast.LENGTH_LONG).show();
        }
    }
}
