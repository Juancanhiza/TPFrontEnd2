package py.com.eko.fisiocenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFichaClinica extends RecyclerView.Adapter<AdapterFichaClinica.FichaClinicaViewHolder> implements View.OnClickListener{

    private Context mContext;
    private ArrayList<FichaClinica> mFichaClinica;
    private View.OnClickListener listener;

    public AdapterFichaClinica(Context c, ArrayList<FichaClinica> array){
        this.mContext = c;
        this.mFichaClinica = array;
    }

    @NonNull
    @Override
    public FichaClinicaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_ficha_clinica, parent, false);
        return new FichaClinicaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FichaClinicaViewHolder holder, int position) {
        FichaClinica currentItem = mFichaClinica.get(position);

        String motivo = currentItem.getMotivo();
        String diagnostico = currentItem.getDiagnostico();
        Persona paciente = currentItem.getPaciente();
        Persona medico = currentItem.getMedico();

        holder.tvDiagnostico.setText( "Diagnóstico: " + diagnostico);
        holder.tvMotivo.setText( "Motivo: " + motivo);
        holder.tvPaciente.setText(paciente.getNombreCompleto());
        holder.tvMedico.setText(medico.getNombreCompleto());
    }

    @Override
    public int getItemCount() {
        return mFichaClinica.size();
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v);
    }

    public class FichaClinicaViewHolder extends RecyclerView.ViewHolder {
        public TextView tvPaciente;
        public TextView tvMedico;
        public TextView tvMotivo;
        public TextView tvDiagnostico;

        public FichaClinicaViewHolder(View itemView){
            super(itemView);
            tvPaciente = itemView.findViewById(R.id.text_paciente);
            tvMedico = itemView.findViewById(R.id.text_medico);
            tvMotivo = itemView.findViewById(R.id.text_motivo);
            tvDiagnostico = itemView.findViewById(R.id.text_diagnostico);
        }
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }
}
