package lofy.fpt.edu.vn.lofy_ver110.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lofy.fpt.edu.vn.lofy_ver110.R;

public class CreateJoinGroup extends AppCompatActivity implements View.OnClickListener {
    private Button btnCreate;
    private Button btnJoin;
    private Button btnCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_join_group);
        initView();
    }

    private void initView() {
        btnCreate = (Button) findViewById(R.id.createJoin_btn_create_group);
        btnJoin = (Button) findViewById(R.id.createGroup_btn_join_group);
        btnCurrent = (Button) findViewById(R.id.createJoin_btn_current_group);
        btnCreate.setOnClickListener(this);
        btnJoin.setOnClickListener(this);
        btnCurrent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent ;
        switch (view.getId()) {
            case R.id.createJoin_btn_create_group:
                intent = new Intent(this,CreateGroup.class);
                startActivity(intent);
                break;
            case R.id.createGroup_btn_join_group:
                intent = new Intent(this,JoinGroup.class);
                startActivity(intent);
                break;
            case R.id.createJoin_btn_current_group:

                break;
            default:
                break;
        }
    }
}
