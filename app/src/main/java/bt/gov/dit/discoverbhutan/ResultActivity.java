package bt.gov.dit.discoverbhutan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ResultActivity extends AppCompatActivity {


    private TextView score;
    private TextView message;
    private Button next,menu;
    private ImageView image;
    private Drawable drawable;
    private int finalScore;
    private String messageString;
    private static final String TAG_PLAYERID = "playerid";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_STAGE = "stage";
    private static final String TAG_SCORE = "score";
    private static final String TAG_USERNAME = "username";
    private ProgressDialog progressDialog;
    JSONParser jsonParser = new JSONParser();
    String username;


    SharedPreferences userprofile;
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

        if(finalScore>7) {
            UpdateScore.execute(playerid,stage,finalScore);
            if(finalScore==10){
                drawable =getResources().getDrawable(R.drawable.bullseye);
                messageString="Perfect Score";
            } else {
                drawable =getResources().getDrawable(R.drawable.good);
                messageString="Good Work!";
            }
            menu.setText("Go back to Home screen");
            next.setText("Play next round");

            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Stages.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else {

            drawable =getResources().getDrawable(R.drawable.fail);
           messageString="Try again!";

            menu.setText("Go back to menu");
            next.setText("Take quiz again");
            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });


            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        image.setImageDrawable(drawable);
        message.setText(messageString);
        score.setText(""+finalScore);








    }

    class UpdateScore extends AsyncTask<String, String, Integer> {

        private String playerid, stage, score;
        private Integer success;
        UpdateScore(String playerid, String stage, String score){

            this.playerid=playerid;
            this.stage=stage;
            this.score=score;


        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ResultActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Updating score");
            progressDialog.show();

        }

        @Override
        protected Integer doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("playerid",playerid));
            params.add(new BasicNameValuePair("stage",stage));
            params.add(new BasicNameValuePair("score",score));

            JSONObject json = jsonParser.makeHttpRequest(Constants.URL_UPDATE_SCORE,
                    "POST", params);

            Log.d("Create Response", json.toString());

            try {
                success = json.getInt(TAG_SUCCESS);

                if (success==1) {
                    // successfully created product
                    username=json.getString(TAG_USERNAME);
                    userprofile = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
                    SharedPreferences.Editor editor = userprofile.edit();

                    editor.putInt("stagestatus",1);
                    playerid=json.getString(TAG_PLAYERID);
                    stage=json.getString(TAG_STAGE);
                    score=json.getString(TAG_SCORE);
                    editor.commit();
                    Intent i = new Intent(getApplicationContext(), Stages.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {
                    userprofile = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);
                    SharedPreferences.Editor editor = userprofile.edit();

                    editor.putInt("stagestatus",1);
                    editor.commit();
                    Log.d("Failed","FAiled+success="+success);
                    // failed to create product
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return success;

        }
        protected void onPostExecute(Integer result) {
            // dismiss the dialog once done
            progressDialog.dismiss();
            if(result==1){
                Toast.makeText(getApplicationContext(), "Successfully updated in the server\n"+"Welcome "+username,Toast.LENGTH_SHORT).show();
            } else if(result==2){

            } else {
                Toast.makeText(getApplicationContext(), "Error updating. Try again later",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
