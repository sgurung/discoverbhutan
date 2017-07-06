package bt.gov.dit.discoverbhutan;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  
    private String TAG = MainActivity.class.getSimpleName();


    Button playLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content);

        playLocation = (Button)findViewById(R.id.play_location);

        playLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(MainActivity.this, Stages.class));
                finish();
               overridePendingTransition(R.animator.left_to_right,R.animator.right_to_left);


            }
        });

    }


}
