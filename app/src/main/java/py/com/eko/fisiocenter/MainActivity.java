package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import py.com.eko.fisiocenter.Modelos.Categoria;
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

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button= (Button) findViewById(R.id.btnChangeActivity);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FichaClinicaActivity.class));
            }
        });

        Button btnReserva= (Button) findViewById(R.id.btnReservaActivity);
        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReservasActivity.class));
            }

        });

        Button btnPaciente= (Button) findViewById(R.id.btnPacienteActivity);
        btnPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PacientesActivity.class));
            }

        });


    }


}
