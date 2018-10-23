package br.com.pablocouto.discool.services.authenticate;

import br.com.pablocouto.discool.model.user.Profile;
import br.com.pablocouto.discool.model.user.retrofit.responses.UserAuthenticated;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Pablo on 03/07/2017.
 */

public interface AuthApi {
        @POST("discool/users/auth")
        Call<UserAuthenticated> auth(@Body Profile profile);
}
