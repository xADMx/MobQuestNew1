package com.example.adm.mobquestnew;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_reg_fragment);

        getSupportActionBar().hide();

        Fragment fragment = Fragment.instantiate(this, LoginFragmentsClass.class.getName());
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.cont_login, fragment);
        ft.commit();

    }

    public void new_fragment(int id)
    {
        Fragment fragment = new Fragment();

        if (id == 0) {
            fragment = Fragment.instantiate(this, LoginFragmentsClass.class.getName());
        } else {
            fragment = Fragment.instantiate(this, RegFragmentsClass.class.getName());
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.cont_login, fragment);
        ft.commit();
    }
}
