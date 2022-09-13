package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {
    public static final String TAG = "EditActivity";

    private TextView txtDay;
    private RecyclerView recyclerView;
    private Button btnAddPlan;

    private PlanAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        initViews();

        adapter = new PlanAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent = getIntent();
        if (intent != null) {
            String day = intent.getStringExtra("day");
            if (day != null) {
                txtDay.setText(day);

                ArrayList<Plan> plans = getPlansByDay(day);
                adapter.setPlans(plans);
            }
        }

        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPlanIntent = new Intent(EditActivity.this, AllTrainingsActivity.class);
                startActivity(addPlanIntent);
            }
        });
    }

    private void initViews() {
        Log.d(TAG, "initViews: started");
        txtDay = findViewById(R.id.txtDay);
        recyclerView = findViewById(R.id.recyclerView);
        btnAddPlan = findViewById(R.id.btnAddPlans);
    }

    private ArrayList<Plan> getPlansByDay(String day) {
        ArrayList<Plan> allPlans = Utils.getPlans();
        ArrayList<Plan> plans = new ArrayList<>();
        for (Plan p : allPlans) {
            if (p.getDay().equalsIgnoreCase(day)) {
                plans.add(p);
            }
        }
        return plans;
    }


}