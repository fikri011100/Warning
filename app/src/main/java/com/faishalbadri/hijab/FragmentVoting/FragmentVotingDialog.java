package com.faishalbadri.hijab.FragmentVoting;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import com.faishalbadri.hijab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentVotingDialog extends DialogFragment {


  public FragmentVotingDialog() {
    // Required empty public constructor
  }

  String id, nama, img;
  Double like;
  TextView txt_nama_voting;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_dialog_voting, container, false);
    Bundle mArgs = getArguments();
    nama = mArgs.getString("nama");
    id = mArgs.getString("id");
    img = mArgs.getString("img");
    like = mArgs.getDouble("like");
    txt_nama_voting = (TextView)v.findViewById(R.id.txt_nama_voting);
    txt_nama_voting.setText(nama);

    return v;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    return dialog;
  }

  @Override
  public void onStart() {
    super.onStart();
    Dialog dialog = getDialog();
    if (dialog != null) {
      int width = ViewGroup.LayoutParams.MATCH_PARENT;
      int height = LayoutParams.WRAP_CONTENT;
      dialog.getWindow().setLayout(width, height);
    }
  }
}
