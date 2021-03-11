package com.faysol.ehomevisit;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.faysol.ehomevisit.SharedPrefrence_classes.Shared_Pref;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageView imageView, hideshow;
    CheckBox checkBox;
    EditText username, passWord;
    ImageButton messagebtn, mebtn, appointbtn, settingbtn;
    Button loginbtn, registerbtn;
    TextView tv1, tv2, Email, txtpres, forgetpassword, msgtv, metv, appointtv, settingtv;
    boolean lang_selected;
    Context context;
    Resources resources;
    ProgressBar pbar;
    //sharedpref
    Shared_Pref shared_pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        shared_pref = new Shared_Pref(MainActivity.this);

        pbar = findViewById(R.id.pbar);
        messagebtn = findViewById(R.id.messagebtn);
        mebtn = findViewById(R.id.mebtn);
        appointbtn = findViewById(R.id.appointbtn);
        settingbtn = findViewById(R.id.settingbtn);
        imageView = findViewById(R.id.imageView3);
        hideshow = findViewById(R.id.hideShow);
        checkBox = findViewById(R.id.cbsavepass);
        username = findViewById(R.id.username);
        passWord = findViewById(R.id.password);
        loginbtn = findViewById(R.id.loginbtn);
        registerbtn = findViewById(R.id.Registerbtn);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        Email = findViewById(R.id.Email);
        txtpres = findViewById(R.id.txtpres);
        forgetpassword = findViewById(R.id.forgetpassword);
        msgtv = findViewById(R.id.msgtv);
        metv = findViewById(R.id.metv);
        appointtv = findViewById(R.id.appointtv);
        settingtv = findViewById(R.id.settingtv);
        pbar.setVisibility(View.GONE);

        settingtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] Language = {"ENGLISH", "deutsch"};
                final int checkedItem;

                if (lang_selected) {
                    checkedItem = 0;
                } else {
                    checkedItem = 1;
                }

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select a Language...")
                        .setSingleChoiceItems(Language, checkedItem, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "" + which, Toast.LENGTH_SHORT).show();
                                settingtv.setText(Language[which]);
                                lang_selected = Language[which].equals("ENGLISH");
                                //if user select prefered language as English then
                                if (Language[which].equals("ENGLISH")) {
                                    context = LocaleHelper.setLocale(MainActivity.this, "en");
                                    resources = context.getResources();

                                    String languageToLoad = "en"; // your language
                                    Locale locale = new Locale(languageToLoad);
                                    Locale.setDefault(locale);
                                    Configuration config = new Configuration();
                                    config.locale = locale;
                                    getBaseContext().getResources().updateConfiguration(config,
                                            getBaseContext().getResources().getDisplayMetrics());
                                    MainActivity.this.setContentView(R.layout.activity_main);


                                    // text1.setText(resources.getString(R.string.language));
                                }
                                //if user select prefered language as Hindi then
                                if (Language[which].equals("deutsch")) {
                                    context = LocaleHelper.setLocale(MainActivity.this, "de");
                                    resources = context.getResources();
                                    String languageToLoad = "de"; // your language
                                    Locale locale = new Locale(languageToLoad);
                                    Locale.setDefault(locale);
                                    Configuration config = new Configuration();
                                    config.locale = locale;
                                    getBaseContext().getResources().updateConfiguration(config,
                                            getBaseContext().getResources().getDisplayMetrics());
                                    MainActivity.this.setContentView(R.layout.activity_main);


                                    // text1.setText(resources.getString(R.string.language));
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }

        });
        hideshow.bringToFront();
        appointbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager manager = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
                NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo mobileNetwork = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

                if (null != activeNetwork) {
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        Intent intent = new Intent(MainActivity.this, AppointmentActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        Intent intent = new Intent(MainActivity.this, AppointmentActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, NoInternet.class);
                    startActivity(intent);
                    finish();

                }
            }
        });
        mebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Please Login First", Toast.LENGTH_LONG).show();
            }
        });
        messagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Please Login First", Toast.LENGTH_LONG).show();
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivityDialog registerActivityDialog = new RegisterActivityDialog(MainActivity.this);
                registerActivityDialog.show();
            }
        });
        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ForgetPassword forgotpassDialog = new ForgetPassword(MainActivity.this);
                forgotpassDialog.show();
            }
        });
        hideshow.setOnClickListener(v -> {
            if (passWord.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                hideshow.setImageResource(R.drawable.eye_off);

                //Show Password
                passWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                hideshow.setImageResource(R.drawable.eye);

                //Hide Password
                passWord.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RunWhich();

            }
        });


    }

    private void RunWhich() {
        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Logging");
        pd.show();

        final String Email = username.getText().toString().trim();
        final String Pass = passWord.getText().toString().trim();


        if (TextUtils.isEmpty(Email)) {
            pd.hide();
            username.setError("Patient ID is required");
            return;
        }
        if (TextUtils.isEmpty(Pass)) {
            pd.hide();
            passWord.setError("Password is required");
            return;
        }


        if (Email.length() == 6) {
            pbar.setVisibility(View.VISIBLE);
            ConnectivityManager manager = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
            NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobileNetwork = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (null != activeNetwork) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                    Intent intent = new Intent(MainActivity.this, WebActivity.class);
                    String patient_number = username.getText().toString().trim();
                    String pass = passWord.getText().toString().trim();
                    //String url="https://www.ehausbesuch.de/app.cgi?action=app&userid=934298&passwort=35eM99379d&version=android";
                    String url = "https://www.ehausbesuch.de/app.cgi?action=app&userid=" + Email + "&passwort=" + Pass + "&version=android";
                    StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("clima", response);
                            if (!response.contains("session_id")) {
                                Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                pd.hide();
                                return;
                            }

                            Toast.makeText(MainActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                            pd.hide();
                            Bundle bundle = new Bundle();
                            bundle.putString("id", patient_number);
                            bundle.putString("password", pass);
                            intent.putExtras(bundle);

                            if (checkBox.isChecked()) {
                                shared_pref.set_loginstatus(true);
                                shared_pref.set_username("" + patient_number);
                                shared_pref.set_password("" + pass);
                            } else {
                                //TODO get TIME, Bcakground service,  session, clear shared preference
                            }
                            startActivity(intent);
                            finish();

                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Something went gone wrong", Toast.LENGTH_SHORT).show();
                            Log.d("clima", "err" + error.getMessage());
                            pd.show();
                            return;
                        }
                    });

                    RequestQueue queue = Volley.newRequestQueue(this);
                    queue.add(request);

                    //Create the bundle
                }
               /* if (activeNetwork.getType()==ConnectivityManager.TYPE_MOBILE){
                    Intent intent = new Intent(MainActivity.this,WebActivity.class);
                    String patient_number = username.getText().toString().trim();
                    String pass = passWord.getText().toString().trim();
                    //Create the bundle
                    Bundle bundle = new Bundle();
                    bundle.putString("id", patient_number);
                    bundle.putString("password", pass);
                    intent.putExtras(bundle);

                    if (checkBox.isChecked())
                    {
                        shared_pref.set_loginstatus(true);
                        shared_pref.set_username(""+patient_number);
                        shared_pref.set_password(""+pass);
                    }
                    else{

                    }
                    startActivity(intent);
                    finish();
                }*/

            } else {
                Intent intent = new Intent(MainActivity.this, NoInternet.class);
                startActivity(intent);
                finish();

            }

        } else {
            Toast.makeText(this, "Wrong Patient ID or Password", Toast.LENGTH_LONG).show();
        }
        pd.hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        shared_pref = new Shared_Pref(MainActivity.this);
        ConnectivityManager manager = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (shared_pref.get_loginstatus()) {
            RunWhich_iflogin();
        } else {

        }
    }

    private void RunWhich_iflogin() {
        ConnectivityManager manager = (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileNetwork = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                String patient_number = shared_pref.get_username();
                String pass = shared_pref.get_password();
                //Create the bundle
                Bundle bundle = new Bundle();
                bundle.putString("id", patient_number);
                bundle.putString("password", pass);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                String patient_number = shared_pref.get_username();
                String pass = shared_pref.get_password();
                //Create the bundle
                Bundle bundle = new Bundle();
                bundle.putString("id", patient_number);
                bundle.putString("password", pass);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }

        } else {
            Intent intent = new Intent(MainActivity.this, NoInternet.class);
            startActivity(intent);
            finish();

        }
    }

    public void onclicktest(View view) {
        Toast.makeText(context, "working", Toast.LENGTH_SHORT).show();
    }
}