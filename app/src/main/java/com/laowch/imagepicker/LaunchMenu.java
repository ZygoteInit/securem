package com.laowch.imagepicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;

public class LaunchMenu extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.securem_menu);

        findViewById(R.id.btn_decrypt).setOnClickListener(this);
        findViewById(R.id.btn_encrypt).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        Intent intent= new Intent(LaunchMenu.this,MainActivity.class);

        if(v.getId()==R.id.btn_decrypt) {
            intent.putExtra(Utility.showCamera,false);


        }
        else
        {
            //Show both icons
            intent.putExtra(Utility.showCamera,true);

        }

        startActivity(intent);


    }
}
