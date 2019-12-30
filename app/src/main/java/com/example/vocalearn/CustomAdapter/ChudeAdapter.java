package com.example.vocalearn.CustomAdapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocalearn.Entity.ChuDe;
import com.example.vocalearn.Entity.Words;
import com.example.vocalearn.MyApplication;
import com.example.vocalearn.R;

import java.util.ArrayList;
import java.util.List;

public class ChudeAdapter extends RecyclerView.Adapter<ChudeAdapter.WordsHolder> {
    private static int[] imgs = {   R.drawable.chude_1,
            R.drawable.chude_2,
            R.drawable.chude_3,
            R.drawable.chude_4,
            R.drawable.chude_5,
            R.drawable.chude_6,
            R.drawable.chude_7,
            R.drawable.chude_8,
            R.drawable.chude_9,
            R.drawable.chude_10,
            R.drawable.chude_11,
            R.drawable.chude_12,
            R.drawable.chude_13,
            R.drawable.chude_14,
            R.drawable.chude_15};
    private static String category[] ={"Eating",
            "Vehicle",
            "Animal",
            "Geography",
            "Family",
            "Traffic",
            "Act",
            "Color",
            "House",
            "Fruit",
            "Education",
            "Human",
            "Character",
            "Health",
            "Love"};
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

    @Override//set các view
    public void onBindViewHolder(@NonNull WordsHolder holder, int position) {
        final ChuDe currentWord = chude.get(position);
        holder.textTenChuDe.setText(currentWord.getTenChuDe());
        holder.txtTenChude.setText(category[Integer.parseInt(currentWord.getIDChuDe())-1]);
        holder.imgChuDe.setImageDrawable(MyApplication.getContext().getDrawable(imgs[Integer.parseInt(currentWord.getIDChuDe())-1]));
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
        private TextView txtTenChude,textTenChuDe;
        private ImageView imgChuDe;
        OnChuDeClickListener onChuDeClickListener;

        public WordsHolder(@NonNull View itemView,OnChuDeClickListener onWordClickListener) {
            super(itemView);
            txtTenChude = itemView.findViewById(R.id.txtTenChuDe);
            imgChuDe = itemView.findViewById(R.id.imgChuDe);
            textTenChuDe = itemView.findViewById(R.id.textTenChuDe);
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
