package com.faishalbadri.hijab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.rengwuxian.materialedittext.MaterialEditText;

public class KritikSaran extends AppCompatActivity {

  private static final String EMAIL_PINKY = "pinkyhijabdeveloper@gmail.com";
  private static final String SUBJECT_PINKY = "Saran";
  MaterialEditText edtSaran;
  Button btnSend;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_kritik_saran);
    edtSaran = (MaterialEditText) findViewById(R.id.edt_saran);
    btnSend = (Button) findViewById(R.id.btn_send);
    btnSend.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        getIntentEmail();
      }
    });
  }

  private void getIntentEmail() {
    String saran = edtSaran.getText().toString();
    Intent email = new Intent(Intent.ACTION_SEND);
    email.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAIL_PINKY});
    email.putExtra(Intent.EXTRA_SUBJECT, SUBJECT_PINKY);
    email.putExtra(Intent.EXTRA_TEXT, saran);
    email.setType("message/rfc822");
    startActivity(Intent.createChooser(email,
        "Choose an Email client"));
  }
}
