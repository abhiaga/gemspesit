package com.example.guha.gemsstudent;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import io.cloudboost.CloudException;
import io.cloudboost.CloudIntegerCallback;
import io.cloudboost.CloudObject;
import io.cloudboost.CloudObjectArrayCallback;
import io.cloudboost.CloudQuery;

public class Attendance extends AppCompatActivity {
    io.cloudboost.json.JSONArray subs;
    String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ArrayList<String> list = new ArrayList<String>(100);
        final ArrayList<String> temp = new ArrayList<String>(100);
        CloudQuery query,query2;



        query = new CloudQuery("Students");
        try {
            query.setLimit(1);
           // query.orderByAsc("USN");
            query=query.equalTo("USN",MainActivity.usn);
            System.out.println(MainActivity.usn);
            query.find(new CloudObjectArrayCallback() {
                @Override
                public void done(CloudObject[] obj, CloudException e) {
                    for (CloudObject c:obj){

                            subs = (io.cloudboost.json.JSONArray) c.get("Subs");
                        for( int i=0;i<subs.length();i++){
                            try {
                                temp.add(subs.get(i).toString());
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }

                        System.out.println(c.get("Subs").toString());

                    }
                }
            });
        } catch (CloudException e) {
            e.printStackTrace();
        }
        query = new CloudQuery("Attendance");
        query2 = new CloudQuery("Attendance");
        for(final String s: temp){
            query=query.equalTo("SubjectCode",s);
            query=query.equalTo("USN",MainActivity.usn);
            query2=query2.equalTo("SubjectCode",s);
            query2=query2.equalTo("USN",MainActivity.usn);
            query2=query2.equalTo("Attendance",true);
            //System.out.println(MainActivity.usn);
            try {
                query2.count(new CloudIntegerCallback(){
                    @Override
                    public void done(Integer count,CloudException e) {
                        x=s+"     "+count.toString()+"/";
//
                    }
                });
            } catch (CloudException e) {
                e.printStackTrace();
            }

            try {
                query.count(new CloudIntegerCallback(){
                @Override
                public void done(Integer count,CloudException e) {
                    x=x+count.toString();
                    list.add(x);
//
                }
                });
            } catch (CloudException e) {
                e.printStackTrace();
            }


        }

        MyCustomAdapter adapter = new MyCustomAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.listView);
        lView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
