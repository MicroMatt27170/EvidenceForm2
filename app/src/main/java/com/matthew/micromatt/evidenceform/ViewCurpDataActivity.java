package com.matthew.micromatt.evidenceform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.matthew.micromatt.evidenceform.Models.Curp;

public class ViewCurpDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_curp_data);

        Curp curp = getIntent().getParcelableExtra("curp");

        ((TextView)findViewById(R.id.DisplayName)).setText(curp.getName());
        ((TextView)findViewById(R.id.DisplayFatherLastName)).setText(curp.getFatherLastName());
        ((TextView)findViewById(R.id.DisplayMotherLastName)).setText(curp.getMotherLastName());
        ((TextView)findViewById(R.id.DisplayGender)).setText(curp.getGender());
        ((TextView)findViewById(R.id.DisplayBirthday)).setText(curp.getBirthdayString());
        ((TextView)findViewById(R.id.DisplayState)).setText(curp.getState());

        ((TextView)findViewById(R.id.DisplayCURP)).setText(curp.getCurp());

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
