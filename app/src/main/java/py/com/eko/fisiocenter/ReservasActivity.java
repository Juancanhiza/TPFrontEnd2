package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import py.com.eko.fisiocenter.Adapters.AdapterReserva;
import py.com.eko.fisiocenter.Modelos.FichaClinica;
import py.com.eko.fisiocenter.Modelos.PersonaShort;
import py.com.eko.fisiocenter.Modelos.Reserva;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.Modelos.TipoProducto;
import py.com.eko.fisiocenter.WebService.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class ReservasActivity extends AppCompatActivity {

    RecyclerView rvReserva;
    Reserva[] array;
    ProgressBar pbCargando;


    PersonaShort[] medicos;
    PersonaShort[] pacientes;
    TipoProducto[] tipoProductos;
    EditText etFechaDesde;
    EditText etFechaHasta;
    EditText tvMedico;
    EditText tvPaciente;


    String selectedFechaInicio = null;
    String selectedFechaFin = null;
    PersonaShort selectedMedico = null;
    PersonaShort selectedPaciente = null;
    TipoProducto selectedTipoProducto = null;

    TextView tvNoResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        rvReserva=findViewById(R.id.rvReservas);
       // pbCargando = findViewById(R.id.cargandoReservas);
       // pbCargando.setVisibility(View.VISIBLE);

        LinearLayoutManager llm=new LinearLayoutManager(this);
        rvReserva.setLayoutManager(llm);
        rvReserva.setHasFixedSize(true);

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

        Button btnAdd = findViewById(R.id.btnAgregarReserva);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNewActivity = new Intent(ReservasActivity.this,
                        ReservasAddActivity.class);
                startActivity(intentNewActivity);            }
        });


        tvNoResults = findViewById(R.id.txtNoResults);
        tvNoResults.setVisibility(View.GONE);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //TODO: traer solo del dia actual
        Call<Lista<Reserva>> callR = Servicios.getServicio().obtenerReservasSimple();
        callR.enqueue(new Callback<Lista<Reserva>>() {
            @Override
            public void onResponse(Call<Lista<Reserva>> call, Response<Lista<Reserva>> response) {
                for (Reserva c : response.body().getLista()) {
                    Log.d("w", "fichaclinica de id " + c.getIdReserva().toString() +
                            " y descripcion " + c.getObservacion());
                }

                //pbCargando.setVisibility(View.INVISIBLE);

                array = response.body().getLista();
                cargarLista();


            }

            @Override
            public void onFailure(Call<Lista<Reserva>> call, Throwable t) {
                Log.w("warning", t);
            }
        });

    }

    private void cargarLista(){
        AdapterReserva adapter= new AdapterReserva(array);
        rvReserva.setAdapter(adapter);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putInt("idReserva",array[rvReserva.getChildAdapterPosition(v)].getIdReserva());
                //b.putString("observacion",array[rvReserva.getChildAdapterPosition(v)].getObservacion());
                //b.putString("paciente",array[rvReserva.getChildAdapterPosition(v)].getIdCliente().getNombreCompleto());



                Intent intentNewActivity = new Intent(ReservasActivity.this,
                        ReservasEditActivity.class);
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
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ReservasActivity.this,
                android.R.layout.select_dialog_singlechoice);
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
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(ReservasActivity.this);
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
                AlertDialog.Builder builderInner = new AlertDialog.Builder(ReservasActivity.this);
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
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ReservasActivity.this, android.R.layout.select_dialog_singlechoice);
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
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(ReservasActivity.this);
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
                AlertDialog.Builder builderInner = new AlertDialog.Builder(ReservasActivity.this);
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

        Log.d("filters", obj.toString());
        Call<Lista<Reserva>> callReservas = Servicios.getServicio().obtenerReservasFilter(obj);
        callReservas.enqueue(new Callback<Lista<Reserva>>() {
            @Override
            public void onResponse(Call<Lista<Reserva>> call, Response<Lista<Reserva>> response) {
                array = response.body().getLista();
                if(array.length == 0){
                    tvNoResults.setVisibility(View.VISIBLE);
                }else{
                    tvNoResults.setVisibility(View.GONE);
                }
                cargarLista();
            }

            @Override
            public void onFailure(Call<Lista<Reserva>> call, Throwable t) {
                Log.w("warning", t);
            }
        });
    }


    public void clearFilter(){
        selectedFechaInicio = null;
        selectedFechaFin = null;
        selectedMedico = null;
        selectedPaciente = null;

        etFechaDesde.setText("");
        etFechaHasta.setText("");
        tvMedico.setText("");
        tvPaciente.setText("");
        Call<Lista<Reserva>> callReservas = Servicios.getServicio().obtenerReservasSimple();
        callReservas.enqueue(new Callback<Lista<Reserva>>() {
            @Override
            public void onResponse(Call<Lista<Reserva>> call, Response<Lista<Reserva>> response) {
                array = response.body().getLista();
                if(array.length == 0){
                    tvNoResults.setVisibility(View.VISIBLE);
                }else{
                    tvNoResults.setVisibility(View.GONE);
                }
                cargarLista();
            }

            @Override
            public void onFailure(Call<Lista<Reserva>> call, Throwable t) {
                Log.w("warning", t);
            }
        });
    }



}
