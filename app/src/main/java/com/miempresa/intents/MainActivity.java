package com.miempresa.intents;

//Intents implicitos para comunicarnos con aplicaciones externas

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {

    public static final int PHOTO_REQUEST_CODE = 100;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageViewPicture);
    }

    public void openNav(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(intent);
    }

    public void openMaps(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-34.603508,-58.381581?z=15&q="+Uri.encode(" OBELISCO ")));
        startActivity(intent);
    }

    public void openTel(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:45556552"));
        startActivity(intent);
    }

    public void openSMS(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);


        intent.setType("vnd.android-dir/mms-sms");
        intent.setData(Uri.parse("smsto:"));
        intent.putExtra("address", new String ("1234567890"));
        intent.putExtra("sms_body","cuerpo sms");
        startActivity(intent);
    }

    public void openMail(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"test@test.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT,"subject");
        intent.putExtra(Intent.EXTRA_TEXT, "text mail");
        startActivity(intent);
    }

    public void openCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PHOTO_REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PHOTO_REQUEST_CODE && resultCode == RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
