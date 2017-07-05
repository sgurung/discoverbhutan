package bt.gov.dit.discoverbhutan;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class ResultActivity extends AppCompatActivity {


    private TextView score;
    private TextView message;
    private Button next,menu;
    private ImageView image;
    private Drawable drawable;
    private int finalScore;
    private String messageString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        score = (TextView)findViewById(R.id.score);
        message = (TextView)findViewById(R.id.message);
        image = (ImageView)findViewById(R.id.imageResult);
        next = (Button)findViewById(R.id.nextRound);
        menu = (Button)findViewById(R.id.goMenu);




            Bundle extras = getIntent().getExtras();

                finalScore= extras.getInt("SCORE");


        YoYo.with(Techniques.BounceInUp).duration(3000).repeat(1).playOn(score);
        YoYo.with(Techniques.Tada).duration(2400).repeat(1).playOn(message);
        YoYo.with(Techniques.BounceIn).duration(4000).repeat(1).playOn(image);

        if(finalScore==10){

            drawable =getResources().getDrawable(R.drawable.bullseye);
            messageString="Perfect Score";
            menu.setText("Go back to menu");
            next.setText("Play next round");


        }else if(finalScore>7) {

            drawable =getResources().getDrawable(R.drawable.good);
            messageString="Good Work!";
            menu.setText("Go back to menu");
            next.setText("Play next round");
        } else {

            drawable =getResources().getDrawable(R.drawable.fail);
           messageString="Try again!";
            menu.setText("Go back to menu");
            next.setText("Take quiz again");
        }

        image.setImageDrawable(drawable);
        message.setText(messageString);
        score.setText(""+finalScore);






    }
}
