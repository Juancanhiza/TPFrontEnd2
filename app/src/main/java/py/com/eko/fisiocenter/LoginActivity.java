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