package com.orazbektulaganov.testishla;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TestAnswerAdapter extends RecyclerView.Adapter<TestAnswerAdapter.TestHolder> {

    private Context context;
    private ArrayList<Test> tests;

    public TestAnswerAdapter(Context context, ArrayList<Test> tests) {
        this.context = context;
        this.tests = tests;
    }

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.incorrect_test_item,parent,false);

        return new TestHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        Test test = tests.get(position);

        holder.questionTextView.setText(position+1+"."+test.getQuestion());
        holder.answerA.setText("A.  "+test.getA());
        holder.answerB.setText("B.  "+test.getB());
        holder.answerC.setText("C.  "+test.getC());
        holder.answerD.setText("D.  "+test.getD());

            if(test.getRight().equals("A")){holder.answerA.setBackgroundResource(R.color.green);
                holder.answerB.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
                holder.answerC.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
                holder.answerD.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
                }
            else if(test.getRight().equals("B")){holder.answerB.setBackgroundResource(R.color.green);
                holder.answerA.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
                holder.answerC.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
                holder.answerD.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
                }
            else if(test.getRight().equals("C")){holder.answerC.setBackgroundResource(R.color.green);
                holder.answerA.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
                holder.answerB.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
                holder.answerD.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
                }
            else {
                holder.answerD.setBackgroundResource(R.color.green);
                holder.answerA.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
                holder.answerB.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
                holder.answerC.setBackgroundResource(androidx.cardview.R.color.cardview_shadow_start_color);
            }
            if(!test.isRight()){
                if(test.getChecked().equals("A"))holder.answerA.setBackgroundResource(R.color.red);
                else if(test.getChecked().equals("B"))holder.answerB.setBackgroundResource(R.color.red);
                else if(test.getChecked().equals("C"))holder.answerC.setBackgroundResource(R.color.red);
                else if(test.getChecked().equals("D"))holder.answerD.setBackgroundResource(R.color.red);
            }
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public class TestHolder extends RecyclerView.ViewHolder{

        TextView questionTextView;
        TextView answerA;
        TextView answerB;
        TextView answerC;
        TextView answerD;

        public TestHolder(@NonNull View itemView) {
            super(itemView);

            questionTextView = itemView.findViewById(R.id.incorrectText);
            answerA = itemView.findViewById(R.id.answerA);
            answerB = itemView.findViewById(R.id.answerB);
            answerC = itemView.findViewById(R.id.answerC);
            answerD = itemView.findViewById(R.id.answerD);
        }
    }
}
