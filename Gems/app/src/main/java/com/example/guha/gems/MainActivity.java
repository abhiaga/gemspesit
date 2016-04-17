package com.example.guha.gems;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.cloudboost.CloudException;
import io.cloudboost.CloudUser;
import io.cloudboost.CloudUserCallback;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText ed1,ed2;

    TextView tx1;
    int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

       // CloudApp.init(myAppId, myKey);
        b1 = (Button) findViewById(R.id.blogin);
        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);

        //b2=(Button)findViewById(R.id.button1);
        tx1 = (TextView) findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // String userN=obj.getString("username");
                // String pass=obj.getString("password");
                //System.out.println(userN+" "+pass+" "+name);
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);


                CloudUser user = new CloudUser();
                try {
                    user.set("username", ed1.getText().toString());
                } catch (CloudException e) {
                    e.printStackTrace();
                }
                try {
                    user.set("password", ed2.getText().toString());
                } catch (CloudException e) {
                    e.printStackTrace();
                }
                CloudUserCallback callbackObj = new CloudUserCallback() {
                    @Override
                    public void done(CloudUser obj, CloudException e) {
                        System.out.println("hi");
//
                    }
                };

                try {
                    user.logIn(callbackObj);
                } catch (CloudException e) {
                    e.printStackTrace();
                }

             //  System.out.println(CloudUser.getcurrentUser().getEmail());

                if (CloudUser.getcurrentUser()!=null) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();

                    Intent a = new Intent(MainActivity.this, Menu1.class);
                    startActivity(a);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials", Toast.LENGTH_SHORT).show();

                    //tx1.setVisibility(View.VISIBLE);
                    //tx1.setBackgroundColor(Color.RED);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        b1.setEnabled(false);
                    }
                }
            }
        });
    }

        public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}