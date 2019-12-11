package com.example.vocalearn.CustomAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocalearn.Entity.ChuDe;
import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.R;

import java.util.ArrayList;
import java.util.List;

public class ChudeAdapter extends RecyclerView.Adapter<ChudeAdapter.WordsHolder> {
    private List<ChuDe> chude = new ArrayList<>();
    private OnChuDeClickListener listener;
    @NonNull
    @Override
    public WordsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chude_item,parent,false);

        return new WordsHolder(itemView,listener);
    }

    public ChudeAdapter(OnChuDeClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull WordsHolder holder, int position) {
        final ChuDe currentWord = chude.get(position);
        holder.txtTenChude.setText(currentWord.getTenChuDe());
    }

    @Override
    public int getItemCount() {
        return chude.size();
    }
    public void setChuDe(List<ChuDe> chude)
    {
        this.chude=chude;
        notifyDataSetChanged();
    }
    class WordsHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView txtTenChude;
        OnChuDeClickListener onChuDeClickListener;

        public WordsHolder(@NonNull View itemView,OnChuDeClickListener onWordClickListener) {
            super(itemView);
            txtTenChude = itemView.findViewById(R.id.txtTenChuDe);
            this.onChuDeClickListener = onWordClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onChuDeClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnChuDeClickListener{
        void onItemClick(int position);
    }
    public void setOnClickListener(OnChuDeClickListener listener){
        this.listener = listener;
    }

}
