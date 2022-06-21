package API;

import com.example.osapp.Dao.MessageDao;
import com.example.osapp.adapters.MessageAdapter;
import com.example.osapp.models.Contact;
import com.example.osapp.models.ContactRemote;
import com.example.osapp.models.Message;
import com.example.osapp.models.MessageRemote;
import com.example.osapp.models.Transfer;

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
    String myServer = "7249";
    public ApiMessage() {
        r = new Retrofit.Builder().baseUrl(request)
                .addConverterFactory(GsonConverterFactory.create()).build();
        api = r.create(Api.class);
    }

    public void getMessagesList(String user, String contact, MessageAdapter adapter
            , MessageDao dao) {
        Call<List<Message>> call = api.getAllMessages(user, contact);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                adapter.setMessages(response.body());
                for(Message m: response.body()){
                    MessageRemote mr = new MessageRemote(m.getId(), user, contact, m.getContent(),
                            m.getCreated(), m.getSent());
                    try {
                        dao.insert(mr);
                    } catch (Exception e){
                        dao.update(mr);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });
    }

    public void send_message_to_me(String user, String contact, MessageAdapter adapter
            , Message m, boolean flag, MessageDao dao) {
        if(!flag)
            m.setSent(false);
        else
            m.setSent(true);
        Call<Void> call = api.sendMessage(user, contact, m);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(flag)
                    getMessagesList(user, contact, adapter, dao);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
    //
    public void send_message_another_server(String user, String contact, String server
            , MessageAdapter adapter, Message m, MessageDao dao) {
        Retrofit rOtherServer = new Retrofit.Builder().baseUrl(server)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Api apiOtherServer = rOtherServer.create(Api.class);
        Transfer t = new Transfer(user, contact, m.getContent());
        Call<Void> call = apiOtherServer.transfer(t);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.code() == 200)
                    send_message_to_me(contact, user, adapter, m, false, dao);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void sendMessage(String user, String contact, String server, MessageAdapter adapter
            , Message m, MessageDao dao) {
        if (server.equals(myServer)) {
            send_message_to_me(contact, user, adapter, m, false, dao);
            send_message_to_me(user, contact, adapter, m, true, dao);
        } else {
            send_message_another_server(user, contact, server, adapter, m, dao);
        }
    }
}
