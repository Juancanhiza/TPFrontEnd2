package py.com.eko.fisiocenter.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import py.com.eko.fisiocenter.R;
import py.com.eko.fisiocenter.models.Lista;
import py.com.eko.fisiocenter.models.Reserva;
import py.com.eko.fisiocenter.webservice.ReservaApi;
import py.com.eko.fisiocenter.webservice.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    RecyclerView rvReserva;
    Reserva[] array;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        rvReserva=getView().findViewById(R.id.rvReserva);

        LinearLayoutManager llm=new LinearLayoutManager(getContext());
        rvReserva.setLayoutManager(llm);
        rvReserva.setHasFixedSize(true);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        Call<Lista<Reserva>> callCategoria = Servicios.getReservaService().obtenerReservas(
                "idReserva","asc"
        );
        callCategoria.enqueue(new Callback<Lista<Reserva>>() {
            @Override
            public void onResponse(Call<Lista<Reserva>> call, Response<Lista<Reserva>> response) {
                /*for (Categoria c : response.body().getLista()) {
                    Log.d("w", "categoria de id " + c.getIdCategoria() +
                            " y descripcion " + c.getDescripcion());
                }*/
                array=response.body().getLista();
                cargarLista();
            }

            @Override
            public void onFailure(Call<Lista<Reserva>> call, Throwable t) {
                Log.w("warning", t.getCause().toString());
            }
        });

    }

    private void cargarLista(){
        AdapterReserva adapter= new AdapterReserva(array);
        rvReserva.setAdapter(adapter);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CategoriaActivity.this,"categoria seleccionada: "+
                //      ""+array[rvCategoria.getChildAdapterPosition(v)].getIdCategoria()+";;"+
                //    array[rvCategoria.getChildAdapterPosition(v)].getDescripcion(),Toast.LENGTH_LONG).show();
                /*Intent intentNewActivity = new Intent(CategoriaActivity.this,
                        AgregarEditarCategoriaActivity.class);
                Bundle b = new Bundle();
                b.putInt("idCategoria",array[rvCategoria.getChildAdapterPosition(v)].getIdCategoria());
                b.putString("descripcion",array[rvCategoria.getChildAdapterPosition(v)].getDescripcion());
                intentNewActivity.putExtras(b);
                startActivity(intentNewActivity);*/
            }
        });
    }
}