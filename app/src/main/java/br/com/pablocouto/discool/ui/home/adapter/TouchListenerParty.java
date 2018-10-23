package br.com.pablocouto.discool.ui.home.adapter;

import android.view.View;

/**
 * Created by Pablo on 19/11/2017.
 */

public interface TouchListenerParty {

    void onClickBtnLocation(View view, int position);

    void onClickBtnDate(View view, int position);

    void onClickBtnTicket(View view, int position);

    void onClickBtnDelete(View view, int position);

    void onClickBtnShare(View view, int position);

    void onClickBtnLike(View view, int position);
}
