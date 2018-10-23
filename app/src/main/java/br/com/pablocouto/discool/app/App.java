package br.com.pablocouto.discool.app;

import android.app.Application;

import br.com.pablocouto.discool.utils.TinyDB;

/**
 * Created by pablo.couto on 06/12/2017.
 */

public class App extends Application {

    TinyDB sharePreference;

    @Override
    public void onCreate() {
        this.sharePreference = new TinyDB(this);
        this.checkIfUserIsLogged();
        super.onCreate();
    }

    private void checkIfUserIsLogged() {
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
