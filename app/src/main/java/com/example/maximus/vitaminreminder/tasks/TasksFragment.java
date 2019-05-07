package com.example.maximus.vitaminreminder.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.maximus.vitaminreminder.R;
import com.example.maximus.vitaminreminder.addedittask.AddEditTaskActivity;
import com.example.maximus.vitaminreminder.addedittask.AddEditTaskFragment;
import com.example.maximus.vitaminreminder.data.Task;
import com.example.maximus.vitaminreminder.pref.SettingsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class TasksFragment extends Fragment implements TasksContract.View {

    private static final String ARGUMENT_TASK_ID = "TASK_ID";
    private static final int REQUEST_ADD_TASK = 1;

    private TasksAdapter mListAdapter;
    private TasksContract.Presenter mPresenter;
    FloatingActionButton fab;
    ArrayList<Task> tasks = new ArrayList<>();



    public static TasksFragment newInstance(String taskId) {
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        args.putString(ARGUMENT_TASK_ID, taskId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListAdapter = new TasksAdapter(tasks, taskItemListener);
        mPresenter = new TasksPresenter(this, getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start(getContext());
    }

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.result(requestCode, resultCode);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.tasks_frag, container, false);

        ListView lvMain = root.findViewById(R.id.tasks_list);
        lvMain.setAdapter(mListAdapter);



        fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.addNewTask();
            }
        });

        setHasOptionsMenu(true);

        return  root;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(getContext(), SettingsActivity.class);
                startActivity(intent);
            case R.id.delete_all:
                mPresenter.deleteAll(getContext());

            default:
                super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    TaskItemListener taskItemListener = new TaskItemListener() {
        @Override
        public void onTaskClick(Task clickedTask) {
            mPresenter.openTaskDetails(clickedTask);

        }
    };

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
//            } else {
//                throw new NullPointerException("TasksActivity");
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
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDestroy();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void showTasks(List<Task> tasks) {
        mListAdapter.replaceData(tasks);
    }

    @Override
    public void showTaskDetailUi(String taskId) {
        Intent intent = new Intent(getContext(), AddEditTaskActivity.class);
        intent.putExtra(AddEditTaskFragment.ARGUMENT_EXTRA_TASK_ID, taskId);
        startActivity(intent);
    }


    @Override
    public void showAddTask() {
        Intent intent = new Intent(getContext(), AddEditTaskActivity.class);
        startActivityForResult(intent, REQUEST_ADD_TASK);
    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showSuccessfullySavedMessage() {

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
}
