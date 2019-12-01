package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import py.com.eko.fisiocenter.Adapters.AdapterFichaClinica;
import py.com.eko.fisiocenter.Modelos.FichaClinica;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.Modelos.PersonaShort;
import py.com.eko.fisiocenter.Modelos.TipoProducto;
import py.com.eko.fisiocenter.WebService.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FichaClinicaActivity extends AppCompatActivity {

    RecyclerView rvFichaClinica;
    py.com.eko.fisiocenter.Modelos.FichaClinica[] array;
    py.com.eko.fisiocenter.Modelos.PersonaShort[] medicos;
    py.com.eko.fisiocenter.Modelos.PersonaShort[] pacientes;
    py.com.eko.fisiocenter.Modelos.TipoProducto[] tipoProductos;
    EditText etFechaDesde;
    EditText etFechaHasta;
    EditText tvMedico;
    EditText tvPaciente;
    EditText tvTipoProducto;

    String selectedFechaInicio = null;
    String selectedFechaFin = null;
    PersonaShort selectedMedico = null;
    PersonaShort selectedPaciente = null;
    TipoProducto selectedTipoProducto = null;

    TextView tvNoResults;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_clinica);

        rvFichaClinica=findViewById(R.id.rvCategoria);

        LinearLayoutManager llm=new LinearLayoutManager(this);
        rvFichaClinica.setLayoutManager(llm);
        rvFichaClinica.setHasFixedSize(true);

        etFechaDesde = findViewById(R.id.editTextFechaDesde);
        etFechaHasta = findViewById(R.id.editTextFechaHasta);
        etFechaDesde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(0);
            }
        });
        etFechaHasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(1);
            }
        });

        tvMedico = findViewById(R.id.selectMedico);
        tvPaciente = findViewById(R.id.selectPaciente);
        tvTipoProducto = findViewById(R.id.selectTipoProducto);
        tvMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dialogMedico();
                } catch (JSONException e) {
                        e.printStackTrace();
                }
            }
        });
        tvPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dialogPaciente();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        tvTipoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dialogTipoProducto();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btn = findViewById(R.id.btnBuscar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    filterData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Button btnClear = findViewById(R.id.btnLimpiarFiltros);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFilter();
            }
        });

        Button btnAdd = findViewById(R.id.btnAgregarFichaClinica);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNewActivity = new Intent(FichaClinicaActivity.this,
                        FichaClinicaAddActivity.class);
                startActivity(intentNewActivity);            }
        });

        tvNoResults = findViewById(R.id.txtNoResults);
        tvNoResults.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Call<Lista<FichaClinica>> callFichasClinicas = Servicios.getServicio().obtenerFichasClinicas();
        callFichasClinicas.enqueue(new Callback<Lista<py.com.eko.fisiocenter.Modelos.FichaClinica>>() {
            @Override
            public void onResponse(Call<Lista<py.com.eko.fisiocenter.Modelos.FichaClinica>> call, Response<Lista<py.com.eko.fisiocenter.Modelos.FichaClinica>> response) {
                array = response.body().getLista();
                if(array.length == 0){
                    tvNoResults.setVisibility(View.VISIBLE);
                }else{
                    tvNoResults.setVisibility(View.GONE);
                }
                cargarLista();
            }

            @Override
            public void onFailure(Call<Lista<py.com.eko.fisiocenter.Modelos.FichaClinica>> call, Throwable t) {
                Log.w("warning", t);
            }
        });
    }

    private void cargarLista(){
        AdapterFichaClinica adapter= new AdapterFichaClinica(array);
        rvFichaClinica.setAdapter(adapter);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();
                b.putInt("idFichaClinica",array[rvFichaClinica.getChildAdapterPosition(v)].getIdFichaClinica());
                b.putString("observacion",array[rvFichaClinica.getChildAdapterPosition(v)].getObservacion());
                b.putString("paciente",array[rvFichaClinica.getChildAdapterPosition(v)].getCliente().getNombreCompleto());

                Intent intentNewActivity = new Intent(FichaClinicaActivity.this,
                        FichaClinicaEditActivity.class);
                intentNewActivity.putExtras(b);
                startActivity(intentNewActivity);
            }
        });
    }

    private static final String CERO = "0";
    private static final String BARRA = "/";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    public void showDatePicker(int type){
        final int t = type;
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                if( t == 0){
                    selectedFechaInicio = year + "" + mesFormateado + "" + diaFormateado;
                    etFechaDesde.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                }else {
                    selectedFechaFin = year + "" + mesFormateado + "" + diaFormateado;
                    etFechaHasta.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
                }


            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }

    public void dialogMedico() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("soloUsuariosDelSistema", true);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FichaClinicaActivity.this, android.R.layout.select_dialog_singlechoice);
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
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(FichaClinicaActivity.this);
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
                tvMedico.setText(strName);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(FichaClinicaActivity.this);
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
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FichaClinicaActivity.this, android.R.layout.select_dialog_singlechoice);
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
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(FichaClinicaActivity.this);
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
                tvPaciente.setText(strName);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(FichaClinicaActivity.this);
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

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FichaClinicaActivity.this, android.R.layout.select_dialog_singlechoice);
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

        AlertDialog.Builder builderSingle = new AlertDialog.Builder(FichaClinicaActivity.this);
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
                tvTipoProducto.setText(strName);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(FichaClinicaActivity.this);
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

    public void filterData() throws JSONException {
        JSONObject obj = new JSONObject();

        if(selectedFechaInicio != null){
            obj.put("fechaDesdeCadena", selectedFechaInicio);
        }

        if(selectedFechaFin != null) {
            obj.put("fechaHastaCadena", selectedFechaFin);
        }

        if(selectedMedico != null){
            JSONObject per = new JSONObject();
            per.put("idPersona",selectedMedico.getIdPesona());
            obj.put("idEmpleado", per);
        }

        if(selectedPaciente != null){
            JSONObject per = new JSONObject();
            per.put("idPersona",selectedPaciente.getIdPesona());
            obj.put("idCliente", per);
        }

        if(selectedTipoProducto != null){
            JSONObject per = new JSONObject();
            per.put("idTipoProducto",selectedTipoProducto.getIdTipoProducto());
            obj.put("idTipoProducto", per);
        }
        Log.d("filters", obj.toString());
        Call<Lista<FichaClinica>> callFichasClinicas = Servicios.getServicio().obtenerFichasClinicasFilter(obj);
        callFichasClinicas.enqueue(new Callback<Lista<py.com.eko.fisiocenter.Modelos.FichaClinica>>() {
            @Override
            public void onResponse(Call<Lista<py.com.eko.fisiocenter.Modelos.FichaClinica>> call, Response<Lista<py.com.eko.fisiocenter.Modelos.FichaClinica>> response) {
                array = response.body().getLista();
                if(array.length == 0){
                    tvNoResults.setVisibility(View.VISIBLE);
                }else{
                    tvNoResults.setVisibility(View.GONE);
                }
                cargarLista();
            }

            @Override
            public void onFailure(Call<Lista<py.com.eko.fisiocenter.Modelos.FichaClinica>> call, Throwable t) {
                Log.w("warning", t);
            }
        });
    }

    public void clearFilter(){
        selectedFechaInicio = null;
        selectedFechaFin = null;
        selectedMedico = null;
        selectedPaciente = null;
        selectedTipoProducto = null;
        
        etFechaDesde.setText("");
        etFechaHasta.setText("");
        tvMedico.setText("");
        tvPaciente.setText("");
        tvTipoProducto.setText("");
        Call<Lista<FichaClinica>> callFichasClinicas = Servicios.getServicio().obtenerFichasClinicas();
        callFichasClinicas.enqueue(new Callback<Lista<py.com.eko.fisiocenter.Modelos.FichaClinica>>() {
            @Override
            public void onResponse(Call<Lista<py.com.eko.fisiocenter.Modelos.FichaClinica>> call, Response<Lista<py.com.eko.fisiocenter.Modelos.FichaClinica>> response) {
                array = response.body().getLista();
                if(array.length == 0){
                    tvNoResults.setVisibility(View.VISIBLE);
                }else{
                    tvNoResults.setVisibility(View.GONE);
                }
                cargarLista();
            }

            @Override
            public void onFailure(Call<Lista<py.com.eko.fisiocenter.Modelos.FichaClinica>> call, Throwable t) {
                Log.w("warning", t);
            }
        });
    }
}
