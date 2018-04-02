package com.marvel.jaderbittencourt.marvelheroes.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.marvel.jaderbittencourt.marvelheroes.R;
import com.marvel.jaderbittencourt.marvelheroes.adapter.HeroItemAdapter;
import com.marvel.jaderbittencourt.marvelheroes.rest.RestClient;
import com.marvel.jaderbittencourt.marvelheroes.rest.model.APIResponse;
import com.marvel.jaderbittencourt.marvelheroes.rest.service.HeroService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityMain extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HeroItemAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private RelativeLayout spinner;
    private LinearLayout somethingWentWrongContainer;
    private Button retryLoadHeroes;

    private final int retry = 5;
    private int count = 0;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadViews();
        loadHeroes(mAdapter.getItemCount());
    }

    private void loadViews() {
        mRecyclerView = findViewById(R.id.heroes_list);
        spinner = findViewById(R.id.loadingPanel);
        somethingWentWrongContainer = findViewById(R.id.something_wrong_container);

        retryLoadHeroes = findViewById(R.id.something_wrong_retry);
        retryLoadHeroes.setOnClickListener(retryLoadHeroesClick);

        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new HeroItemAdapter(getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                mRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL
        );
        mRecyclerView.addItemDecoration(mDividerItemDecoration);
        mRecyclerView.addOnScrollListener(onScrollListener);
    }

    private View.OnClickListener retryLoadHeroesClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            loadHeroes(mAdapter.getItemCount());
            somethingWentWrongContainer.setVisibility(View.INVISIBLE);
            spinner.setVisibility(View.VISIBLE);
        }
    };

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if(dy > 0) {
                visibleItemCount = mLayoutManager.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                if (loading && (visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                    loading = false;
                    spinner.setVisibility(View.VISIBLE);
                    loadHeroes(mAdapter.getItemCount());
                }
            }
        }
    };

    private void loadHeroes(int offset) {
        String ts = RestClient.getTs();
        Call<APIResponse> call = HeroService.getHero(offset, 50, ts, RestClient.PUBLIC_KEY, RestClient.generateHash(ts));
        call.enqueue(callbackGetHero);
    }

    /**
     * Handle the get Hero callback
     */
    private Callback<APIResponse> callbackGetHero = new Callback<APIResponse>() {
        @Override
        public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
            if (response.isSuccessful()) {
                mAdapter.setHeroesList(response.body().getData().getResults());
                mAdapter.notifyDataSetChanged();
                count = 0;
                loading = true;

                spinner.setVisibility(View.GONE);

            } else if (count < retry) {
                count++;
                loadHeroes(mAdapter.getItemCount());
            } else {
                Toast.makeText(getApplicationContext(), "Something unexpected happened. Please, try again.", Toast.LENGTH_LONG).show();
                count = 0;
                loading = true;

                if (mAdapter.getItemCount() == 0) {
                    somethingWentWrongContainer.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public void onFailure(Call<APIResponse> call, Throwable t) {
            loading = true;
            somethingWentWrongContainer.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.GONE);

        }
    };



}
