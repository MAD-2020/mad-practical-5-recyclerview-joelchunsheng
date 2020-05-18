package sg.edu.np.mad.mad_recyclerview;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder {
    TextView itemName;
    LinearLayout item;

    public Holder(@NonNull View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.rowTxt);
        item = itemView.findViewById(R.id.rowLayout);
    }
}
