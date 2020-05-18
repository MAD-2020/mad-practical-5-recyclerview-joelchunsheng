package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Task List";
    EditText taskText;
    Button addBtn;
    String input;
    ArrayList<String> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskText = findViewById(R.id.editText);
        addBtn = findViewById(R.id.addButton);

        taskList = new ArrayList<>();
        taskList.add("Task1");
        taskList.add("Task2");
        taskList.add("Task3");

        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        final Adapter mAdapter = new Adapter(taskList, this);

        LinearLayoutManager mLayoutManager =  new LinearLayoutManager(this);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Add btn clicked");
                input = taskText.getText().toString();

                if (input.isEmpty()){
                    Log.i(TAG, "Empty input");
                }
                else{
                    Log.i(TAG, input);
                    taskList.add(input);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });


    }

    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param data ArrayList that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }
}
