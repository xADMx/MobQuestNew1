package com.example.adm.mobquestnew;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by ADM on 22.10.2016.
 */

public class LoginFragmentsClass extends Fragment {
    SharedPreferences sPref;
    View v;
    LoginActivity ActivLogin;
    TextView _signupLink;
    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    CheckBox _check_rec_login;
    Editor ed;
    Http HttpHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_login, container, false);
        //Bundle bundle = getArguments();
        // Gets the MapView from the XML layout and creates it
        ActivLogin = ((LoginActivity)getActivity());

        sPref = ActivLogin.getPreferences(ActivLogin.MODE_PRIVATE);
        ed = sPref.edit();

        _signupLink = (TextView) v.findViewById(R.id.link_signup_login);
        _emailText = (EditText) v.findViewById(R.id.input_email_login);
        _passwordText = (EditText) v.findViewById(R.id.input_password_login);
        _loginButton = (Button) v.findViewById(R.id.btn_login);
        _check_rec_login = (CheckBox) v.findViewById(R.id.check_rec_login);

        if (sPref.getBoolean("check_login", false))
        {
            _emailText.setText(sPref.getString("email", ""));

            if(sPref.getString("pass", "") != "")
                _passwordText.setText(getResources().getString(R.string.login__pass_hide));

            _check_rec_login.setChecked(true);
        }

        _signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                ActivLogin.new_fragment(1);
            }
        });

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Signup activity
                if (_check_rec_login.isChecked()){
                    if (sPref.getString("email", "") != _emailText.getText().toString())
                        ed.putString("email", _emailText.getText().toString());

                    if (sPref.getString("pass", "") != SHA256(_passwordText.getText().toString()) ||
                            _passwordText.getText().toString() != getResources().getString(R.string.login__pass_hide))
                        ed.putString("pass", SHA256(_passwordText.getText().toString()));
                    ed.putBoolean("check_login", true);
                } else {
                    ed.putBoolean("check_login", false);
                }
                new DepartmentRequest().execute();

            }
        });
        return v;
    }

    public static String SHA256 (String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(text.getBytes());
            byte[] digest = md.digest();

            return Base64.encodeToString(digest, Base64.DEFAULT);
        } catch (NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
        return "";
    }

    private class DepartmentRequest extends AsyncTask<Integer, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(Integer... Distance) {
            RequestBody formBody = new FormBody.Builder()
                    .add("distance", Distance[0].toString())
                    .build();

            try {
                HttpHelper.post("http://62.109.0.46/index.php/api/update_app", formBody);
                return new JSONObject(HttpHelper.response.body().string());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            Intent intent = new Intent(ActivLogin, MainActivity.class);
            startActivity(intent);
        }
    }
}