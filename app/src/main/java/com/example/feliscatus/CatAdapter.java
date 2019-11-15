package com.example.feliscatus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CatAdapter extends RecyclerView.Adapter<CatAdapter.CatViewHolder> {
    private List<Cat> catsToAdapt;

    public void setData(List<Cat> catsToAdapt) {
        this.catsToAdapt = catsToAdapt;
    }

    @NonNull
    @Override
    public CatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.cat_search, parent, false);

        CatViewHolder catViewHolder = new CatViewHolder(view);
        return catViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CatViewHolder holder, int position) {
        final Cat catAtPosition = catsToAdapt.get(position);
        holder.bind(catAtPosition);
    }

    @Override
    public int getItemCount() {
        return catsToAdapt.size();
    }

    public static class CatViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView breedTV;
        public TextView originTV;
        public TextView descTV;
        public TextView tempTV;
        public TextView weightTV;
        public TextView lifespanTV;
        public TextView dogfriendTV;
        public ImageView catImageView;
        public TextView linkTV;

        // This constructor is used in onCreateViewHolder
        public CatViewHolder(View v) {
            super(v);
            view = v;
            breedTV = v.findViewById(R.id.cat_search);


        }

        // See comment in onBindViewHolder
        public void bind(final Cat cat) {
            breedTV.setText(cat.getBreed());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();

                    Intent intent = new Intent(context, CatDetailActivity.class);
                    intent.putExtra("id", cat.getId());
                    context.startActivity(intent);
                }
            });



            String imageUrl = cat.getCatImage();
            //Glide.with(view.getContext()).load(imageUrl).into(coverImageView);
        }
    }
}
