package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import py.com.eko.fisiocenter.Modelos.Categoria;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.Modelos.Reserva;
import py.com.eko.fisiocenter.WebService.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    Reserva[] array;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Call<Lista<Categoria>> callCategoria = Servicios.getServicio().obtenerCategorias(
                "idCategoria","asc"
        );
        callCategoria.enqueue(new Callback<Lista<Categoria>>() {
            @Override
            public void onResponse(Call<Lista<Categoria>> call, Response<Lista<Categoria>> response) {
                for (Categoria c : response.body().getLista()) {
                    Log.d("web", "categoria de id " + c.getIdCategoria() +
                            " y descripcion " + c.getDescripcion());
                }
                //array=response.body().getLista();
                //cargarLista();
            }

            @Override
            public void onFailure(Call<Lista<Categoria>> call, Throwable t) {
                Log.w("warning", t.getCause().toString());
            }
        });
    }

}
