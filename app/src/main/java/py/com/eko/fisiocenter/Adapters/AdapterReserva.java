package py.com.eko.fisiocenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import py.com.eko.fisiocenter.Modelos.Reserva;
import py.com.eko.fisiocenter.R;

import java.text.DateFormat;
import java.util.Date;
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
            holder.tvObservacion.setText("Observacion: "  +lista[position].getObservacion());
        if(lista[position].getIdCliente()!=null)
            holder.tvCliente.setText("Paciente: "  + lista[position].getIdCliente().getNombreCompleto());
        if(lista[position].getIdEmpleado()!=null)
            holder.tvMedico.setText("Medico: " + lista[position].getIdEmpleado().getNombreCompleto());
        if(lista[position].getIdEmpleado()!=null) {
            String fecha = lista[position].getFechaCadena();
            fecha = fecha.substring(6,8)+'/'+fecha.substring(4,6)+'/'+fecha.substring(0,4);
            holder.tvFecha.setText("Fecha: " + fecha);
        }
        if(lista[position].getHoraInicioCadena()!=null && lista[position].getHoraFinCadena()!=null){

            String horaInicio = lista[position].getHoraInicioCadena().substring(0,2)+":"+
                                lista[position].getHoraInicioCadena().substring(2,4);

            String horaFin = lista[position].getHoraFinCadena().substring(0,2)+":"+
                    lista[position].getHoraFinCadena().substring(2,4);

            holder.tvHoraIF.setText("Horario: "+
                    horaInicio+" - "+
                    horaFin);
        }
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
        TextView tvMedico;
        TextView tvCliente;
        TextView tvFecha;
        TextView tvHoraIF;//hora inicio-fin

        public AdapterReservaHolder(View v){
            super(v);
            tvIdReserva=v.findViewById(R.id.txtIdReserva);
            tvMedico=v.findViewById(R.id.txtMedico1);
            tvCliente=v.findViewById(R.id.txtPaciente1);
            tvFecha=v.findViewById(R.id.txtFecha1);
            tvHoraIF=v.findViewById(R.id.txtHoraIF1);
            tvObservacion=v.findViewById(R.id.txtObservacion);
        }
    }


}
