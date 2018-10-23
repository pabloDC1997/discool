package br.com.pablocouto.discool.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.text.InputFilter;
import android.text.Spanned;

import java.util.List;

import br.com.pablocouto.discool.model.party.Party;




/**
 * Created by Pablo on 24/01/2017.
 */

public class ActivityUtils {

    private Context mContext;
    private static ProgressDialog progressDialog;

    public static void showSnackbarMessage(Activity activity, String message){
        Snackbar.make(activity.findViewById(android.R.id.content),
                message,
                Snackbar.LENGTH_LONG).show();
    }

    public static void showProgressDialog(Context mContext, String title, String message) {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    public static void stopProgressDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    public static InputFilter removeWhiteSpaceFilterET(){
        return new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
    }

    public static boolean temEventoHoje(List<Party> partyList) {
        return false;
    }
}
