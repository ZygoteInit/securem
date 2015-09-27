package com.laowch.imagepicker;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AppLogin extends Activity  implements View.OnClickListener  {

    EditText editPin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_login);
        editPin= (EditText)findViewById(R.id.editTextpin);
        findViewById(R.id.btnlogin).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(editPin.getText().length()>0)
        {
           if(editPin.getText().toString().equals(SharedPreferenceManager.getStringPreferences("Pin",AppLogin.this,"0")))
           {
               Intent intent= new Intent(AppLogin.this,LaunchMenu.class);
               startActivity(intent);
           }
        }
    }
}
