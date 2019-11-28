package py.com.eko.fisiocenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class FichaClinicaActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdapterFichaClinica mAdapterFichaClinica;
    private ArrayList<FichaClinica> fichas;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_clinica);

        handleSSLHandshake();

        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        fichas = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }
    private void parseJSON(){
        String url = "https://gy7228.myfoscam.org:8443/stock-pwfe/fichaClinica";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("lista");
                    for(int i=0; i < jsonArray.length(); i++) {
                        JSONObject hit = jsonArray.getJSONObject(i);
                        Integer idFichaClinica = hit.getInt("idFichaClinica");
                        String diagnostico = hit.getString("diagnostico");
                        String motivo = hit.getString("motivoConsulta");
                        JSONObject paciente = hit.getJSONObject("idCliente");
                        Persona cliente = new Persona(paciente.getInt("idPersona"), paciente.getString("nombre"), paciente.getString("apellido"));
                        JSONObject fisio = hit.getJSONObject("idEmpleado");
                        Persona medico = new Persona(fisio.getInt("idPersona"), fisio.getString("nombre"), fisio.getString("apellido"));
                        fichas.add(new FichaClinica(idFichaClinica, motivo, diagnostico, medico, cliente));
                    }

                    mAdapterFichaClinica = new AdapterFichaClinica(FichaClinicaActivity.this, fichas);
                    mRecyclerView.setAdapter(mAdapterFichaClinica);

                    mAdapterFichaClinica.setListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intentNewActivity = new Intent(FichaClinicaActivity.this,
                                    FichaClinicaAddEditActivity.class);
                            Log.d("entro", "entro al click listener");
                            intentNewActivity.putExtra("object",fichas.get(mRecyclerView.getChildAdapterPosition(view)));
                            startActivity(intentNewActivity);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("ERRORRRR", "ENTRO ACA");
            }
        });

        mRequestQueue.add(request);
    }

    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
}
