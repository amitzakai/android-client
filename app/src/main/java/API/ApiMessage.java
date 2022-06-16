package API;

import com.example.osapp.adapters.MessageAdapter;
import com.example.osapp.models.Contact;
import com.example.osapp.models.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    public void getMessagesList(String user, String contact, MessageAdapter adapter) {
        Call<List<Message>> call = api.getAllMessages(user, contact);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                adapter.setMessages(response.body());
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });
    }
}
