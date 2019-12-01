package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import py.com.eko.fisiocenter.Modelos.FichaClinica;
import py.com.eko.fisiocenter.Modelos.Paciente;
import py.com.eko.fisiocenter.Modelos.Persona;
import py.com.eko.fisiocenter.WebService.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PacienteAddEditActivity extends AppCompatActivity {

    EditText edNombre = null;
    EditText edApellido = null;
    EditText edFechaNacimiento = null;
    EditText edTelefono = null;
    EditText edCedula = null;
    EditText edRuc = null;
    EditText edEmail = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_add_edit);

        edNombre = findViewById(R.id.addPacienteNombre);
        edApellido = findViewById(R.id.addPacienteApellido);
        edFechaNacimiento = findViewById(R.id.addPacienteFechaNacimiento);
        edTelefono = findViewById(R.id.addPacienteTelefono);
        edCedula = findViewById(R.id.addPacienteCedula);
        edRuc = findViewById(R.id.addPacienteRUC);
        edEmail = findViewById(R.id.addPacienteEmail);

        edFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        Button btnAddPaciente = findViewById(R.id.btnAddPaciente);
        btnAddPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePaciente();
            }
        });
    }

    private static final String CERO = "0";
    private static final String BARRA = "-";

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    private void showDatePicker(){

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
                edFechaNacimiento.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);

            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        },anio, mes, dia);
        //Muestro el widget
        recogerFecha.show();
    }

    private void savePaciente(){
        Paciente paciente = new Paciente();

        String nombre = edNombre.getText().toString();
        String apellido = edApellido.getText().toString();
        String cedula = edCedula.getText().toString();
        String ruc = edRuc.getText().toString();
        String telefono = edTelefono.getText().toString();
        String email = edEmail.getText().toString();
        String fechaNacimiento = edFechaNacimiento.getText().toString();

        paciente.setNombre(nombre);
        paciente.setApellido(apellido);
        paciente.setCedula(cedula);
        paciente.setRuc(ruc);
        paciente.setTelefono(telefono);
        paciente.setEmail(email);
        paciente.setFechaNacimiento(fechaNacimiento + " 00:00:00");
        paciente.setTipoPersona("FISICA");

        Call<Paciente> createPaciente = Servicios.getServicio().guardarPaciente(paciente);
        createPaciente.enqueue(new Callback<Paciente>() {
            @Override
            public void onResponse(Call<Paciente> call, Response<Paciente> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Exito al guardar",Toast.LENGTH_LONG).show();
//                        Intent intentNewActivity = new Intent(FichaClinicaAddActivity.this,
//                                FichaClinicaActivity.class);
//                        startActivity(intentNewActivity);
                }else{
                    Toast.makeText(getApplicationContext(),"Error al guardar",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Paciente> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error al guardar",Toast.LENGTH_LONG).show();
                Log.w("warning", t);
            }
        });
    }

}
