package com.example.colleger;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        EditText courseNameEditText = findViewById(R.id.courseNameEditText);
        Spinner creditHoursSpinner = findViewById(R.id.creditHoursSpinner);
        EditText professorNameEditText = findViewById(R.id.professorNameEditText);
        Spinner gradeSpinner = findViewById(R.id.gradeSpinner);
        EditText lectureDateEditText = findViewById(R.id.lectureDateEditText);
        EditText lectureTimeEditText = findViewById(R.id.lectureTimeEditText);
        EditText sectionDateEditText = findViewById(R.id.sectionDateEditText);
        EditText sectionTimeEditText = findViewById(R.id.sectionTimeEditText);

        // Populate the credit hours spinner with an array of values
        ArrayAdapter<CharSequence> creditHoursAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.credit_hours_array,
                android.R.layout.simple_spinner_item
        );
        creditHoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        creditHoursSpinner.setAdapter(creditHoursAdapter);

        // Populate the grade spinner with an array of values
        ArrayAdapter<CharSequence> gradeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.grades_array,
                android.R.layout.simple_spinner_item
        );
        gradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(gradeAdapter);

        // Add listeners or set up other UI elements for Lecture Date, Lecture Time, Section Date, and Section Time

        Button saveCourseButton = findViewById(R.id.saveCourseButton);

        saveCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveCourse();
                startCourseListActivity();
            }
        });
    }

    private void saveCourse() {
         Intent intent = new Intent(this, CourseDetailsActivity.class);

        // Retrieve values from UI elements
        EditText courseNameEditText = findViewById(R.id.courseNameEditText);
        Spinner creditHoursSpinner = findViewById(R.id.creditHoursSpinner);
        EditText professorNameEditText = findViewById(R.id.professorNameEditText);
        Spinner gradeSpinner = findViewById(R.id.gradeSpinner);
        EditText lectureDateEditText = findViewById(R.id.lectureDateEditText);
        EditText lectureTimeEditText = findViewById(R.id.lectureTimeEditText);
        EditText sectionDateEditText = findViewById(R.id.sectionDateEditText);
        EditText sectionTimeEditText = findViewById(R.id.sectionTimeEditText);

        String courseName = courseNameEditText.getText().toString();
        int creditHours = Integer.parseInt(creditHoursSpinner.getSelectedItem().toString());
        String professorName = professorNameEditText.getText().toString();
        String selectedGrade = gradeSpinner.getSelectedItem().toString();
        String lectureDate = lectureDateEditText.getText().toString();
        String lectureTime = lectureTimeEditText.getText().toString();
        String sectionDate = sectionDateEditText.getText().toString();
        String sectionTime = sectionTimeEditText.getText().toString();

        intent.putExtra("courseName", courseName);
        intent.putExtra("creditHours", creditHours);
        intent.putExtra("professorName", professorName);
        intent.putExtra("selectedGrade", selectedGrade);
        intent.putExtra("lectureDate", lectureDate);
        intent.putExtra("lectureTime", lectureTime);
        intent.putExtra("sectionDate", sectionDate);
        intent.putExtra("sectionTime", sectionTime);

        // Optionally, you can navigate back to the previous activity or perform other actions
        startActivity(intent);
        finish();
    }
    private void startCourseListActivity() {
    }
}
