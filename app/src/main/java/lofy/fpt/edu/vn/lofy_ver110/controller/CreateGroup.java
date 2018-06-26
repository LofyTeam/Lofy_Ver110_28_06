package lofy.fpt.edu.vn.lofy_ver110.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;

import java.util.Calendar;
import java.util.Date;

import lofy.fpt.edu.vn.lofy_ver110.R;
import lofy.fpt.edu.vn.lofy_ver110.business.AppFunctions;
import lofy.fpt.edu.vn.lofy_ver110.dbo.QueryFirebase;
import lofy.fpt.edu.vn.lofy_ver110.entities.Group;

public class CreateGroup extends AppCompatActivity implements View.OnClickListener {

    private Pinview ciCode;
    private AppFunctions appFunctions;
    private Button btnCreate;
    private EditText edtGroupName;

    private QueryFirebase queryFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        initView();
        createCode();
    }

    private void initView() {
        edtGroupName =(EditText) findViewById(R.id.createGroup_edt_group_name);
        ciCode = (Pinview ) findViewById(R.id.createGroup_ci_code);
        btnCreate = (Button) findViewById(R.id.createGroup_btn_create_group);
        btnCreate.setOnClickListener(this);
        appFunctions = new AppFunctions();
        queryFirebase = new QueryFirebase(this);
    }

    private void createCode() {
        ciCode.setValue(appFunctions.randomString(6));
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

    private void createGroup() {
        if(edtGroupName.getText().toString() == null || ciCode.getValue().toString() == null){
            Toast.makeText(this, "Please enter group name !", Toast.LENGTH_SHORT).show();
        }else{
            //String groupId, String groupName, String start_Date, String end_Date, double start_Long, double start_Lat, double end_Long, double end_Lat, String status
            Date currentTime = Calendar.getInstance().getTime();
            Group group = new Group(ciCode.getValue().toString(),edtGroupName.getText().toString(),currentTime.toString(),currentTime.toString() ,0.0 ,0.0 ,0.0 ,0.0,"open" );
            queryFirebase.pushGroupToFirebase(group,ciCode.getValue().toString());
        }
    }
}
