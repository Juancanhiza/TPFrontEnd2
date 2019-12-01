package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import py.com.eko.fisiocenter.Adapters.AdapterFichaClinica;
import py.com.eko.fisiocenter.Adapters.AdapterPacientes;
import py.com.eko.fisiocenter.Modelos.FichaClinica;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.Modelos.Paciente;
import py.com.eko.fisiocenter.WebService.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PacientesActivity extends AppCompatActivity {

    EditText nombre;
    EditText apellido;
    Paciente[] array;
    TextView tvNoResults;
    RecyclerView rvPacientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        nombre  = findViewById(R.id.selectNombrePaciente);
        apellido = findViewById(R.id.selectApellidoPaciente);

        rvPacientes = findViewById(R.id.rvPacientes);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        rvPacientes.setLayoutManager(llm);
        rvPacientes.setHasFixedSize(true);

        Button btnBuscar = findViewById(R.id.btnBuscarPaciente);
        Button btnLimpiarFiltro = findViewById(R.id.btnLimpiarFiltrosPaciente);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    filter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        btnLimpiarFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFilter();
            }
        });

        tvNoResults = findViewById(R.id.txtNoResultsPaciente);
        tvNoResults.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        call();
    }

    private void filter() throws JSONException {

        String nom = nombre.getText().toString();
        String ape = apellido.getText().toString();

        JSONObject obj = new JSONObject();

        obj.put("nombre", nom);
        obj.put("apellido", ape);

        Call<Lista<Paciente>> callPacientes = Servicios.getServicio().obtenerPacientesFilter(obj, "S");
        callPacientes.enqueue(new Callback<Lista<Paciente>>() {
            @Override
            public void onResponse(Call<Lista<Paciente>> call, Response<Lista<Paciente>> response) {
                array = response.body().getLista();
                if(array.length == 0){
                    tvNoResults.setVisibility(View.VISIBLE);
                }else{
                    tvNoResults.setVisibility(View.GONE);
                }
                cargarLista();
            }

            @Override
            public void onFailure(Call<Lista<Paciente>> call, Throwable t) {
                Log.w("warning", t);
            }
        });
    }

    private void clearFilter(){
        nombre.setText("");
        apellido.setText("");
        call();
    }

    private void call(){
        Call<Lista<Paciente>> callPacientes = Servicios.getServicio().obtenerPacientes();
        callPacientes.enqueue(new Callback<Lista<Paciente>>() {
            @Override
            public void onResponse(Call<Lista<Paciente>> call, Response<Lista<Paciente>> response) {
                array = response.body().getLista();
                if(array.length == 0){
                    tvNoResults.setVisibility(View.VISIBLE);
                }else{
                    tvNoResults.setVisibility(View.GONE);
                }
                cargarLista();
            }

            @Override
            public void onFailure(Call<Lista<Paciente>> call, Throwable t) {
                Log.w("warning", t);
            }
        });
    }

    private void cargarLista(){
        AdapterPacientes adapter= new AdapterPacientes(array);
        rvPacientes.setAdapter(adapter);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentNewActivity = new Intent(PacientesActivity.this,
                        PacienteAddEditActivity.class);
                intentNewActivity.putExtra("objeto", array[rvPacientes.getChildAdapterPosition(v)]);
                startActivity(intentNewActivity);
            }
        });
    }
}
