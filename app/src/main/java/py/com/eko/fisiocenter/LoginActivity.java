package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.util.Log;
import py.com.eko.fisiocenter.Modelos.Persona;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.WebService.Servicios;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText usuario;
    EditText password;
    Persona[] array;
    private Persona[] arrayUsuarios;
    private String[] nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario=findViewById(R.id.txtNombreUsuario);
        password=findViewById(R.id.txtPassword);
    }

    @Override
    protected void onResume() {
        super.onResume();


        Call<Lista<Persona>> callR = Servicios.getServicio().getListaUsuarios("{\"soloUsuariosDelSistema\":true}");
        callR.enqueue(new Callback<Lista<Persona>>() {
            @Override
            public void onResponse(Call<Lista<Persona>> call, Response<Lista<Persona>> response) {
                /*for (Persona c : response.body().getLista()) {
                    Log.d("w", "fichaclinica de id " + c.getIdReserva().toString() +
                            " y descripcion " + c.getObservacion());
                }

                pbCargando.setVisibility(View.INVISIBLE);

                 */
                arrayUsuarios = response.body().getLista();
                nombres = new String[arrayUsuarios.length];

                for(int i=0 ; i< arrayUsuarios.length ; i++){
                    nombres[i] = arrayUsuarios[i].getUsuarioLogin();
                }


            }

            @Override
            public void onFailure(Call<Lista<Persona>> call, Throwable t) {
                Log.e("Login", t.getLocalizedMessage());
            }
        });

    }


    public void ingresar(View view) {

        if (usuario.getText().toString().equals("admin")
                && password.getText().toString().equals("123")) {

            Intent intentNewActivity = new Intent(LoginActivity.this, MainActivity.class);
            Bundle b = new Bundle();
            b.putString("usuario",usuario.getText().toString());
            intentNewActivity.putExtras(b);
            startActivity(intentNewActivity);


        }
    }
}