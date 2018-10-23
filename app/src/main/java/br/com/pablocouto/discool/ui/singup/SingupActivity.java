package br.com.pablocouto.discool.ui.singup;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.pablocouto.discool.R;
import br.com.pablocouto.discool.model.user.User;
import br.com.pablocouto.discool.model.user.retrofit.responses.UserRegistedResponse;
import br.com.pablocouto.discool.services.callback.ServicesCallback;
import br.com.pablocouto.discool.services.singup.SingupClient;
import br.com.pablocouto.discool.utils.ActivityUtils;
import br.com.pablocouto.discool.utils.DataUtils;
import br.com.pablocouto.discool.utils.EncryptUtils;
import br.com.pablocouto.discool.utils.FormatterUtils;
import br.com.pablocouto.discool.utils.Keys;
import br.com.pablocouto.discool.utils.Mask;
import br.com.pablocouto.discool.utils.MaskEditable;
import br.com.pablocouto.discool.utils.MyUUID;
import br.com.pablocouto.discool.utils.network.NetworkUtil;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingupActivity extends AppCompatActivity implements ServicesCallback.ServicesCallbackRequestInserUser {

    @BindView(R.id.register_first_name)
    EditText inputFirstName;

    @BindView(R.id.register_last_name)
    EditText inputLastName;

    @BindView(R.id.register_email)
    EditText inputEmail;

    @BindView(R.id.register_passoword)
    EditText inputPassword;

    @BindView(R.id.register_confirm_passoword)
    EditText inputConfirmPassword;

    EditText inputPhone;

    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        progress = new ProgressDialog(this);
        inputPhone = findViewById(R.id.register_phone);

        this.inputPhone.addTextChangedListener(MaskEditable.insert(this.inputPhone, Mask.PHONE_9));
        
        this.inputEmail.setFilters(new InputFilter[] { ActivityUtils.removeWhiteSpaceFilterET() });
        this.inputPassword.setFilters(new InputFilter[] { ActivityUtils.removeWhiteSpaceFilterET() });
        this.inputConfirmPassword.setFilters(new InputFilter[] { ActivityUtils.removeWhiteSpaceFilterET() });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.btn_singup)
    public void onClickBtnSingup(View view){
        try{

            if (NetworkUtil.getConnectivityStatusString(SingupActivity.this) == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED){
                Toast.makeText(SingupActivity.this, "Dispositivo sem conexão com a internet", Toast.LENGTH_LONG).show();
                return;
            }

            this.validateFieldsSingup();
            this.insertNewUser();
        } catch (Exception e){
            showErroMsg(Keys.INVALID_INFORMATION, e.getMessage());
        }
    }

    private void insertNewUser() {
        final String name = this.inputFirstName.getText().toString() + " " + this.inputLastName.getText().toString();
        final String email = this.inputEmail.getText().toString();
        final String password = this.inputPassword.getText().toString();
        final String phone = FormatterUtils.removeMaskFromPhone(this.inputPhone.getText().toString());

        User user = new User(MyUUID.generate(), name, email, EncryptUtils.encrypt(password), phone);
        SingupClient.insert(user,this);
    }

    private void validateFieldsSingup() throws Exception {
        final String email = this.inputEmail.getText().toString();
        final String password = this.inputPassword.getText().toString();
        final String confirmPassword = this.inputConfirmPassword.getText().toString();
        final String phone = FormatterUtils.removeMaskFromPhone(this.inputPhone.getText().toString());

        if (    this.inputFirstName.getText().toString().isEmpty() ||
                this.inputLastName.getText().toString().isEmpty()){
            throw  new Exception(Keys.MESSAGE_ERROR_NAME);
        }

        if (!(DataUtils.validateIfEmailIsValid(email))){
            throw  new Exception(Keys.MESSAGE_ERROR_EMAIL);
        }

        if (password.length() < 4){
            throw  new Exception(Keys.MESSAGE_ERROR_PASSWORD);
        }

        if (!password.equals(confirmPassword)){
            throw  new Exception(Keys.MESSAGE_ERROR_PASSWORD_DONT_MATCH);
        }

        if (phone.length() < 11) {
            throw  new Exception(Keys.MESSAGE_ERROR_PHONE);
        }
    }

    //Fixme  - implementation callback insert new user
    @Override
    public void onPreExecution() {
        progress.setMessage("Cadastrando...");
        progress.show();
    }

    @Override
    public void onSuccess(UserRegistedResponse body) {
        if (body.isCadastro()){
            Toast.makeText(getApplicationContext(),body.getMsg(),Toast.LENGTH_LONG).show();
            finish();
        } else  {
            showErroMsg(Keys.OPS, body.getMsg());
            Log.e(SingupActivity.class.getName(), body.getMsg());
        }
        progress.dismiss();
    }

    @Override
    public void onResponseUnexpected(String message) {
        showErroMsg(Keys.OPS, message);
        progress.dismiss();
    }

    @Override
    public void onfailure(String message) {
        showErroMsg(Keys.OPS, message);
        progress.dismiss();
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
}
