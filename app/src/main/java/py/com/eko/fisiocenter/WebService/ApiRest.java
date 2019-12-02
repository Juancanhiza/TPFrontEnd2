package py.com.eko.fisiocenter.WebService;

import org.json.JSONObject;

import py.com.eko.fisiocenter.Modelos.Categoria;
import py.com.eko.fisiocenter.Modelos.FichaClinica;
import py.com.eko.fisiocenter.Modelos.Lista;
import py.com.eko.fisiocenter.Modelos.Paciente;
import py.com.eko.fisiocenter.Modelos.Persona;
import py.com.eko.fisiocenter.Modelos.PersonaShort;
import py.com.eko.fisiocenter.Modelos.Reserva;
import py.com.eko.fisiocenter.Modelos.TipoProducto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiRest {

    @GET("reserva")
    Call<Lista<Reserva>> obtenerReservas(@Query("orderBy") String orderBy,
                                         @Query("orderDir") String orderDir);

    @GET("reserva")
    Call<Lista<Reserva>> obtenerReservasSimple();

    @GET("reserva")
    Call<Lista<Reserva>> obtenerReservasFilter(@Query("ejemplo") JSONObject obj);

    @GET("reserva/{id}")
    Call<Reserva> obtenerReserva(@Path("id") int idReserva);



    @GET("categoria")
    Call<Lista<Categoria>> obtenerCategorias(@Query("orderBy") String orderBy,
                                             @Query("orderDir") String orderDir);

    @POST("categoria")
    Call<Categoria> agregarCategoria(@Body Categoria categoria);

    @PUT("categoria")
    Call<Categoria> actualizarCategoria(@Body Categoria categoria);

    @GET("fichaClinica")
    Call<Lista<FichaClinica>> obtenerFichasClinicas();

    @Headers({
            "Content-Type: application/json",
            "usuario:gustavo"
    })
    @PUT("fichaClinica")
    Call<FichaClinica> actualizarFichaClinica(@Body FichaClinica obj);

    @Headers({
            "Content-Type: application/json",
            "usuario:gustavo"
    })
    @POST("fichaClinica")
    Call<FichaClinica> guardarFichaClinica(@Body FichaClinica obj);

    @GET("fichaClinica")
    Call<Lista<FichaClinica>> obtenerFichasClinicasFilter(@Query("ejemplo") JSONObject obj);

    @GET("persona")
    Call<Lista<PersonaShort>> obtenerPersonas(@Query("ejemplo") JSONObject obj);

    @Headers("Content-Type: application/json")
    @POST("persona")
    Call<Paciente> guardarPaciente(@Body Paciente obj);

    @GET("persona")
    Call<Lista<Paciente>> obtenerPacientesFilter(@Query("ejemplo") JSONObject obj, @Query("like") String like);

    @GET("persona")
    Call<Lista<Paciente>> obtenerPacientes();

    @GET("tipoProducto")
    Call<Lista<TipoProducto>> obtenerTipoProductos();

}
