package com.example.corne.trivia;

import android.content.Context;
import android.util.Log;

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

public class HighScoreRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private final Context context;
    String url = "https://ide50-cornheijnen.cs50.io:8080/list";
    HighScoreRequest.Callback activity;
    private ArrayList<HighScore> HighScoreList = new ArrayList<>();
    VolleyError error;
    JSONArray response;

    public interface Callback{
        void gotHighScores(ArrayList<HighScore> highScores);
        void gotHighScoresError(String message);
    }

    public HighScoreRequest(Context context) {
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotHighScoresError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try{
            JSONArray highscoreArray = response.getJSONArray("asdf");
            for (int i = 0; i < highscoreArray.length(); i++){
                JSONObject  highscoreObject = highscoreArray.getJSONObject(i);
                String name = highscoreObject.getString("name");
                String score = highscoreObject.getString("score");
                HighScore newHighScore = new HighScore(name, score);
                HighScoreList.add(newHighScore);
            }
        } catch (JSONException e){
            Log.e("Request", e.getMessage());
        }
        activity.gotHighScores(HighScoreList);
    }

    ArrayList<HighScore> getHighScores(final HighScoreRequest.Callback activity){
        RequestQueue queue = Volley.newRequestQueue(context);
        try{
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
            queue.add(jsonObjectRequest);
        }
        catch (Exception error){
            Log.e("Request", error.getMessage());
        }
        return null;

    }

    }
