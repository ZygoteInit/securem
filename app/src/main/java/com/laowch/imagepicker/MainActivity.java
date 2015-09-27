package com.laowch.imagepicker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {


    private static final int REQUEST_CODE_TAKEN_PHOTO_GALLERY = 0x02;
    private static final int PICTURE_ACTIVITY_CODE = 111;
    private static File file=null;
    LinearLayout imageLayout;
    boolean showCamera=false;
    Button btncamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showCamera= getIntent().getBooleanExtra(Utility.showCamera,false);
        findViewById(R.id.add_picture).setOnClickListener(this);
        findViewById(R.id.btn_sendemail).setOnClickListener(this);
        imageLayout = (LinearLayout) findViewById(R.id.image_layout);
        btncamera=(Button)findViewById(R.id.take_picture);
        if(showCamera) {
            btncamera.setOnClickListener(this);
        }
        else
        {
            btncamera.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.add_picture:
//                onTakenGalleryPhoto(v);
//                break;
//            case R.id.take_picture:
//                onTakenGalleryPhoto(v);
//                break;
//
//        }

        onTakenGalleryPhoto(v);
    }


    public void onTakenGalleryPhoto(View view) {
        switch (view.getId()) {
            case R.id.take_picture: {
                final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, PICTURE_ACTIVITY_CODE);
                break;
            }
            case R.id.add_picture: {
                final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setClass(this, ImagePickerActivity.class);
                    intent.setType("image/*");
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(intent, REQUEST_CODE_TAKEN_PHOTO_GALLERY);
               // startActivityForResult(intent, PICTURE_ACTIVITY_CODE);
                break;
            }
            case R.id.btn_sendemail:
            {
                Utility.SendEmail(MainActivity.this,file);
            }

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {

                case REQUEST_CODE_TAKEN_PHOTO_GALLERY: {
                    if (data != null) {
                        if (data.getParcelableArrayListExtra("uris") != null) {
                            List<Uri> uriList = data.getParcelableArrayListExtra("uris");
                            for (int i = 0; i < uriList.size(); i++) {

                                new GetBitmapFromUriTask(this, uriList.get(i), new GetBitmapFromUriTask.IOnImageTakenListener() {
                                    @Override
                                    public void onImageTaken(final Bitmap pBitmap) {
                                        MainActivity.this.onImageTaken(pBitmap);
                                    }
                                }).execute();
                            }
                        } else {
                            new GetBitmapFromUriTask(this, data.getData(), new GetBitmapFromUriTask.IOnImageTakenListener() {
                                @Override
                                public void onImageTaken(final Bitmap pBitmap) {
                                    MainActivity.this.onImageTaken(pBitmap);
                                }
                            }).execute();
                        }

                        break;
                    }
                }
                case PICTURE_ACTIVITY_CODE: {

                    if (data != null) {
                        if (data.getParcelableArrayListExtra("uris") != null) {
                            List<Uri> uriList = data.getParcelableArrayListExtra("uris");
                            for (int i = 0; i < uriList.size(); i++) {

                                new GetBitmapFromUriTask(this, uriList.get(i), new GetBitmapFromUriTask.IOnImageTakenListener() {
                                    @Override
                                    public void onImageTaken(final Bitmap pBitmap) {
                                        MainActivity.this.onImageTaken(pBitmap);
                                    }
                                }).execute();
                            }
                        } else {
                            new GetBitmapFromUriTask(this, data.getData(), new GetBitmapFromUriTask.IOnImageTakenListener() {
                                @Override
                                public void onImageTaken(final Bitmap pBitmap) {
                                    MainActivity.this.onImageTaken(pBitmap);
                                }
                            }).execute();
                        }

                    }
                }
            }
        }
    }

    private void onImageTaken(Bitmap pBitmap) {
        if (pBitmap == null) {
            Toast.makeText(this, "image decode error", Toast.LENGTH_LONG).show();
            return;
        }
        DisplayMetrics dm = getResources().getDisplayMetrics();

//        int width = dm.widthPixels;
//        int height = pBitmap.getHeight() * dm.widthPixels / pBitmap.getWidth();
        int width = 256;
        int height = 256;

        Bitmap eBitmap=null;
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        //imageView.setImageBitmap(BitmapUtils.resizeBitmap(pBitmap, width, height));
        if(pBitmap.getWidth()>width || pBitmap.getHeight()>height) {
            pBitmap = BitmapUtils.resizeBitmap(pBitmap, width, height);
        }
        int[][] pixelatedImage=Utility.getPixelatedImage(pBitmap);

        if(showCamera) {
            eBitmap    = Utility.encryptImage(pixelatedImage);
        }
        else {
            eBitmap    = Utility.decryptImage(pixelatedImage);
        }


        imageView.setImageBitmap(eBitmap);

       file= Utility.saveBitmapToSD(MainActivity.this, eBitmap);


        imageLayout.addView(imageView);

        findViewById(R.id.btn_sendemail).setVisibility(View.VISIBLE);
    }
}
