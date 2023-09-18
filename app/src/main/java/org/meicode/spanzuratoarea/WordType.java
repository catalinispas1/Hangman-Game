package org.meicode.spanzuratoarea;

public class WordType {
    private int imageSource;
    private String description;

    public WordType(int imageSource, String description) {
        this.imageSource = imageSource;
        this.description = description;
    }

    public int getImageSource() {
        return imageSource;
    }

    public void setImageSource(int imageSource) {
        this.imageSource = imageSource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
