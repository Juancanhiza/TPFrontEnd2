package py.com.eko.fisiocenter;

import py.com.eko.fisiocenter.Modelos.Reserva;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface FichaClinicaService {
    @GET("fichaClinica")
    Call<Lista<FichaClinicaModel>> obtenerFichasClinicas();
}
