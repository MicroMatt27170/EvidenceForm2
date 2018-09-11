package com.matthew.micromatt.evidenceform.Models;


import android.os.Parcel;
import android.os.Parcelable;
import java.util.GregorianCalendar;

public class Curp  implements Parcelable{

    private String name;
    private String fatherLastName;
    private String motherLastName;
    private String gender;
    private int birthdayYear;
    private int birthdayMonth;
    private int birthdayDay;
    private String state;

    public Curp(){
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
}
