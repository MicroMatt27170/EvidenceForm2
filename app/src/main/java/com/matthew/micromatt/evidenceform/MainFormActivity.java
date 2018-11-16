package com.matthew.micromatt.evidenceform;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.matthew.micromatt.evidenceform.Models.Curp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFormActivity extends AppCompatActivity {

    private final int RESULT_LOAD_IMG = 1;
    private final int PICTURE_REQUEST_CODE = 2;

    EditText formInputBirthdayDay, formInputBirthdayMonth, formInputBirthdayYear, formInputName, formInputFatherLastName, formInputMotherLastName;
    RadioGroup formRadioGroupGender;
    Spinner formSpinnerState;
    ImageView formImageView;
    String path;
    Uri uriImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);

        formInputBirthdayDay = findViewById(R.id.FormInputBirthdayDay);
        formInputBirthdayMonth = findViewById(R.id.FormInputBirthdayMonth);
        formInputBirthdayYear = findViewById(R.id.FormInputBirthdayYear);
        formInputName = findViewById(R.id.FormInputName);
        formInputFatherLastName = findViewById(R.id.FormInputFatherLastName);
        formInputMotherLastName = findViewById(R.id.FormInputMotherLastName);
        formRadioGroupGender = findViewById(R.id.FormRadioGroupGender);
        formSpinnerState = findViewById(R.id.FormSpinnerState);
        formImageView = findViewById(R.id.ImageViewForm);

    }

    public void SaveCurpButton(View view) {
        try{
            int day = Integer.parseInt((formInputBirthdayDay).getText().toString());
            int month = Integer.parseInt((formInputBirthdayMonth).getText().toString());
            int year = Integer.parseInt((formInputBirthdayYear).getText().toString());


            String name = (formInputName).getText().toString();
            String fLastName = (formInputFatherLastName).getText().toString();
            String mLastName = (formInputMotherLastName).getText().toString();
            String gender =((RadioButton)findViewById((formRadioGroupGender).getCheckedRadioButtonId())).getText().toString();
            String state = (formSpinnerState).getSelectedItem().toString();
            String path = this.path;


            Curp curp = new Curp(name, fLastName, mLastName, gender, day, month, year, state, path);

            curp.save(this);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(R.string.save_curp_dialog);
            alertDialogBuilder.setMessage(R.string.save_curp_dialog_body).setCancelable(true);
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        catch (Exception e){
            AlertDialog alertDialog = new AlertDialog.Builder(MainFormActivity.this).create();
            alertDialog.setTitle(getString(R.string.alert_title_data_error));
            alertDialog.setMessage(getString(R.string.alert_body_data_error));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.alert_button_data_error),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuBack:
                finish();
                return true;
            case R.id.menuListView:
                Intent intent = new Intent(MainFormActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





    //UNIMPLEMENTED FUNCTOIONS

    public void FormButtonClicked(View view) {
        try{
            int day = Integer.parseInt((formInputBirthdayDay).getText().toString());
            int month = Integer.parseInt((formInputBirthdayMonth).getText().toString());
            int year = Integer.parseInt((formInputBirthdayYear).getText().toString());


            String name = (formInputName).getText().toString();
            String fLastName = (formInputFatherLastName).getText().toString();
            String mLastName = (formInputMotherLastName).getText().toString();
            String gender =((RadioButton)findViewById((formRadioGroupGender).getCheckedRadioButtonId())).getText().toString();
            String state = (formSpinnerState).getSelectedItem().toString();

            Curp curp = new Curp(name, fLastName, mLastName, gender, day, month, year, state);

            Intent intent = new Intent(MainFormActivity.this, ViewCurpDataActivity.class);
            intent.putExtra("curp", curp);
            startActivity(intent);
        }
        catch (Exception e){
            AlertDialog alertDialog = new AlertDialog.Builder(MainFormActivity.this).create();
            alertDialog.setTitle(getString(R.string.alert_title_data_error));
            alertDialog.setMessage(getString(R.string.alert_body_data_error));
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.alert_button_data_error),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

    }

    public void getImageFromGallery(View view) throws IOException {

        //Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivityForResult(takePictureIntent, RESULT_LOAD_IMG);

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                uriImage = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriImage);
                startActivityForResult(takePictureIntent, RESULT_LOAD_IMG);
            }
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK){
            path = uriImage.toString();
            formImageView.setImageURI(uriImage);

        }
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

}
