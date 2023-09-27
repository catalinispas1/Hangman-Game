package org.meicode.spanzuratoarea;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import org.meicode.spanzuratoarea.adapters.GameHistoryAdapter;
import org.meicode.spanzuratoarea.db.GameHistoryAppDatabase;
import org.meicode.spanzuratoarea.db.entity.GameHistory;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameHistoryFragment extends Fragment implements ItemClickListener{
    RecyclerView recyclerView;
    Fragment fragment = this;
    MainActivity mainActivity;
    Button goBackButton;
    Button clearHistory;
    Button history;
    static GameHistoryAppDatabase gameHistoryAppDatabase;
    static ArrayList<GameHistory> gameHistoryArrayList;
    static GameHistoryAdapter gameHistoryAdapter;

    public GameHistoryFragment(Button goBackButton, Button clearHistory, Button history) {
        this.goBackButton = goBackButton;
        this.clearHistory = clearHistory;
        this.history = history;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_history, container, false);
        recyclerView = view.findViewById(R.id.historyView);

        mainActivity = (MainActivity) getActivity();
        goBackButton.setVisibility(View.VISIBLE);
        gameHistoryArrayList.clear();
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.loadFragment(fragment, true);
                mainActivity.recyclerView.setVisibility(View.VISIBLE);
                goBackButton.setVisibility(View.INVISIBLE);
                history.setVisibility(View.VISIBLE);
                clearHistory.setVisibility(View.INVISIBLE);
            }
        });

        clearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAll();
            }
        });


        gameHistoryAdapter = new GameHistoryAdapter(gameHistoryArrayList, fragment.requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(gameHistoryAdapter);
        displayAllGameHistory();

        gameHistoryAdapter.setClickListener(this);
        clearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAll();
            }
        });
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.loadFragment(fragment, true);
                mainActivity.recyclerView.setVisibility(View.VISIBLE);
                goBackButton.setVisibility(View.INVISIBLE);
                clearHistory.setVisibility(View.INVISIBLE);
                history.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    private void deleteAll() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                gameHistoryAppDatabase.getGameHistoryDAO().deleteAll();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameHistoryArrayList.clear();
                        gameHistoryAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void displayAllGameHistory(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                gameHistoryArrayList.addAll(gameHistoryAppDatabase.getGameHistoryDAO().getAll());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameHistoryAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    @Override
    public void itemClicked(View view, int position) {
        Toast.makeText(fragment.requireContext(), "Word: " + gameHistoryArrayList.get(position).getWordRevealed(), Toast.LENGTH_SHORT).show();
    }
}