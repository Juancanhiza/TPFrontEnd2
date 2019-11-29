package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Request;
import py.com.eko.fisiocenter.Modelos.FichaClinica;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.WebService.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FichaClinicaEditActivity extends AppCompatActivity {

    Integer fichaId;
    String obs;
    String nombrePaciente;
    TextView tvNombrePaciente;
    EditText etObs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_clinica_edit);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        fichaId = b.getInt("idFichaClinica");
        obs = b.getString("observacion");
        nombrePaciente = b.getString("paciente");

        tvNombrePaciente = findViewById(R.id.editFichaClinicaNombrePaciente);
        etObs = findViewById(R.id.editFichaClinicaObservacion);

        tvNombrePaciente.setText(nombrePaciente);
        etObs.setText(obs);

        Button btnEdit = findViewById(R.id.btnEditFichaClinica);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateFichaClinica();
            }
        });
    }

    public void updateFichaClinica(){
        obs = etObs.getText().toString();

        JSONObject obj = new JSONObject();
        try {
            obj.put("idFichaClinica", fichaId);
            obj.put("observacion", obs);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        FichaClinica f = new FichaClinica();

        f.setObservacion(obs);
        f.setIdFichaClinica(fichaId);
        Call<FichaClinica> updateFicha = Servicios.getServicio().actualizarFichaClinica(f);
        updateFicha.enqueue(new Callback<FichaClinica>() {
            @Override
            public void onResponse(Call<FichaClinica> call, Response<FichaClinica> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Exito al editar",Toast.LENGTH_LONG).show();
                    Intent intentNewActivity = new Intent(FichaClinicaEditActivity.this,
                            FichaClinicaActivity.class);
                    startActivity(intentNewActivity);
                }else{
                    Toast.makeText(getApplicationContext(),"Error al editar",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<FichaClinica> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error al editar",Toast.LENGTH_LONG).show();
                Log.w("warning", t);
            }
        });
    }
}
