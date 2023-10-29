package edu.sbu.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class addNewTasksPage extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText details_et;
    private EditText title_et;
    private EditText expdate_et;
    private EditText posotion_et;
    private String title_str;
    private String details_str;
    private String expdate_str;
    private String position_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_tasks_page);

        title_et = (EditText) findViewById(R.id.Title_editText);
        details_et = (EditText) findViewById(R.id.Details_editText);
        expdate_et = (EditText) findViewById(R.id.expDate_editText);
        posotion_et = (EditText) findViewById(R.id.position);
        Toast.makeText(this, "If the position blank is not filled, your task won't be added to the list!", Toast.LENGTH_LONG).show();
        receiveDataFromEdit();

        findViewById(R.id.DatePickerBTN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

    }

    public void addNewTaskToList(View view) {
        title_str = title_et.getText().toString();
        details_str = details_et.getText().toString();
        expdate_str = expdate_et.getText().toString();
        position_str = posotion_et.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("title", title_str);
        intent.putExtra("details", details_str);
        intent.putExtra("expdate", expdate_str);
        intent.putExtra("position", position_str);
        startActivity(intent);
    }

    public void receiveDataFromEdit() {
        if (getIntent().hasExtra("TaskEdit")) {
            Tasks task = getIntent().getParcelableExtra("TaskEdit");
            expdate_et.setText(task.getExpDate());
            title_et.setText(task.getTaskName());
            details_et.setText(task.getTasksDetails());
        }
    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        expdate_et.setText(year+"/"+month+"/"+dayOfMonth);
    }
}
