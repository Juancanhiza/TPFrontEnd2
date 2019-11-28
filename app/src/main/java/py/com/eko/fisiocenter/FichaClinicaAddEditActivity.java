package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class FichaClinicaAddEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_clinica_add_edit);
        FichaClinica ficha = (FichaClinica) getIntent().getSerializableExtra("object");
        Log.d("Nombre del paciente", ficha.getPaciente().getNombreCompleto());
    }
}
