package org.meicode.spanzuratoarea.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.meicode.spanzuratoarea.ItemClickListener;
import org.meicode.spanzuratoarea.R;

import java.util.List;

public class GameModeAdapter extends RecyclerView.Adapter<GameModeAdapter.MyViewHolder> {

    private List<WordType> wordTypeList;

    public ItemClickListener clickListener;

    public void setClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public GameModeAdapter(List<WordType> wordTypeList) {
        this.wordTypeList = wordTypeList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This creates new View Holder for my items
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_type_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Binds the data from your dataset to the views within the view holder
        WordType wordType = wordTypeList.get(position);

        holder.textView.setText(wordType.getDescription());
        holder.imageView.setImageResource(wordType.getImageSource());

    }

    @Override
    public int getItemCount() {
        return wordTypeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Holds references to the views within the item layout
        ImageView imageView;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.game_mode);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.itemClicked(view, getAdapterPosition());
        }
    }
}
