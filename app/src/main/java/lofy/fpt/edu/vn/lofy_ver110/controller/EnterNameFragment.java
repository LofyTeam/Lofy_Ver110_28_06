package lofy.fpt.edu.vn.lofy_ver110.controller;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import lofy.fpt.edu.vn.lofy_ver110.R;
import lofy.fpt.edu.vn.lofy_ver110.business.AppFunctions;
import lofy.fpt.edu.vn.lofy_ver110.dbo.QueryFirebase;
import lofy.fpt.edu.vn.lofy_ver110.entities.User;

public class EnterNameFragment extends Fragment implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private View rootView;
    private EditText edtUserName;
    private EditText edtPhonenumber;
    private TextView tvColorPicker;
    private Button btnGetStart;
    private Button btnColorPicker;

    private String username = "";
    private String phoneNumber = "";
    private String userID = "";

    private AppFunctions appFunctions;
    private QueryFirebase queryFirebase;

    private SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_enter_name, container, false);
        initView();
        checkFirstTime();
        return rootView;
    }

    private void initView() {
        edtUserName = (EditText) rootView.findViewById(R.id.enterName_edt_username);
        edtPhonenumber = (EditText) rootView.findViewById(R.id.enterName_edt_phone_number);
        btnGetStart = (Button) rootView.findViewById(R.id.enterName_btn_getStart);
        btnGetStart.setOnClickListener(this);
        appFunctions = new AppFunctions();
        queryFirebase = new QueryFirebase(rootView.getContext());
        mSharedPreferences = getActivity().getSharedPreferences("Inital-Data", Context.MODE_MULTI_PROCESS);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enterName_btn_getStart:
                getNextActivity();
                break;
            default:
                break;
        }
    }

    private boolean checkFirstTime() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (mSharedPreferences.getBoolean("is_first", true)) {
            editor.putBoolean("is_first", false);
            userID = appFunctions.randomString(10);
            editor.putString("userId", userID);
            editor.apply();
            return false;
        } else {
            mSharedPreferences.getBoolean("is_first", false);
            userID = mSharedPreferences.getString("userId", "xxxxxxxxxx");
            return true;
        }
    }

    private void getNextActivity() {
        String name = edtUserName.getText().toString();
        String phone = edtPhonenumber.getText().toString();
        if (name == null || name.equals("") || phone == null || phone.equals("")) {
            Toast.makeText(rootView.getContext(), "Please enter full your Information !", Toast.LENGTH_SHORT).show();
            return;
        } else {
            User user = new User(userID, "Facebook_ID", edtUserName.getText().toString(), R.mipmap.ic_launcher, edtPhonenumber.getText().toString(), "Status: I am fine!", 0.0, 0.0);
            queryFirebase.pushUserToFirebase(user, userID);
            CreateJoinFragment createJoinFragment = new CreateJoinFragment();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.ln_main, createJoinFragment, CreateJoinFragment.class.getName())
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }
}
