package com.orazbektulaganov.testishla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class TestSolvingActivity extends AppCompatActivity {

    Button prevButton;
    Button nextButton;
    TextView question;
    RadioGroup radioGroup;
    RadioButton aButton;
    RadioButton bButton;
    RadioButton cButton;
    RadioButton dButton;
    int currentId;
    int prevId;
    public static ArrayList<Test> tests;
    public static HashMap<Integer, String> answerList;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_solving);
        Intent intent = getIntent();
        currentId = intent.getIntExtra("currentId", 0);

        radioGroup = findViewById(R.id.radioGroup);
        question = findViewById(R.id.questionTextView);
        aButton = findViewById(R.id.AradioButton);
        bButton = findViewById(R.id.BradioButton);
        cButton = findViewById(R.id.CradioButton);
        dButton = findViewById(R.id.DradioButton);
        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        progressBar = findViewById(R.id.progressBar);

        if (currentId == 24) {
            nextButton.setVisibility(View.INVISIBLE);
        }



        if (currentId == 0) {
            prevButton.setVisibility(View.INVISIBLE);
        }

        prevId = intent.getIntExtra("prevId", -1);
        if (prevId == -1) {
            progressBar.setVisibility(View.VISIBLE);
            TestSolvingActivity.tests = new ArrayList<>();
            answerList = new HashMap<>();
            String url = intent.getStringExtra("url");
            getTestsFromNet(url);
        } else {
            setTestValues();
            if (answerList != null && answerList.containsKey(currentId)) {
                String key = answerList.get(currentId);
                if(key.equals("A"))aButton.setChecked(true);
                else if(key.equals("B"))bButton.setChecked(true);
                else if(key.equals("C"))cButton.setChecked(true);
                else if(key.equals("D"))dButton.setChecked(true);
            }
        }

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(getApplicationContext(), TestSolvingActivity.class);
                nextIntent.putExtra("currentId", currentId + 1);
                nextIntent.putExtra("prevId", currentId);
                startActivity(nextIntent);
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.AradioButton:
                        answerList.put(currentId, "A");
                        tests.get(currentId).setChecked("A");
                        break;
                    case R.id.BradioButton:
                        answerList.put(currentId, "B");
                        tests.get(currentId).setChecked("B");
                        tests.get(currentId).setIsChecked(true);
                        break;
                    case R.id.CradioButton:
                        answerList.put(currentId, "C");
                        tests.get(currentId).setChecked("C");
                        break;
                    case R.id.DradioButton:
                        answerList.put(currentId, "D");
                        tests.get(currentId).setChecked("D");
                        break;
                }
            }
        });
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextIntent = new Intent(getApplicationContext(), TestSolvingActivity.class);
                nextIntent.putExtra("currentId", currentId - 1);
                nextIntent.putExtra("prevId", currentId);
                startActivity(nextIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.finish_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.finish_test){
            Intent intent = new Intent(getApplicationContext(),FinishActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void getTestsFromNet(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("tests");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Test test = new Test();
                                test.setQuestion(jsonObject.getString("question"));
                                test.setA(jsonObject.getString("a"));
                                test.setB(jsonObject.getString("b"));
                                test.setC(jsonObject.getString("c"));
                                test.setD(jsonObject.getString("d"));
                                test.setRight(jsonObject.getString("right"));
                                tests.add(test);
                            }
                            setTestValues();
                            progressBar.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        requestQueue.add(objectRequest);
    }

    private void setTestValues() {

        question.append(currentId+1+"/"+"25\n\n"+tests.get(currentId).getQuestion());
        aButton.append(tests.get(currentId).getA());
        bButton.append(tests.get(currentId).getB());
        cButton.append(tests.get(currentId).getC());
        dButton.append(tests.get(currentId).getD());
    }
}