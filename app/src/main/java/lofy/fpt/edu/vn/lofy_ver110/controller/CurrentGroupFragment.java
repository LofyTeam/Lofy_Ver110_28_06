package lofy.fpt.edu.vn.lofy_ver110.controller;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import lofy.fpt.edu.vn.lofy_ver110.R;


public class CurrentGroupFragment extends Fragment implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private View rootView;
    private Button btnCQuitGroup;
    private SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_current_group, container, false);
        initView();
        return rootView;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
    private void initView() {
        btnCQuitGroup = (Button) rootView.findViewById(R.id.currentGroup_btn_quit_group);
        btnCQuitGroup.setOnClickListener(this);
        mSharedPreferences = getActivity().getSharedPreferences("Inital-Data", Context.MODE_MULTI_PROCESS);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.currentGroup_btn_quit_group:
                quitGroup();
                break;
            default:
                break;
        }
    }

    private void quitGroup() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("GroupId","NA");
        editor.apply();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }
}
