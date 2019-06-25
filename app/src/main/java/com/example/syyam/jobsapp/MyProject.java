package com.example.syyam.jobsapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MyProject extends AppCompatActivity {

    private MyProjectAdapter mAdapter;
    public static ArrayList<ArrayList<Project>> ProjectSet;
    public static ArrayList<Project> ChildProjectSet;
    private Button AddProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);

        AddProject = findViewById(R.id.add_project);
        AddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(ProjectSet.size(), 0, "Untitled Project");
            }
        });

        BuildRecyclerView();
    }

    public void BuildRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ProjectSet = new ArrayList<>();
        ChildProjectSet = new ArrayList<>();
        ProjectSet.add(new ArrayList<Project>() {
            {
                add(new Project(getApplicationContext(), "Untitled Project", 0, 0));
            }
        });
        ProjectSet.add(new ArrayList<Project>() {
            {
                add(new Project(getApplicationContext(), "Untitled Project", 1, 0));
            }
        });
        ProjectSet.get(0).add(new Project(this, 0, 1));
        mAdapter = new MyProjectAdapter(ProjectSet, ChildProjectSet, this);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MyProjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int position, final int position2, int callerId) {
                if (callerId == MyProjectAdapter.UPDATE_TITLE) {
                    final EditText input = new EditText(getApplicationContext());
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    new AlertDialog.Builder(MyProject.this)
                            .setTitle("Enter Title")
                            .setView(input)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    MyProject.ProjectSet.get(position).get(position2).setTextViewText(input.getText().toString());
                                    mAdapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                } else if (callerId == MyProjectAdapter.ADD_PRODUCT) {
                    Intent i = new Intent(getApplicationContext(), FetchProduct.class);
                    i.putExtra("Position", position);
                    i.putExtra("Position2", position2);
                    startActivity(i);
                } else if (callerId == MyProjectAdapter.ADD_SHADE) {
                    Intent i = new Intent(getApplicationContext(), FetchShade.class);
                    i.putExtra("Position", position);
                    i.putExtra("Position2", position2);
                    startActivity(i);
                } else if (callerId == MyProjectAdapter.ASSIGN_DEALER) {
                    Intent i = new Intent(getApplicationContext(), FetchDealer.class);
                    i.putExtra("Position", position);
                    i.putExtra("Position2", position2);
                    startActivity(i);
                } else if (callerId == MyProjectAdapter.ADD_ENTRY) {
                    addItem(position, position2, "null");
                } else if (callerId == MyProjectAdapter.REMOVE_ENTRY) {
                    removeItem(position, position2);
                } else if (callerId == MyProjectAdapter.CONFIRM_ORDER) {
                    JSONObject OrderJSON = null;
                    if(isDataCompleted(position))
                        OrderJSON = ConfirmOrder(position);
                    else
                        Toast.makeText(MyProject.this, "Please Complete Project's data to place order", Toast.LENGTH_SHORT).show();
                    // Do Something with the JSON here
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    public void removeItem(int position, int position2) {
        if (position2 == 0)
            ProjectSet.remove(position);
        else
            ProjectSet.get(position).remove(position2);
        mAdapter.notifyDataSetChanged();

    }

    public void addItem(final int position1, final int position2, final String title) {
        if (title.compareTo("null") == 0)
            ProjectSet.get(position1).add(new Project(getApplicationContext(), position1, position2));
        else {
            ProjectSet.add(new ArrayList<Project>() {
                {
                    add(new Project(getApplicationContext(), "Untitled Project", position1, position2));
                }
            });
        }
        mAdapter.notifyDataSetChanged();
    }

    public boolean isDataCompleted(int position){
        ArrayList<Project> projects = ProjectSet.get(position);
        if(!projects.get(0).isDealerSet())
            return false;
        for(Project project : projects){
            if(project.getProjectID() == -1 || project.getShadeID() == -1)
                return false;
        }
        return true;
    }

    public JSONObject ConfirmOrder(int position) {
        JSONObject ProjectJSON = new JSONObject();
        ArrayList<Project> projects = ProjectSet.get(position);
        try {
            ProjectJSON.put("ProjectID", projects.get(0).getProjectID());
            ProjectJSON.put("ShadeID", projects.get(0).getShadeID());
            ProjectJSON.put("DealerID", projects.get(0).getDealerID());
            JSONArray elements = new JSONArray();
            for (int i = 1; i < projects.size(); i++) {
                JSONObject ID = new JSONObject();
                ID.put("ID", i);
                ID.put("ProjectID", projects.get(i).getProjectID());
                ID.put("ShadeID", projects.get(i).getShadeID());
                elements.put(ID);
            }
            ProjectJSON.put("Children", elements);
        } catch (JSONException e) {
            // DO SOMETHING WITH ME IF YOU SO DESIRE
        }
        return ProjectJSON;
    }
}
