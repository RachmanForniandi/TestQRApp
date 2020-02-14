package com.example.testqrapp;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {

    //@BindView(R.id.txt_result_scanner)
    TextView txtResultScanner;
    Button btnToDetail;

    public BottomSheetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
        //ButterKnife.bind(this,view);
        txtResultScanner = view.findViewById(R.id.txt_result_scanner);
        btnToDetail = view.findViewById(R.id.btn_to_detail);

        String resultScan = getArguments().getString("result");
        txtResultScanner.setText(resultScan);

        btnToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Ke Detail", Toast.LENGTH_SHORT).show();
            }
        });
        return view;

    }

}
