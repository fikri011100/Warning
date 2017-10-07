package com.faishalbadri.hijab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class ProfileActivity extends AppCompatActivity {

  Bitmap bitmap;

  Boolean check = true;
  ProgressDialog pd;

  String imageName = "image_name";

  String imagePath = "image_path";

  String serverUploadPath = "http://api.santriprogrammer.com/hijab/library/editimguser.php";

  Button btnIntentGaleri;
  Button btnPostPhoto;
  ImageView imgUserPhoto;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    ButterKnife.bind(this);
    btnIntentGaleri = (Button)findViewById(R.id.btn_intent_galeri);
    btnPostPhoto = (Button)findViewById(R.id.btn_post_photo);
    imgUserPhoto = (ImageView)findViewById(R.id.img_user_photo);
    btnIntentGaleri.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        intentGalery();
      }
    });
    btnPostPhoto.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        postPhoto();
      }
    });
  }

  @Override
  protected void onActivityResult(int RC, int RQC, Intent I) {
    super.onActivityResult(RC, RQC, I);
    if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

      Uri uri = I.getData();

      try {

        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

        imgUserPhoto.setImageBitmap(bitmap);

      } catch (IOException e) {

        e.printStackTrace();
      }
    }
  }


  private void postPhoto() {
    ByteArrayOutputStream byteArrayOutputStreamObject ;

    byteArrayOutputStreamObject = new ByteArrayOutputStream();

    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStreamObject);

    byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

    final String ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

    class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

      @Override
      protected void onPreExecute() {

        super.onPreExecute();

        pd = ProgressDialog.show(ProfileActivity.this,"Image is Uploading","Please Wait",false,false);
      }

      @Override
      protected void onPostExecute(String string1) {

        super.onPostExecute(string1);

        // Dismiss the progress dialog after done uploading.
        pd.dismiss();

        // Printing uploading success message coming from server on android app.
        Toast.makeText(ProfileActivity.this,string1,Toast.LENGTH_LONG).show();

        // Setting image as transparent after done uploading.
        imgUserPhoto.setImageResource(android.R.color.transparent);


      }

      @Override
      protected String doInBackground(Void... params) {

        ImageProcessClass imageProcessClass = new ImageProcessClass();

        HashMap<String,String> HashMapParams = new HashMap<String,String>();

        HashMapParams.put(imageName, "name");

        HashMapParams.put(imagePath, ConvertImage);

        String FinalData = imageProcessClass.ImageHttpRequest(serverUploadPath, HashMapParams);

        return FinalData;
      }
    }
    AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();

    AsyncTaskUploadClassOBJ.execute();
  }

  public class ImageProcessClass{

    public String ImageHttpRequest(String requestURL,HashMap<String, String> PData) {

      StringBuilder stringBuilder = new StringBuilder();

      try {

        URL url;
        HttpURLConnection httpURLConnectionObject ;
        OutputStream OutPutStream;
        BufferedWriter bufferedWriterObject ;
        BufferedReader bufferedReaderObject ;
        int RC ;

        url = new URL(requestURL);

        httpURLConnectionObject = (HttpURLConnection) url.openConnection();

        httpURLConnectionObject.setReadTimeout(19000);

        httpURLConnectionObject.setConnectTimeout(19000);

        httpURLConnectionObject.setRequestMethod("POST");

        httpURLConnectionObject.setDoInput(true);

        httpURLConnectionObject.setDoOutput(true);

        OutPutStream = httpURLConnectionObject.getOutputStream();

        bufferedWriterObject = new BufferedWriter(

            new OutputStreamWriter(OutPutStream, "UTF-8"));

        bufferedWriterObject.write(bufferedWriterDataFN(PData));

        bufferedWriterObject.flush();

        bufferedWriterObject.close();

        OutPutStream.close();

        RC = httpURLConnectionObject.getResponseCode();

        if (RC == HttpsURLConnection.HTTP_OK) {

          bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

          stringBuilder = new StringBuilder();

          String RC2;

          while ((RC2 = bufferedReaderObject.readLine()) != null){

            stringBuilder.append(RC2);
          }
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
      return stringBuilder.toString();
    }

    private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

      StringBuilder stringBuilderObject;

      stringBuilderObject = new StringBuilder();

      for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {

        if (check)

          check = false;
        else
          stringBuilderObject.append("&");

        stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

        stringBuilderObject.append("=");

        stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
      }

      return stringBuilderObject.toString();
    }

  }

  private void intentGalery() {
    Intent intent = new Intent();

    intent.setType("image/*");

    intent.setAction(Intent.ACTION_GET_CONTENT);

    startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);
  }
}
