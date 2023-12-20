package com.example.colleger;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "college_db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    private static final String TABLE_SEMESTERS = "semesters";
    private static final String TABLE_COURSES = "courses";

    // Common column names
    private static final String KEY_ID = "id";

    // Semesters table column names
    static final String KEY_SEMESTER_NAME = "semester_name";
    static final String KEY_SEMESTER_GPA = "semester_gpa";

    // Courses table column names
    static final String KEY_COURSE_NAME = "course_name";
    static final String KEY_CREDIT_HOURS = "credit_hours";
    static final String KEY_GRADE = "grade";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        String CREATE_TABLE_SEMESTERS = "CREATE TABLE " + TABLE_SEMESTERS +
                "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_SEMESTER_NAME + " TEXT," +
                KEY_SEMESTER_GPA + " REAL" +
                ")";
        db.execSQL(CREATE_TABLE_SEMESTERS);

        String CREATE_TABLE_COURSES = "CREATE TABLE " + TABLE_COURSES +
                "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_COURSE_NAME + " TEXT," +
                KEY_CREDIT_HOURS + " INTEGER," +
                KEY_GRADE + " TEXT," +
                KEY_SEMESTER_NAME + " TEXT," +
                "FOREIGN KEY(" + KEY_SEMESTER_NAME + ") REFERENCES " + TABLE_SEMESTERS + "(" + KEY_SEMESTER_NAME + ")" +
                ")";
        db.execSQL(CREATE_TABLE_COURSES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if they exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEMESTERS);

        // Create new tables
        onCreate(db);
    }

    // Insert a new semester
    long insertSemester(Semester semester) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SEMESTER_NAME, semester.getSemesterName());
        values.put(KEY_SEMESTER_GPA, semester.getSemesterGPA());

        long id = db.insert(TABLE_SEMESTERS, null, values);
        db.close();
        return id;
    }

    // Insert a new course
    long insertCourse(Course course, String semesterName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COURSE_NAME, course.getCourseName());
        values.put(KEY_CREDIT_HOURS, course.getCreditHours());
        values.put(KEY_GRADE, course.getGrade());
        values.put(KEY_SEMESTER_NAME, semesterName);

        long id = db.insert(TABLE_COURSES, null, values);
        db.close();
        return id;
    }

    // Get all semesters
    List<Semester> getAllSemesters() {
        List<Semester> semesters = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SEMESTERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(KEY_SEMESTER_NAME));
                double gpa = cursor.getDouble(cursor.getColumnIndexOrThrow(KEY_SEMESTER_GPA));

                Semester semester = new Semester(name);
                semester.setSemesterGPA(gpa);
                semesters.add(semester);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return semesters;
    }

    // Get all courses for a given semester

    // Calculate GPA for a given semester


    double calculateCGPA() {
        List<Semester> semesters = getAllSemesters();

        double totalCreditHours = 0.0;
        double totalGradePoints = 0.0;

        for (Semester semester : semesters) {
            double semesterGPA =semester.getSemesterGPA();
            int semesterCreditHours = semester.getTotalCreditHours();

            totalCreditHours += semesterCreditHours;
            totalGradePoints += (semesterCreditHours * semesterGPA);
        }

        if (totalCreditHours > 0) {
            return totalGradePoints / totalCreditHours;
        } else {
            return 0.0; // Avoid division by zero
        }
    }
}
