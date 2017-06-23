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
    private ViewGroup mRootView;
    private View mRedBox, mGreenBox, mBlueBox, mBlackBox;
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

               overridePendingTransition(R.animator.left_to_right,R.animator.right_to_left);


            }
        });
//        mRootView = (ViewGroup) findViewById(R.id.activity_main);
//        mRootView.setOnClickListener(this);
//
//        mRedBox = findViewById(R.id.red);
//        mGreenBox = findViewById(R.id.yellow);
//        mBlueBox = findViewById(R.id.blue);
//        mBlackBox = findViewById(R.id.black);
    }




    /* @Override
    public void onClick(View v) {
        //TransitionManager.beginDelayedTransition(mRootView, new Slide());
//        toggleVisibility(mRedBox, mGreenBox, mBlueBox, mBlackBox);




         *//*   fragment.setEnterTransition(new Slide());
            fragment.setExitTransition(new Slide());
            getFragmentManager().beginTransaction().replace(R.id.activity_main,fragment,TAG);*//*

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.animator.enter_anim, R.animator.exit_anim);
            ft.replace(R.id.activity_main, new GetUser());
            ft.commit();


    }
*/
//    private static void toggleVisibility(View... views) {
//        for (View view : views) {
//            boolean isVisible = view.getVisibility() == View.VISIBLE;
//            view.setVisibility(isVisible ? View.INVISIBLE : View.VISIBLE);
//        }
//    }

}
