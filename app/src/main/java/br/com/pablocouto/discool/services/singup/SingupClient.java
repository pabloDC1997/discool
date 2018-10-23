package br.com.pablocouto.discool.services.singup;

import android.util.Log;

import br.com.pablocouto.discool.model.user.User;
import br.com.pablocouto.discool.model.user.retrofit.responses.UserRegistedResponse;
import br.com.pablocouto.discool.services.callback.ServicesCallback;
import br.com.pablocouto.discool.services.RetrofitClient;
import br.com.pablocouto.discool.utils.Keys;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Pablo on 03/07/2017.
 */

public class SingupClient {

    private static final String DEBUG_TAG = SingupClient.class.getName();
    private static final SingupApi service = RetrofitClient.getClient(Keys.BASE_URL).create(SingupApi.class);

    public static void insert(User user, final ServicesCallback.ServicesCallbackRequestInserUser callback ){

        callback.onPreExecution();

        service.insert(user)
                .enqueue(new Callback<UserRegistedResponse>() {
                    @Override
                    public void onResponse(Call<UserRegistedResponse> call, Response<UserRegistedResponse> response) {

                        Log.i(DEBUG_TAG, "Resoponse code: "     + response.code());
                        Log.i(DEBUG_TAG, "Resoponse body: "     + response.body());
                        Log.i(DEBUG_TAG, "Resoponse message: "  + response.toString());

                        if (response.isSuccessful()){
                            UserRegistedResponse body = response.body();
                            callback.onSuccess(body);
                        } else {
                            callback.onResponseUnexpected(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserRegistedResponse> call, Throwable t) {
                        callback.onfailure(t.getMessage());
                    }
                });
    }
}