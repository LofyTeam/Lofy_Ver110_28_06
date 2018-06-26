package lofy.fpt.edu.vn.lofy_ver110.dbo;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import lofy.fpt.edu.vn.lofy_ver110.entities.Group;
import lofy.fpt.edu.vn.lofy_ver110.entities.User;

public class QueryFirebase {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mReference;
    private static final String TAG = "MY_TAG";
    private Context mContext;
    private ArrayList<String> codeList;

    public QueryFirebase (Context context){
        this.mContext = context;
    }

    // push user to firebase
    public void pushUserToFirebase(User user, String key) {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference("users");
        DatabaseReference newRef = mReference.child(key);
        newRef.setValue(user);
    }

    // push group to firebase
    public void pushGroupToFirebase(Group group, String key) {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference("groups");
        DatabaseReference newRef = mReference.child(key);
        newRef.setValue(group);
    }

    //get code group
    public ArrayList<String> getCodeFromFirebase() {
        codeList = new ArrayList<>();
        final int[] ncount = {0};
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference("groups").addValueEventListener(new ValueEventListener() {
            ArrayList <String> mData = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                   for (DataSnapshot gr : dataSnapshot.getChildren()) {
                       ncount[0]++;
                       Group group=gr.getValue(Group.class);
                       codeList.add(group.getGroupId());
                       // codeList has data
                   }
                   mData = codeList;
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        Log.d(TAG, "ReturnCodelist: "+codeList.size());
        // codeList hasn't data
        return codeList;
    }
}