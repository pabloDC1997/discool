package br.com.pablocouto.discool.services.party;

import android.util.Log;

import java.util.List;

import br.com.pablocouto.discool.model.party.Party;
import br.com.pablocouto.discool.services.RetrofitClient;
import br.com.pablocouto.discool.services.callback.ServicesCallback;
import br.com.pablocouto.discool.services.singup.SingupClient;
import br.com.pablocouto.discool.utils.Keys;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PartyClient {

    private static final String DEBUG_TAG = SingupClient.class.getName();
    private static final PartyApi service = RetrofitClient.getClient(Keys.BASE_URL).create(PartyApi.class);

    public static void list( final ServicesCallback.ServicesCallbackRequestListParty callback ) {
        callback.onPreExecution();
        service.list()
                .enqueue(new Callback<List<Party>>() {
                    @Override
                    public void onResponse(Call<List<Party>> call, Response<List<Party>> response) {

                        Log.i(DEBUG_TAG, "Resoponse code: " + response.code());
                        Log.i(DEBUG_TAG, "Resoponse body: " + response.body());
                        Log.i(DEBUG_TAG, "Resoponse message: " + response.toString());

                        if (response.isSuccessful()) {
                            List<Party> body = response.body();
                            callback.onSuccess(body);
                        } else {
                            callback.onResponseUnexpected(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Party>> call, Throwable t) {
                        callback.onfailure(t.getMessage());
                    }
                });
    }
}
