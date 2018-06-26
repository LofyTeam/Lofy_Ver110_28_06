package lofy.fpt.edu.vn.lofy_ver110.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.glomadrian.codeinputlib.CodeInput;
import com.goodiebag.pinview.Pinview;

import java.util.ArrayList;

import lofy.fpt.edu.vn.lofy_ver110.R;
import lofy.fpt.edu.vn.lofy_ver110.dbo.QueryFirebase;

public class JoinGroup extends AppCompatActivity implements View.OnClickListener {
    private EditText ciEnterCode;
    private Button btnOkCode;
    private QueryFirebase queryFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_group);
        initView();
    }

    private void initView() {
        ciEnterCode = (EditText  ) findViewById(R.id.join_ci_enter_code);
        btnOkCode = (Button) findViewById(R.id.join_btn_ok_code);
        btnOkCode.setOnClickListener(this);
        queryFirebase = new QueryFirebase(this);
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
        String code="";
        ArrayList<String> alCode= new ArrayList<>();
        alCode = queryFirebase.getCodeFromFirebase();
        Log.d("List Code", "joinGroup: "+alCode.size());
//        if(ciEnterCode.getText().toString().toUpperCase() == code  || alCode.size()<=0 || alCode.isEmpty()){
//            Toast.makeText(this, "Fail to join group!", Toast.LENGTH_SHORT).show();
//        }else{
//            for(int i=0;i<alCode.size();i++){
//                Log.d("Group ID", "joinGroup: "+alCode.get(i));
//                if (ciEnterCode.getText().toString().equals(alCode.get(i))){
//                    Toast.makeText(this, "Success !", Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(this, "Fail to join group!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
    }
}
