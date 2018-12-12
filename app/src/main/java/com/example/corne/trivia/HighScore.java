package com.example.corne.trivia;

public class HighScore {
    private String mName;
    private String mScore;

    public HighScore (String name, String score){
        mName = name;
        mScore = score;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setScore(String score) {
        mScore = score;
    }

    public String getName() {

        return mName;
    }

    public String getScore() {
        return mScore;
    }
}
