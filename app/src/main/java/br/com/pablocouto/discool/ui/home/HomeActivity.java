package br.com.pablocouto.discool.ui.home;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import br.com.pablocouto.discool.R;
import br.com.pablocouto.discool.model.party.Party;
import br.com.pablocouto.discool.model.user.Profile;
import br.com.pablocouto.discool.services.callback.ServicesCallback;
import br.com.pablocouto.discool.services.party.PartyClient;
import br.com.pablocouto.discool.ui.home.adapter.HomeAdapter;
import br.com.pablocouto.discool.ui.home.adapter.TouchListenerParty;
import br.com.pablocouto.discool.ui.party.liked.PartiesLikedActivity;
import br.com.pablocouto.discool.utils.Keys;
import br.com.pablocouto.discool.utils.TinyDB;
import br.com.pablocouto.discool.utils.network.NetworkUtil;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TouchListenerParty, ServicesCallback.ServicesCallbackRequestListParty {

    private ArrayList<Party> partyList;
    private RecyclerView recyclerView;
    private HomeAdapter mAdapter;
    private Profile profile;
    private ProgressDialog dialog;
    TinyDB sharePreference;
    @BindView(R.id.empty_list_view)
    RelativeLayout emptyView;
    @BindView(R.id.no_network)
    RelativeLayout noNetworkMessega;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.sharePreference = new TinyDB(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        this.partyList = new ArrayList<>();
        View headerLayout = navigationView.getHeaderView(0);
        Intent intent = getIntent();

        editTextSearch = this.findViewById(R.id.edit_text_search);
        editTextSearch.setVisibility(View.GONE);

        if (intent.getExtras() != null){
            Bundle bundle = intent.getExtras();
            profile = (Profile) bundle.get(Keys.PROFILE_STORED);
            if (profile != null) {
                ((TextView) headerLayout.findViewById(R.id.name_nav_header)).setText(profile.getName());
                ((TextView) headerLayout.findViewById(R.id.email_nav_header)).setText(profile.getEmail());
            }
        } else {
            Toast.makeText(this,Keys.WE_CANT_FOUND_ANY_USER_LOGGED, Toast.LENGTH_SHORT).show();
            finish();
        }
        this.callHttpService();
        this.startRecycleView();
    }

    private void callHttpService() {
        PartyClient.list(this);
    }

    private void startRecycleView() {
        recyclerView = this.findViewById(R.id.recycle_view_home);
        mAdapter = new HomeAdapter(this.partyList,this, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishSuper();
        }
    }

    private void finishSuper() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Fazer logoff?")
                .setMessage("Tem certeza que deseja fazer logoff do usuario \""+ this.profile.getName() + "\" ?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });


        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_filter:
                //todo - implement parties filter
                return true;
            case R.id.action_search:
                switchSearchViewLayout();
                return true;
            case R.id.action_update:
                callHttpService();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.nav_profile:
                break;
            case R.id.nav_m_party:
                starPartiesLikedActivity();
                break;
            case R.id.nav_trash:
                break;
            case R.id.nav_about:
                break;
            case R.id.nav_contact:
                break;
            default:
                //todo - make an log here
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void starPartiesLikedActivity() {
        if (hasPartiesLiked()){
            startActivity(new Intent(getApplicationContext(), PartiesLikedActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), Keys.YOU_HAVENT_ALREADY_LIKED_ANY_PARTY, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean hasPartiesLiked() {
        for (Party p : partyList){
            if (p.isLiked()){
                return true;
            }
        }
        return false;
    }

    //fixme - Callback recycleView
    @Override
    public void onClickBtnLocation(View view, int position) {

    }

    @Override
    public void onClickBtnDate(View view, int position) {

    }

    @Override
    public void onClickBtnTicket(View view, int position) {

    }

    @Override
    public void onClickBtnDelete(View view, int position) {
        final int  pos = position;
        new AlertDialog.Builder(this)
                .setTitle("Remover evento?")
                .setMessage("Tem certeza que deseja remover este evento da sua linha do tempo?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        partyList.remove(pos);
                        notifyDataSetChanged();
                        Toast.makeText(HomeActivity.this, "Evento removido com sucesso.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
    }

    @Override
    public void onClickBtnShare(View view, int position) {
        //todo - implement this later
    }

    @Override
    public void onClickBtnLike(View view, int position) {
        final int pos = position;
        if (partyList.get(position).isLiked()){
            new AlertDialog.Builder(HomeActivity.this)
                    .setTitle("Cancelar preseça?")
                    .setMessage("Tem certeza que deseja cancelar sua presença nesse evento?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            partyList.get(pos).setUnlike();
                            Toast.makeText(getApplicationContext(),"Presença cancelada.", Toast.LENGTH_SHORT).show();
                            dialogInterface.dismiss();
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .create()
                    .show();
        } else {
            partyList.get(position).setLike();
            Toast.makeText(getApplicationContext(),"Presença confirmada.", Toast.LENGTH_SHORT).show();
        }
        notifyDataSetChanged();
    }

    //fixme - end Callback recycleView
    //Callback api ListParties

    @Override
    public void onPreExecution() {
        noNetworkMessega.setVisibility(View.GONE);
        dialog = new ProgressDialog(HomeActivity.this);
        dialog.setMessage("Carregando...");
        dialog.show();
    }
    @Override
    public void onResponseUnexpected(String message) {
        if (NetworkUtil.getConnectivityStatusString(HomeActivity.this) == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED){
            showNoNetworkScreen();
        } else {
            showErroMsg(Keys.OPS, message);
        }
        dialog.dismiss();
    }

    @Override
    public void onfailure(String message) {
        if (NetworkUtil.getConnectivityStatusString(HomeActivity.this) == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED){
            showNoNetworkScreen();
        } else {
            showErroMsg(Keys.OPS, message);
        }
        dialog.dismiss();
    }

    private void showNoNetworkScreen() {
        noNetworkMessega.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(List<Party> body) {
        this.partyList.clear();
        this.partyList.addAll(body);
        notifyDataSetChanged();
        dialog.dismiss();
    }

    //End Callback api ListParties
    public void showErroMsg(String title, String msg) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    private void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();

        if (partyList.size() > 0 ) {
            this.emptyView.setVisibility(View.GONE);
        } else {
            this.emptyView.setVisibility(View.VISIBLE);
        }
    }

    private void switchSearchViewLayout( ) {
        if ( editTextSearch.getVisibility() == View.GONE ) {
            editTextSearch.setVisibility(View.VISIBLE);
        } else if ( editTextSearch.getVisibility() == View.VISIBLE ) {
            editTextSearch.setVisibility(View.GONE);
        } else {
            Log.e("ERROR: ", HomeActivity.class.getName());
        }
    }
}
