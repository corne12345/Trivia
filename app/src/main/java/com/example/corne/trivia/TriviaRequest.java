package com.example.corne.trivia;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class TriviaRequest implements Response.Listener<JSONObject>, Response.ErrorListener{

    private Context mContext;
    private String mDifficulty;
    Callback activity;
    private ArrayList<Question> questionList = new ArrayList<>();

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotQuestonsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try {
            JSONArray questionsArray = response.getJSONArray("results");
            for (int i = 0; i < questionsArray.length(); i++){
                JSONObject  questionObject = questionsArray.getJSONObject(i);
                String question = decodeString(questionObject.getString("question"));
                String answerA = decodeString(questionObject.getString("correct_answer"));
                JSONArray incorrectAnswers = questionObject.getJSONArray("incorrect_answers");
                String answerB = decodeString(incorrectAnswers.getString(0));
                String answerC = decodeString(incorrectAnswers.getString(1));
                String answerD = decodeString(incorrectAnswers.getString(2));
                Question newQuestion = new Question(question, answerA, answerB, answerC, answerD, answerA);
                questionList.add(newQuestion);
            }
        } catch (JSONException e) {
            Log.e("Request", e.getMessage());
        }
        activity.gotQuestions(questionList);
    }

    public interface Callback {
        void gotQuestions(ArrayList<Question> questionList);
        void gotQuestonsError(String message);
    }

    public TriviaRequest(Context context, String difficulty) {
        this.mContext = context;
        this.mDifficulty = difficulty;
    }

    ArrayList<Question> getQuestions(Callback activity){
        this.activity = activity;
        String url = "https://opentdb.com/api.php?amount=50&difficulty=" + mDifficulty + "&type=multiple&encode=url3986";
        RequestQueue queue = Volley.newRequestQueue(mContext);
        try{
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
            queue.add(jsonObjectRequest);
        }
        catch (Exception error){
            Log.e("Request", error.getMessage());
        }
        return null;
    }

    public String decodeString(String string){
        try {
            string = URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.e("Request", "failed to decode");
        }
        return string;
    }
}
