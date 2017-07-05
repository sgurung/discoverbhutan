package bt.gov.dit.discoverbhutan;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class QuizActivity extends AppCompatActivity {

    private QuestionLibrary ql;


    private TextView mScoreView;
    private TextView mQuestionView;
    private TextView mQuestionNumberView;
    private TextView timer;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private static final int questionTotal=10;
    CountDownTimer cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ql = new QuestionLibrary(getApplicationContext());

        mScoreView = (TextView) findViewById(R.id.score);
        mQuestionView = (TextView) findViewById(R.id.question);
        mButtonChoice1 = (Button) findViewById(R.id.choice1);
        mButtonChoice2 = (Button) findViewById(R.id.choice2);
        mButtonChoice3 = (Button) findViewById(R.id.choice3);
        mButtonChoice4 = (Button) findViewById(R.id.choice4);
        mQuestionNumberView = (TextView) findViewById(R.id.questionno);
        timer = (TextView) findViewById(R.id.timer);

        updateQuestion();


        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Create Response", "Correctanswer:" + mAnswer + "your selection:" + mButtonChoice1.getText());
                if (mButtonChoice1.getText().toString().equals(mAnswer)) {

                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(QuizActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(QuizActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });


        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Create Response", "Correctanswer:" + mAnswer + "your selection:" + mButtonChoice2.getText());

                if (mButtonChoice2.getText().toString().equals(mAnswer)) {

                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(QuizActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(QuizActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });


        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Create Response", "Correctanswer:" + mAnswer + "your selection:" + mButtonChoice3.getText());
                if (mButtonChoice3.getText().toString().equals(mAnswer)) {

                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(QuizActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(QuizActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Create Response", "Correctanswer:" + mAnswer + "your selection:" + mButtonChoice4.getText());
                if (mButtonChoice4.getText().toString().equals(mAnswer)) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                    Toast.makeText(QuizActivity.this, "Correct", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(QuizActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });


    }

    private void updateQuestion() {

        if(mQuestionNumber<questionTotal){

            YoYo.with(Techniques.StandUp).duration(1500).repeat(1).playOn(mQuestionView);
            mQuestionView.setText(ql.getQuestion(mQuestionNumber));
            YoYo.with(Techniques.BounceInRight).duration(1500).repeat(1).playOn(mButtonChoice1);

            mButtonChoice1.setText(ql.getOptionOne(mQuestionNumber));
            YoYo.with(Techniques.BounceInRight).duration(2500).repeat(1).playOn(mButtonChoice2);
            mButtonChoice2.setText(ql.getOptionTwo(mQuestionNumber));
            YoYo.with(Techniques.BounceInRight).duration(3500).repeat(1).playOn(mButtonChoice3);
            mButtonChoice3.setText(ql.getOptionThree(mQuestionNumber));
            YoYo.with(Techniques.BounceInRight).duration(4500).repeat(1).playOn(mButtonChoice4);
            mButtonChoice4.setText(ql.getOptionFour(mQuestionNumber));
            mAnswer = ql.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
            YoYo.with(Techniques.FlipInY).duration(2000).repeat(1).playOn(mQuestionNumberView);
            mQuestionNumberView.setText(mQuestionNumber + "/10");
            timerstart(20000);
        }
        else {

            timerstop();
            Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
            intent.putExtra("SCORE",mScore);
            startActivity(intent);

            finish();

        }

    }


    private void updateScore(int point) {
        YoYo.with(Techniques.StandUp).duration(1500).repeat(1).playOn(mScoreView);
        mScoreView.setText("" + mScore);
    }


    public void timerstart(long timervalue) {


        if (cd == null) {
            cd = new CountDownTimer(timervalue, 1000) {

                public void onTick(long millisUntilFinished) {
                    timer.setText("" + millisUntilFinished / 1000 + "s");
                }

                public void onFinish() {
                    updateQuestion();
                }
            }.start();

        } else {
            cd.cancel();
            cd = new CountDownTimer(timervalue, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timer.setText("" + millisUntilFinished / 1000 + "s");
                }

                @Override
                public void onFinish() {
                    Toast.makeText(QuizActivity.this, "Time out", Toast.LENGTH_SHORT).show();
                        updateQuestion();

                }
            }.start();

        }


    }

    public void timerstop(){
        cd.cancel();


    }
}
