package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FichaClinica extends AppCompatActivity {

    RecyclerView rvFichaClinica;
    FichaClinicaModel[] array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_clinica);

        rvFichaClinica=findViewById(R.id.rvCategoria);

        LinearLayoutManager llm=new LinearLayoutManager(this);
        rvFichaClinica.setLayoutManager(llm);
        rvFichaClinica.setHasFixedSize(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Call<Lista<FichaClinicaModel>> callFichasClinicas = Servicios.getFichaClinicaService().obtenerFichasClinicas();
        callFichasClinicas.enqueue(new Callback<Lista<FichaClinicaModel>>() {
            @Override
            public void onResponse(Call<Lista<FichaClinicaModel>> call, Response<Lista<FichaClinicaModel>> response) {
                for (FichaClinicaModel c : response.body().getLista()) {
                    Log.d("w", "fichaclinica de id " + c.getIdFichaClinica() +
                            " y descripcion " + c.getMotivo());
                }

                array = response.body().getLista();
                cargarLista();
            }

            @Override
            public void onFailure(Call<Lista<FichaClinicaModel>> call, Throwable t) {
                Log.w("warning", t);
            }
        });
    }

    public void irAddFicha(View view) {
        Intent intentNewActivity = new Intent(FichaClinica.this,
                FichaClinicaAddEdit.class);
        startActivity(intentNewActivity);
    }

    private void cargarLista(){
        AdapterFichaClinica adapter= new AdapterFichaClinica(array);
        rvFichaClinica.setAdapter(adapter);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentNewActivity = new Intent(FichaClinica.this,
                        FichaClinicaAddEdit.class);
//                Bundle b = new Bundle();
//                b.putInt("idCategoria",array[rvFichaClinica.getChildAdapterPosition(v)].getIdCategoria());
//                b.putString("descripcion",array[rvFichaClinica.getChildAdapterPosition(v)].getDescripcion());
//                intentNewActivity.putExtras(b);
                startActivity(intentNewActivity);
            }
        });
    }

}
