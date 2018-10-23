package br.com.pablocouto.discool.ui.recycle.shared;

/**
 * Created by Pablo on 22/01/2017.
 */
import android.view.View;

public interface ClickListener {
    void onClick(View view, int position);
    void onLongClick(View view, int position);
}
