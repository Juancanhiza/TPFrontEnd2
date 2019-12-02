package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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

        CardView button= (CardView) findViewById(R.id.card_fichas);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FichaClinicaActivity.class));
            }
        });

        CardView btnReserva= (CardView) findViewById(R.id.card_reservas);
        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReservasActivity.class));
            }

        });

        CardView btnPaciente= (CardView) findViewById(R.id.card_pacientes);
        btnPaciente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,PacientesActivity.class));
            }

        });


    }


}
