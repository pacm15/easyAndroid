package com.desarrollador.easytour;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.desarrollador.BD.UbicacionDBHelper;
import com.desarrollador.BD.UbicacionManager;

import java.io.File;


public class Camera extends AppCompatActivity {
    public static final String DIR_TAG = "Tag dir";
    private String APP_DIRECTORY = "pictureApp";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";

    private final int PHOTO_CODE = -1;
    private final int SELECT_PICTURE = 200;


    private ImageView imageView;
    private Button btn;

    private UbicacionManager ubicacionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = (ImageView) findViewById(R.id.img);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {"Tomar Foto", "Desde Galeria", "Cancelar"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(Camera.this);
                builder.setTitle("Elige una opcion");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (options[which] == "Tomar Foto") {
                            openCamera();
                        } else if (options[which] == "Elegir de galeria") {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            intent.setType("image/");
                            startActivityForResult(intent.createChooser(intent, "Selecciona app de imagen"), SELECT_PICTURE);
                        } else if (options[which] == "Cancelar") {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

    }

    private void openCamera() {
        File file = new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        file.mkdir();
        String path = Environment.getExternalStorageDirectory() + file.separator + MEDIA_DIRECTORY + file.separator + TEMPORAL_PICTURE_NAME;
        File newfile = new File(path);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newfile));
        startActivityForResult(intent, PHOTO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PHOTO_CODE:

                if(requestCode==RESULT_OK){
                    String DIR=Environment.getExternalStorageDirectory()
                            +File.separator+MEDIA_DIRECTORY
                            +File.separator+TEMPORAL_PICTURE_NAME;
                    decodeBitmap(DIR);
                    data.putExtra(DIR_TAG,DIR);
                }
                break;
            case SELECT_PICTURE:
                if(requestCode==RESULT_OK){
                    Uri path =data.getData();
                    imageView.setImageURI(path);
                    data.putExtra(DIR_TAG,path);

                }
                break;
        }
    }
    private void decodeBitmap(String dir) {
        Bitmap bitmap;
        bitmap= BitmapFactory.decodeFile(dir);
        imageView.setImageBitmap(bitmap);
    }

}
