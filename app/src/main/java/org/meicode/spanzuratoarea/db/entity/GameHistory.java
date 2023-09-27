package org.meicode.spanzuratoarea.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Game History")
public class GameHistory {
    @ColumnInfo(name = "image_src")
    private int imageSrc;

    @ColumnInfo(name = "word_state")
    private String wordState;

    @ColumnInfo(name = "attempts_left")
    private int attemptsLeft;

    @ColumnInfo(name = "game_won")
    private boolean gameWon;

    @ColumnInfo(name = "word_revealed")
    private String wordRevealed;

    @ColumnInfo(name = "game_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    public GameHistory(int imageSrc, String wordState, int attemptsLeft, boolean gameWon, int id, String wordRevealed) {
        this.imageSrc = imageSrc;
        this.wordState = wordState;
        this.attemptsLeft = attemptsLeft;
        this.gameWon = gameWon;
        this.id = id;
        this.wordRevealed = wordRevealed;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getWordState() {
        return wordState;
    }

    public void setWordState(String wordState) {
        this.wordState = wordState;
    }

    public int getAttemptsLeft() {
        return attemptsLeft;
    }

    public void setAttemptsLeft(int attemptsLeft) {
        this.attemptsLeft = attemptsLeft;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public String getWordRevealed() {
        return wordRevealed;
    }

    public void setWordRevealed(String wordRevealed) {
        this.wordRevealed = wordRevealed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
