package com.example.keepnotes;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    public MainAdapter(List<MainData> dataList, Activity context) {
        this.dataList = dataList;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row_main,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainData data=dataList.get(position);
        database=RoomDB.getInstance(context);
        holder.textView.setText(data.getText());
        holder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                MainData d=dataList.get(holder.getAdapterPosition());
                int sID=d.getID();
                String sText=d.getText();
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.dialog_click);
                int width= WindowManager.LayoutParams.WRAP_CONTENT;
                //initialize height
                int height =WindowManager.LayoutParams.WRAP_CONTENT;
                //set layout
                dialog.getWindow().setLayout(width,height);
                //show dialog
                dialog.show();




                return false;
            }
        });
        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData d=dataList.get(holder.getAdapterPosition());
                //get id
                int sID=d.getID();
                //get text
                String sText=d.getText();
                //create dialog
                Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.dialog_update);
                //initialize width
                int width= WindowManager.LayoutParams.MATCH_PARENT;
                //initialize height
                int height =WindowManager.LayoutParams.WRAP_CONTENT;
                //set layout
                dialog.getWindow().setLayout(width,height);
                //show dialog
                dialog.show();
                //initialize and assign variable
                EditText editText=dialog.findViewById(R.id.edit_text);
                Button btUpdate=dialog.findViewById(R.id.bt_update);
                //set text on edit text
                editText.setText(sText);
                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //dismiss dialog
                        dialog.dismiss();
                        //get update text from edit text
                        String uText=editText.getText().toString().trim();
                        //update text in database
                        database.mainDao().updte(sID,uText);
                        //notify when data is update
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });

            }
        });
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainData d=dataList.get(holder.getAdapterPosition());
                database.mainDao().delete(d);
                int position=holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView btEdit,btDelete,bt_delete1,bt_edit1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_view);
            btEdit=itemView.findViewById(R.id.bt_edit);
            btDelete=itemView.findViewById(R.id.bt_delete);
            bt_delete1=itemView.findViewById(R.id.bt_delete1);
            bt_edit1=itemView.findViewById(R.id.bt_edit1);
        }
    }
}
