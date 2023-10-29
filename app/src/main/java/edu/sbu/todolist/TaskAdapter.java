package edu.sbu.todolist;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private static List<Tasks> TasksList = new ArrayList<>();
    private OnTaskListener OTL;

    TaskAdapter(List<Tasks> task, OnTaskListener onTaskListener) {
        super();
        this.TasksList = task;
        this.OTL = onTaskListener;
    }

    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view, OTL);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.title.setText(TasksList.get(position).getTaskName());
        holder.myCheckBox.setChecked(TasksList.get(position).isDone());
    }

    @Override
    public int getItemCount() {
        return TasksList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        CheckBox myCheckBox;
        OnTaskListener onTaskListener;

        public ViewHolder(@NonNull View itemView, final OnTaskListener onTaskListener) {
            super(itemView);
            title = itemView.findViewById(R.id.list_item_task_name);
            myCheckBox = itemView.findViewById(R.id.switch2);
            this.onTaskListener = onTaskListener;

            title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onTaskListener.OnTaskClick(getAdapterPosition());
                }
            });

            myCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (myCheckBox.isChecked()){
                            TasksList.get(getAdapterPosition()).setDone(true);
                            System.out.println("checked");
                            title.setText(TasksList.get(getAdapterPosition()).getTaskName()+" (DONE)");
                            title.setTextColor(Color.parseColor("#03DAC5"));
                        }
                        else if (!myCheckBox.isChecked()){
                            TasksList.get(getAdapterPosition()).setDone(false);
                            System.out.println("unchecked");
                            title.setText(TasksList.get(getAdapterPosition()).getTaskName());
                            title.setTextColor(Color.parseColor("#CC000000"));
                        }
                    }catch (Exception e){
                        System.out.println("ERROR : "+e);
                    }
                }
            });
        }
    }

    public interface OnTaskListener {
        void OnTaskClick(int position);
    }

}
