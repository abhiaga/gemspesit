package com.example.guha.gems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import io.cloudboost.CloudException;
import io.cloudboost.CloudObject;
import io.cloudboost.CloudObjectArrayCallback;
import io.cloudboost.CloudObjectCallback;
import io.cloudboost.CloudQuery;

public class layout2 extends Activity {
public static String subcode=null;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            CloudQuery query=new CloudQuery("Subject");
            try {
                query.setLimit(1);
                query=query.equalTo("Section",Menu1.section);
                query=query.equalTo("Teacher",MainActivity.TName);
                query.find(new CloudObjectArrayCallback() {
                    @Override
                    public void done(CloudObject[] obj, CloudException e) {
                        for(CloudObject c:obj) {
                            subcode = c.getString("SubjectCode");
                        }
                    }
                });
            } catch (CloudException e) {
                e.printStackTrace();
            }

            final ArrayList<String> list = new ArrayList<String>(100);
            setContentView(R.layout.activity_layout_1a);


             query = new CloudQuery("Students");
            try {
                query.setLimit(100);
                query.equalTo("Section", Menu1.section);
                query.orderByAsc("USN");
                query.find(new CloudObjectArrayCallback() {
                    @Override
                    public void done(CloudObject[] obj, CloudException e) {
                        for (CloudObject c:obj){
                            list.add(c.get("USN").toString());
                    }
                }
            });
            } catch (CloudException e) {
                e.printStackTrace();
            }


            //list.add("");

            //instantiate custom adapter
            MyCustomAdapter adapter = new MyCustomAdapter(list, this);

            //handle listview and assign adapter
            ListView lView = (ListView)findViewById(R.id.listView);
            lView.setAdapter(adapter);
            final Button Submit = (Button)findViewById(R.id.submit);
            System.out.println(Submit);
            Submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    for (String u : list) {
                        CloudObject obj = new CloudObject("Attendance");
                        try {
                            obj.set("SubjectCode",subcode);
                            obj.set("Date",Menu1.date);
                            obj.set("USN", u); //store string
                        } catch (CloudException e) {
                            // System.out.println("hellllllooooooo2");
                            e.printStackTrace();
                        }
                        try {
                            //System.out.println("hellllllooooooo3");
                            obj.set("time", Menu1.time); // store number
                        } catch (CloudException e) {
                            //System.out.println("hellllllooooooo4");
                            e.printStackTrace();
                        }
                        try {
                            //System.out.println("hellllllooooooo5");
                            obj.set("Attendance", false);
                            // store number
                        } catch (CloudException e) {
                            // System.out.println("hellllllooooooo6");
                            e.printStackTrace();
                        }
                        try {
                            obj.save(new CloudObjectCallback() {
                                @Override
                                public void done(CloudObject x, CloudException t) {
                                    if (x != null) {
                                        System.out.println("hellllllooooooo11");
                                    }
                                    if (t != null) {
                                        System.out.println(t.toString());
                                    }
                                }
                            });
                        } catch (CloudException e) {
                            //System.out.println("hellllllooooooo9");
                            e.printStackTrace();
                        }
                        Intent a = new Intent(layout2.this, Menu1.class);
                        startActivity(a);

                    }

                }

            });

        }




}


