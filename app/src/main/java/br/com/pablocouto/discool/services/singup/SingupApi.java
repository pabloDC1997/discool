package br.com.pablocouto.discool.services.singup;

import br.com.pablocouto.discool.model.user.User;
import br.com.pablocouto.discool.model.user.retrofit.responses.UserRegistedResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Pablo on 03/07/2017.
 */

public interface SingupApi {

        @POST("discool/users/insert")
        Call<UserRegistedResponse> insert(@Body User user);
}
