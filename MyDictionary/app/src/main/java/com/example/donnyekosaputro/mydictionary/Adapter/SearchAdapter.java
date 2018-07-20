package com.example.donnyekosaputro.mydictionary.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.donnyekosaputro.mydictionary.Model.KamusModel;
import com.example.donnyekosaputro.mydictionary.R;
import com.example.donnyekosaputro.mydictionary.ViewHolder.SearchViewHolder;

import java.util.ArrayList;



public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

    private ArrayList<KamusModel> list = new ArrayList<>();

    public SearchAdapter() {
    }

    public void replaceAll(ArrayList<KamusModel> items) {
        list = items;
        notifyDataSetChanged();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.content_list_item, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
