package py.com.eko.fisiocenter.Adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import py.com.eko.fisiocenter.FichaClinicaActivity;
import py.com.eko.fisiocenter.FichaClinicaAddActivity;
import py.com.eko.fisiocenter.Modelos.Archivo;
import py.com.eko.fisiocenter.Modelos.FichaClinica;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.Modelos.Paciente;
import py.com.eko.fisiocenter.R;
import py.com.eko.fisiocenter.WebService.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterArchivo extends RecyclerView.Adapter<AdapterArchivo.AdapterArchivoHolder> {




    Archivo[] lista;

    public AdapterArchivo(Archivo[] lista) {
        this.lista=lista;
    }

    @NonNull
    @Override
    public AdapterArchivoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_paciente, parent,false);

        AdapterArchivoHolder ac=new AdapterArchivoHolder(v);

        return ac;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterArchivoHolder holder, final int position) {
        if(lista[position].getIdFichaClinica()!=null)
            holder.tvFichaId.setText("Ficha " + lista[position].getIdFichaClinica());
        if(lista[position].getNombre()!=null)
            holder.tvNombre.setText(lista[position].getNombre());
        if(lista[position].getUrlImagen()!=null)
            holder.tvUrl.setText(lista[position].getUrlImagen());
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteArchivo(lista[position].getIdFichaClinica());
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.length;
    }



    public static class AdapterArchivoHolder extends RecyclerView.ViewHolder {
        TextView tvFichaId;
        TextView tvUrl;
        TextView tvNombre;
        Button btnEliminar;


        public AdapterArchivoHolder(View v){
            super(v);
            tvNombre=v.findViewById(R.id.txtArchivoNombre);
            tvFichaId=v.findViewById(R.id.txtArchivoFichaId);
            tvUrl=v.findViewById(R.id.txtArchivoURL);
            btnEliminar=v.findViewById(R.id.buttonEliminarArchivo);

        }
    }

    public void deleteArchivo(Integer idFicha){
        Call<String> callArchivo = Servicios.getServicio().deleteArchivo(idFicha);
        callArchivo.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
//                    Toast.makeText(this,"Exito al guardar",Toast.LENGTH_LONG).show();
//                    Intent intentNewActivity = new Intent(FichaClinicaAddActivity.this,
//                            FichaClinicaActivity.class);
//                    startActivity(intentNewActivity);
                }else{
//                    Toast.makeText(getApplicationContext(),"Error al guardar",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.w("warning", t);
            }
        });
    }
}