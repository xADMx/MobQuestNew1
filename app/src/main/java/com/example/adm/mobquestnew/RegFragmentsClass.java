package com.example.adm.mobquestnew;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegFragmentsClass extends Fragment {

    View v;
    LoginActivity ActivLogin;
    EditText _nameText;
    EditText _emailText;
    EditText _passwordText;
    Button _regButton;
    TextView _link_login_reg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_reg, container, false);
        //Bundle bundle = getArguments();
        // Gets the MapView from the XML layout and creates it

        ActivLogin = ((LoginActivity)getActivity());

        _nameText = (EditText) v.findViewById(R.id.input_name_reg);
        _emailText = (EditText) v.findViewById(R.id.input_email_reg);
        _passwordText = (EditText) v.findViewById(R.id.input_password_reg);
        _regButton = (Button) v.findViewById(R.id.btn_signup);
        _link_login_reg = (TextView) v.findViewById(R.id.link_login_reg);

        _link_login_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                ActivLogin.new_fragment(0);
            }
        });

        return v;
    }
}