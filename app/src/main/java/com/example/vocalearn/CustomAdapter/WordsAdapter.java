package com.example.vocalearn.CustomAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.R;

import java.util.ArrayList;
import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordsHolder> {
    private List<Words> words = new ArrayList<>();
    @NonNull
    @Override
    public WordsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.words_item,parent,false);

        return new WordsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WordsHolder holder, int position) {
        Words currentWord = words.get(position);
        holder.txtWord.setText(currentWord.getTu());
        holder.txtNghia.setText(currentWord.getNghia());
        holder.txtGhiChu.setText(currentWord.getGhichu());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }
    public void setWords(List<Words> words)
    {
        this.words=words;
        notifyDataSetChanged();
    }
    class WordsHolder extends RecyclerView.ViewHolder
    {
        private TextView txtWord;
        private TextView txtNghia;
        private TextView txtGhiChu;


        public WordsHolder(@NonNull View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtWord);
            txtNghia= itemView.findViewById(R.id.txtNghia);
            txtGhiChu= itemView.findViewById(R.id.txtGhiChu);
        }
    }
}
