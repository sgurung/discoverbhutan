package bt.gov.dit.discoverbhutan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import bt.gov.dit.discoverbhutan.Constants.*;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private static final String TAG_SUCCESS = "success";
    private static final int REQUEST_LOGIN = 0;
    private ProgressDialog progressDialog;
    JSONParser jsonParser = new JSONParser();


    @BindView(R.id.input_name) EditText _nameText;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_signup) Button _signupButton;
    @BindView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, REQUEST_LOGIN);
                finish();
                overridePendingTransition(R.animator.left_to_right, R.animator.right_to_left);
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        //_signupButton.setEnabled(false);





        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        new RegisterPlayer(name,email,password).execute();
        // TODO: Implement your own signup logic here.

//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onSignupSuccess or onSignupFailed
//                        // depending on success
//                        onSignupSuccess();
//                        // onSignupFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    class RegisterPlayer extends AsyncTask<String, String, Integer> {

        private String name,email, password;
        private Integer success;
        RegisterPlayer(String name,String email, String password){

            this.email=email;
            this.password=password;
            this.name=name;

        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(SignupActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating and registering player...");
            progressDialog.show();

        }

        @Override
        protected Integer doInBackground(String... args) {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("name",name));

            JSONObject json = jsonParser.makeHttpRequest(Constants.URL_SIGNUP_PLAYER,
                    "POST", params);

            Log.d("Create Response", json.toString());

            try {
                success = json.getInt(TAG_SUCCESS);

                if (success==1) {
                    // successfully created product

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);

                    // closing this screen
                    finish();
                } else {

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
                Toast.makeText(getApplicationContext(), "Player registration successful",Toast.LENGTH_SHORT).show();
            } else if(result==2){
                Toast.makeText(getApplicationContext(), "User already exists with the email. Sign in instead!",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Error registering player",Toast.LENGTH_SHORT).show();
            }
        }
    }
}