package com.example.mealsapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import java.util.HashMap;

public class MealPlanContentProvider extends ContentProvider {
    public MealPlanContentProvider() {
    }

    // defining authority so that other application can access it
    static final String PROVIDER_NAME = "com.demo.mealplans.provider";

    // defining content URI
    static final String URL = "content://" + PROVIDER_NAME + "/mealplans";

    // parsing the content URI
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _id = "_id";
    static final String mealPlanName = "mealPlanName";
    static final String breakfastDay1 = "breakfastDay1";
    static final String lunchDay1 = "lunchDay1";
    static final String dinnerDay1 = "dinnerDay1";
    static final String snackDay1 = "snackDay1";
    static final String breakfastDay2 = "breakfastDay2";
    static final String lunchDay2 = "lunchDay2";
    static final String dinnerDay2 = "dinnerDay2";
    static final String snackDay2 = "snackDay2";
    static final String breakfastDay3 = "breakfastDay3";
    static final String lunchDay3 = "lunchDay3";
    static final String dinnerDay3 = "dinnerDay3";
    static final String snackDay3 = "snackDay3";
    static final String breakfastDay4 = "breakfastDay4";
    static final String lunchDay4 = "lunchDay4";
    static final String dinnerDay4 = "dinnerDay4";
    static final String snackDay4 = "snackDay4";
    static final String breakfastDay5 = "breakfastDay5";
    static final String lunchDay5 = "lunchDay5";
    static final String dinnerDay5 = "dinnerDay5";
    static final String snackDay5 = "snackDay5";

    static final int uriCode = 1;
    static final UriMatcher uriMatcher;
    private static HashMap<String, String> values;

    static {

        // to match the content URI
        // every time user access table under content provider
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // to access whole table
        uriMatcher.addURI(PROVIDER_NAME, "mealplans", uriCode);

        // to access a particular row
        // of the table
        uriMatcher.addURI(PROVIDER_NAME, "mealplans/*", uriCode);
    }
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case uriCode:
                return "vnd.android.cursor.dir/mealplans";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
    // creating the database
    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        if (db != null) {
            return true;
        }
        return false;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);
        switch (uriMatcher.match(uri)) {
            case uriCode:
                qb.setProjectionMap(values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if (sortOrder == null || sortOrder == "") {
            sortOrder = _id;
        }
        Cursor c = qb.query(db, projection, selection, selectionArgs, null,
                null, sortOrder);
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    // adding data to the database
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = db.insert(TABLE_NAME, "", values);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLiteException("Failed to add a record into " + uri);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case uriCode:
                count = db.update(TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case uriCode:
                count = db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    // creating object of database
    // to perform query
    private SQLiteDatabase db;

    // declaring name of the database
    static final String DATABASE_NAME = "MealPlansDB";

    // declaring table name of the database
    static final String TABLE_NAME = "MealPlans";

    // declaring version of the database
    static final int DATABASE_VERSION = 1;

    // sql query to create the table
    static final String CREATE_DB_TABLE = " CREATE TABLE " + TABLE_NAME
            + " ( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + " mealPlanName TEXT NOT NULL, "
            + " breakfastDay1 TEXT, "
            + " lunchDay1 TEXT, "
            + " dinnerDay1 TEXT, "
            + " snackDay1 TEXT, "
            + " breakfastDay2 TEXT, "
            + " lunchDay2 TEXT, "
            + " dinnerDay2 TEXT, "
            + " snackDay2 TEXT, "
            + " breakfastDay3 TEXT, "
            + " lunchDay3 TEXT, "
            + " dinnerDay3 TEXT, "
            + " snackDay3 TEXT, "
            + " breakfastDay4 TEXT, "
            + " lunchDay4 TEXT, "
            + " dinnerDay4 TEXT, "
            + " snackDay4 TEXT, "
            + " breakfastDay5 TEXT, "
            + " lunchDay5 TEXT, "
            + " dinnerDay5 TEXT, "
            + " snackDay5 TEXT);";

    // creating a database
    private static class DatabaseHelper extends SQLiteOpenHelper {

        // defining a constructor
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        // creating a table in the database
        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            // sql query to drop a table
            // having similar name
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
