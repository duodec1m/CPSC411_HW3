package com.example.cpsc411hw2;


import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;

public class StudentDB extends Activity {

    File dbFile;
    private static SQLiteDatabase mSQLiteDatabase;

    public StudentDB(Context c){
        dbFile = c.getDatabasePath("myDB.db");
        mSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(dbFile,null);

        String sql = "CREATE TABLE IF NOT EXISTS STUDENT (FirstName Text, LastName Text, CWID Text)";
        mSQLiteDatabase.execSQL(sql);
        sql = "CREATE TABLE IF NOT EXISTS COURSES(CWID Text, Course Text, Grade Text)";
        mSQLiteDatabase.execSQL(sql);
    }

    public static ArrayList<Student> getPersonList(){
        ArrayList<Student> tempList = new ArrayList<>();
        Student tempStudent;
        ArrayList<CourseEnrollment> tempCourses = new ArrayList<>();
        Cursor cursor = mSQLiteDatabase.query("STUDENT", null, null, null, null, null, null);
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                tempStudent = new Student(cursor.getString(0),cursor.getString(1),cursor.getString(2));
                Cursor cursor2 = mSQLiteDatabase.query("COURSES",null,"CWID=?",new String[]{cursor.getString(2)},null,null,null);
                if(cursor2.getCount() > 0){
                    tempCourses = new ArrayList<>();
                    while(cursor2.moveToNext()) {
                        tempCourses.add(new CourseEnrollment(cursor2.getString(1),cursor2.getString(2)));
                    }
                }
                tempStudent.setcList(tempCourses);
                tempList.add(tempStudent);
            }
        }
        return tempList;
    }

    public static void addPersonList(ArrayList<Student> personList){
        for(Student person:personList){
            mSQLiteDatabase.execSQL("INSERT INTO STUDENT VALUES (?, ?, ?)", new String[]{person.getFirstName(), person.getLastName(), person.getCWID()});

            for(CourseEnrollment course:person.getcList()){
                mSQLiteDatabase.execSQL("INSERT INTO COURSES VALUES (?, ?, ?)", new String[]{person.getCWID(), course.getCourseID(), course.getGrade()});
            }
        }
    }
}
