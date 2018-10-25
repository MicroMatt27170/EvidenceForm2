package com.matthew.micromatt.evidenceform.Models;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;

import com.matthew.micromatt.evidenceform.DatabaseHandler.CurpContract;
import com.matthew.micromatt.evidenceform.DatabaseHandler.CurpDbHelper;
import com.matthew.micromatt.evidenceform.DatabaseHandler.CurpContract.CurpEntry;

import java.util.GregorianCalendar;
import java.util.ArrayList;

public class Curp  implements Parcelable{

    private long id;
    private String name;
    private String fatherLastName;
    private String motherLastName;
    private String gender;
    private int birthdayYear;
    private int birthdayMonth;
    private int birthdayDay;
    private String state;

    public Curp(){
        this.id = 0;
        this.name = "";
        this.fatherLastName = "";
        this.motherLastName = "";
        this.gender = "";
        this.birthdayYear = 1900;
        this.birthdayMonth = 1;
        this.birthdayDay = 1;
        this.state = "";
    }

    private Curp(Parcel in){
        this.id = 0;
        this.name = in.readString();
        this.fatherLastName = in.readString();
        this.motherLastName = in.readString();
        this.gender = in.readString();
        this.birthdayYear = in.readInt();
        this.birthdayMonth = in.readInt();
        this.birthdayDay = in.readInt();
        this.state = in.readString();
    }

    public Curp(String name, String fatherLastName, String motherLastName, String gender, int day, int month, int year, String state){
        this.name = name;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
        this.gender = gender;
        this.birthdayYear = year;
        this.birthdayMonth = month;
        this.birthdayDay = day;
        this.state = state;
    }

