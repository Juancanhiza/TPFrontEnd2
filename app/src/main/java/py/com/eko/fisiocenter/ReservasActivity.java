package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import py.com.eko.fisiocenter.Adapters.AdapterReserva;
import py.com.eko.fisiocenter.Modelos.Reserva;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.WebService.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class ReservasActivity extends AppCompatActivity {

    RecyclerView rvReserva;
    Reserva[] array;
    ProgressBar pbCargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        rvReserva=findViewById(R.id.rvReservas);
        pbCargando = findViewById(R.id.cargandoReservas);
        pbCargando.setVisibility(View.VISIBLE);

        LinearLayoutManager llm=new LinearLayoutManager(this);
        rvReserva.setLayoutManager(llm);
        rvReserva.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();


        Call<Lista<Reserva>> callR = Servicios.getServicio().obtenerReservasSimple();
        callR.enqueue(new Callback<Lista<Reserva>>() {
            @Override
            public void onResponse(Call<Lista<Reserva>> call, Response<Lista<Reserva>> response) {
                for (Reserva c : response.body().getLista()) {
                    Log.d("w", "fichaclinica de id " + c.getIdReserva().toString() +
                            " y descripcion " + c.getObservacion());
                }

                pbCargando.setVisibility(View.INVISIBLE);

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

                //Intent intentNewActivity = new Intent(FichaClinica.this,
                  //      FichaClinicaAddEdit.class);
//                Bundle b = new Bundle();
//                b.putInt("idCategoria",array[rvFichaClinica.getChildAdapterPosition(v)].getIdCategoria());
//                b.putString("descripcion",array[rvFichaClinica.getChildAdapterPosition(v)].getDescripcion());
//                intentNewActivity.putExtras(b);
                //startActivity(intentNewActivity);
            }
        });
    }
}
