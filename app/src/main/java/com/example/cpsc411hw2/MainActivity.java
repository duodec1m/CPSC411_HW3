package com.example.cpsc411hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.cpsc411hw2.adapter.SummaryListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    StudentDB sDB;

    protected ListView mSummaryView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sDB = new StudentDB(this);

        setContentView(R.layout.activity_main);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //createStudentObjects();

        mSummaryView = findViewById(R.id.summary_list_id);
        SummaryListAdapter ad = new SummaryListAdapter();
        mSummaryView.setAdapter(ad);
    }

    protected void createStudentObjects(){
        ArrayList<Student> students = new ArrayList<Student>();

        ArrayList<CourseEnrollment> cTemp = new ArrayList<CourseEnrollment>();
        cTemp.add(new CourseEnrollment("CPSC411", "A"));
        cTemp.add(new CourseEnrollment("CPSC440", "D"));
        Student sTemp = new Student("Bob", "Ross","12345");
        sTemp.setcList(cTemp);
        students.add(sTemp);

        cTemp = new ArrayList<CourseEnrollment>();
        cTemp.add(new CourseEnrollment("CPSC666", "A"));
        cTemp.add(new CourseEnrollment("CPSC212", "C"));
        sTemp = new Student("Brannon", "Ha","54321");
        sTemp.setcList(cTemp);
        students.add(sTemp);

        sDB.addPersonList(students);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(this, Add.class);
                startActivity(intent);
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
