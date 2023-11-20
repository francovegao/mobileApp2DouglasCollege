package com.example.mealsapp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MealPlanCursorAdapter extends CursorAdapter {

    public MealPlanCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.meal_plan_card, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView mealPlanTitleTxtView = view.findViewById(R.id.mealPlanTitleTxtView);
        TextView breakfastDay1TextView = view.findViewById(R.id.breakfastDay1TextView);
        TextView lunchDay1TextView = view.findViewById(R.id.lunchDay1TextView);
        TextView dinnerDay1TextView = view.findViewById(R.id.dinnerDay1TextView);
        TextView snackDay1TextView = view.findViewById(R.id.snackDay1TextView);
        TextView breakfastDay2TextView = view.findViewById(R.id.breakfastDay2TextView);
        TextView lunchDay2TextView = view.findViewById(R.id.lunchDay2TextView);
        TextView dinnerDay2TextView = view.findViewById(R.id.dinnerDay2TextView);
        TextView snackDay2TextView = view.findViewById(R.id.snackDay2TextView);
        TextView breakfastDay3TextView = view.findViewById(R.id.breakfastDay3TextView);
        TextView lunchDay3TextView = view.findViewById(R.id.lunchDay3TextView);
        TextView dinnerDay3TextView = view.findViewById(R.id.dinnerDay3TextView);
        TextView snackDay3TextView = view.findViewById(R.id.snackDay3TextView);
        TextView breakfastDay4TextView = view.findViewById(R.id.breakfastDay4TextView);
        TextView lunchDay4TextView = view.findViewById(R.id.lunchDay4TextView);
        TextView dinnerDay4TextView = view.findViewById(R.id.dinnerDay4TextView);
        TextView snackDay4TextView = view.findViewById(R.id.snackDay4TextView);
        TextView breakfastDay5TextView = view.findViewById(R.id.breakfastDay5TextView);
        TextView lunchDay5TextView = view.findViewById(R.id.lunchDay5TextView);
        TextView dinnerDay5TextView = view.findViewById(R.id.dinnerDay5TextView);
        TextView snackDay5TextView = view.findViewById(R.id.snackDay5TextView);

        // Extract properties from cursor
        String _id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String mealPlanTitle = cursor.getString(cursor.getColumnIndexOrThrow("mealPlanName"));
        String breakfastDay1 = cursor.getString(cursor.getColumnIndexOrThrow("breakfastDay1"));
        String lunchDay1 = cursor.getString(cursor.getColumnIndexOrThrow("lunchDay1"));
        String dinnerDay1 = cursor.getString(cursor.getColumnIndexOrThrow("dinnerDay1"));
        String snackDay1 = cursor.getString(cursor.getColumnIndexOrThrow("snackDay1"));
        String breakfastDay2 = cursor.getString(cursor.getColumnIndexOrThrow("breakfastDay2"));
        String lunchDay2 = cursor.getString(cursor.getColumnIndexOrThrow("lunchDay2"));
        String dinnerDay2 = cursor.getString(cursor.getColumnIndexOrThrow("dinnerDay2"));
        String snackDay2 = cursor.getString(cursor.getColumnIndexOrThrow("snackDay2"));
        String breakfastDay3 = cursor.getString(cursor.getColumnIndexOrThrow("breakfastDay3"));
        String lunchDay3 = cursor.getString(cursor.getColumnIndexOrThrow("lunchDay3"));
        String dinnerDay3 = cursor.getString(cursor.getColumnIndexOrThrow("dinnerDay3"));
        String snackDay3 = cursor.getString(cursor.getColumnIndexOrThrow("snackDay3"));
        String breakfastDay4 = cursor.getString(cursor.getColumnIndexOrThrow("breakfastDay4"));
        String lunchDay4 = cursor.getString(cursor.getColumnIndexOrThrow("lunchDay4"));
        String dinnerDay4 = cursor.getString(cursor.getColumnIndexOrThrow("dinnerDay4"));
        String snackDay4 = cursor.getString(cursor.getColumnIndexOrThrow("snackDay4"));
        String breakfastDay5 = cursor.getString(cursor.getColumnIndexOrThrow("breakfastDay5"));
        String lunchDay5 = cursor.getString(cursor.getColumnIndexOrThrow("lunchDay5"));
        String dinnerDay5 = cursor.getString(cursor.getColumnIndexOrThrow("dinnerDay5"));
        String snackDay5 = cursor.getString(cursor.getColumnIndexOrThrow("snackDay5"));

        // Populate fields with extracted properties
        mealPlanTitleTxtView.setText(mealPlanTitle);
        breakfastDay1TextView.setText(breakfastDay1);
        lunchDay1TextView.setText(lunchDay1);
        dinnerDay1TextView.setText(dinnerDay1);
        snackDay1TextView.setText(snackDay1);
        breakfastDay2TextView.setText(breakfastDay2);
        lunchDay2TextView.setText(lunchDay2);
        dinnerDay2TextView.setText(dinnerDay2);
        snackDay2TextView.setText(snackDay2);
        breakfastDay3TextView.setText(breakfastDay3);
        lunchDay3TextView.setText(lunchDay3);
        dinnerDay3TextView.setText(dinnerDay3);
        snackDay3TextView.setText(snackDay3);
        breakfastDay4TextView.setText(breakfastDay4);
        lunchDay4TextView.setText(lunchDay4);
        dinnerDay4TextView.setText(dinnerDay4);
        snackDay4TextView.setText(snackDay4);
        breakfastDay5TextView.setText(breakfastDay5);
        lunchDay5TextView.setText(lunchDay5);
        dinnerDay5TextView.setText(dinnerDay5);
        snackDay5TextView.setText(snackDay5);
    }
}
