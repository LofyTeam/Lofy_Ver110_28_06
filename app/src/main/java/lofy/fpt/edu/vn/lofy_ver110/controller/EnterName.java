package lofy.fpt.edu.vn.lofy_ver110.controller;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lofy.fpt.edu.vn.lofy_ver110.R;
import lofy.fpt.edu.vn.lofy_ver110.business.AppFunctions;
import lofy.fpt.edu.vn.lofy_ver110.dbo.QueryFirebase;
import lofy.fpt.edu.vn.lofy_ver110.entities.User;
import petrov.kristiyan.colorpicker.ColorPicker;

public class EnterName extends AppCompatActivity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private EditText edtUserName;
    private EditText edtPhonenumber;
    private TextView tvColorPicker;
    private Button btnGetStart;
    private Button btnColorPicker;

    private String username="";
    private String phoneNumber="";
    private int mColorPicker=-1;
    private String userID = "";

    private AppFunctions appFunctions ;
    private QueryFirebase queryFirebase ;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_name);
        initView();
        checkFirstTime();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    private boolean checkFirstTime() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if(mSharedPreferences.getBoolean("is_first",true)){
            editor.putBoolean("is_first",false);
            userID=appFunctions.randomString(10);
            editor.putString("userId",userID);
            editor.apply();
            return false;
        }else{
            mSharedPreferences.getBoolean("is_first",false);
            userID= mSharedPreferences.getString("userId","xxxxxxxxxx");
            return true;
        }
    }

    private void initView() {
        tvColorPicker =(TextView) findViewById(R.id.enterName_tv_color_picker);
        btnColorPicker = (Button) findViewById(R.id.enterName_btn_color_picker);
        edtUserName = (EditText) findViewById(R.id.enterName_edt_username);
        edtPhonenumber = (EditText) findViewById(R.id.enterName_edt_phone_number);
        btnColorPicker.setOnClickListener(this);
        btnGetStart=(Button) findViewById(R.id.enterName_btn_getStart);
        btnGetStart.setOnClickListener(this);
        appFunctions = new AppFunctions();
        queryFirebase = new QueryFirebase(this);
        mSharedPreferences =this.getSharedPreferences("Inital-Data", Context.MODE_PRIVATE);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enterName_btn_color_picker:
                chooseColor();
                break;
            case R.id.enterName_btn_getStart:
                getNextActivity();
                break;
            default:
                break;
        }
    }

    private void getNextActivity() {
        if(edtUserName.getText().toString() == null || edtPhonenumber.getText().toString()==null ||mColorPicker==-1){
            Toast.makeText(this, "Please enter full your Information !", Toast.LENGTH_SHORT).show();
        }else{
            User user = new User(userID,"Facebook_ID",edtUserName.getText().toString(),R.mipmap.ic_launcher, edtPhonenumber.getText().toString(),"Status: I am fine!",0.0,0.0);
            queryFirebase.pushUserToFirebase(user,userID);
            Intent intent = new Intent(this,CreateJoinGroup.class);
            startActivity(intent);
        }
    }

    private void chooseColor() {
        final ColorPicker colorPicker = new ColorPicker(this);
        ArrayList<String> colors = new ArrayList<>();
        colors.add("#82B926");
        colors.add("#a276eb");
        colors.add("#6a3ab2");
        colors.add("#666666");
        colors.add("#FFFF00");
        colors.add("#3C8D2F");
        colors.add("#FA9F00");
        colors.add("#FF0000");
        colorPicker
                .setDefaultColorButton(Color.parseColor("#f84c44"))
                .setColors(colors)
                .setColumns(5)
                .setRoundColorButton(true)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                        Log.d("position", "" + position);// will be fired only when OK button was tapped
                        tvColorPicker.setBackgroundColor(color);
                        //Toast.makeText(EnterName.this, position, Toast.LENGTH_SHORT).show();
                        mColorPicker = color;
                    }

                    @Override
                    public void onCancel() {

                    }
                }).show();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }
}
