package py.com.eko.fisiocenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import py.com.eko.fisiocenter.Modelos.Reserva;
import py.com.eko.fisiocenter.R;

import java.util.List;

public class AdapterReserva extends RecyclerView.Adapter<AdapterReserva.AdapterReservaHolder>
        implements View.OnClickListener{


    private View.OnClickListener listener;

    Reserva[] lista;

    public AdapterReserva(Reserva[] lista) {
        this.lista=lista;
    }

    @NonNull
    @Override
    public AdapterReservaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_reserva, parent,false);
        v.setOnClickListener(this);
        AdapterReservaHolder ac=new AdapterReservaHolder(v);

        return ac;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterReservaHolder holder, int position) {
        if(lista[position].getIdReserva()!=null)
            holder.tvIdReserva.setText(lista[position].getIdReserva().toString());
        if(lista[position].getObservacion()!=null)
            holder.tvObservacion.setText(lista[position].getObservacion());

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


    public static class AdapterReservaHolder extends RecyclerView.ViewHolder {
        TextView tvIdReserva;
        TextView tvObservacion;

        public AdapterReservaHolder(View v){
            super(v);
            tvIdReserva=v.findViewById(R.id.txtIdReserva);
            tvObservacion=v.findViewById(R.id.txtObservacion);
        }
    }


}
