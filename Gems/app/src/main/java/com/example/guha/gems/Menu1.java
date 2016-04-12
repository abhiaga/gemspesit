package com.example.guha.gems;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

public class Menu1 extends AppCompatActivity {

    TableLayout table_layout;

    Button b,b2,b3,b4,b5,b6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        table_layout = (TableLayout) findViewById(R.id.tableLayout1);
        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int rows = 6, cols = 2;

                table_layout.removeAllViews();
                BuildTable(rows, cols);


            }
        });
        b2 = (Button) findViewById(R.id.button);
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int rows = 6, cols = 2;

                table_layout.removeAllViews();
                BuildTable(rows, cols);


            }
        });
        b3 = (Button) findViewById(R.id.button2);
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int rows = 6, cols = 2;

                table_layout.removeAllViews();
                BuildTable(rows, cols);


            }
        });
        b4 = (Button) findViewById(R.id.button3);
        b4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int rows = 6, cols = 2;

                table_layout.removeAllViews();
                BuildTable(rows, cols);


            }
        });
        b5 = (Button) findViewById(R.id.button4);
        b5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int rows = 6, cols = 2;

                table_layout.removeAllViews();
                BuildTable(rows, cols);


            }
        });
        b6 = (Button) findViewById(R.id.button5);
        b6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int rows = 6, cols = 2;

                table_layout.removeAllViews();
                BuildTable(rows, cols);


            }



        });

    }

    private void BuildTable(int rows, int cols) {
    String[] a={"-------","6-CSE-A","6-CSE-B","6-CSE-C","3-CSE-A","3-CSE-B","3-CSE-C","3-CSE-D","3-CSE-E","3-CSE-F","3-CSE-G"};
    String[] t={"8:15-9:15","9:15-10:15","10:45-11:45","11:45-12:45",   "1:30-2:30","2:30-3:30"};
        // outer for loop
        int c=1;
        Random r=new Random();
        int random = r.nextInt(7);
        int second=1;
        int p=0;
        for (int i = 1; i <= rows; i++) {

            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));

// inner for loop
            for (int j = 1; j <= cols; j++) {

                TextView tv = new TextView(this);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                LinearLayout.LayoutParams tvlp = (LinearLayout.LayoutParams) tv.getLayoutParams();
                tvlp.bottomMargin=25;
                tvlp.rightMargin=10;
//tv.setBackgroundResource(R.drawable.cell_shape);
                tv.setPadding(5, 5, 40, 40);
                random = r.nextInt(7);
                //tv.setText("Class             Time");
                //row.addView(tv);
                if(second==0 && random==0 )
                {
                    second=1;
                    random=1;
                }
                tv.setGravity(Gravity.CENTER);
                /*if(p==0)
                {
                    if(c%2==1) {
                        tv.setText("Class");
                    }
                    else
                    {tv.setText("Time");
                        p=1;
                        c--;c--;
                    }
                        j--;


                }
                else {*/
                    if (c % 2 == 1)
                        tv.setText(a[(random)]);
                    else {
                        tv.setText(t[(i - 1)]);
                    }
               // }
               // Drawable dr = new BitmapDrawable("@drawable/draw");
                //table_layout.setBackgroundDrawable(dr);
                tv.setBackgroundColor(Color.BLUE);
                //ow.setBackgroundDrawable("@drawable/draw");
                tv.setMinHeight(120);
                tv.setTextColor(Color.BLUE);
                tv.setBackgroundResource((R.drawable.draw));
                tv.setTextAppearance(this, android.R.style.TextAppearance_Medium);
                //tv.setBackgroundDrawable(draw);
                c++;
               // tv.setOnClickListener(new listener()));
                tv.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent a=new Intent(Menu1.this,layout.class);
                        startActivity(a);
                    }
                });
                //tv.setLayoutParams(lprams);
               // dynamicview.addView(btn);
                row.addView(tv);
                if(i==6 && j==2)
                    p=0;
            }

            /* OnClickListener btnclick = new OnClickListener() {

        @Override
        public void onClick(View view) {

            switch(view.getId()){
              case 1:
               //first button click
              break;
               //Second button click
              case 2:
              break;
              case 3:
               //third button click
              break;
              case 4:
               //fourth button click
              break;
            .
            .
            .
             default:
              break;
              }

        }
    };*/

            table_layout.addView(row);

        }
    }
}
