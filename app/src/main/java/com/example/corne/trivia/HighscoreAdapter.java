package com.example.corne.trivia;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HighscoreAdapter extends ArrayAdapter<HighScore> {

    private ArrayList<HighScore> highscores;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.highscore_item, parent, false);
        }
        TextView highScoreName = (TextView) convertView.findViewById(R.id.highscoreName);
        TextView highScoreScore = (TextView) convertView.findViewById(R.id.highscoreScore);

        String name = highscores.get(position).getName();
        Log.e("Request", name);
        String score = highscores.get(position).getScore();

        highScoreName.setText(name);
        highScoreScore.setText(score);

        return convertView;    }

    public HighscoreAdapter(Context context, int resource, ArrayList<HighScore> objects) {
        super(context, resource, objects);
        this.highscores = objects;
    }
}
