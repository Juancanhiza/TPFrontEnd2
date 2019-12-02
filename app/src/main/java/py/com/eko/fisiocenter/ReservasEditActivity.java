package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.Modelos.Reserva;
import py.com.eko.fisiocenter.WebService.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class ReservasEditActivity extends AppCompatActivity {

    Integer reservaId;
    Reserva reserva;

    EditText etMedico;
    EditText etObservaciones;

    Button btnGuardar;
    Button btnAtras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas_edit);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        reservaId = b.getInt("idReserva");

        etMedico = findViewById(R.id.etMedico);
        etObservaciones = findViewById(R.id.etObservaciones);


        btnGuardar = findViewById(R.id.btnGurdad);
        btnAtras = findViewById(R.id.btnAtras);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentNewActivity = new Intent(ReservasEditActivity.this,
                        ReservasActivity.class);
                startActivity(intentNewActivity);
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


    @Override
    protected void onResume(){
        super.onResume();




        Call<Reserva> callR = Servicios.getServicio().obtenerReserva(reservaId);
        callR.enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(Call<Reserva> call, Response<Reserva> response) {


                //pbCargando.setVisibility(View.INVISIBLE);

                reserva = response.body();
                cargarDatos();


            }

            @Override
            public void onFailure(Call<Reserva> call, Throwable t) {
                Log.w("warning", t);
            }
        });





    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioSi:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.radioNo:
                if (checked)
                    // Ninjas rule
                    break;
        }
    }


    private void cargarDatos(){

        etMedico.setText( reserva.getIdEmpleado().getNombreCompleto());
        etMedico.setEnabled(false);

        etObservaciones.setText( reserva.getObservacion());

        if(reserva.getFlagAsistio()!=null){
            if(reserva.getFlagAsistio().equals("S")){
                RadioButton r = findViewById(R.id.radioSi);
                r.setChecked(true);
            }else{
                RadioButton r = findViewById(R.id.radioNo);
                r.setChecked(true);
            }
        }

    }

}
