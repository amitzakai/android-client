package API;

//import android.telecom.Call;

import java.util.List;

import com.example.osapp.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @GET("users")
    Call<List<User>> getAll();

    @POST("users")
    Call<Void> createUser(@Body User user);

    @GET("users/{id}")
    Call<User> getUser(@Path("id") String id);
}