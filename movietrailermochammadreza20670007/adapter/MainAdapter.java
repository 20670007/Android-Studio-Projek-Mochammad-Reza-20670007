package com.example.movietrailermochammadreza20670007.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movietrailermochammadreza20670007.model.MovieModel;
import com.example.movietrailermochammadreza20670007.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<MovieModel.Results> results;
    private Context context;

    public  MainAdapter(List<MovieModel.Results> results, Context context) {
        this.results = results;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_movie,
                        viewGroup,false)
        );

    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        MovieModel.Results result = results.get(position);

        holder.text_title.setText( result.getOriginal_title());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image_poster;
        TextView text_title;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_poster = itemView.findViewById(R.id.image_poster);
            text_title = itemView.findViewById(R.id.text_title);
        }
    }
    public void setData(List<MovieModel.Results>newResult){
        results.clear();
        results.addAll( newResult);
        notifyDataSetChanged();
    }

}

