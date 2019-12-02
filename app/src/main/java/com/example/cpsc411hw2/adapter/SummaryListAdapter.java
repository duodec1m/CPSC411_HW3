package com.example.cpsc411hw2.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cpsc411hw2.PersonDetailActivity;
import com.example.cpsc411hw2.R;
import com.example.cpsc411hw2.Student;
import com.example.cpsc411hw2.StudentDB;

public class SummaryListAdapter extends BaseAdapter {

    @Override
    public int getCount() {
        return StudentDB.getPersonList().size();
    }
    @Override
    public Object getItem(int position) {
        return StudentDB.getPersonList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final View row_view;

        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            row_view = inflater.inflate(R.layout.person_row, viewGroup, false);
        }else row_view = view;

        //
        Student p = StudentDB.getPersonList().get(i);
        //
        ((TextView) row_view.findViewById(R.id.name)).setText(p.getFirstName() + " " + p.getLastName());

        row_view.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Intent intent = new Intent(view.getContext(), PersonDetailActivity.class);
                        intent.putExtra("PersonIndex", i);
                        view.getContext().startActivity(intent);
                    }
                }
        );
        return row_view;
    }
}
