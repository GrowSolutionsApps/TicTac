package com.musiktok.musictok.ActivitesFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.musiktok.musictok.ApiClasses.ApiLinks;
import com.musiktok.musictok.ApiClasses.ApiRequest;
import com.musiktok.musictok.Interfaces.Callback;
import com.musiktok.musictok.Main_Menu.MainMenuActivity;
import com.musiktok.musictok.Main_Menu.WelComeActivity;
import com.musiktok.musictok.SimpleClasses.Variables;
import com.musiktok.musictok.Main_Menu.MainMenuActivity;
import com.musiktok.videoapp.R;
import com.musiktok.musictok.ApiClasses.ApiLinks;
import com.musiktok.musictok.ApiClasses.ApiRequest;
import com.musiktok.musictok.Interfaces.Callback;
import com.musiktok.musictok.SimpleClasses.Variables;

import org.json.JSONException;
import org.json.JSONObject;

public class Splash_A extends AppCompatActivity {


    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);


        Variables.sharedPreferences = getSharedPreferences(Variables.pref_name, MODE_PRIVATE);


        if (Variables.sharedPreferences.getString(Variables.device_id, "0").equals("0")) {
            Call_api_register_device();
        } else
            Set_Timer();


    }


    public void Set_Timer() {
        countDownTimer = new CountDownTimer(2500, 500) {

            public void onTick(long millisUntilFinished) {
                // this will call on every 500 ms
            }

            public void onFinish() {
                if (getBooleanData(Splash_A.this, "isFirst", true)) {
                    saveData(Splash_A.this, "isFirst", false);
                    Intent intent = new Intent(Splash_A.this, WelComeActivity.class);
//                Intent intent = new Intent(Splash_A.this, MainMenuActivity.class);
                    if (getIntent().getExtras() != null) {
                        intent.putExtras(getIntent().getExtras());
                        setIntent(null);
                    }
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                    finish();
                } else {
                    Intent intent = new Intent(Splash_A.this, MainMenuActivity.class);
                    if (getIntent().getExtras() != null) {
                        intent.putExtras(getIntent().getExtras());
                        setIntent(null);
                    }
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                    finish();
                }

            }
        }.start();
    }

    static public boolean getBooleanData(Context context, String key, boolean def) {

        try {
            if (context != null)
                return context.getSharedPreferences("video_pref", Context.MODE_PRIVATE).getBoolean(key, def);
            else
                return false;
        } catch (Exception e) {
            return false;
        }
    }

    static public void saveData(Context context, String key, boolean val) {
        if (context == null)
            return;
        context.getSharedPreferences("video_pref", Context.MODE_PRIVATE)
                .edit()
                .putBoolean(key, val)
                .apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void Call_api_register_device() {
        String androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        JSONObject param = new JSONObject();
        try {
            param.put("key", androidId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(this, ApiLinks.registerDevice, param, new Callback() {
            @Override
            public void Responce(String resp) {
                Log.w("msg", "resp=== " + resp);
                try {
                    JSONObject jsonObject = new JSONObject(resp);
                    String code = jsonObject.optString("code");
                    if (code.equals("200")) {

                        Set_Timer();

                        JSONObject msg = jsonObject.optJSONObject("msg");
                        JSONObject Device = msg.optJSONObject("Device");
                        SharedPreferences.Editor editor2 = Variables.sharedPreferences.edit();
                        editor2.putString(Variables.device_id, Device.optString("id")).commit();


                    } else {
                        Call_api_show_register_device();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    public void Call_api_show_register_device() {
        String androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        JSONObject param = new JSONObject();
        try {
            param.put("key", androidId);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.w("msg", "androidId=== " + androidId);

        ApiRequest.Call_Api(this, ApiLinks.showDeviceDetail, param, new Callback() {
            @Override
            public void Responce(String resp) {
                Log.w("msg", "resp1=== " + resp);

                try {
                    JSONObject jsonObject = new JSONObject(resp);
                    String code = jsonObject.optString("code");
                    if (code.equals("200")) {

                        Set_Timer();

                        JSONObject msg = jsonObject.optJSONObject("msg");
                        JSONObject Device = msg.optJSONObject("Device");
                        SharedPreferences.Editor editor2 = Variables.sharedPreferences.edit();
                        editor2.putString(Variables.device_id, Device.optString("id")).commit();


                    } /*else {
                        //Added
                        Intent intent = new Intent(Splash_A.this, WelComeActivity.class);
//                Intent intent = new Intent(Splash_A.this, MainMenuActivity.class);

                        if (getIntent().getExtras() != null) {
                            intent.putExtras(getIntent().getExtras());
                            setIntent(null);
                        }

                        startActivity(intent);
                        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                        finish();
                    }*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
