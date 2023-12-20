package com.example.colleger.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.colleger.*;
import com.example.colleger.CourseListActivity;

import android.content.Intent;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.List;
public class HomeFragment extends Fragment {

    private List<Semester> semesterList = new ArrayList<>();
    private SemesterAdapter semesterAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Button addSemesterButton = view.findViewById(R.id.addSemesterButton);
        addSemesterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSemester();
            }
        });

        ListView semesterListView = view.findViewById(R.id.semesterListView);
        semesterAdapter = new SemesterAdapter(requireContext(), semesterList);
        semesterListView.setAdapter(semesterAdapter);

        semesterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                openCourseListActivity(position);
            }
        });

        return view;
    }

    private void addSemester() {
        // For simplicity, let's assume the semester name is hard-coded here
        String semesterName = "Semester " + (semesterList.size() + 1);

        Semester semester = new Semester(semesterName);
        semesterList.add(semester);
        semesterAdapter.notifyDataSetChanged();
    }

    private void removeSemester(int position) {
        semesterList.remove(position);
        semesterAdapter.notifyDataSetChanged();
    }

    private void openCourseListActivity(int position) {
        Semester selectedSemester = semesterList.get(position);

        Intent intent = new Intent(requireContext(), CourseListActivity.class);
        intent.putExtra("semesterName", selectedSemester.getSemesterName());
        intent.putExtra("semesterGPA", selectedSemester.getSemesterGPA());
        startActivity(intent);
    }
}