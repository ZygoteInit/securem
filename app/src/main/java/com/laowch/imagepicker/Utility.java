package com.laowch.imagepicker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mua on 9/26/2015.
 */
public class Utility {


    //int[] sBoxm= new int[] {37,186,245,114,3,94,20,144,143,14,126,11,119,24,1,86,46,205,213,196,161,21,146,29,167,195,231,153,85,52,216,190,238,135,121,91,32,64,136,89,185,113,10,116,66,183,152,35,158,88,39,132,105,239,240,83,164,232,79,198,225,74,100,246,111,17,77,82,234,147,70,241,250,251,131,72,0,215,197,49,203,219,96,122,139,202,218,178,255,84,54,220,180,252,92,26,160,134,97,12,22,149,53,248,50,212,169,48,206,201,181,34,80,69,81,9,15,174,33,78,221,141,112,210,217,87,193,165,182,98,93,60,230,156,8,109,166,36,173,237,27,117,25,157,142,13,123,41,233,253,62,44,95,19,140,106,189,127,172,227,200,229,154,68,243,57,194,222,73,101,177,170,242,223,138,249,56,150,99,42,4,115,107,191,133,7,59,228,31,171,61,71,226,118,2,38,187,5,51,192,235,18,148,47,207,209,224,155,55,247,28,163,179,120,129,137,175,244,45,145,65,108,30,67,159,176,102,75,16,125,130,204,184,23,151,236,188,43,124,103,208,168,58,63,76,254,214,199,110,6,104,128,90,40,211,162};
    public static  int[] sBoxm = new int[] {37,186,245,114,3,94,20,144,143,14,126,11,119,24,1,86,46,205,213,196,161,21, 146, 29, 167, 195, 231, 153, 85, 52, 216, 190, 238,135,121,91,32,64,136,89,185,113,10,116,66,183,152,35,158,88,39,132,105,239,240,83,164,232,79,198,225,74,100,246,111,17,77,82,234,147,70,241,250,251,131,72,0,215,197,49,203,219,96,122,139,202,218,178,255,84,54,220,180,252,92,26,160,134,97,12,22,149,53,248,50,212,169,48,206,201,181,34,80,69,81,9,15,174,33,78,221,141,112,210,217,87,193,165,182,98,93,60,230,156,8,109,166,36,173,237,27,117,25,157,142,13,123,41,233,253,62,44,95,19,140,106,189,127,172,227,200,229,154,68,243,57,194,222,73,101,177,170,242,223,138,249,56,150,99,42,4,115,107,191,133,7,59,228,31,171,61,71,226,118,2,38,187,5,51,192,235,18,148,47,207,209,224,155,55,247,28,163,179,120,129,137,175,244,45,145,65,108,30,67,159,176,102,75,16,125,130,204,184,23,151,236,188,43,124,103,208,168,58,63,76,254,214,199,110,6,104,128,90,40,211,162};
    // int[] sBoxm= new int[] {38,187,246,115,4,95,21,145,144,15,127,12,120,25,2,87,47,206,214,197,162,22,147,30,168,196,232,154,86,53,217,191,239,136,122,92,33,65,137,90,186,114,11,117,67,184,153,36,159,89,40,133,106,240,241,84,165,233,80,199,226,75,101,247,112,18,78,83,235,148,71,242,251,252,132,73,1,216,198,50,204,220,97,123,140,203,219,179,256,85,55,221,181,253,93,27,161,135,98,13,23,150,54,249,51,213,170,49,207,202,182,35,81,70,82,10,16,175,34,79,222,142,113,211,218,88,194,166,183,99,94,61,231,157,9,110,167,37,174,238,28,118,26,158,143,14,124,42,234,254,63,45,96,20,141,107,190,128,173,228,201,230,155,69,244,58,195,223,74,102,178,171,243,224,139,250,57,151,100,43,5,116,108,192,134,8,60,229,32,172,62,72,227,119,3,39,188,6,52,193,236,19,149,48,208,210,225,156,56,248,29,164,180,121,130,138,176,245,46,146,66,109,31,68,160,177,103,76,17,126,131,205,185,24,152,237,189,44,125,104,209,169,59,64,77,255,215,200,111,7,105,129,91,41,212,163};


    public static String showCamera="showcamera";
    public static String decryptImage="decrypt";
    public static String encryptImage="encrypt";
    public static int bitmapWidth=256;
    public static int bitmapHeight=256;


