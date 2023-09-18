package org.meicode.spanzuratoarea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickListener{
    // 1. Addapter View
    RecyclerView recyclerView;

    // 2. Data Source
    List<WordType> wordTypeList;

    // 3. Adapter
    MyAdapter myAdapter;
    static int optionChoosen;
    static String word;
    Button goBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        wordTypeList = new ArrayList<>();
        goBackButton = findViewById(R.id.goBack);
        goBackButton.setVisibility(View.INVISIBLE);

        WordType wordType1 = new WordType(R.drawable.userinput, "Type a word for others to guess it");
        WordType wordType2 = new WordType(R.drawable.fruits, "Get a random fruit and guess it");
        WordType wordType3 = new WordType(R.drawable.vegetables, "Get a random vegetable and guess it");
        WordType wordType4 = new WordType(R.drawable.movies, "Get a random movie and guess it");
        WordType wordType5 = new WordType(R.drawable.all, "Get a random word from the 3 categories above");

        wordTypeList.add(wordType1);
        wordTypeList.add(wordType2);
        wordTypeList.add(wordType3);
        wordTypeList.add(wordType4);
        wordTypeList.add(wordType5);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(wordTypeList);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setClickListener(this);

    }

    public void loadFragment(Fragment fragment, boolean removeFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (removeFragment) {
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
            return;
        }

        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void itemClicked(View view, int position) {
        recyclerView.setVisibility(View.GONE);
        optionChoosen = position;

        if(position == 0) {
            loadFragment(new InputFragment(goBackButton), false);
        } else loadFragment(new GameRunning(goBackButton), false);
    }
}