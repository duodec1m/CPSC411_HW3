package com.example.cpsc411hw2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class PersonDetailActivity extends AppCompatActivity {

    StudentDB sDB;

    final List<String> Courses = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         sDB = new StudentDB(this);

        setContentView(R.layout.activity_student_detail);

        int personIndx = getIntent().getIntExtra("PersonIndex", 0);
        //
        Student pObj = sDB.getPersonList().get(personIndx);
        //
        EditText editView = findViewById(R.id.p_first_name_id);
        editView.setText(pObj.getFirstName());
        editView.setEnabled(false);
        editView = findViewById(R.id.p_last_name_id);
        editView.setText(pObj.getLastName());
        editView.setEnabled(false);
        editView = findViewById(R.id.p_cwid);
        editView.setText(pObj.getCWID());
        editView.setEnabled(false);

        ListView lv = findViewById(R.id.p_course_list);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(PersonDetailActivity.this, android.R.layout.simple_list_item_1, Courses);
        lv.setAdapter(adapter);

        for (CourseEnrollment temp : pObj.getcList()) {
            Courses.add(temp.getCourseID() + "(" + temp.getGrade() + ")");
        }
        adapter.notifyDataSetChanged();
    }
}

