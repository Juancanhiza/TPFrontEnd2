package py.com.eko.fisiocenter.WebService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Servicios {
    public static ApiRest getServicio() {
        return getClient("http://181.123.253.74:8080/stock-pwfe/").create(ApiRest.class);
    }
    public static Retrofit getClient(String baseUrl) {

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
}
