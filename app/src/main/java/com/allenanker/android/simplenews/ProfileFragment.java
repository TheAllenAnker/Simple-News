package com.allenanker.android.simplenews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private EditText editText1;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private Button profilebtn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_layout, container, false);
        editText1 = view.findViewById(R.id.edt1);
        editText2 = view.findViewById(R.id.edt2);
        editText3 = view.findViewById(R.id.edt3);
        editText4 = view.findViewById(R.id.edt4);
        editText5 = view.findViewById(R.id.edt5);
        profilebtn = view.findViewById(R.id.minebutton);
        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

}
