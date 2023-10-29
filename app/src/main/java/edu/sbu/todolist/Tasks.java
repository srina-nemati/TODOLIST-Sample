package edu.sbu.todolist;

import android.os.Parcel;
import android.os.Parcelable;

public class Tasks implements Parcelable {
    private String TaskName;
    private boolean isDone = false;
    private String expDate;
    private String TasksDetails;

    protected Tasks(Parcel in) {
        TaskName = in.readString();
        isDone = in.readByte() != 0;
        expDate = in.readString();
        TasksDetails = in.readString();
    }

    protected Tasks(String TaskName,String expDate,String TasksDetails){
        this.TaskName=TaskName;
        this.expDate=expDate;
        this.TasksDetails=TasksDetails;
    }

    protected Tasks(){
    }

    public static final Creator<Tasks> CREATOR = new Creator<Tasks>() {
        @Override
        public Tasks createFromParcel(Parcel in) {
            return new Tasks(in);
        }

        @Override
        public Tasks[] newArray(int size) {
            return new Tasks[size];
        }
    };

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getTasksDetails() {
        return TasksDetails;
    }

    public void setTasksDetails(String tasksDetails) {
        TasksDetails = tasksDetails;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(TaskName);
        dest.writeByte((byte) (isDone ? 1 : 0));
        dest.writeString(expDate);
        dest.writeString(TasksDetails);
    }
}
