package com.example.colleger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    private List<Course> courseList;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        // Initialize the course list and adapter
        Intent intent = new Intent(this, test.class);

        courseList = new ArrayList<>();
        courseAdapter = new CourseAdapter(this, courseList);

        // Get the semester name from the intent
        String semesterName = getIntent().getStringExtra("semesterName");
        double semetergpa = getIntent().getDoubleExtra("semesterGPA",0.0);

        // Set the semester title
        TextView semesterTitleTextView = findViewById(R.id.semesterTitleTextView);
        semesterTitleTextView.setText(semesterName);

        TextView semesterGPA = findViewById(R.id.semesterGPA);
        semesterGPA.setText("GPA: " + semetergpa);

        // Set up the ListView and the CourseAdapter
        ListView courseListView = findViewById(R.id.courseListView);
        courseListView.setAdapter(courseAdapter);

        // Button to add a new course
        Button addCourseButton = findViewById(R.id.addCourseButton);
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCourse();
            }
        });

        // Button to remove the semester (add your logic here)
        Button back = findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starthome();
            }
        });



        courseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Course selectedCourse = courseList.get(position);

                startActivity(intent);
            }
        });


    }

    private void startCourseDetailsActivity() {
        // Start the CourseDetailsActivity to add a new course
        Intent intent = new Intent(this, CourseDetailsActivity.class);
        startActivity(intent); // Use a requestCode (e.g., 1) to identify the result
    }
    private void starthome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityIfNeeded(intent,1); // Use a requestCode (e.g., 1) to identify the result
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the result is from the CourseDetailsActivity
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Retrieve the course details from the result
            String courseName = data.getStringExtra("courseName");
            int creditHours = data.getIntExtra("creditHours", 0);
            String professorName = data.getStringExtra("professorName");
            String grade = data.getStringExtra("grade");
//            String lectureDate = data.getStringExtra("lectureDate");
//            String lectureTime = data.getStringExtra("lectureTime");
//            String sectionDate = data.getStringExtra("sectionDate");
//            String sectionTime = data.getStringExtra("sectionTime");

            // Create a new Course object with the retrieved details
            Course newCourse = new Course(courseName, creditHours, professorName, grade);

            // Add the new course to the course list
            courseList.add(newCourse);

            // Notify the adapter that the data set has changed
            courseAdapter.notifyDataSetChanged();
        }
    }
    private void addCourse() {
        // For simplicity, let's assume the semester name is hard-coded here
        String Name = "Course " + (courseList.size() + 1);

        Course course = new Course(Name);
        courseList.add(course);
        courseAdapter.notifyDataSetChanged();
    }
}




//
//import android.os.Bundle;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.colleger.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CourseListActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_course_list);
//
//
//        TextView semesterTitleTextView = findViewById(R.id.semesterTitleTextView);
//        ListView courseListView = findViewById(R.id.courseListView);
//
//        // Get the semester name from the intent
//        String semesterName = getIntent().getStringExtra("semesterName");
//        semesterTitleTextView.setText("Semester: " + semesterName);
//
//        // In this example, we'll create a dummy list of courses
//        List<String> courseList = new ArrayList<>();
//
//
//        // Create an ArrayAdapter to display the courses in the ListView
//        ArrayAdapter<String> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, courseList);
//        courseListView.setAdapter(courseAdapter);
//
//        // You can add more functionality here, such as adding a course or navigating to a course details activity
//    }
//}