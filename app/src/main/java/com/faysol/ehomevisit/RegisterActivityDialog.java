package com.faysol.ehomevisit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivityDialog extends Dialog {
    
        Context context;
    public RegisterActivityDialog(@NonNull Context context) {
        super(context);
        this.context=context;
    }
    Button registerbtn;
    TextView R1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_dialog);
        registerbtn = findViewById(R.id.Registerbtn);
        R1= findViewById(R.id.R1);
            registerbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    Toast.makeText(context, "you cannot register on your own. Please schedule an appointment with the practice to receive login data", Toast.LENGTH_SHORT).show();
                }
            });        
    }
}