package py.com.eko.fisiocenter.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicios {
    public static ReservaApi getReservaService() {
        return getClient("http://181.123.253.74:8080/stock-pwfe/").create(ReservaApi.class);
    }
    public static Retrofit getClient(String baseUrl) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
