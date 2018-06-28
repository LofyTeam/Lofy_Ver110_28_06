package lofy.fpt.edu.vn.lofy_ver110.controller;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import lofy.fpt.edu.vn.lofy_ver110.R;
import lofy.fpt.edu.vn.lofy_ver110.adapter.GroupHostAdapter;
import lofy.fpt.edu.vn.lofy_ver110.business.AppFunctions;
import lofy.fpt.edu.vn.lofy_ver110.dbo.QueryFirebase;
import lofy.fpt.edu.vn.lofy_ver110.entities.Group;
import lofy.fpt.edu.vn.lofy_ver110.entities.GroupUser;
import lofy.fpt.edu.vn.lofy_ver110.entities.User;

import static android.content.ContentValues.TAG;

public class CreateGroupFragment extends Fragment implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private View rootView;
    private Pinview ciCode;
    private AppFunctions appFunctions;
    private Button btnCreate;
    private EditText edtGroupName;
    private SharedPreferences mSharedPreferences;
    private ListView lvHost;

    private QueryFirebase queryFirebase;
    private SharedPreferences.Editor editor;
    private GroupHostAdapter groupHostAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_group, container, false);
        initView();
        createCode();
        return rootView;
    }

    private void initView() {
        edtGroupName = (EditText) rootView.findViewById(R.id.createGroup_edt_group_name);
        ciCode = (Pinview) rootView.findViewById(R.id.createGroup_ci_code);
        btnCreate = (Button) rootView.findViewById(R.id.createGroup_btn_create_group);
        btnCreate.setOnClickListener(this);
        //lvHost =(ListView) rootView.findViewById(R.id.createGroup_lv_host);
        appFunctions = new AppFunctions();
        queryFirebase = new QueryFirebase(rootView.getContext());
        mSharedPreferences = getActivity().getSharedPreferences("Inital-Data", Context.MODE_MULTI_PROCESS);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        editor = mSharedPreferences.edit();
    }

    private void createCode() {
        ciCode.setValue(appFunctions.randomString(6));
    }

    private void createGroup() {
        if (edtGroupName.getText().toString() == null || edtGroupName.getText().toString().equals("") || ciCode.getValue().toString() == null || ciCode.getValue().toString().equals("")) {
            Toast.makeText(rootView.getContext(), "Please enter group name !", Toast.LENGTH_SHORT).show();
        } else {
            // push group to firebase
            Date currentTime = Calendar.getInstance().getTime();
            Group group = new Group(ciCode.getValue().toString(), edtGroupName.getText().toString(), currentTime.toString(), currentTime.toString(), 0.0, 0.0, 0.0, 0.0, "open");
            queryFirebase.pushGroupToFirebase(group, ciCode.getValue().toString());

            // push group-user to firebase
            editor.putString("GroupId", ciCode.getValue().toString());
            editor.apply();
            String mGroupID = mSharedPreferences.getString("GroupId","NA");
            String userID = mSharedPreferences.getString("userId", "NA");
            User us = queryFirebase.getUserByUserID(userID);
            GroupUser groupUser = new GroupUser(mGroupID + userID, userID, mGroupID, us.getUserName(),
                    false, false, "NA", "NA", 0.0, "Today i'm fine!");
            String mGroupUserID = mGroupID+userID+"";
            queryFirebase.pushGroupUserToFirebase(groupUser,mGroupUserID);

            // add to lisview
//            ArrayList<User> alus=queryFirebase.getUserOrderByGroupID(mGroupID);
//            groupHostAdapter = new GroupHostAdapter(rootView.getContext(),alus);
//            lvHost.setAdapter(groupHostAdapter);
//
           Toast.makeText(rootView.getContext(), "Create Successed !", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createGroup_btn_create_group:
                createGroup();
                break;
            default:
                break;
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
