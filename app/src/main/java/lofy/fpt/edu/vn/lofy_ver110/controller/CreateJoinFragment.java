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
import android.widget.Toast;

import lofy.fpt.edu.vn.lofy_ver110.R;


public class CreateJoinFragment extends Fragment implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {
    private View rootView;
    private Button btnCreate;
    private Button btnJoin;
    private Button btnCurrent;
    private CreateGroupFragment createGroupFragment;
    private JoinGroupFragment joinGroupFragment;
    private CurrentGroupFragment currentGroupFragment;
    private SharedPreferences mSharedPreferences;
    String currentGroupId = "";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_create_join, container, false);
        initView();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    private void initView() {
        btnCreate = (Button) rootView.findViewById(R.id.createJoin_btn_create_group);
        btnJoin = (Button) rootView.findViewById(R.id.createGroup_btn_join_group);
        btnCurrent = (Button) rootView.findViewById(R.id.createJoin_btn_current_group);
        btnCreate.setOnClickListener(this);
        btnJoin.setOnClickListener(this);
        btnCurrent.setOnClickListener(this);
        mSharedPreferences = getActivity().getSharedPreferences("Inital-Data", Context.MODE_MULTI_PROCESS);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        currentGroupId = mSharedPreferences.getString("GroupId", "NA");
        switch (view.getId()) {
            case R.id.createJoin_btn_create_group:
                if (currentGroupId.equals("NA")) {
                    if (createGroupFragment == null) {
                        createGroupFragment = new CreateGroupFragment();
                    }
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.ln_main, createGroupFragment, CreateGroupFragment.class.getName())
                            .addToBackStack(null)
                            .commit();
                } else {
                    Toast.makeText(rootView.getContext(), "You have been in a group! Please get-out old group !", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.createGroup_btn_join_group:
                if (currentGroupId.equals("NA")) {
                    if (joinGroupFragment == null) {
                        joinGroupFragment = new JoinGroupFragment();
                    }
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.ln_main, joinGroupFragment, JoinGroupFragment.class.getName())
                            .addToBackStack(null)
                            .commit();
                } else {
                    Toast.makeText(rootView.getContext(), "You have been in a group! Please get-out old group !", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.createJoin_btn_current_group:
                if (currentGroupFragment == null) {
                    currentGroupFragment = new CurrentGroupFragment();
                }else{
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.ln_main, currentGroupFragment, CurrentGroupFragment.class.getName())
                            .addToBackStack(null)
                            .commit();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {

    }
}