    public static int[][] getPixelatedImage(Bitmap bitmap)
    {
        int mwidth=bitmap.getWidth(),mheight=bitmap.getHeight();
        int[][] imagePixels= new int[mwidth][mheight];
        for(int height=0;height<mheight;height++)
        {
            for(int width=0;width<mwidth;width++)
            {
                //imagePixels[width][height]=getLastBytes(mBitmapimage.getPixel(width, height));
                imagePixels[width][height]=bitmap.getPixel(width, height);
                // Log.d("Pixels row:"+width+"&column:"+height, getLastBytes(imagePixels[width][height])+"");
            }
        }

        return imagePixels;
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int width, int height) {
        final int bitmapWidth = bitmap.getWidth();
        final int bitmapHeight = bitmap.getHeight();

        final float scale = Math.min((float) width / (float) bitmapWidth,
                (float) height / (float) bitmapHeight);

        final int scaledWidth = (int) (bitmapWidth * scale);
        final int scaledHeight = (int) (bitmapHeight * scale);

        final Bitmap decoded = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
        final Canvas canvas = new Canvas(decoded);

        return decoded;
    }

//    public void getSBox(int[][] pixels)
//    {
//
//
//
//        //int[] sBoxm2 = new int[] {37,186,245,114,3,94,20,144,143,14,126,11,119,24,1,86,46,205,213,196,161,21,146,29,167,195,231,153,85,52,216,190,238,135,121,91,32,64,136,89,185,113,10,116,66,183,152,35,158,88,39,132,105,239,240,83,164,232,79,198,225,74,100,246,111,17,77,82,234,147,70,241,250,251,131,72,0,215,197,49,203,219,96,122,139,202,218,178,255,84,54,220,180,252,92,26,160,134,97,12,22,149,53,248,50,212,169,48,206,201,181,34,80,69,81,9,15,174,33,78,221,141,112,210,217,87,193,165,182,98,93,60,230,156,8,109,166,36,173,237,27,117,25,157,142,13,123,41,233,253,62,44,95,19,140,106,189,127,172,227,200,229,154,68,243,57,194,222,73,101,177,170,242,223,138,249,56,150,99,42,4,115,107,191,133,7,59,228,31,171,61,71,226,118,2,38,187,5,51,192,235,18,148,47,207,209,224,155,55,247,28,163,179,120,129,137,175,244,45,145,65,108,30,67,159,176,102,75,16,125,130,204,184,23,151,236,188,43,124,103,208,168,58,63,76,254,214,199,110,6,104,128,90,40,211,162};
//
//        //ENCRYPTION BEGINS HERE
//        for(int i=0;i<=255;i++)
//        {
//            for(int j=0;j<=255;j++)
//            {
//                //if(j>255)
//                //   j=j-1;
//
//                int x=pixels[i][j];
//                int y=sBoxm[j];
//                subs[i][y]= x;
//            }
//        }
//
//
//        //      Bitmap compare = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);
//
//        /// DECRYPTION BEGINS HERE
//        for(int i=0;i<=255;i++)
//        {
//            for(int j=0;j<=255;j++)
//            {
//                // if(j>255)
//                //   j=j-1;
//
//                int x=subs[i][sBoxm[j]];
//                subsfinal[i][j]= x;
//            }
//        }
//
//
//       // image.setImageBitmap(compare);
//
//        // Log.d("Arrays are equal ", String.valueOf(Arrays.deepEquals(pixels, subs)));
//
//    }


    public static Bitmap encryptImage(int[][] pixels)
    {
         int[][] subs= new int[256][256];
        // int[][] subsfinal= new int[256][256];

        //ENCRYPTION BEGINS HERE
        for(int i=0;i<=255;i++)
        {
            for(int j=0;j<=255;j++)
            {
                //if(j>255)
                //   j=j-1;

                int x=pixels[i][j];
                int y=sBoxm[j];
                subs[i][y]= x;
            }
        }

        // Putting pixels back to bitmap
        Bitmap compare = Bitmap.createBitmap(256, 256, Bitmap.Config.RGB_565);
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {

                compare.setPixel(x, y, subs[x][y]);
            }
        }

        return compare;
    }


    public static Bitmap decryptImage(int[][] pixels)
    {

        int[][] subsfinal= new int[256][256];

        /// DECRYPTION BEGINS HERE
        for(int i=0;i<=255;i++)
        {
            for(int j=0;j<=255;j++)
            {
                // if(j>255)
                //   j=j-1;

                int y=sBoxm[j];
                int x=pixels[i][y];
                subsfinal[i][j]= x;
            }
        }

        // Putting pixels back to bitmap
        Bitmap compare = Bitmap.createBitmap(256, 256, Bitmap.Config.RGB_565);
        for (int x = 0; x < 256; x++) {
            for (int y = 0; y < 256; y++) {

                compare.setPixel(x, y, subsfinal[x][y]);
            }
        }

        return compare;
    }

    public static File saveBitmapToSD(Context context,Bitmap bitmap)
    {
        String picDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()).toString();
        String year = picDate.substring(0, 4);
        String month = picDate.substring(4, 6);
        String day = picDate.substring(6, 8);
       String fileName = year + "-" + month + "-" + day;
        String path = Environment.getExternalStorageDirectory().toString();
        OutputStream fOut = null;
        File file=null;

        try {
            file = new File(path,fileName+".jpg"); // the File to save to
            fOut = new FileOutputStream(file);
            //Bitmap pictureBitmap = getImageBitmap(myurl); // obtaining the Bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            fOut.flush();
            fOut.close(); // do not forget to close the stream
            MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }

    public static Bitmap getBitmapFromFilePath(File file)
    {

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inSampleSize=2;
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        bitmap = Bitmap.createScaledBitmap(bitmap, bitmapWidth, bitmapHeight, true);
        //imageView.setImageBitmap(bitmap);

        return bitmap;
    }

    public static void SendEmail(final Context context,File file) {

        final Uri uri= Uri.fromFile(file);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Email");
        alertDialog.setMessage("Enter email address");

        final EditText input = new EditText(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input); // uncomment this line

        alertDialog.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (uri != null) {
                            String email = input.getText().toString();
                            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                            emailIntent.setType("application/image");
                            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]
                                    {email});
                            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Encrypted" +
                                    " " +
                                    "Image");
                            emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "From My App");
                            //Uri uri = Uri.fromFile(file);
                            emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
                            context.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                        } else {
                            Toast.makeText(context, "Please take an image first.", Toast
                                    .LENGTH_SHORT).show();
                        }

                    }
                });

        alertDialog.setNegativeButton("NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }
}
