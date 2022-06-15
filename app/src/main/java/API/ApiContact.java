package API;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.example.osapp.adapters.ContactsListAdapter;
import com.example.osapp.models.Contact;
import com.example.osapp.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiContact {

    private Retrofit r;
    private Api api;

    public ApiContact() {
        r = new Retrofit.Builder().baseUrl("http://10.0.2.2:7249/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        api = r.create(Api.class);
    }

    public void getAll(String id, ContactsListAdapter adapter) {
        Call<List<Contact>> call = api.getAllContacts(id);
        call.enqueue(new Callback<List<Contact>>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                adapter.setContacts(response.body());
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }
        });
    }
}
