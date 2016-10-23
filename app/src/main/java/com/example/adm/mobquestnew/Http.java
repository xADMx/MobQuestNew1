package com.example.adm.mobquestnew;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;

import java.io.IOException;

import okhttp3.*;

/**
 * Created by ADM on 07.08.2016.
 */

public class Http {
    public OkHttpClient client = new OkHttpClient();
    private OkHttpClient.Builder builder = new OkHttpClient.Builder();
    public Response response;
    Context cont;

    public Http(Context _cont){
        cont = _cont;
        client = new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor(cont))
                .addInterceptor(new ReceivedCookiesInterceptor(cont))
                .build();

        builder.addInterceptor(new AddCookiesInterceptor(cont));
        builder.addInterceptor(new ReceivedCookiesInterceptor(cont));
        client = builder.build();
    }

    public void post(String url, RequestBody post) throws IOException {
        Request request;

        if (post != null) {
            request = new Request.Builder()
                    .url(url)
                    .post(post)
                    .build();
        } else {
            request = new Request.Builder()
                    .url(url)
                    .build();
        }

        response = client.newCall(request).execute();

        if (response.code() == 401) {
            if (logintoservice() == 200) {
                post(url,post);
            }
        }
    }

    public Integer logintoservice() throws IOException {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(cont);

        TelephonyManager telephonyManager = (TelephonyManager) cont.getSystemService(Context.TELEPHONY_SERVICE);

        RequestBody formBody = new FormBody.Builder()
                .add("email", sharedPreferences.getString("login", ""))
                .add("iddev", telephonyManager.getDeviceId())
                .build();

        Request request = new Request.Builder()
                .url("http://62.109.0.46/index.php/api/auth")
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        return response.code();
    }

}