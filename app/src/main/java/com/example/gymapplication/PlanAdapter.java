package com.example.gymapplication;

import static com.example.gymapplication.TrainingActivity.TRAINING_KEY;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder> {

    private ArrayList<Plan> plans = new ArrayList<>();
    private Context context;
    private String type = "";

    public PlanAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(plans.get(position).getTraining().getName());
        holder.txtDescription.setText((plans.get(position).getTraining().getShortDesc()));
        holder.txtTime.setText(String.valueOf(plans.get(position).getMinutes()));
        Glide.with(context)
                .asBitmap()
                .load(plans.get(position).getTraining().getImageUrl())
                .into(holder.image);

        if (plans.get(position).isAccomplished()) {
            holder.emptyCircle.setVisibility(View.GONE);
            holder.checkedCircle.setVisibility(View.VISIBLE);
        } else {
            holder.emptyCircle.setVisibility(View.VISIBLE);
            holder.checkedCircle.setVisibility(View.VISIBLE);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TrainingActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(TRAINING_KEY, plans.get(position).getTraining());
                context.startActivity(intent);
            }
        });

        if (type.equals("edit")) {
            holder.emptyCircle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Finished")
                            .setMessage("Have you finished " + plans.get(position).getTraining().getName() + "?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (Plan p : Utils.getPlans()) {
                                        if (p.equals(plans.get(position))) {
                                            p.setAccomplished(true);
                                        }
                                    }

                                    notifyDataSetChanged();
                                }
                            });
                }
            });
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public void setPlans(ArrayList<Plan> plans) {
        this.plans = plans;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTime, txtName, txtDescription;
        private MaterialCardView parent;
        private ImageView image, emptyCircle, checkedCircle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTime = itemView.findViewById(R.id.txtTime);
            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            parent = itemView.findViewById(R.id.parent);
            image = itemView.findViewById(R.id.trainingImage);
            emptyCircle = itemView.findViewById(R.id.emptyCircle);
            checkedCircle = itemView.findViewById(R.id.checkedCircle);

        }
    }
}
