package com.example.colleger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class SemesterAdapter extends ArrayAdapter<Semester> {
    public SemesterAdapter(Context context, List<Semester> semesters) {
        super(context, 0, semesters);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Semester semester = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_semester, parent, false);
        }

        TextView name = convertView.findViewById(R.id.semesterNameTextView);
        TextView ch = convertView.findViewById(R.id.chTextView);
        TextView gpa = convertView.findViewById(R.id.GPATextView);
        if (semester != null) {
            name.setText("Semester: " +semester.getSemesterName());
            ch.setText("credit hours: " +semester.getTotalCreditHours());
            gpa.setText(String.valueOf("GPA: " +semester.getSemesterGPA()));
        }

        return convertView;
    }
}