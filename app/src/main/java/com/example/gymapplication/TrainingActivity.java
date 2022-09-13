package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.DataOutputStream;

public class TrainingActivity extends AppCompatActivity implements PlanDetailDialog.PassPlanInterface {

    public static final String TAG = "TrainingActivity";
    public static final String TRAINING_KEY = "training";

    private Button btnAddToPlan;
    private TextView txtName, txtDescription;
    private ImageView trainingImage;

    @Override
    public void getPlan(Plan plan) {
        Log.d(TAG, "getPlan: Plan: " + plan.toString());
        if (Utils.addPlan(plan)) {
            Toast.makeText(this, plan.getTraining().getName() + " Added to Your Plan", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PlanActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate Training: called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        initViews();

        Intent intent = getIntent();
        if (intent != null) {
            Training training = intent.getParcelableExtra(TRAINING_KEY);
            if (null != training) {
                txtName.setText(training.getName());
                txtDescription.setText(training.getLongDesc());
                Glide.with(this)
                        .asBitmap()
                        .load(training.getImageUrl())
                        .into(trainingImage);

                btnAddToPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PlanDetailDialog planDetailDialog = new PlanDetailDialog();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(TRAINING_KEY, training);
                        planDetailDialog.setArguments(bundle);
                        planDetailDialog.show(getSupportFragmentManager(), "plan detail dialog");

                    }
                });

            }

        }

    }

    private void initViews() {
        btnAddToPlan = findViewById(R.id.btnAddToPlan);
        txtName = findViewById(R.id.txtName);
        trainingImage = findViewById(R.id.trainingImage);
        txtDescription = findViewById(R.id.txtDescription);
    }

}