package com.example.colleger;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class test extends AppCompatActivity {
    TextView text;
    Button button;
    EditText grade;
    EditText material ;
    Button sectiondatee , sectiontime ;
    Button button2;
    Button download ;
    Button  save;
    EditText CreditHours ;
    EditText ProfessorName ;
    EditText CourseName  ;
    EditText CourseGrade ;
    DownloadManager manager;
    Boolean isAllFabsVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        save =  findViewById(R.id.save_button);
        CreditHours = findViewById(R.id.credithours);
        grade = findViewById(R.id.grade);
        sectiondatee = findViewById(R.id.sectiondate);
        sectiontime = findViewById(R.id.sectiontime);
        ProfessorName = findViewById(R.id.editTextText2) ;
        // CourseGrade = findViewById(R.id.editTextText3) ;
        CourseName = findViewById(R.id.editTextText) ;
       /* fab3 = findViewById(R.id.imageButton7);
        fab2 = findViewById(R.id.imageButton8); */
        material = findViewById(R.id.editTextText4);
        text = findViewById(R.id.showtext);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button3);
        download = findViewById(R.id.button2);
        /*fab.setVisibility(View.INVISIBLE);
        fab2.setVisibility(View.GONE);
        fab3.setVisibility(View.GONE);
        isAllFabsVisible = false;*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeDialog();
            }
        });


        String fileurl = material.getText().toString();
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(fileurl);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                long reference = manager.enqueue(request);

            }
        });
        sectiontime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeDialog();
            }
        });
        sectiondatee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        Intent intent = new Intent(this,CourseListActivity.class);
        Toast saved = Toast.makeText(this, "Course Is Saved", Toast.LENGTH_LONG);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve values from UI elements


                String courseName = CourseName.getText().toString();
                int creditHours =  Integer.valueOf(CreditHours.getText().toString());
                String professorName = ProfessorName.getText().toString();
                String selectedGrade = CourseGrade.getText().toString();

                intent.putExtra("courseName", courseName);
                intent.putExtra("creditHours", creditHours);
                intent.putExtra("professorName", professorName);
                intent.putExtra("selectedGrade", selectedGrade);

                saved.show();
                startActivityIfNeeded(intent,1);
//                finish();

            }
        });







    }
    private void openDialog() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                text.setText(String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(dayOfMonth));
            }
        }, 2022, 0, 15);
        dialog.show();

    }
    private void openTimeDialog(){
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                text.setText(String.valueOf(hourOfDay)+":+"+String.valueOf(minute));
            }
        },11,43,false);
        timePickerDialog.show();
    }

}