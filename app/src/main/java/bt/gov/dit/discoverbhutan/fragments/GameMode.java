package bt.gov.dit.discoverbhutan.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import bt.gov.dit.discoverbhutan.MainActivity;
import bt.gov.dit.discoverbhutan.R;
import bt.gov.dit.discoverbhutan.Stages;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameMode extends Fragment {


    Button playLocation;
    public GameMode() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_game_mode, container, false);
        playLocation = (Button)view.findViewById(R.id.play_location);

        playLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(getActivity(), Stages.class));
                
                getActivity().overridePendingTransition(R.animator.left_to_right, R.animator.right_to_left);


            }
        });

        return view;
    }

}
