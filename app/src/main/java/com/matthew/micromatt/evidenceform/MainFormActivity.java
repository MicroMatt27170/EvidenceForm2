package com.matthew.micromatt.evidenceform;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.matthew.micromatt.evidenceform.Models.Curp;

public class MainFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_form);
    }

    public void FormButtonClicked(View view) {
        try{
            int day = Integer.parseInt(((EditText)findViewById(R.id.FormInputBirthdayDay)).getText().toString());
            int month = Integer.parseInt(((EditText)findViewById(R.id.FormInputBirthdayMonth)).getText().toString());
            int year = Integer.parseInt(((EditText)findViewById(R.id.FormInputBirthdayYear)).getText().toString());


            String name = ((EditText)findViewById(R.id.FormInputName)).getText().toString();
            String fLastName = ((EditText)findViewById(R.id.FormInputFatherLastName)).getText().toString();
            String mLastName = ((EditText)findViewById(R.id.FormInputMotherLastName)).getText().toString();
            String gender =((RadioButton)findViewById(((RadioGroup)findViewById(R.id.FormRadioGroupGender)).getCheckedRadioButtonId())).getText().toString();
            String state = ((Spinner)findViewById(R.id.FormSpinnerState)).getSelectedItem().toString();

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
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
