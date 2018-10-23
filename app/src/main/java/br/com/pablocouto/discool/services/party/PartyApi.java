package br.com.pablocouto.discool.services.party;

import java.util.List;

import br.com.pablocouto.discool.model.party.Party;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PartyApi {

    @GET("discool/parties/list")
    Call<List<Party>> list();
}
