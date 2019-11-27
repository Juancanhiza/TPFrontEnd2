package py.com.eko.fisiocenter.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import py.com.eko.fisiocenter.R;
import py.com.eko.fisiocenter.models.Reserva;

import java.util.List;

public class AdapterReserva extends RecyclerView.Adapter<AdapterReserva.AdapterCategoriaHolder>
        implements View.OnClickListener{


    private View.OnClickListener listener;

    Reserva[] lista;

    public AdapterReserva(Reserva[] lista) {
        this.lista=lista;
    }

    @NonNull
    @Override
    public AdapterCategoriaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent,false);
        v.setOnClickListener(this);
        AdapterCategoriaHolder ac=new AdapterCategoriaHolder(v);

        return ac;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategoriaHolder holder, int position) {
        holder.tvDescripcion.setText(lista[position].getObservacion());

        holder.tvIdCategoria.setText(lista[position].getIdReserva().toString());
    }

    @Override
    public int getItemCount() {
        return lista.length;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }


    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public static class AdapterCategoriaHolder extends RecyclerView.ViewHolder {
        TextView tvIdCategoria;
        TextView tvDescripcion;
        public AdapterCategoriaHolder(View v){
            super(v);
            tvDescripcion=v.findViewById(R.id.txtObservacionItem);
            tvIdCategoria=v.findViewById(R.id.txtIdReservaItem);

        }
    }
}

