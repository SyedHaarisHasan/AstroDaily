package com.example.astrodaily;

import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter extends ArrayAdapter<Article> {
    Context context;
    ArrayList<Article> articles;

    public ArticleAdapter(Context c, ArrayList<Article> articles) {
        super(c, R.layout.row, R.id.title, articles);
        this.context = c;
        this.articles = articles;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.row, parent, false);
        ImageView images = row.findViewById(R.id.image);
        TextView mTitle = row.findViewById(R.id.title);
        TextView mAuthor = row.findViewById(R.id.author);
        TextView mType = row.findViewById(R.id.type);
        TextView mSource = row.findViewById(R.id.source);

        Article article = articles.get(position);

        Picasso.get().load(article.getPicUrl()).into(images);
        mTitle.setText(article.getTitle());
        mAuthor.setText(article.getAuthor().equals("null") ? null : articles.get(position).getAuthor());
        mType.setText(article.getIsOpinion().equals("true") ? "Opinion" : "Fact");
        mSource.setText(article.getSourceUrl());

        return row;
    }
}
