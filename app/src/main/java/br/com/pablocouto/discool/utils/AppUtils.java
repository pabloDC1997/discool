package br.com.pablocouto.discool.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by pablo.couto on 06/12/2017.
 */

public class AppUtils {

    public static String getAppVersion(Context context) {
        PackageInfo pInfo = null;
        String version = "";
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pInfo != null) {
            version = pInfo.versionName;
        }
        return version;
    }
}
