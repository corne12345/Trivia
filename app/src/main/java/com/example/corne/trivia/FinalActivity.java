package com.example.corne.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class FinalActivity extends AppCompatActivity{

    String name;
    String score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        name = (String) getIntent().getSerializableExtra("name");
        score = (String) getIntent().getSerializableExtra("score");
        Log.e("Request", "score");
        TextView finalScoreView = (TextView) findViewById(R.id.finalScoreView);
        String insert = "Finished with a score of " + score + " points";
        finalScoreView.setText(insert);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://ide50-cornheijnen.cs50.io:8080/list";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", "response");
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Request", "Error");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", name);
                params.put("score", score);

                return params;
            }
        };
        queue.add(postRequest);

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button highscoreButton = (Button) findViewById(R.id.highScoresButton);
        highscoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (FinalActivity.this, HighscoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
