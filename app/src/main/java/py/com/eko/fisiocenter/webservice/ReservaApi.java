package py.com.eko.fisiocenter.webservice;


import py.com.eko.fisiocenter.models.Lista;
import py.com.eko.fisiocenter.models.Persona;
import py.com.eko.fisiocenter.models.Reserva;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ReservaApi {

    @GET("reserva")
    Call<Lista<Reserva>> obtenerReservas(@Query("orderBy") String orderBy,
                                           @Query("orderDir") String orderDir);

}
