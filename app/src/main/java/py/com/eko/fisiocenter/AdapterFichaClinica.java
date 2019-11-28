package py.com.eko.fisiocenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterFichaClinica extends RecyclerView.Adapter<AdapterFichaClinica.AdapterFichaClinicaHolder>
        implements View.OnClickListener{


    private View.OnClickListener listener;

    FichaClinicaModel[] lista;

    public AdapterFichaClinica(FichaClinicaModel[] lista) {
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
        if(lista[position].getIdFichaClinica()!=null)
            holder.tvIdFichaClinica.setText(lista[position].getIdFichaClinica().toString());
        if(lista[position].getDiagnostico()!=null)
            holder.tvDiagnostico.setText(lista[position].getDiagnostico());
        if(lista[position].getMotivo()!=null)
            holder.tvMotivo.setText(lista[position].getMotivo());

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

        public AdapterFichaClinicaHolder(View v){
            super(v);
            tvIdFichaClinica=v.findViewById(R.id.txtIdFichaClinicaItem);
            tvMotivo=v.findViewById(R.id.txtMotivo);
            tvDiagnostico=v.findViewById(R.id.txtDiagnostico);
        }
    }
}
