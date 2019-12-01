package py.com.eko.fisiocenter.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import py.com.eko.fisiocenter.Modelos.Paciente;
import py.com.eko.fisiocenter.R;

public class AdapterPacientes extends RecyclerView.Adapter<AdapterPacientes.AdapterPacientesHolder>
        implements View.OnClickListener{


    private View.OnClickListener listener;

    Paciente[] lista;

    public AdapterPacientes(Paciente[] lista) {
        this.lista=lista;
    }

    @NonNull
    @Override
    public AdapterPacientesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_paciente, parent,false);
        v.setOnClickListener(this);
        AdapterPacientesHolder ac=new AdapterPacientesHolder(v);

        return ac;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPacientesHolder holder, int position) {
        if(lista[position].getNombre()!=null && lista[position].getApellido()!=null)
            holder.tvNombre.setText(lista[position].getNombre() + " " + lista[position].getApellido());
        if(lista[position].getFechaNacimiento()!=null)
            holder.tvFechaNacimiento.setText("Fecha de nacimiento: " + lista[position].getFechaNacimiento());
        if(lista[position].getCedula()!=null)
            holder.tvCedula.setText("Cédula: "  + lista[position].getCedula());
        if(lista[position].getTelefono()!=null)
            holder.tvTelefono.setText("Teléfono: " + lista[position].getTelefono());
        if(lista[position].getEmail()!=null)
            holder.tvEmail.setText( lista[position].getEmail());
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

    public static class AdapterPacientesHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvFechaNacimiento;
        TextView tvCedula;
        TextView tvTelefono;
        TextView tvEmail;


        public AdapterPacientesHolder(View v){
            super(v);
            tvNombre=v.findViewById(R.id.txtPacienteNombre);
            tvFechaNacimiento=v.findViewById(R.id.txtPacienteFechaDeNacimiento);
            tvCedula=v.findViewById(R.id.txtPacienteCedula);
            tvTelefono=v.findViewById(R.id.txtPacienteTelefono);
            tvEmail=v.findViewById(R.id.txtPacienteEmail);

        }
    }
}

