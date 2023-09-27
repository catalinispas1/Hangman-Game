package org.meicode.spanzuratoarea;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InputFragment extends Fragment {

    Fragment inputFragment;
    Button goBackButton;
    Button history;
    InputFragment(Button goBackButton, Button history){
        this.inputFragment = this;
        this.goBackButton = goBackButton;
        this.history = history;
    }

    MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_fragment, container, false);
        EditText inpuText = view.findViewById(R.id.inputText);
        TextView titleText = view.findViewById(R.id.title_text);
        Button submitButton = view.findViewById(R.id.submitLetter);
        goBackButton.setVisibility(View.VISIBLE);

        mainActivity = (MainActivity) getActivity();
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.loadFragment(inputFragment, true);
                mainActivity.recyclerView.setVisibility(View.VISIBLE);
                mainActivity.goBackButton.setVisibility(View.INVISIBLE);
                history.setVisibility(View.VISIBLE);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.word = inpuText.getText().toString();
                if (MainActivity.word.length() < 3) {
                    Toast.makeText(getActivity(), "Type a larger word", Toast.LENGTH_SHORT).show();
                } else {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.loadFragment(new GameRunning(goBackButton, history), false);
                }
            }
        });
        return view;
    }
}