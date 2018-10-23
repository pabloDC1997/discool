package br.com.pablocouto.discool.ui.login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import br.com.pablocouto.discool.R;
import br.com.pablocouto.discool.model.user.Profile;
import br.com.pablocouto.discool.model.user.retrofit.responses.UserAuthenticated;
import br.com.pablocouto.discool.services.authenticate.AuthClient;
import br.com.pablocouto.discool.services.callback.ServicesCallback;
import br.com.pablocouto.discool.ui.home.HomeActivity;
import br.com.pablocouto.discool.ui.singup.SingupActivity;
import br.com.pablocouto.discool.utils.DataUtils;
import br.com.pablocouto.discool.utils.EncryptUtils;
import br.com.pablocouto.discool.utils.Keys;
import br.com.pablocouto.discool.utils.TinyDB;
import br.com.pablocouto.discool.utils.network.NetworkUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    TinyDB sharePreference;
    ProgressDialog progressDialog;

    @BindView(R.id.et_email)
    EditText editTextEmail;

    @BindView(R.id.et_password)
    EditText editTextPassword;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @BindView(R.id.tv_get_new_password)
    TextView tvNewPassword;

    @BindView(R.id.btn_make_register)
    Button btnRegister;

    @BindView(R.id.btn_sigin_facebook)
    Button btnRegisterFacebook;

    @BindView(R.id.btn_sigin_google)
    Button btnRegisterGoogle;

    @BindView(R.id.switch_still_logged)
    Switch stillLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        this.progressDialog = new ProgressDialog(this);
        sharePreference = new TinyDB(this);

        if (sharePreference.getBoolean(Keys.STILL_LOGGED_SELECTED)){
            Profile p = (Profile) sharePreference.getObject(Keys.USER_LOGGED,Profile.class);
            doAuth(p);
        }

        if (sharePreference.getBoolean(Keys.STILL_LOGGED_SELECTED)){
            this.stillLogged.setChecked(true);
        } else {
            this.stillLogged.setChecked(false);
        }
    }

    @OnClick(R.id.btn_login)
    public void onClickBtnLogin(View view){

        TextInputLayout textInputEmail = findViewById(R.id.text_input_layout_email);
        TextInputLayout textInputPass = findViewById(R.id.text_input_layout_pass);

        textInputEmail.setErrorEnabled(false);
        textInputPass.setErrorEnabled(false);

        String email = this.editTextEmail.getText().toString();
        String password = this.editTextPassword.getText().toString();

        if (email.length() <=0){
            textInputEmail.setErrorEnabled(true);
            textInputEmail.setError(Keys.MESSAGE_ERROR_EMAIL);
            return;
        }

        if (!DataUtils.validateIfEmailIsValid(email)){
            textInputEmail.setErrorEnabled(true);
            textInputEmail.setError(Keys.MESSAGE_ERROR_EMAIL);
            return;
        }

        if (password.length() <=0){
            textInputPass.setErrorEnabled(true);
            textInputPass.setError(Keys.MESSAGE_ERROR_PASSWORD_IS_INVALID);
            return;
        }

        Profile profile = new Profile(email, EncryptUtils.encrypt(password));

        if (sharePreference.getBoolean(Keys.STILL_LOGGED_SELECTED)){
            sharePreference.putObject(Keys.USER_LOGGED, profile);
        } else {
            sharePreference.remove(Keys.USER_LOGGED);
        }

        doAuth(profile);
    }

    private void doAuth(final Profile profile) {
        if (NetworkUtil.getConnectivityStatusString(LoginActivity.this) == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED){
            Toast.makeText(LoginActivity.this, "Dispositivo sem conexão com a internet", Toast.LENGTH_LONG).show();
            return;
        }

        AuthClient.authenticate(profile, new ServicesCallback.ServicesCallbackRequestAuthenticate() {
            @Override
            public void onPreExecution() {
                progressDialog.setMessage("Autenticando...");
                progressDialog.show();
            }

            @Override
            public void onSuccess(UserAuthenticated body) {
                if (body.isStatus()){
                    Toast.makeText(getApplicationContext(),body.getMsg(),Toast.LENGTH_SHORT).show();
                    sharePreference.putObject(Keys.PROFILE_STORED, body.getProfile());
                    sharePreference.putBoolean(Keys.IS_USER_LOGGED, true);

                    Profile p = body.getProfile();

                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Keys.PROFILE_STORED, p);
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtras(bundle);

                    editTextPassword.setText("");
                    sharePreference.putObject(Keys.PROFILE_STORED, p);
                    startActivity(intent);

                } else {
                    showErroMsg(Keys.OPS, body.getMsg());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onResponseUnexpected(String message) {
                showErroMsg(Keys.OPS, message);
                progressDialog.dismiss();
            }

            @Override
            public void onfailure(String message) {
                showErroMsg(Keys.OPS, message);
                progressDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.tv_get_new_password)
    public void onClickBtnNewPassword(View view){
        //TODO - Implement this
    }

    @OnClick(R.id.btn_make_register)
    public void onClickBtnMakeRegister(View view){
        startActivity(new Intent(getApplicationContext(), SingupActivity.class));
    }

    @OnClick(R.id.btn_sigin_facebook)
    public void onClickBtnRegisterWithFacebook(View view){
//TODO - Implement this
    }

    @OnClick(R.id.btn_sigin_google)
    public void onClickBtnRegisterWithGoogle(View view){
//TODO - Implement this
    }

    public void showErroMsg(String title, String msg) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
    }

    @OnClick(R.id.tv_get_new_password)
    public void onClickNewPassword(View view){
        Toast.makeText(getApplicationContext(), "Implement send e-mail to recieve a new password",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.switch_still_logged)
    public void onChangedStillLogged(View view) {
        sharePreference.putBoolean(Keys.STILL_LOGGED_SELECTED, this.stillLogged.isChecked());
    }
}
