package com.example.cpsc411hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Add extends AppCompatActivity {

    StudentDB sDB;

    EditText firstName;
    EditText lastName;
    EditText cwid;
    EditText courseID;
    EditText grade;
    List<String[]> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sDB = new StudentDB(this);

        setContentView(R.layout.activity_add);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myToolbar.setTitle("");

        firstName = findViewById(R.id.set_first_name);
        lastName = findViewById(R.id.set_last_name);
        cwid = findViewById(R.id.set_cwid);
        courseID = findViewById(R.id.courseID);
        grade = findViewById(R.id.grade);
        courses = new ArrayList<String[]>();

        Button addCourse = findViewById(R.id.add_course);
        addCourse.setOnClickListener(new View.OnClickListener() {
            int row = 0;
            public void onClick(View v)
            {
                if(courseID.getText().toString() != "" && grade.getText().toString() != "") {
                    courses.add(new String[] {courseID.getText().toString(),grade.getText().toString()});
                    Toast.makeText(getApplicationContext(),
                            "Course: " + courseID.getText().toString() + " Grade: " + grade.getText().toString() + " successfully added!",
                            Toast.LENGTH_LONG).show();
                    courseID.setText("");
                    grade.setText("");
                    row++;
                }
                else
                    Toast.makeText(getApplicationContext(),
                            "Please enter a course ID and grade",
                            Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.done_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                ArrayList<Student> students = new ArrayList<Student>();
                ArrayList<CourseEnrollment> cTemp = new ArrayList<CourseEnrollment>();
                for(String[]course : courses){
                    cTemp.add(new CourseEnrollment(course[0],course[1]));
                }
                Student sTemp = new Student(firstName.getText().toString(), lastName.getText().toString(), cwid.getText().toString());
                sTemp.setcList(cTemp);
                students.add(sTemp);
                sDB.addPersonList(students);

                finish();
                return true;
            case android.R.id.home:
                finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
