package com.faysol.ehomevisit.SharedPrefrence_classes;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared_Pref {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public  Shared_Pref(Context context)
    {
        sharedPreferences=context.getSharedPreferences("sharedprefforweb1",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void set_loginstatus(Boolean status)
    {
        editor.putBoolean("loginstatus",status);
        editor.commit();
    }
    public Boolean get_loginstatus()
    {
        return sharedPreferences.getBoolean("loginstatus",false);
    }

    public void set_username(String name)
    {
        editor.putString("name",name);
        editor.commit();
    }
    public String get_username()
    {
        return sharedPreferences.getString("name","");
    }
    public void set_password(String pass)
    {
        editor.putString("pass",pass);
        editor.commit();
    }
    public String get_password()
    {
        return sharedPreferences.getString("pass","");
    }

}
