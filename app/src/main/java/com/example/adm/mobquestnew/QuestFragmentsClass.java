package com.example.adm.mobquestnew;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ADM on 20.10.2016.
 */

public class QuestFragmentsClass extends Fragment {
    Http HttpHelper;
    View v;
    MainActivity ActivMain;
    Button BtnDistance;
    DialogInterface.OnClickListener myClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.quest_list, container, false);
        //Bundle bundle = getArguments();
        // Gets the MapView from the XML layout and creates it
        ActivMain = ((MainActivity)getActivity());
        ActivMain.getSupportActionBar().setTitle(R.string.toolbar_quest);

        BtnDistance = (Button) v.findViewById(R.id.distancekm);

        myClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            };
        };

        View.OnClickListener BtnDistance = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
                adb.setTitle(R.string.dialog_distance_title);
                adb.setMessage(R.string.dialog_distance_message);
                adb.setSingleChoiceItems(new String[] {"5 км", "10 км", "20 км", "50 км", "100 км"}, -1, myClickListener);
                AlertDialog alert = adb.create();
                alert.show();

            }
        };

        new DepartmentRequest().execute();

        return v;
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
            if (jsonObject != null && jsonObject.length() > 0) {
                Gson gson = new Gson();

            }
        }
    }
}