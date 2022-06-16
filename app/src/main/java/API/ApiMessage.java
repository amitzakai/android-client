package API;

import com.example.osapp.models.Message;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiMessage {
    private Retrofit r;
    private Api api;
    String request = "http://10.0.2.2:7249/api/";
    String server = "7249";
    public ApiMessage() {
        r = new Retrofit.Builder().baseUrl(request)
                .addConverterFactory(GsonConverterFactory.create()).build();
        api = r.create(Api.class);
    }

}
