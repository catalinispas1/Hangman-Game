package org.meicode.spanzuratoarea;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class PrepareGame {
    String[] fruits = {"Apple", "Apricot", "Avocado", "Aubergine", "Berries", "Butternut squash", "Cherries", "Chupa-chupa", "Crab apple", "Clementine", "Cucumber", "Courgette", "Custard apple",
            "Damsons", "Durian", "Dates", "Dragon fruit", "Eggfruit", "Entawak", "Fig", "Finger lime", "Grapefruit", "Grapes", "Guava", "Imbe", "Jackfruit", "Java apple", "Jambolan", "Jeruk limo",
            "Kiwi", "Kaffir lime", "Kumquat", "Lemon", "Lime", "Lychee", "Loquat", "Mango", "Mandarin", "Mangosteen", "Melon", "Nectarine", "Nyssa ogeche", "Nashi pear", "Olive", "Orange",
            "Papaya", "Persimmon", "Prickly pear", "Peach", "Pomegranate", "Pineapple", "Passion fruit", "Pluot", "Pear", "Pepper", "Pumpkin", "Pomelo", "Quince", "Rambutan", "Raisins", "Rose hips",
            "Star fruit", "Tomato", "Tangerine", "Tamarind", "Tamarillo", "Ugli fruit", "Voavanga", "Ximenia caffra fruit", "Yangmei"};

    String[] vegetables = {"Artichokes", "Arugula", "Asparagus", "Beets", "Bell peppers", "Bok choy", "Broccoli", "Brussels sprouts", "Cabbage", "Carrots", "Cauliflower", "Celery", "Collard greens",
            "Cucumbers", "Endive", "Escarole", "Fennel", "French beans", "Garlic", "Ginger", "Green beans", "Green onions", "Green peas", "Kohlrabi", "Leeks", "Lettuce", "Lima beans", "Mustard greens",
            "Okra", "Onions", "Oregano", "Parsley", "Parsnips", "Peas", "Potatoes", "Pumpkin", "Radicchio", "Radishes", "Rapini", "Red cabbage", "Red onion", "Red pepper", "Romaine lettuce",
            "Rutabaga", "Scallions", "Shallots", "Spinach", "Squash", "Sweet potatoes", "Turnips", "Vanilla", "Zucchini"};

    String[] movies = {"The Proud Family Movie", "Newsies", "Chariots of Fire", "Godzilla", "Teen Beach", "Lawrence of Arabia", "Pocahontas", "Harry and the Hendersons",
            "Read It and Weep", "The Nightmare Before Christmas", "The Bourne Ultimatum", "Ghost", "Kiki's Delivery Service", "Zootopia", "The Rescuers", "Bedknobs and Broomsticks",
            "The Return of Jafar", "Sky High", "Johnny Tsunami", "Angels in the Outfield", "The Croods", "The Peanuts Movie", "October Sky", "Star Wars: The Force Awakens", "Indiana Jones and the Last Crusade",
            "The NeverEnding Story", "Patch Adams", "Beethoven", "Airplane", "Jump In", "Jett Jackson: The Movie", "Pokemon: Power of One", "Return to Halloweentown", "Swiss Family Robinson",
            "The Cheetah Girls: One World", "The Jungle Book", "Now You See Me", "Journey to the Center of the Earth", "Heavyweights", "Crocodile Dundee", "Romeo and Juliet",
            "Corpse Bride", "Into the Woods", "It's a Wonderful Life", "Teenage Mutant Ninja Turtles", "Star Trek Beyond", "The Prestige", "Dennis the Menace"};

    char[] wordArray;
    char[] guessWordArray;
    int countLetterFound;
    int category;
    ArrayList<Character> history;
    TextView textView;
    TextView attemptsText;
    EditText getUserChar;
    Button button;
    ImageView imageView;
    Context context;
    int[] imageResorces;
    int attempts;
    PrepareGame(int category, TextView textView, TextView attemptsText, EditText getUserChar, Button submitButton,ImageView imageView, Context context, int[] imageResorces) {
        this.category = category;
        this.textView = textView;
        this.attemptsText = attemptsText;
        this.getUserChar = getUserChar;
        this.context = context;
        this.button = submitButton;
        this.imageView = imageView;
        this.imageResorces = imageResorces;
        history = new ArrayList<>();
        attempts = 7;
    }

    void setWord() {
        Random random = new Random();

        if(category == 0) return;
        if (category == 1) {
            int index = random.nextInt(fruits.length);
            MainActivity.word = fruits[index];

        } else if (category == 2) {
            int index = random.nextInt(vegetables.length);
            MainActivity.word = vegetables[index];

        } else if (category == 3) {
            int index = random.nextInt(movies.length);
            MainActivity.word = movies[index];

        } else if (category == 4) {
            int randomCategory = random.nextInt(3);
            if (randomCategory == 0) {
                int index = random.nextInt(fruits.length);
                MainActivity.word = fruits[index];
            } else if (randomCategory == 1) {
                int index = random.nextInt(vegetables.length);
                MainActivity.word = vegetables[index];
            } else {
                int index = random.nextInt(movies.length);
                MainActivity.word = movies[index];
            }
        }

    }

    void setStarterWord() {
        String word1 = MainActivity.word.toLowerCase();
        wordArray = word1.toCharArray();
        guessWordArray = new char[wordArray.length];

        for (int i = 0; i < wordArray.length; i++) {
            if (wordArray[0] == wordArray[i]) {
                guessWordArray[i] = wordArray[0];
                countLetterFound++;
                history.add(wordArray[0]);
            } else if (wordArray[wordArray.length - 1] == wordArray[i]) {
                guessWordArray[i] = wordArray[wordArray.length - 1];
                countLetterFound++;
                history.add(wordArray[wordArray.length - 1]);
            } else if (wordArray[i] == ' ') {
                guessWordArray[i] = ' ';
                countLetterFound++;
                history.add(' ');
            } else if (wordArray[i] == ':') {
                guessWordArray[i] = ' ';
                countLetterFound++;
                history.add(' ');
            } else if (wordArray[i] == "\u0027".charAt(0)) {
                guessWordArray[i] = "\u0027".charAt(0);
                countLetterFound++;
                history.add(' ');
            } else {
                guessWordArray[i] = '_';
            }
        }
    }

    public String printGuessWord() {
        String guessWord = "";
        for (int i = 0; i < wordArray.length; i++) {
            guessWord = guessWord + guessWordArray[i];
        }
        return guessWord;
    }

    void prepareGame() {
        setWord();
        setStarterWord();
        printGuessWord();
    }

    String printWord(char[] word) {
        String getWord = "";
        for(int i = 0; i < word.length; i++) {
            getWord = getWord + word[i];
        }
        return getWord;
    }

    boolean isDublicate(char userChar) {
        for(int i = 0; i < history.size(); i++) {
            if(userChar == history.get(i)) {
                return true;
            }
        }
        return false;
    }

    boolean isLetterGuessed(char userChar) {
        boolean letterFound = false;
        for(int i = 1; i < wordArray.length - 1; i++) {
            if(userChar == wordArray[i]) {
                guessWordArray[i] = wordArray[i];
                countLetterFound++;
                letterFound = true;
            }
        }
        if (letterFound) return true;
        return false;
    }


    public void checkGameLosing() {
        if (getUserChar.getText().toString().isEmpty()) return;

        char userChar = getUserChar.getText().charAt(0);
        if(userChar == ' ') return;

        getUserChar.setText("");
        if(isDublicate(userChar)) {
            Toast.makeText(context, "You already typed this letter", Toast.LENGTH_SHORT).show();
            return;
        }

        if(isLetterGuessed(userChar)){
            textView.setText(printGuessWord());
        } else {
            attempts--;
            imageView.setImageResource(imageResorces[attempts]);
            if(attempts > 0) {
                Toast.makeText(context, "The letter doesn't match", Toast.LENGTH_SHORT).show();
                attemptsText.setText(attempts + " attempts");
            } else {
                Toast.makeText(context, "This was your last attempt.", Toast.LENGTH_SHORT).show();
                textView.setText(printWord(wordArray));
                attemptsText.setText("Word Reveal, You Lost!");
                attemptsText.setTextSize(25);
                button.setVisibility(View.INVISIBLE);
                getUserChar.setEnabled(false);
                return;
            }
        }

        if(countLetterFound == wordArray.length) {
            attemptsText.setText("You Won!");
            attemptsText.setTextSize(40);
            button.setVisibility(View.INVISIBLE);
            getUserChar.setEnabled(false);
        }
        history.add(userChar);
    }
}

