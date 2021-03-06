package API;

//import android.telecom.Call;

import java.util.List;

import com.example.osapp.models.Contact;
import com.example.osapp.models.Invitation;
import com.example.osapp.models.Message;
import com.example.osapp.models.Transfer;
import com.example.osapp.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    @GET("users")
    Call<List<User>> getAllUsers();

    @POST("users")
    Call<Void> createUser(@Body User user);

    @GET("users/{id}")
    Call<User> getUser(@Path("id") String id);

    @GET("contacts/{id}")
    Call<List<Contact>> getAllContacts(@Path("id") String id);

    @POST("contacts/{id}")
    Call<Void> addContact(@Path("id") String id, @Body Contact c);

    @POST("api/invitations")
    Call<Void> invitation(@Body Invitation i);

    @GET("contacts/{id}/{cId}/messages")
    Call<List<Message>> getAllMessages(@Path("id") String id, @Path("cId") String c);

    @POST("contacts/{id}/{cId}/messages")
    Call<Void> sendMessage(@Path("id") String id, @Path("cId") String c, @Body Message m);

    @POST("api/transfer")
    Call<Void> transfer(@Body Transfer t);
}