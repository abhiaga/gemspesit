package com.example.guha.gems;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class layout2 extends AppCompatActivity {
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout2);
        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (ed1.getText().toString().equals("admin") &&

                        ed2.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();

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
                */
              //  Intent a=new Intent(MainActivity.this,Menu1.class);
                //startActivity(a);
                //AlertDialog.Builder but = new AlertDialog.Builder(this);
                Toast.makeText(getApplicationContext(), "Successfully submitted", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
