package bt.gov.dit.discoverbhutan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private QuestionLibrary ql;


    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ql = new QuestionLibrary(getApplicationContext());

        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        mButtonChoice4 = (Button)findViewById(R.id.choice4);

        updateQuestion();


        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here
                Log.d("Create Response", "Correctanswer:"+mAnswer+"your selection:"+mButtonChoice1.getText());
                if (mButtonChoice1.getText().toString()==mAnswer){

                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });


        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d("Create Response", "Correctanswer:"+mAnswer+"your selection:"+mButtonChoice2.getText());

                if (mButtonChoice2.getText().toString() == mAnswer){

                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });


        mButtonChoice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Log.d("Create Response", "Correctanswer:"+mAnswer+"your selection:"+mButtonChoice3.getText());
                if (mButtonChoice3.getText().toString() == mAnswer){

                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Log.d("Create Response", "Correctanswer:"+mAnswer+"your selection:"+mButtonChoice4.getText());
                if (mButtonChoice4.getText().toString() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });






    }

    private void updateQuestion(){
        mQuestionView.setText(ql.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(ql.getOptionOne(mQuestionNumber));
        mButtonChoice2.setText(ql.getOptionTwo(mQuestionNumber));
        mButtonChoice3.setText(ql.getOptionThree(mQuestionNumber));
        mButtonChoice4.setText(ql.getOptionFour(mQuestionNumber));
        mAnswer = ql.getCorrectAnswer(mQuestionNumber);
        mQuestionNumber++;
    }


    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }




}
