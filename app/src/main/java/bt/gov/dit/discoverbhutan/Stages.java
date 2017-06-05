package bt.gov.dit.discoverbhutan;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Stages extends AppCompatActivity {

    private Button buttonStageOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_stages);

        //overridePendingTransition(R.animator.right_to_left, R.animator.left_to_right);

        buttonStageOne = (Button)findViewById(R.id.play_stageone);
        buttonStageOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), StageOne.class);
                startActivity(intent);

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        //overridePendingTransition(R.animator.right_to_left, R.animator.left_to_right);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
