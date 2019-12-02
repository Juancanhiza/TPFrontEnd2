package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import py.com.eko.fisiocenter.Adapters.AdaparteReservarConsulta;
import py.com.eko.fisiocenter.Adapters.AdapterReserva;
import py.com.eko.fisiocenter.Modelos.FichaClinica;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.Modelos.PersonaShort;
import py.com.eko.fisiocenter.Modelos.Reserva;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

public class ReservasAddActivity extends AppCompatActivity {


    List<Reserva> array;

    Boolean checkead = false;

    PersonaShort[] medicos;
    PersonaShort[] pacientes;

    ScrollView svLista;
    RecyclerView rvTurnos;
    EditText etMedico;
    TextView tvPaciente;
    EditText etFecha;
    EditText etTicket;
    EditText etObservaciones;
    CheckBox cbDisponible;
    Button btnBuscar;
    Button btnGuardar;


    String selectedFecha = null;
    PersonaShort selectedMedico = null;
    PersonaShort selectedPaciente = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas_add);


        rvTurnos=findViewById(R.id.rvTurnos);


        LinearLayoutManager llm=new LinearLayoutManager(this);
        rvTurnos.setLayoutManager(llm);
        rvTurnos.setHasFixedSize(true);


        etTicket = findViewById(R.id.etNroTicket);
        etObservaciones = findViewById(R.id.etObservaciones);



        etFecha = findViewById(R.id.etFecha);
        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker(0);
            }
        });


        etMedico = findViewById(R.id.selectMedico);
        tvPaciente = findViewById(R.id.selectPaciente);


        etMedico.setOnClickListener(new View.OnClickListener() {
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

        btnBuscar = findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filtrar();
            }
        });


        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });




    }


    private void guardar(){

        /*JSONObject obj = new JSONObject();
        if(selectedFecha != null){
            try {
                obj.put("fechaCadena",selectedFecha);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(etTicket.getText().toString()!=null){

            Integer pos=Integer.parseInt(etTicket.getText().toString());
            Reserva r = array.get(pos);

            try {
            obj.put("horaInicioCadena",r.getHoraInicioCadena());
            obj.put("horaFinCadena",r.getHoraFinCadena());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if(selectedMedico != null){
                JSONObject per = new JSONObject();
                try {
                    per.put("idPersona", selectedMedico.getIdPesona());
                    obj.put("idEmpleado", per);
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if(selectedPaciente != null){
                JSONObject per = new JSONObject();
                try {
                    per.put("idPersona", selectedPaciente.getIdPesona());
                    obj.put("idCliente", per);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }*/


        Reserva r = new Reserva();
        Integer pos=Integer.parseInt(etTicket.getText().toString());
        Reserva r1 = array.get(pos);

        r.setFechaCadena(selectedFecha);
        r.setHoraInicioCadena(r1.getHoraInicioCadena());
        r.setHoraFinCadena(r1.getHoraFinCadena());
        r.setEmpleado(selectedMedico);
        r.setCliente(selectedPaciente);



        Call<Reserva> createReserva = Servicios.getServicio().guardarReserva(r);
        createReserva.enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Exito al guardar",Toast.LENGTH_LONG).show();
                    Intent intentNewActivity = new Intent(ReservasAddActivity.this,
                            ReservasActivity.class);
                    startActivity(intentNewActivity);
                }else{
                    Toast.makeText(getApplicationContext(),"Error al guardar",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Reserva> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error al guardar",Toast.LENGTH_LONG).show();
                Log.w("warning", t);
            }
        });



        Log.d("filters", etTicket.getText().toString());




    }


    private void filtrar(){

        cbDisponible = findViewById(R.id.cbDisponibles);
        String disponibles = cbDisponible.isChecked() ? "S" : null;
        Integer idMedico = selectedMedico.getIdPesona();

        Call<List<Reserva>> callOpciones = Servicios.getServicio().obtenerAgenda(idMedico, selectedFecha, disponibles);

        callOpciones.enqueue(new Callback<List<Reserva>>() {
            @Override
            public void onResponse(Call<List<Reserva>> call, Response<List<Reserva>> response) {


                    Log.w("warning", "trajo");

                    array = response.body();

                    cargarLista();
            }

            @Override
            public void onFailure(Call<List<Reserva>> call, Throwable t) {
                Log.w("warning", t);
            }
        });



    }


    private void cargarLista(){

        AdaparteReservarConsulta adapter= new AdaparteReservarConsulta(array);
        rvTurnos.setAdapter(adapter);

        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //RadioButton radioButton = v.findViewById(R.id.rbSelecionado);

                //if(radioButton.isChecked()){
                  //  checkead = true;
                   // Log.println(Log.DEBUG,"yayaya","Cool");
                //}


            }
        });
                //svLista.setVisibility(View.VISIBLE);

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
                    selectedFecha = year + "" + mesFormateado + "" + diaFormateado;
                    etFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
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
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ReservasAddActivity.this,
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
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(ReservasAddActivity.this);
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
                etMedico.setText(strName);
                AlertDialog.Builder builderInner = new AlertDialog.Builder(ReservasAddActivity.this);
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
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ReservasAddActivity.this, android.R.layout.select_dialog_singlechoice);
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
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(ReservasAddActivity.this);
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
                AlertDialog.Builder builderInner = new AlertDialog.Builder(ReservasAddActivity.this);
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






}
