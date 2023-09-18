package org.meicode.spanzuratoarea;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GameRunning extends Fragment {
    private Fragment gameRunning;
    Button goBackButton;
    MainActivity mainActivity;
    int[] imageResources = {
            R.drawable.image8,
            R.drawable.image7,
            R.drawable.image6,
            R.drawable.image5,
            R.drawable.image4,
            R.drawable.image3,
            R.drawable.image2,
            R.drawable.image1
    };
    GameRunning(Button goBackButton){
        this.goBackButton = goBackButton;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_running, container, false);
        TextView guessWord = view.findViewById(R.id.title_text);
        TextView attempts = view.findViewById(R.id.attemptsText);
        EditText editText = view.findViewById(R.id.inputLetter);
        Button submitLetter = view.findViewById(R.id.submitLetter);
        ImageView imageView = view.findViewById(R.id.hangman);
        imageView.setImageResource(imageResources[imageResources.length - 1]);
        goBackButton.setVisibility(View.VISIBLE);

        gameRunning = this;
        mainActivity = (MainActivity) getActivity();

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.loadFragment(gameRunning, true);
                mainActivity.recyclerView.setVisibility(View.VISIBLE);
                goBackButton.setVisibility(View.INVISIBLE);
            }
        });
        PrepareGame prepareGame = new PrepareGame(MainActivity.optionChoosen, guessWord, attempts, editText, submitLetter,imageView, getActivity(), imageResources);
        prepareGame.prepareGame();
        guessWord.setText(prepareGame.printGuessWord());
        attempts.setText(prepareGame.attempts + " attempts");

        if(prepareGame.countLetterFound == MainActivity.word.length()) {
            attempts.setText("Easy win, all letters are the same");
            editText.setEnabled(false);
            submitLetter.setVisibility(View.INVISIBLE);
        }

        submitLetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prepareGame.checkGameLosing();
            }
        });

        return view;
    }
}