package com.example.adm.mobquestnew;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by ADM on 21.10.2016.
 */

public final class other {

    public class marker_map
    {

            private String title;
            private String body;
            private int type;

            public marker_map (String _title, String _body, int _type){
                setBody(_body);
                setTitle(_title);
                setType(_type);
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }



        }

}
