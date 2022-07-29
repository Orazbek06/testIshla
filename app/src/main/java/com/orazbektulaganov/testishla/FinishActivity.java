package com.orazbektulaganov.testishla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FinishActivity extends AppCompatActivity {

    TextView rightCountText;
    TextView percentText;
    RecyclerView recyclerView;
    ArrayList<Test> testAnswers;
    TestAnswerAdapter answerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        int correctCount = 0;
        ArrayList<Test> tests = TestSolvingActivity.tests;
        for (int i = 0; i < tests.size(); i++) {
            if (tests.get(i).isRight())correctCount++;
        }

        rightCountText = findViewById(R.id.rightCountText);
        percentText = findViewById(R.id.percentText);
        recyclerView = findViewById(R.id.resultRecyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        answerAdapter = new TestAnswerAdapter(this,tests);
        recyclerView.setAdapter(answerAdapter);
        rightCountText.setText(correctCount+"/25");
        percentText.setText(correctCount*4+"%");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}