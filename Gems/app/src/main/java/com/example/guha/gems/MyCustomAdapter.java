package com.example.guha.gems;

import android.content.Context;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import io.cloudboost.CloudException;
import io.cloudboost.CloudObject;
import io.cloudboost.CloudObjectCallback;

/**
 * Created by Abhishek on 14-04-2016.
 */
public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;





    public MyCustomAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_layout2, null);
        }

        //Handle TextView and display string from your list
        final TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        final Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);



        deleteBtn.setOnClickListener(new View.OnClickListener(){
            String usn=null;


            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                System.out.println(listItemText.getText().toString());
                    usn=listItemText.getText().toString();
                    CloudObject obj = new CloudObject("Attendance");
                try {
                    System.out.println("hellllllooooooo1");
                    obj.set("USN", usn); //store string
                } catch (CloudException e) {
                    System.out.println("hellllllooooooo2");
                    e.printStackTrace();
                }
                try {
                    System.out.println("hellllllooooooo3");
                    obj.set("time", Menu1.time); // store number
                } catch (CloudException e) {
                    System.out.println("hellllllooooooo4");
                    e.printStackTrace();
                }
                try {
                    System.out.println("hellllllooooooo5");
                    obj.set("Attendance", true);
                    // store number
                } catch (CloudException e) {
                    System.out.println("hellllllooooooo6");
                    e.printStackTrace();
                }
                try {
                    obj.save(new CloudObjectCallback(){
                        @Override
                        public void done(CloudObject x, CloudException t) {
                            if(x != null){
                                System.out.println("hellllllooooooo7");
                            }
                            if(t != null){
                                System.out.println(t.toString());
                            }
                        }
                    });
                } catch (CloudException e) {
                    System.out.println("hellllllooooooo9");
                    e.printStackTrace();
                }

                list.remove(position);
                    notifyDataSetChanged();

            }
        });

        return view;
    }
}