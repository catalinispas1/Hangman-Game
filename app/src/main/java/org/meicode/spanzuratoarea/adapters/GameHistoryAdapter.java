package org.meicode.spanzuratoarea.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.meicode.spanzuratoarea.ItemClickListener;
import org.meicode.spanzuratoarea.R;
import org.meicode.spanzuratoarea.db.entity.GameHistory;

import java.util.ArrayList;

public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryAdapter.MyViewHolder> {

    private ArrayList<GameHistory> gameHistoryArrayList;
    private Context context;
    public ItemClickListener clickListener;
    public void setClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    public GameHistoryAdapter(ArrayList<GameHistory> gameHistoryArrayList, Context context) {
        this.gameHistoryArrayList = gameHistoryArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_history_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GameHistory gameHistory = gameHistoryArrayList.get(position);

        holder.imageView.setImageResource(gameHistory.getImageSrc());
        holder.title.setText(gameHistory.getWordState());
        holder.attempts.setText("" + gameHistory.getAttemptsLeft());

        if(gameHistory.isGameWon()) holder.gameWon.setText("Game Won");
        else holder.gameWon.setText("Game Lost");
    }

    @Override
    public int getItemCount() {
        return gameHistoryArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView title;
        TextView attempts;
        TextView gameWon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.historyImage);
            title = itemView.findViewById(R.id.guessedWord);
            attempts = itemView.findViewById(R.id.attemptsText);
            gameWon = itemView.findViewById(R.id.gameWon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.itemClicked(view, getAdapterPosition());
        }
    }
}
