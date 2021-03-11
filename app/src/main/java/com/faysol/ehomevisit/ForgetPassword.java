package com.faysol.ehomevisit;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ForgetPassword extends Dialog {
    Context context;
    public ForgetPassword(@NonNull Context context) {
        super(context);
        this.context=context;
    }
Button forgetdialogbtn;
    TextView txt1,txt2,txt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        forgetdialogbtn = findViewById(R.id.okbtn);
                txt1 = findViewById(R.id.txt1);
                txt2 = findViewById(R.id.txt2);
                txt3 = findViewById(R.id.txt3);
            forgetdialogbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    Toast.makeText(context, "Please contact office@drgersch.de to request new login data.", Toast.LENGTH_SHORT).show();
                }
            });
    }
}