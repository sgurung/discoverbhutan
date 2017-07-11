package bt.gov.dit.discoverbhutan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FetchQuestion extends AppCompatActivity {

    JSONParser jsonParser = new JSONParser();
    ProgressDialog progressDialog;
    private final String TAG_SUCCESS = "success";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_question);

        new DownloadQuestion("0").execute();




    }

    class DownloadQuestion extends AsyncTask<String, String, Integer> {

        private String stage;
        private Integer success;
        DownloadQuestion(String stage){

            this.stage=stage;

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(FetchQuestion.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Fetching questionaire");
            progressDialog.show();

        }

        @Override
        protected Integer doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("stageid", stage));


            JSONObject json = jsonParser.makeHttpRequest(Constants.URL_GETQUESTION,"POST", params);

            Log.d("Create Response", json.toString());

            try {
                success = json.getInt(TAG_SUCCESS);

                if (success==1) {

                    SharedPreferences pf = getSharedPreferences("QuestionaireStage"+stage,MODE_PRIVATE);
                    SharedPreferences.Editor editor = pf.edit();
                    editor.putString("jsondata", json.toString());
                    editor.commit();




                } else {

                    Log.d("Failed","FAiled+success="+success);

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
                Toast.makeText(getApplicationContext(), "Successfully fetched question\n",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(i);
                finish();
            } else if(result==2){

            } else {
                Toast.makeText(getApplicationContext(), "Error fetching question. Please check your internet or try again later.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
