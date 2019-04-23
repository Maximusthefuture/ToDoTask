package com.example.maximus.vitaminreminder.tasks;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.RestrictTo;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.maximus.vitaminreminder.addedittask.AddActivity;
import com.example.maximus.vitaminreminder.CustomCalendarView;
import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.data.Task;
import com.example.maximus.vitaminreminder.data.source.local.TaskDbHelper;
import com.example.maximus.vitaminreminder.pref.LocalData;
import com.example.maximus.vitaminreminder.pref.SettingsActivity;
import com.example.maximus.vitaminreminder.sync.ReminderTask;
import com.example.maximus.vitaminreminder.utils.NotificationUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


//REFACTOR AND ADD NEW FEATURE
//TODO: 1: MVP
//TODO: Add a editable fragment to add add edit time and other things
//TODO: Нужно вместо Activity использовать Fragments, потому-что мы используем BotttomNavigationBar
public class TasksActivity extends AppCompatActivity implements TasksContract.View {

    private static final String TAG = "TasksActivity";
    private TasksContract.Presenter mPresenter;
    private TasksPresenter mTaskPresenter;
    LocalData localData;
    ReminderTask reminderTask;
    CustomCalendarView customCalendarView;
    FloatingActionButton fab;
    private BottomNavigationView bottomNavigationView;
    TasksAdapter mListAdapter;
    ArrayList<Task> tasksk = new ArrayList<>();


    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvMain = findViewById(R.id.tasks_list);

        mListAdapter = new TasksAdapter(tasksk, taskItemListener);
        lvMain.setAdapter(mListAdapter);

        mTaskPresenter = new TasksPresenter(this, this);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                mPresenter.addNewTask();
            }
        });

        bottomNavigationView = findViewById(R.id.nav_bottom_view);

        disableShiftMode(bottomNavigationView);


        //TODO: HOW TO SHOW DATA IN LAYOUT!!?!?!?!?
//        TaskDbHelper db = new TaskDbHelper(this);
//        List<Task> tasks = db.getTasks();
//        for (Task task : tasks) {
//            String log = "Id: " + task.getId() + " , Title: " + task.getTitle() + " , Time: " + task.getTime();
//            tasksk.add(task);
//            Log.d(TAG, log);
//        }

//        deleteFromDB();



//        fakeData();
        //TODO: SEE DATA FROM SQLITE!!!!!!!

    }

    public void fakeData() {
        for (int i = 0; i < 10; i++) {
            tasksk.add(new Task("Vitamin " + i, "13:" + i * 10));

        }
    }

    public void deleteFromDB() {
        TaskDbHelper db = new TaskDbHelper(this);
        db.deleteTasks();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }

    public void testNotification() {
        NotificationUtils.showNotification(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        //TODO: SHOW NOTHING
        //PROBLEM IN THIS METHOD!
        mPresenter.start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(TasksActivity.this, SettingsActivity.class);
                startActivity(intent);

                default:
                    super.onOptionsItemSelected(item);
        }
        return true;
    }


    @SuppressLint("RestrictedApi")
    public void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());


            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }



    @Override
    public void setLoadingIndicator() {

    }



    @Override
    public void showTasks(List<Task> tasks) {
        mListAdapter.replaceData(tasks);
    }

    @Override
    public void showTaskDetailUi(String taskId) {
        Intent intent = new Intent(getApplicationContext(), AddActivity.class);
        intent.putExtra(AddActivity.EXTRA_TASK_ID, taskId);
        startActivity(intent);
    }



    @Override
    public void showAddTask() {
        Intent intent = new Intent(getApplicationContext(), AddActivity.class);
        startActivityForResult(intent, AddActivity.REQUEST_ADD_TASK);
    }

    @Override
    public void setBottomNavigationView() {

    }

    @Override
    public void showNoTasks() {

    }

    @Override
    public void showNotification() {

    }


    TaskItemListener taskItemListener = new TaskItemListener() {
        @Override
        public void onTaskClick(Task clickedTask) {
            mPresenter.openTaskDetails(clickedTask);
        }
    };


    private void showNoTasksViews(String mainText, int iconRes, boolean showAddView) {


    }



    //TODO: 1.Добавить адаптер -- DONE
    //TODO: 2.Test with fake data -- DONE
    //TODO: 3.Test Adapter -- DONE
    private static class TasksAdapter extends BaseAdapter {
        private List<Task> mTasks;
        private TaskItemListener mItemListener;

        public TasksAdapter(ArrayList<Task> task, TaskItemListener itemListener) {

            this.mTasks = task;
            mItemListener = itemListener;

        }

        private void setList(List<Task> tasks) {
            if (!tasks.isEmpty()) {
                mTasks = tasks;
            } else {
                throw new NullPointerException("TasksActivity");
            }
        }

        public void replaceData(List<Task> tasks) {
            setList(tasks);
            notifyDataSetChanged();
        }


        @Override
        public int getCount() {
            return mTasks.size();
        }

        @Override
        public Task getItem(int position) {
            return mTasks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            if (rowView == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                rowView = inflater.inflate(R.layout.task_item, parent, false);
            }

            final Task task = getItem(position);

            TextView title = rowView.findViewById(R.id.vitamin_name);
            title.setText(task.getTitle());

            TextView time = rowView.findViewById(R.id.time);
            time.setText(task.getTime());



            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemListener.onTaskClick(task);
                }
            });

            return rowView;
        }



    }


    public interface TaskItemListener {

        void onTaskClick(Task clickedTask);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }
}
