package py.com.eko.fisiocenter.WebService;

import py.com.eko.fisiocenter.Modelos.Categoria;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.Modelos.Reserva;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiRest {

    @GET("reserva")
    Call<Lista<Reserva>> obtenerReservas(@Query("orderBy") String orderBy,
                                         @Query("orderDir") String orderDir);

    @GET("reserva")
    Call<Lista<Reserva>> obtenerReservasSimple();

    @GET("categoria")
    Call<Lista<Categoria>> obtenerCategorias(@Query("orderBy") String orderBy,
                                             @Query("orderDir") String orderDir);

    @POST("categoria")
    Call<Categoria> agregarCategoria(@Body Categoria categoria);

    @PUT("categoria")
    Call<Categoria> actualizarCategoria(@Body Categoria categoria);
}
