package com.faishalbadri.hijab.FragmentDialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import com.faishalbadri.hijab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentExitDialog extends DialogFragment {


  public FragmentExitDialog() {
    // Required empty public constructor
  }

  private Button btn_yes,btn_no;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_exit_dialog, container, false);
    btn_no = (Button)v.findViewById(R.id.btn_no_exit);
    btn_yes = (Button)v.findViewById(R.id.btn_yes_exit);
    btn_yes.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        ((Activity)getActivity()).finish();
      }
    });
    btn_no.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        dismiss();
      }
    });
    return v;
  }

  @Override
  public void onStart() {
    super.onStart();
    Dialog dialog = getDialog();
    if (dialog != null) {
      int width = LayoutParams.MATCH_PARENT;
      int height = LayoutParams.WRAP_CONTENT;
      dialog.getWindow().setLayout(width, height);
    }
  }

}
