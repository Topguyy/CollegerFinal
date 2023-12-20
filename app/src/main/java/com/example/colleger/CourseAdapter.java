package com.example.colleger;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course> {

    private List<Course> courseList;

    public CourseAdapter(Context context, List<Course> courseList) {
        super(context, 0, courseList);
        this.courseList = courseList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Course course = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_course, parent, false);
        }

        // Lookup view for data population
        TextView courseNameTextView = convertView.findViewById(R.id.courseNameTextView);
        TextView professorNameTextView = convertView.findViewById(R.id.professorNameTextView);
        TextView creditHoursTextView = convertView.findViewById(R.id.creditHoursTextView);
        TextView gradeTextView = convertView.findViewById(R.id.gradeTextView);


        // Populate the data into the template view using the data object
        if (course != null) {
            courseNameTextView.setText("Course: " + course.getCourseName());
            professorNameTextView.setText("Professor: " + course.getProfessorName());
            creditHoursTextView.setText("Credit Hours: " + course.getCreditHours());
            gradeTextView.setText("Grade: " + course.getGrade());


        }

        // Return the completed view to render on screen
        return convertView;
    }
}
