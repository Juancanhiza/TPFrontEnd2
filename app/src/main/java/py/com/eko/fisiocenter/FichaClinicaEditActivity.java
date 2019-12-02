package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import py.com.eko.fisiocenter.Adapters.AdapterArchivo;
import py.com.eko.fisiocenter.Adapters.AdapterFichaClinica;
import py.com.eko.fisiocenter.Modelos.Archivo;
import py.com.eko.fisiocenter.Modelos.FichaClinica;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.Utils.FilePath;
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

    Button  bUpload;
    EditText chooseFile;
    ProgressDialog dialog;

    Archivo[] archivos;
    RecyclerView rvArchivos;
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String selectedFilePath;
    private String SERVER_URL = "http://coderefer.com/extras/UploadToServer.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_clinica_edit);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        fichaId = b.getInt("idFichaClinica");
        obs = b.getString("observacion");
        nombrePaciente = b.getString("paciente");

        rvArchivos = findViewById(R.id.rvArchivos);

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

        bUpload = findViewById(R.id.btnGuardarArchivo);
        chooseFile = findViewById(R.id.seleccionarArchivo);

        chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFileChooser();
            }
        });

        bUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedFilePath != null) {
                    dialog = ProgressDialog.show(FichaClinicaEditActivity.this, "", "Uploading File...", true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //creating new thread to handle Http Operations
                            uploadFile(selectedFilePath);
                        }
                    }).start();
                } else {
                    Toast.makeText(FichaClinicaEditActivity.this, "Please choose a File First", Toast.LENGTH_SHORT).show();
                }
            }
        });

        LinearLayoutManager llm=new LinearLayoutManager(this);
        rvArchivos.setLayoutManager(llm);
        rvArchivos.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getArchivos();

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

    private void showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), PICK_FILE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_FILE_REQUEST) {
                if (data == null) {
                    //no data present
                    return;
                }


                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this, selectedFileUri);
                Log.i(TAG, "Selected File Path:" + selectedFilePath);

                if (selectedFilePath != null && !selectedFilePath.equals("")) {
                    chooseFile.setText(selectedFilePath);
                } else {
                    Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    //android upload file to server
    public void uploadFile(final String selectedFilePath) {

        File selectedFile = new File(selectedFilePath);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length - 1];

        //pass it like this
        File file = new File(selectedFilePath);

        Map<String,Object> params = new HashMap<String, Object>();

        params.put("file", file);
        params.put("nombre", fileName);
        params.put("idFichaClinica", fichaId);

        Call<String> uploadFile = Servicios.getServicio().guardarArchivo(params);
        uploadFile.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Exito al subir archivo",Toast.LENGTH_LONG).show();
                }else{
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Error al subir archivo",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"Error al subir archivo",Toast.LENGTH_LONG).show();
                Log.w("warning", t);
            }
        });


    }

    private void cargarArchivos(){
        AdapterArchivo adapter= new AdapterArchivo(archivos);
        rvArchivos.setAdapter(adapter);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer idFichaArchivo = archivos[rvArchivos.getChildAdapterPosition(v)].getIdFichaClinica();
                AlertDialog diaBox = AskOption(idFichaArchivo);
                diaBox.show();
            }
        });
    }

    private AlertDialog AskOption(Integer idFichaArchivo)
    {
        final Integer idFA = idFichaArchivo;
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                // set message, title, and icon
                .setTitle("Archivos")
                .setMessage("¿Eliminar archivo?")

                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        deleteArchivo(idFA);
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return myQuittingDialogBox;
    }

    public void deleteArchivo(Integer idFA){
        Call<Void> callArchivos = Servicios.getServicio().deleteArchivo(idFA);
        callArchivos.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Exito al eliminar",Toast.LENGTH_LONG).show();
                    getArchivos();
                }else{
                    Toast.makeText(getApplicationContext(),"Error al eliminar",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Error al eliminar",Toast.LENGTH_LONG).show();
                Log.w("warning", t);
            }
        });
    }

    public void getArchivos(){
        Call<Lista<Archivo>> callArchivos = Servicios.getServicio().getArchivos(fichaId);
        callArchivos.enqueue(new Callback<Lista<Archivo>>() {
            @Override
            public void onResponse(Call<Lista<Archivo>> call, Response<Lista<Archivo>> response) {
                archivos = response.body().getLista();
                cargarArchivos();
            }

            @Override
            public void onFailure(Call<Lista<Archivo>> call, Throwable t) {
                Log.w("warning", t);
            }
        });
    }
}
