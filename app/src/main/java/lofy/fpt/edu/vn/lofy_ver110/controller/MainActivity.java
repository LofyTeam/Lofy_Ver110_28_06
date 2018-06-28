package lofy.fpt.edu.vn.lofy_ver110.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lofy.fpt.edu.vn.lofy_ver110.R;

public class MainActivity extends AppCompatActivity {
    private EnterNameFragment enterNameFragment;
    private CreateGroupFragment createGroupFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        showFragmentEnterName();
    }

    private void initFragment() {

    }

    private void showFragmentEnterName(){
        if(enterNameFragment==null){
            enterNameFragment = new EnterNameFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ln_main,enterNameFragment,EnterNameFragment.class.getName())
                .addToBackStack(EnterNameFragment.class.getName())
                .commit();
        getFragmentManager().popBackStackImmediate();
    }
}
