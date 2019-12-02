package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import py.com.eko.fisiocenter.Modelos.PersonaShort;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.WebService.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText usuario;
    EditText password;
    String[] nombress;
    PersonaShort[] medicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario=findViewById(R.id.txtNombreUsuario);
        usuario.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        password=findViewById(R.id.txtPassword);
        password.setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        JSONObject obj = new JSONObject();
        try {
            obj.put("soloUsuariosDelSistema", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<Lista<PersonaShort>> callMedicos = Servicios.getServicio().obtenerPersonas(obj);
        callMedicos.enqueue(new Callback<Lista<PersonaShort>>() {
            @Override
            public void onResponse(Call<Lista<PersonaShort>> call, Response<Lista<PersonaShort>> response) {
                medicos = response.body().getLista();

            }

            @Override
            public void onFailure(Call<Lista<PersonaShort>> call, Throwable t) {
                Log.w("warning", t);
            }
        });
    }




    public void ingresar(View view) {

        String user = usuario.getText().toString();
        String pass = password.getText().toString();
        Boolean bn = false;
        for(int i = 0; i < medicos.length; i++) {
            if (user.equals(medicos[i].getNombre()))
            {
                bn=true;
            }
        }

        if (bn==true) {

            Intent intentNewActivity = new Intent(LoginActivity.this, MainActivity.class);
            Bundle b = new Bundle();
            b.putString("usuario",usuario.getText().toString());
            intentNewActivity.putExtras(b);
            startActivity(intentNewActivity);


        }else {

            Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_LONG).show();
            usuario.setText("");
            password.setText("");
        }
    }
}