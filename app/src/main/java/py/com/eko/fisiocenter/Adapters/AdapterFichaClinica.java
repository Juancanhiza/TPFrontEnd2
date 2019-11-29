package py.com.eko.fisiocenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import py.com.eko.fisiocenter.Modelos.FichaClinica;
import py.com.eko.fisiocenter.R;

public class AdapterFichaClinica extends RecyclerView.Adapter<AdapterFichaClinica.AdapterFichaClinicaHolder>
        implements View.OnClickListener{


    private View.OnClickListener listener;

    FichaClinica[] lista;

    public AdapterFichaClinica(FichaClinica[] lista) {
        this.lista=lista;
    }

    @NonNull
    @Override
    public AdapterFichaClinicaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista, parent,false);
        v.setOnClickListener(this);
        AdapterFichaClinicaHolder ac=new AdapterFichaClinicaHolder(v);

        return ac;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFichaClinicaHolder holder, int position) {
        if(lista[position].getDiagnostico()!=null)
            holder.tvDiagnostico.setText("Diagnóstico: " +lista[position].getDiagnostico());
        if(lista[position].getMotivo()!=null)
            holder.tvMotivo.setText("Motivo: " + lista[position].getMotivo());
        if(lista[position].getCliente()!=null)
            holder.tvCliente.setText("Paciente: "  + lista[position].getCliente().getNombreCompleto());
        if(lista[position].getMedico()!=null)
            holder.tvMedico.setText("Médico: " + lista[position].getMedico().getNombreCompleto());
        if(lista[position].getFechaHora()!=null)
            holder.tvFechaHora.setText( lista[position].getFechaHora());
        if(lista[position].getFechaHora()!=null)
            holder.tvTipoProducto.setText( "Tipo producto: " + lista[position].getIdTipoProducto().getNombreCategoria());
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

    public static class AdapterFichaClinicaHolder extends RecyclerView.ViewHolder {
        TextView tvIdFichaClinica;
        TextView tvMotivo;
        TextView tvDiagnostico;
        TextView tvMedico;
        TextView tvCliente;
        TextView tvFechaHora;
        TextView tvTipoProducto;


        public AdapterFichaClinicaHolder(View v){
            super(v);
            tvIdFichaClinica=v.findViewById(R.id.txtIdFichaClinicaItem);
            tvMotivo=v.findViewById(R.id.txtMotivo);
            tvDiagnostico=v.findViewById(R.id.txtDiagnostico);
            tvMedico=v.findViewById(R.id.txtMedico);
            tvCliente=v.findViewById(R.id.txtCliente);
            tvFechaHora=v.findViewById(R.id.txtFecha);
            tvTipoProducto=v.findViewById(R.id.txtTipoProducto);

        }
    }
}
