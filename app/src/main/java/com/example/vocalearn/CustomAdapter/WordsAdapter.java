package com.example.vocalearn.CustomAdapter;

import android.app.Application;
import android.os.Message;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.R;

import java.util.ArrayList;
import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.WordsHolder> {
    private List<Words> words = new ArrayList<>();
    private OnWordClickListener listener;
    @NonNull
    @Override
    public WordsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.words_item,parent,false);

        return new WordsHolder(itemView,listener);
    }

    public WordsAdapter(OnWordClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull WordsHolder holder, int position) {
        final Words currentWord = words.get(position);
        holder.txtWord.setText(currentWord.getTu());
        holder.txtNghia.setText(currentWord.getNghia());
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
    class WordsHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView txtWord;
        private TextView txtNghia;
        OnWordClickListener onWordClickListener;

        public WordsHolder(@NonNull View itemView,OnWordClickListener onWordClickListener) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtWord);
            txtNghia= itemView.findViewById(R.id.txtNghia);
            this.onWordClickListener = onWordClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onWordClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnWordClickListener{
        void onItemClick(int position);
    }
    public void setOnClickListener(OnWordClickListener listener){
        this.listener = listener;
    }

}
