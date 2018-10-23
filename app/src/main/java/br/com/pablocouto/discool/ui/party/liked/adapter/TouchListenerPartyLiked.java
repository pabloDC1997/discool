package br.com.pablocouto.discool.ui.party.liked.adapter;

import android.view.View;

import br.com.pablocouto.discool.ui.home.adapter.TouchListenerParty;

/**
 * Created by Pablo on 19/11/2017.
 */

public interface TouchListenerPartyLiked extends TouchListenerParty {

    void onClickBtnDislike(View view, int position);
}
