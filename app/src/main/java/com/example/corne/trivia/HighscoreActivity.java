package com.example.corne.trivia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HighscoreActivity extends AppCompatActivity{
//public class HighscoreActivity extends AppCompatActivity implements HighScoreRequest.Callback {
private Context mContext;
private Activity mActivity;
private String url = "https://ide50-cornheijnen.cs50.io:8080/list";
private ArrayList<HighScore> HighScoreList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        mContext = getApplicationContext();
        mActivity = HighscoreActivity.this;

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject highscoreObject = response.getJSONObject(i);
                                String name = highscoreObject.getString("name");
                                String score = highscoreObject.getString("score");
                                HighScore newHighScore = new HighScore(name, score);
                                HighScoreList.add(newHighScore);
                                Log.e("Request", Integer.toString(i));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Request", error.getMessage());
                    }
                });
        requestQueue.add(jsonArrayRequest);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HighscoreAdapter adapter = new HighscoreAdapter(HighscoreActivity.this, R.layout.highscore_item, HighScoreList);
                ListView startScreen = findViewById(R.id.listView);
                startScreen.setAdapter(adapter);
            }
        });

    }



//        HighScoreRequest x = new HighScoreRequest(this);
//        x.getHighScores(this);
//}
//    @Override
//    public void gotHighScores(ArrayList<HighScore> highScores) {
//        HighscoreAdapter adapter = new HighscoreAdapter(this, R.layout.highscore_item, highScores);
//        ListView startScreen = findViewById(R.id.listView);
//        startScreen.setAdapter(adapter);
//    }
//
//    @Override
//    public void gotHighScoresError(String message) {
//        Log.e("Request", "error in loading");
//
//    }
}
