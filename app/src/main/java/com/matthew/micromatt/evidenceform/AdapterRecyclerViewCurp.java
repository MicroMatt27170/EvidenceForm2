package com.matthew.micromatt.evidenceform;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.matthew.micromatt.evidenceform.Models.Curp;

public class AdapterRecyclerViewCurp extends RecyclerView.Adapter<AdapterRecyclerViewCurp.ViewHolderData> {

    private ArrayList<Curp> CurpList;

    public TextView Name, FatherLastName, MotherLastName, State, Gender, Birthday, Curp;
    public ImageView Image;

    public class ViewHolderData extends RecyclerView.ViewHolder {

        public ViewHolderData(@NonNull View itemView){
            super(itemView);
            Name = itemView.findViewById(R.id.rv_name);
            FatherLastName = itemView.findViewById(R.id.rv_father_last_name);
            MotherLastName = itemView.findViewById(R.id.rv_mother_last_name);
            State = itemView.findViewById(R.id.rv_state);
            Gender = itemView.findViewById(R.id.rv_gender);
            Birthday = itemView.findViewById(R.id.rv_birthday);
            Curp = itemView.findViewById(R.id.rv_curp_title);
            Image = itemView.findViewById(R.id.rv_image);
        }
    }

    public AdapterRecyclerViewCurp(ArrayList<Curp> curpList) {this.CurpList = curpList; }

    @NonNull
    @Override
    public AdapterRecyclerViewCurp.ViewHolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.curp_list, viewGroup, false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerViewCurp.ViewHolderData viewHolderData, int i) {
        Curp c = CurpList.get(i);

        Name.setText(c.getName());
        FatherLastName.setText(c.getFatherLastName());
        MotherLastName.setText(c.getMotherLastName());
        State.setText(c.getState());
        Gender.setText(c.getGender());
        Birthday.setText(c.getBirthdayString());
        Curp.setText(c.getCurp());

        Uri uri = Uri.parse(c.getPath());
        Image.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        try{
            return CurpList.size();
        } catch (Exception e){
            return 0;
        }
    }
}