    public Curp(String name, String fatherLastName, String motherLastName, String gender, int day, int month, int year, String state, long id){
        this.id = id;
        this.name = name;
        this.fatherLastName = fatherLastName;
        this.motherLastName = motherLastName;
        this.gender = gender;
        this.birthdayYear = year;
        this.birthdayMonth = month;
        this.birthdayDay = day;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GregorianCalendar getBirthday() {
        return new GregorianCalendar(birthdayYear, birthdayMonth, birthdayDay);
    }

    public String getFatherLastName() {
        return fatherLastName;
    }

    public String getGender() {
        return gender;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public String getState() {
        return state;
    }

    public int getBirthdayDay() {
        return birthdayDay;
    }

    public int getBirthdayMonth() {
        return birthdayMonth;
    }

    public int getBirthdayYear() {
        return birthdayYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(int year, int month, int day) {
        this.birthdayDay = day;
        this.birthdayMonth = month;
        this.birthdayYear = year;
    }

    public void setFatherLastName(String fatherLastName) {
        this.fatherLastName = fatherLastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(fatherLastName);
        parcel.writeString(motherLastName);
        parcel.writeString(gender);
        parcel.writeInt(birthdayYear);
        parcel.writeInt(birthdayMonth);
        parcel.writeInt(birthdayDay);
        parcel.writeString(state);
    }

    public static final Parcelable.Creator<Curp> CREATOR
            = new Parcelable.Creator<Curp>(){
        public Curp createFromParcel(Parcel in) {
            return new Curp(in);
        }

        @Override
        public Curp[] newArray(int i) {
            return new Curp[i];
        }
    };

    public String getCurp(){
        String c = "";
        c += (this.fatherLastName.toUpperCase().toCharArray())[0];
        c += (this.fatherLastName.toUpperCase().toCharArray())[1];
        c += (this.motherLastName.toUpperCase().toCharArray())[0];
        c += (this.name.toUpperCase().toCharArray())[0];
        c += String.format("%1$02d%2$02d%3$02d", this.birthdayYear, this.birthdayMonth, this.birthdayDay);

        return c;
    }

    public String getBirthdayString(){
        return String.format("%1$02d/%2$02d/%3$02d", this.birthdayDay, this.birthdayMonth, this.birthdayYear);
    }

    public void save(Context context){
        SaveInDatabaseArgs saveInDatabaseArgs = new SaveInDatabaseArgs(context);
        new SaveInDatabase().execute(saveInDatabaseArgs);
    }

    public static ArrayList<Curp> getCurps(Context context){
        DatabaseSelectArgs dsa = new DatabaseSelectArgs(context);
        ArrayList<Curp> curps = new getCurpsInDatabase().doInBackground(dsa);
        return curps;
    }

    public static ArrayList<Curp> getCurps(Context context, String selection, String[] selectionArgs){
        DatabaseSelectArgs dsa = new DatabaseSelectArgs(context, selection, selectionArgs);
        ArrayList<Curp> curps = new getCurpsInDatabase().doInBackground(dsa);
        return curps;
    }

    public static ArrayList<Curp> getCurps(Context context, String selection, String[] selectionArgs, String sortOrder){
        DatabaseSelectArgs dsa = new DatabaseSelectArgs(context, selection, selectionArgs, sortOrder);
        ArrayList<Curp> curps = new getCurpsInDatabase().doInBackground(dsa);
        return curps;
    }

    //ASYNC TASK CLASSES

    private class SaveInDatabaseArgs {
        private Context context;

        public SaveInDatabaseArgs(Context c){
            this.context = c;
        }

        public Context getContext() {
            return context;
        }
    }

    private static class DatabaseSelectArgs {
        private Context context;
        private String selection;
        private String[] selectionArgs;
        private String sortOrder;

        public final String ASC = CurpEntry.COLUMN_NAME_NAME +  " ASC";
        public final String DESC = CurpEntry.COLUMN_NAME_NAME + " DESC";

        public DatabaseSelectArgs(Context context){
            this.context = context;
            this.selection = "";
            String[] a = {};
            this.selectionArgs = a;
            this.sortOrder = this.ASC;
        }

        public DatabaseSelectArgs(Context context, String selection, String[] selectionArgs){
            this.context = context;
            this.selection = selection;
            this.selectionArgs = selectionArgs;
            this.sortOrder = this.ASC;
        }

        public DatabaseSelectArgs(Context context, String selection, String[] selectionArgs, String sortOrder) {
            this.context = context;
            this.selection = selection;
            this.selectionArgs = selectionArgs;
            this.sortOrder = sortOrder;
        }

        public Context getContext() {
            return context;
        }

        public String getSelection() {
            return selection;
        }

        public String[] getSelectionArgs() {
            return selectionArgs;
        }

        public String getSortOrder() {
            return sortOrder;
        }
    }

    private class SaveInDatabase extends AsyncTask<SaveInDatabaseArgs, Void, Void> {

        @Override
        protected Void doInBackground(SaveInDatabaseArgs... saveInDatabaseArgs) {
            Context context = saveInDatabaseArgs[0].getContext();
            CurpDbHelper curpDbHelper = new CurpDbHelper(context);
            SQLiteDatabase db = curpDbHelper.getWritableDatabase();
            //Create new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(CurpEntry.COLUMN_NAME_NAME, name);
            values.put(CurpEntry.COLUMN_NAME_FATHER_LAST_NAME, fatherLastName);
            values.put(CurpEntry.COLUMN_NAME_MOTHER_LAST_NAME, motherLastName);
            values.put(CurpEntry.COLUMN_NAME_GENDER, gender);
            values.put(CurpEntry.COLUMN_NAME_STATE, state);
            values.put(CurpEntry.COLUMN_NAME_BIRTHDAY_YEAR, birthdayYear);
            values.put(CurpEntry.COLUMN_NAME_BIRTHDAY_MONTH, birthdayMonth);
            values.put(CurpEntry.COLUMN_NAME_BIRTHDAY_DAY, birthdayDay);

            if (id == 0){
                Long ids = db.insert(CurpEntry.TABLE_NAME, CurpEntry._ID, values);
                id = ids;
            } else {
                String[] selectionArgs = { String.valueOf(id) };
                db.update(CurpEntry.TABLE_NAME, values, CurpEntry._ID + " = ?", selectionArgs);
            }
            return null;
        }
    }

    private static class getCurpsInDatabase extends AsyncTask<DatabaseSelectArgs, Void, ArrayList<Curp>> {

        @Override
        protected ArrayList<Curp> doInBackground(DatabaseSelectArgs... databaseSelectArgsArray) {
            DatabaseSelectArgs dsa = databaseSelectArgsArray[0];
            CurpDbHelper curpDbHelper = new CurpDbHelper(dsa.getContext());
            SQLiteDatabase db = curpDbHelper.getWritableDatabase();

            String[] projection = {
                    CurpEntry._ID,
                    CurpEntry.COLUMN_NAME_NAME,
                    CurpEntry.COLUMN_NAME_FATHER_LAST_NAME,
                    CurpEntry.COLUMN_NAME_MOTHER_LAST_NAME,
                    CurpEntry.COLUMN_NAME_GENDER,
                    CurpEntry.COLUMN_NAME_STATE,
                    CurpEntry.COLUMN_NAME_BIRTHDAY_YEAR,
                    CurpEntry.COLUMN_NAME_BIRTHDAY_MONTH,
                    CurpEntry.COLUMN_NAME_BIRTHDAY_DAY
            };

            Cursor cursor = db.query(
                    CurpEntry.TABLE_NAME,
                    projection,
                    dsa.getSelection(),
                    dsa.getSelectionArgs(),
                    null,
                    null,
                    dsa.getSortOrder()
            );

            ArrayList<Curp> items = new ArrayList<Curp>();
            while (cursor.moveToNext()) {
                long c_Id = cursor.getLong(cursor.getColumnIndexOrThrow(CurpEntry._ID));
                String c_Name = cursor.getString(cursor.getColumnIndexOrThrow(CurpEntry.COLUMN_NAME_NAME));
                String c_FatherLastName = cursor.getString(cursor.getColumnIndexOrThrow(CurpEntry.COLUMN_NAME_FATHER_LAST_NAME));
                String c_MotherLastName = cursor.getString(cursor.getColumnIndexOrThrow(CurpEntry.COLUMN_NAME_MOTHER_LAST_NAME));
                String c_Gender = cursor.getString(cursor.getColumnIndexOrThrow(CurpEntry.COLUMN_NAME_GENDER));
                String c_State = cursor.getString(cursor.getColumnIndexOrThrow(CurpEntry.COLUMN_NAME_STATE));
                int c_year = cursor.getInt(cursor.getColumnIndexOrThrow(CurpEntry.COLUMN_NAME_BIRTHDAY_YEAR));
                int c_month = cursor.getInt(cursor.getColumnIndexOrThrow(CurpEntry.COLUMN_NAME_BIRTHDAY_MONTH));
                int c_day = cursor.getInt(cursor.getColumnIndexOrThrow(CurpEntry.COLUMN_NAME_BIRTHDAY_DAY));

                Curp c = new Curp(c_Name, c_FatherLastName, c_MotherLastName, c_Gender, c_year, c_month, c_day, c_State, c_Id);

                items.add(c);
            }

            cursor.close();

            return items;
        }
    }
}



