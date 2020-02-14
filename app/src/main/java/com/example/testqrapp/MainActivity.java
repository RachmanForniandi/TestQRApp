package com.example.testqrapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.zxing.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private CodeScanner scannerTest;

    /*@BindView(R.id.scanner_view_test)*/
    CodeScannerView scannerView;

    @BindView(R.id.bottom_sheet_result)
    LinearLayout layoutBottomSheetResult;

    BottomSheetBehavior sheetResultScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ButterKnife.bind(this);
        scannerView = findViewById(R.id.scanner_view_test);

        scannerTest = new CodeScanner(this,scannerView);

        showResultScanner();

        /*sheetResultScanner = BottomSheetBehavior.from(layoutBottomSheetResult);

         sheetResultScanner.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                switch (i){
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {

                        showResultScanner();
                    }
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });*/
    }

    private void showResultScanner() {

        scannerTest.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable(){

                    @Override
                    public void run() {
                       //Toast.makeText(MainActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putString("result",result.getText());
                        BottomSheetFragment bsf= new BottomSheetFragment();
                        bsf.setArguments(bundle);
                        bsf.show(getSupportFragmentManager(),bsf.getTag());
                    }
                });
            }
        });

        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scannerTest.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerTest.startPreview();
    }

    @Override
    protected void onPause() {
        scannerTest.releaseResources();
        super.onPause();
    }
}
