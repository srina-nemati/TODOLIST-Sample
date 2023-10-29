package edu.sbu.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskListener {

    private RecyclerView recyclerViewTasksList;
    private TaskAdapter taskAdapter;
    private List<Tasks> TasksList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewTasksList = (RecyclerView) findViewById(R.id.first_recyclerview_page);


        initRecyclerView();
        setFakeNotes();
        addTasksToList();

    }

    public void addNewTaskBTN(View view) {
        Intent intent = new Intent(this, addNewTasksPage.class);
        startActivity(intent);
    }

    private void setFakeNotes() {
        for (int i = 0; i <= 5; i++) {
            Tasks task = new Tasks();
            task.setTaskName("task " + i);
            task.setTasksDetails("this is task number " + i);
            task.setExpDate("20June");
            task.setDone(false);
            TasksList.add(task);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerViewTasksList.setLayoutManager(linearLayoutManager);
        taskAdapter = new TaskAdapter(TasksList, this);
        recyclerViewTasksList.setAdapter(taskAdapter);
    }

    private void addTasksToList() {
        try {
            Intent intent = getIntent();
            int position = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("position")));
            Tasks tasks = new Tasks();
            tasks.setDone(false);
            tasks.setTaskName(intent.getStringExtra("title"));
            tasks.setTasksDetails(intent.getStringExtra("details"));
            tasks.setExpDate(intent.getStringExtra("expdate"));
            TasksList.add(position, tasks);
            taskAdapter.notifyItemInserted(position);
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
        }
    }

    @Override
    public void OnTaskClick(int position) {
        Intent intent = new Intent(this, addNewTasksPage.class);
        intent.putExtra("TaskEdit", TasksList.get(position));
        startActivity(intent);
    }

}
