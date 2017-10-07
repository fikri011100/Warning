package com.faishalbadri.hijab;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.faishalbadri.hijab.Helper.Server;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;
import java.io.IOException;
import java.util.UUID;

public class ProfileActivity extends AppCompatActivity {

  Button btnIntentGaleri;
  Button btnPostPhoto;
  ImageView imgUserPhoto;

  private int PICK_IMAGE_REQUEST = 1;

  private static final int STORAGE_PERMISSION_CODE = 123;

  private Bitmap bitmap;
  String id;
  //Uri to store the image uri
  private Uri filePath;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    id = getIntent().getStringExtra("email");
    Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    requestStoragePermission();
    btnIntentGaleri=  (Button)findViewById(R.id.btn_intent_galeri);
    btnPostPhoto=  (Button)findViewById(R.id.btn_post_photo);
    imgUserPhoto = (ImageView)findViewById(R.id.img_user_photo);
    btnIntentGaleri.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        showFileChooser();
        btnIntentGaleri.setEnabled(true);
      }
    });
    btnPostPhoto.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        uploadMultipart();
      }
    });

  }

  public void uploadMultipart() {
    String path = getPath(filePath);
    try {
      String uploadId = UUID.randomUUID().toString();

      new MultipartUploadRequest(this, uploadId, Server.BASE_URL+"uploadimage.php")
          .addFileToUpload(path, "image")
          .addParameter("id", id)
          .setNotificationConfig(new UploadNotificationConfig())
          .setMaxRetries(2)
          .startUpload();
    } catch (Exception exc) {
      Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
    }

  }

  private void showFileChooser() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);


    if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
      filePath = data.getData();
      try {
        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
        imgUserPhoto.setImageBitmap(bitmap);

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public String getPath(Uri uri) {
    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
    cursor.moveToFirst();
    String document_id = cursor.getString(0);
    document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
    cursor.close();

    cursor = getContentResolver().query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
    cursor.moveToFirst();
    String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
    cursor.close();

    return path;
  }

  private void requestStoragePermission() {
    if (ContextCompat
        .checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
      return;

    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

    }
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    if (requestCode == STORAGE_PERMISSION_CODE) {

      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
      } else {

        Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
      }
    }
  }
}