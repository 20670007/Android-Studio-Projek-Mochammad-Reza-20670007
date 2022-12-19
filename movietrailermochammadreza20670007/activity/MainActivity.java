package com.example.movietrailermochammadreza20670007.activity;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.movietrailermochammadreza20670007.R;
import com.example.movietrailermochammadreza20670007.adapter.MainAdapter;
import com.example.movietrailermochammadreza20670007.model.MovieModel;
import com.example.movietrailermochammadreza20670007.retrofit.Constant;
import com.example.movietrailermochammadreza20670007.retrofit.MovieService;
import com.example.movietrailermochammadreza20670007.retrofit.Retrofitinstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    private MovieService service = Retrofitinstance.getUrl().create(MovieService.class);
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private MainAdapter adapter;
    private List<MovieModel.Results> movies = new ArrayList<>();
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupView();
        setupRecyclerView();

    @Override void onStart(); {
        super.onStart();
        getMoviePopular();
        }


    }
    private void setupView(){
        recyclerView = findViewById(R.id.list_movie);
        progressBar = findViewById(R.id.progress_loading);
    }

    private void setupRecyclerView(){
        adapter = new MainAdapter(movies, this);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setAdapter( adapter );

    }
    @Override
    protected void onStart() {
        super.onStart();
        getMoviePopular();
    }

    private void getMoviePopular(){

        showLoading( true );

        Call<MovieModel> call = service.getPopular(Constant.API_KEY, Constant.LANGUAGE, "1");
        call.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

                showLoading( false );
                if (response.isSuccessful()) {
                    MovieModel movie = response.body();
//                    List<MovieModel.Results> results = movieModel.getResults();
//                    Log.d(TAG, results.toString());
                    showMovie( movie );
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {
                showLoading( false );
                Log.d(TAG, t.toString());

            }
        });



    }

    private void showLoading(Boolean loading){
        if (loading) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }

    }

    private void showMovie(MovieModel movie) {
        movies = movie.getResults();
        adapter.setData( movies );
    }
}