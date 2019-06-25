package com.example.syyam.jobsapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyProjectAdapter extends RecyclerView.Adapter<MyProjectAdapter.ProjectViewHolder> {
    static final int UPDATE_TITLE = 0;
    static final int ADD_PRODUCT = 1;
    static final int ADD_SHADE = 2;
    static final int ASSIGN_DEALER = 3;
    static final int ADD_ENTRY = 4;
    static final int REMOVE_ENTRY = 5;
    static final int CONFIRM_ORDER = 6;

    private ArrayList<ArrayList<Project>> mDataset;
    private ArrayList<Project> mChildDataset;
    private OnItemClickListener mOnClickListener;
    private Context mContext;


    public interface OnItemClickListener{
        void onItemClick(int position, int position2, int callerId);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnClickListener = listener;
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        RecyclerView recyclerView;
        ConstraintLayout AddProduct, AddShade, AssignDealer;
        ImageView FirstBoxImage, SecondBoxImage;
        TextView ThirdImageTextView;
        Spinner MoreOptions;
        Button ConfirmOrder;
        ProjectViewHolder(View v, final OnItemClickListener listener) {
            super(v);
            recyclerView = v.findViewById(R.id.nestedView);

            ConfirmOrder = v.findViewById(R.id.confirm_order);

            textView = v.findViewById(R.id.title_project);
            AddProduct = v.findViewById(R.id.firstBox);
            AddShade = v.findViewById(R.id.secondBox);
            AssignDealer = v.findViewById(R.id.thirdBox);

            FirstBoxImage = v.findViewById(R.id.firstBox_image);
            SecondBoxImage = v.findViewById(R.id.secondBox_image);

            ThirdImageTextView = v.findViewById(R.id.thirdBox_view);

            MoreOptions = v.findViewById(R.id.more_options);
            List<String> list = new ArrayList<>();
            list.add("");   //  Initial dummy entry
            list.add("Set Title");
            list.add("Add Row");
            list.add("Delete Project");
            SpinnerAdapter arrayAdapter = new SpinnerAdapter(v.getContext(), android.R.layout.simple_spinner_item, list, 0);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            MoreOptions.setAdapter(arrayAdapter);
            MoreOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int p, long id) {
                    switch (p){
                        case 0:
                            // Empty Option
                            break;
                        case 1:
                            MoreOptions.setSelection(0);
                            if(listener != null){
                                final int pos = getAdapterPosition();
                                listener.onItemClick(pos, 0, UPDATE_TITLE);
                            }

                            break;
                        case 2:
                            MoreOptions.setSelection(0);
                            if(listener != null){
                                int pos1 = getAdapterPosition();
                                int pos2 = MyProject.ProjectSet.get(pos1).size();
                                listener.onItemClick(pos1, pos2, ADD_ENTRY);
                            }
                            break;
                        case 3:
                            MoreOptions.setSelection(0);
                            new AlertDialog.Builder(view.getContext())
                                    .setTitle("Confirmation")
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .setMessage("Are you sure? This will Delete the Project")
                                    .setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            if(listener != null){
                                                int pos1 = getAdapterPosition();
                                                listener.onItemClick(pos1, 0, REMOVE_ENTRY);
                                            }
                                        }
                                    })
                                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    })
                                    .show();
                            break;
                        default:
                            Toast.makeText(view.getContext(), "Please Select The correct option", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            AddProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addListener(ADD_PRODUCT, getAdapterPosition(), listener);
                }
            });

            AddShade.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(MyProject.ProjectSet.get(position).get(0).getProjectID() != -1) {
                            listener.onItemClick(position, 0, ADD_SHADE);
                        }
                        else
                            Toast.makeText(v.getContext(), "Please Select a Product", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            AssignDealer.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    addListener(ASSIGN_DEALER, getAdapterPosition(), listener);
                }
            });

            ConfirmOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addListener(CONFIRM_ORDER, getAdapterPosition(), listener);
                }
            });
        }
    }

    private static void addListener(int ID, int position, OnItemClickListener listener){
        if(listener != null){
            if(position != RecyclerView.NO_POSITION){
                listener.onItemClick(position, 0, ID);
            }
        }
    }

    MyProjectAdapter(ArrayList<ArrayList<Project>> myDataset, ArrayList<Project> childDataSet, Context context) {
        mDataset = myDataset;
        mChildDataset = childDataSet;
        mContext = context;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_card_view, parent, false);
        return new ProjectViewHolder(v, mOnClickListener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.textView.setText(mDataset.get(position).get(0).getTextViewText());
        holder.FirstBoxImage.setImageDrawable(mDataset.get(position).get(0).getImageView1());
        holder.SecondBoxImage.setImageDrawable(mDataset.get(position).get(0).getImageView2());
        holder.ThirdImageTextView.setText(mDataset.get(position).get(0).getImageTextView3());

        mChildDataset = mDataset.get(position);
        ArrayList<Project> ChildClone = (ArrayList<Project>) mChildDataset.clone();
        ChildClone.remove(0);
        ChildRecyclerView childRecyclerViewAdapter = new ChildRecyclerView(mContext, mChildDataset, ChildClone);
        childRecyclerViewAdapter.setOnItemClickListener(mOnClickListener);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recyclerView.setAdapter(childRecyclerViewAdapter);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}