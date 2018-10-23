package br.com.pablocouto.discool.services.callback;

import java.util.List;

import br.com.pablocouto.discool.model.party.Party;
import br.com.pablocouto.discool.model.user.retrofit.responses.UserAuthenticated;
import br.com.pablocouto.discool.model.user.retrofit.responses.UserRegistedResponse;

/**
 * Created by Pablo on 07/07/2017.
 */

public class ServicesCallback {

    public interface BaseApiServicesCallback {
        void onPreExecution();
        void onResponseUnexpected(String message);
        void onfailure(String message);
    }

    public interface ServicesCallbackRequestAuthenticate extends BaseApiServicesCallback {
        void onSuccess(UserAuthenticated body);
    }

    public interface ServicesCallbackRequestInserUser extends BaseApiServicesCallback {
        void onSuccess(UserRegistedResponse body);
    }

    public interface ServicesCallbackRequestListParty extends BaseApiServicesCallback {
        void onSuccess(List<Party> body);
    }

}
