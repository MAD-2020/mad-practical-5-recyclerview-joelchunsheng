package sg.edu.np.mad.mad_recyclerview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Holder> {

    ArrayList<String> taskList;
    Context context;
    AlertDialog.Builder dialog;
    TextView deleteText;

    public Adapter(ArrayList<String> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_row, parent, false);
        final Holder holder = new Holder(v);

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Clicked position", String.valueOf(holder.getAdapterPosition()));

                dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Delete");

                View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog, null);
                deleteText = (TextView) dialogView.findViewById(R.id.deleteTxt);
                deleteText.setText(taskList.get(holder.getAdapterPosition()) + "?");

                dialog.setCancelable(true);
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Log.i("Dialog", "Yes Clicked");
                        taskList.remove(holder.getAdapterPosition());
                        Log.i("Task list size", String.valueOf(taskList.size()));
                        notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Log.i("Dialog", "No Clicked");
                    }
                });
                dialog.setView(dialogView);
                dialog.show();
            }

        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        String taskName = taskList.get(position);
        holder.itemName.setText(taskName);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
