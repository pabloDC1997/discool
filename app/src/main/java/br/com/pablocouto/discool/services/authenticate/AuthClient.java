package br.com.pablocouto.discool.services.authenticate;

import android.util.Log;

import br.com.pablocouto.discool.model.user.Profile;
import br.com.pablocouto.discool.model.user.retrofit.responses.UserAuthenticated;
import br.com.pablocouto.discool.services.callback.ServicesCallback;
import br.com.pablocouto.discool.services.RetrofitClient;
import br.com.pablocouto.discool.utils.Keys;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Pablo on 03/07/2017.
 */

public class AuthClient {

    private static final String DEBUG_TAG = AuthClient.class.getName();
    private static final AuthApi service = RetrofitClient.getClient(Keys.BASE_URL).create(AuthApi.class);

    public static void authenticate(Profile profile, final ServicesCallback.ServicesCallbackRequestAuthenticate callback ){
        Log.i("Profile", profile.toString());

        callback.onPreExecution();
        service.auth(profile)
                .enqueue(new Callback<UserAuthenticated>() {
                    @Override
                    public void onResponse(Call<UserAuthenticated> call, Response<UserAuthenticated> response) {
                        Log.i(DEBUG_TAG, "Resoponse code: " + response.code());
                        Log.i(DEBUG_TAG, "Resoponse body: " + response.body());
                        Log.i(DEBUG_TAG, "Resoponse message: " + response.toString());

                        if (response.isSuccessful()){
                            UserAuthenticated body = response.body();
                            callback.onSuccess(body);
                        } else {
                            callback.onResponseUnexpected(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserAuthenticated> call, Throwable t) {
                        callback.onfailure(t.getMessage());
                    }
                });
    }
}