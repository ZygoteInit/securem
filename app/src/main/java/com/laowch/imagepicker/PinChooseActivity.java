package com.laowch.imagepicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PinChooseActivity extends Activity implements View.OnClickListener  {

    EditText edittextpin,editTextconfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_choose);

        edittextpin= (EditText)findViewById(R.id.editTextenter);
        editTextconfirm= (EditText)findViewById(R.id.editTextconfirm);

        findViewById(R.id.btnpin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(edittextpin.getText().length()>0 && editTextconfirm.getText().length()>0)
        {
            if(editTextconfirm.getText().toString().equals(edittextpin.getText().toString()))
            {
                SharedPreferenceManager.saveUserData(editTextconfirm.getText().toString(),PinChooseActivity.this);
                Intent intent= new Intent(PinChooseActivity.this,LaunchMenu.class);
                startActivity(intent);
                finish();
            }
        }

    }
}
