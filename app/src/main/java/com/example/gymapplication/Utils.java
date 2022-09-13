package com.example.gymapplication;

import android.transition.TransitionManager;
import android.webkit.TracingConfig;

import java.util.ArrayList;

public class Utils {
    private static ArrayList<Training> trainings;
    private static ArrayList<Plan> plans;

    public static void initTrainings() {
        if (trainings == null) {
            trainings = new ArrayList<>();
        }

        Training pushUp = new Training(1,
                "Push Up",
                "An exercise in which a person lies facing the floor",
                "The definition of a push-up is an exercise done laying with face, palms, " +
                        "and toes facing down, keeping legs and back straight while pushing up from " +
                        "the ground.",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/Man_Doing_Push_Ups_Cartoon.svg/1024px-Man_Doing_Push_Ups_Cartoon.svg.png");

        Training squat = new Training(2,
                "Squat",
                "If you crouch down very low, " +
                "and sit on your heels you squat",
                "A squat is a strength exercise in which " +
                "the trainee lowers their hips from a standing position and then into a sitting position",
                "https://e7.pngegg.com/pngimages/478/131/png-clipart-squat-%E3%82%B7%E3%83%8C%E3%83%9E%E3%83%87%E3%82%A2%E3%83%AB%E3%82%AF%E3%83%8B%E3%83%AF%E3%82%B9%E3%82%AF%E3%83%AF%E3%83%83%E3%83%88%E3%83%80%E3%82%B1%E3%82%B9%E3%83%AC%E3%83%90%E3%82%A4%E3%82%A4-%E3%81%84%E3%82%89%E3%81%99%E3%81%A8%E3%82%84-leg-muscle-spot-date-child-hand.png");

        Training legPress = new Training(3,
                "leg Press",
                "The leg press is a leg " +
                "exercise.",
                "The leg press is a weight" +
                "training exercise in which the individual pushes eight or resistance away from their " +
                "body with their legs.",
                "https://img.freepik.com/premium-vector/sports-woman-doing-exercises-leg-press-machine-girl-flexing-muscles-training-gym-workout-healthy-lifestyle-concept-white-background_48369-26798.jpg?w=2000");

        Training pullUps = new Training(4, "Pull-Ups", "An exercise involving " +
                "raising oneself above a bar.", "The pull-up is a workout in which an " +
                "individual raises them self above a bar using their back and bicep muscles.",
                "https://cdn2.vectorstock.com/i/1000x1000/64/06/cartoon-girl-pulling-up-on-a-horizontal-bar-vector-16076406.jpg");


        trainings.add(pushUp);
        trainings.add(squat);
        trainings.add(legPress);
        trainings.add(pullUps);



    }

    public static ArrayList<Training> getTrainings() {
        return trainings;
    }

    public static boolean addPlan(Plan plan) {
        if (plans == null) {
            plans = new ArrayList<>();
        }
        return plans.add(plan);
    }

    public static ArrayList<Plan> getPlans() {
        return plans;
    }
}
