package com.example.syyam.jobsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.syyam.jobsapp.MyProjectAdapter.ADD_PRODUCT;
import static com.example.syyam.jobsapp.MyProjectAdapter.ADD_SHADE;
import static com.example.syyam.jobsapp.MyProjectAdapter.REMOVE_ENTRY;

public class ChildRecyclerView extends RecyclerView.Adapter<ChildRecyclerView.ChildRVViewHolder> {
    ArrayList<Project> mChildDataSet;
    ArrayList<Project> mCloneData;
    Context mContext;

    private MyProjectAdapter.OnItemClickListener mOnClickListener;

    public void setOnItemClickListener(MyProjectAdapter.OnItemClickListener listener){
        mOnClickListener = listener;
    }

    public ChildRecyclerView(Context context, ArrayList<Project> data, ArrayList<Project> cloneData){
        mContext = context;
        mChildDataSet = data;
        mCloneData = cloneData;
    }

    public class ChildRVViewHolder extends RecyclerView.ViewHolder{

        ImageView AddProduct, AddShade, RemoveEntry;
        public ChildRVViewHolder(@NonNull View v, final MyProjectAdapter.OnItemClickListener listener) {
            super(v);

            AddProduct = v.findViewById(R.id.firstBox_image);
            AddShade = v.findViewById(R.id.secondBox_image);
            RemoveEntry = v.findViewById(R.id.remove_image);

            AddProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = Character.getNumericValue(RemoveEntry.getContentDescription().charAt(0));
                        int position2 = getAdapterPosition() + 1;
                        if(MyProject.ProjectSet.get(position).get(0).isDealerSet())
                            listener.onItemClick(position, position2, ADD_PRODUCT);
                        else
                            Toast.makeText(mContext, "Please Assign a Dealer", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            AddShade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = Character.getNumericValue(RemoveEntry.getContentDescription().charAt(0));
                        int position2 = getAdapterPosition() + 1;
                        if(MyProject.ProjectSet.get(position).get(0).isDealerSet()) {
                            if(MyProject.ProjectSet.get(position).get(position2).getProjectID() != -1)
                                listener.onItemClick(position, position2, ADD_SHADE);
                            else
                                Toast.makeText(mContext, "Please Choose a Product", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(mContext, "Please Assign a Dealer", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            RemoveEntry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = Character.getNumericValue(RemoveEntry.getContentDescription().charAt(0));
                        int position2 = getAdapterPosition() + 1;
                        if(position2 != RecyclerView.NO_POSITION){
                            listener.onItemClick(position, position2, REMOVE_ENTRY);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ChildRVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.child_project_card_view, viewGroup, false);
        return new ChildRVViewHolder(view, mOnClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildRVViewHolder holder, int i) {
        holder.AddProduct.setImageDrawable(mCloneData.get(i).getImageView1());
        holder.AddShade.setImageDrawable(mCloneData.get(i).getImageView2());
        holder.RemoveEntry.setContentDescription(mCloneData.get(i).getIndex1() + "" + mCloneData.get(i).getIndex2());
    }

    @Override
    public int getItemCount() {
        return mCloneData.size();
    }

}
