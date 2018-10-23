package br.com.pablocouto.discool.ui.party.liked;

import android.os.Bundle;
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

import java.util.ArrayList;

import br.com.pablocouto.discool.R;
import br.com.pablocouto.discool.fakedata.Fake;
import br.com.pablocouto.discool.model.party.Party;
import br.com.pablocouto.discool.ui.home.HomeActivity;
import br.com.pablocouto.discool.ui.party.liked.adapter.PartiesLikedAdapter;
import br.com.pablocouto.discool.ui.party.liked.adapter.TouchListenerPartyLiked;
import br.com.pablocouto.discool.utils.TinyDB;
import butterknife.ButterKnife;

public class PartiesLikedActivity extends AppCompatActivity implements TouchListenerPartyLiked {

    private TinyDB sharePreference;
    private EditText editTextSearch;
    private ArrayList<Party> partyList;
    private RecyclerView recyclerView;
    private PartiesLikedAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parties_liked);
        this.sharePreference = new TinyDB(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        this.partyList = new ArrayList<>();

        editTextSearch = this.findViewById(R.id.edit_text_search);
        editTextSearch.setVisibility(View.GONE);

        this.startRecycleView();
        this.callHttpService();
    }

    private void startRecycleView() {
        recyclerView = this.findViewById(R.id.recycle_view_home);
        mAdapter = new PartiesLikedAdapter(this.partyList,this, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
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
                //todo - get the implementation on HomeActivity
                return true;
            case R.id.action_search:
                switchSearchViewLayout();
                return true;
            case R.id.action_update:
                callHttpService();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void callHttpService() {
        this.partyList.clear();
        this.partyList.addAll(Fake.getDatas());
        this.mAdapter.notifyDataSetChanged();
    }

    private void switchSearchViewLayout( ) {
        if ( editTextSearch.getVisibility() == View.GONE ) {
            editTextSearch.setVisibility(View.VISIBLE);
        } else if ( editTextSearch.getVisibility() == View.VISIBLE ) {
            editTextSearch.setVisibility(View.GONE);
        } else {
            Log.e("ERROR: ", PartiesLikedAdapter.class.getName());
        }
    }


    //Callback's click by items from adapters
    @Override
    public void onClickBtnDislike(View view, int position) {

    }

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

    }

    @Override
    public void onClickBtnShare(View view, int position) {

    }

    @Override
    public void onClickBtnLike(View view, int position) {
        //fixme - this method aren't in use
    }
}
