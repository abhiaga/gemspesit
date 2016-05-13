package com.example.guha.gems;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import io.cloudboost.CloudException;
import io.cloudboost.CloudObject;
import io.cloudboost.CloudObjectCallback;

public class UpdateTT extends AppCompatActivity {
public static String path=null;
    public static int testno=0;
   public static  String subjectCode=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tt);
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Button b = (Button) findViewById(R.id.fselect);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
               showFileChooser();


            }
        });
        Button b1 = (Button) findViewById(R.id.d);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated meth showFileChooser();
                if((testno==0)||(subjectCode==null)){
                    Intent a = new Intent(UpdateTT.this, Exam.class);
                    startActivity(a);

                }



                Updater update=new Updater();
                if(path!=null) {
                    update.setInputFile(path);
                    System.out.println("wassup");
                    try {
                        update.read();
                        for(int j=0;j<update.data.length;j++){
                            for(int k=0;k<update.data[j].length;k++){
                                System.out.print(update.data[j][k]+"    ");

                            }
                            System.out.println();
                        }
                        CloudObject obj = new CloudObject("Marks");
                        System.out.println(update.data.length+"                  "+update.data[0].length);
                        for(int i = 1; i < update.data[0].length; ++i) {

                            try {
                                //System.out.println("hellllllooooooo1");
                                obj.set("SubjectCode",subjectCode);
                                obj.set("TestNo", testno);
                                obj.set("USN", update.data[0][i]); //store string
                                obj.set("Score",Integer.parseInt(update.data[1][i]));
                            } catch (CloudException e) {
                                System.out.println("hellllllooooooo2");
                                e.printStackTrace();
                            }


                            try {
                                obj.save(new CloudObjectCallback() {
                                    @Override
                                    public void done(CloudObject x, CloudException t) {
                                        if (x != null) {
                                            System.out.println("hellllllooooooo7");
                                        }
                                        if (t != null) {
                                            System.out.println(t.toString());
                                        }
                                    }
                                });
                            } catch (CloudException e) {
                                System.out.println("hellllllooooooo9");
                                e.printStackTrace();
                            }
                        }
                        Toast.makeText(getBaseContext(), "Done!",Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // Toast.makeText(getBaseContext(), path,Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getBaseContext(), "Choose a file please",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"), 1);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    File myFile = new File(uri.toString());
                    System.out.println(uri.toString());
                    path=uri.getPath();
                    System.out.println(path);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



    }

