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
import android.widget.Toast;

import java.util.ArrayList;

import lofy.fpt.edu.vn.lofy_ver110.R;
import lofy.fpt.edu.vn.lofy_ver110.dbo.QueryFirebase;
import lofy.fpt.edu.vn.lofy_ver110.entities.GroupUser;
import lofy.fpt.edu.vn.lofy_ver110.entities.User;

import static android.content.ContentValues.TAG;

public class JoinGroupFragment extends Fragment implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private View rootView;
    private EditText ciEnterCode;
    private Button btnOkCode;
    private QueryFirebase queryFirebase;
    private SharedPreferences mSharedPreferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_join_group, container, false);
        initView();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    private void initView() {
        ciEnterCode = (EditText) rootView.findViewById(R.id.join_ci_enter_code);
        btnOkCode = (Button) rootView.findViewById(R.id.join_btn_ok_code);
        btnOkCode.setOnClickListener(this);
        queryFirebase = new QueryFirebase(rootView.getContext());
        mSharedPreferences = getActivity().getSharedPreferences("Inital-Data", Context.MODE_MULTI_PROCESS);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        editor = mSharedPreferences.edit();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.join_btn_ok_code:
                joinGroup();
                break;
            default:
                break;
        }
    }

    private void joinGroup() {
        ArrayList<String> alCode = queryFirebase.getCodeFromFirebase();
        String mCode = ciEnterCode.getText().toString().toUpperCase();
        if (mCode.equals("") || mCode.isEmpty() || alCode.size() <= 0 || alCode.isEmpty()) {
            Toast.makeText(rootView.getContext(), "Fail to join group!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            for (int i = 0; i < alCode.size(); i++) {
                if (mCode.equals(alCode.get(i).toString())) {
                    // String groupsUsersID, String userId, String groupId, String userNickName, boolean isHost,
                    // boolean isVice, String userColor, String timeStamp, double sizeRadius, String userStatus
                    String userID = mSharedPreferences.getString("userId", "NA");
                    editor.putString("GroupId", mCode);
                    editor.apply();
//                    String mGroupID = mSharedPreferences.getString("GroupId", "NA");
//                    User us = queryFirebase.getUserByChild(userID);
//                    GroupUser groupUser = new GroupUser(mGroupID + userID, userID, mGroupID, us.getUserName(),
//                            false, false, "NA", "NA", 0.0, "Today i'm fine!");
                    Toast.makeText(rootView.getContext(), "Success !", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(rootView.getContext(), "Fail to join group2!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }
}
