package com.marvel.jaderbittencourt.marvelheroes.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marvel.jaderbittencourt.marvelheroes.R;
import com.marvel.jaderbittencourt.marvelheroes.rest.model.*;
import com.marvel.jaderbittencourt.marvelheroes.ui.ActivityDetails;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HeroItemAdapter extends RecyclerView.Adapter<HeroItemAdapter.ViewHolder> {

    Context context;
    List<Hero> heroesList = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView heroName;

        public ViewHolder(View v ) {
            super(v);
            image = v.findViewById(R.id.listImage);
            heroName = v.findViewById(R.id.heroName);
        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public HeroItemAdapter(Context context) {
        this.context = context;
    }

    public void setHeroesList(List<Hero> heroes) {
        heroesList.addAll(heroes);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HeroItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_text_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Hero item = heroesList.get(position);
        holder.heroName.setText(item.getName());
        final String thumbnail = item.getThumbnail().getPath().replace("http", "https") + "/standard_xlarge.jpg";
        Picasso.with(context).load(thumbnail).into(holder.image);

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityDetails.class);
                intent.putExtra("name", heroesList.get(position).getName());
                intent.putExtra("description", heroesList.get(position).getDescription());
                intent.putExtra("thumbnail", thumbnail);

                String comics = buildItemList(heroesList.get(position).getComics().getItems());
                String series = buildItemList(heroesList.get(position).getSeries().getItems());

                intent.putExtra("comics", comics);
                intent.putExtra("series", series);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return heroesList.size();
    }

    private String buildItemList(List<Items> list) {
        StringBuilder result = new StringBuilder();
        for (Items i: list) {
            result.append(i.getName() + "\n");
        }
        return result.toString();
    }
}

