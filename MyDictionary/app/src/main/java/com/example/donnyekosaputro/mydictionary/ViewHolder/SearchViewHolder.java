package com.example.donnyekosaputro.mydictionary.ViewHolder;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.donnyekosaputro.mydictionary.DetailKamusActivity;
import com.example.donnyekosaputro.mydictionary.Model.KamusModel;
import com.example.donnyekosaputro.mydictionary.R;


public class SearchViewHolder extends RecyclerView.ViewHolder {

    TextView tvKosakata, tvArti, tvCategory;

    public SearchViewHolder(View itemView) {
        super(itemView);

        tvKosakata  = (TextView)itemView.findViewById(R.id.tvKosakata);
        tvArti      = (TextView)itemView.findViewById(R.id.tvArti);
    }

    public void bind(final KamusModel kamusModel) {
        tvKosakata.setText(kamusModel.getKata());
        tvArti.setText(kamusModel.getDeskripsi());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), DetailKamusActivity.class);
                intent.putExtra(DetailKamusActivity.ITEM_KOSAKATA, kamusModel.getKata());
                intent.putExtra(DetailKamusActivity.ITEM_ARTI, kamusModel.getDeskripsi());
                intent.putExtra(DetailKamusActivity.ITEM_CATEGORY, kamusModel.getCategory());
                itemView.getContext().startActivity(intent);
            }
        });
    }
}
