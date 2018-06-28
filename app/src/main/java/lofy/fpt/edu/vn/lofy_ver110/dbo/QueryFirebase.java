package lofy.fpt.edu.vn.lofy_ver110.dbo;

import android.content.Context;
import android.provider.FontRequest;
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
import lofy.fpt.edu.vn.lofy_ver110.entities.GroupUser;
import lofy.fpt.edu.vn.lofy_ver110.entities.User;

public class QueryFirebase {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mReference;
    private static final String TAG = "MY_TAG";
    private Context mContext;
    private ArrayList<String> codeList;
    private ArrayList<User> alUser;
    private ArrayList<GroupUser> alGroupUser;


    public QueryFirebase(Context context) {
        this.mContext = context;
        registerEventGroup();
        registerEventUser();
        registerEventGroupUser();
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

    // push group-user to firebase
    public void pushGroupUserToFirebase(GroupUser groupUser, String key) {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference("groups-users");
        DatabaseReference newRef = mReference.child(key);
        newRef.setValue(groupUser);
    }

    //get code group
    public void registerEventGroup() {
        codeList = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference("groups").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot gr : dataSnapshot.getChildren()) {
                    Group group = gr.getValue(Group.class);
                    codeList.add(group.getGroupId());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void registerEventUser() {
        alUser = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               if (dataSnapshot.hasChildren()){
                    for (DataSnapshot us : dataSnapshot.getChildren()) {
                        User user = us.getValue(User.class);
                        alUser.add(user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void registerEventGroupUser() {
        alGroupUser = new ArrayList<>();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference("groups-users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               if(dataSnapshot.hasChildren()){
                   for (DataSnapshot us : dataSnapshot.getChildren()) {
                       GroupUser groupUser = us.getValue(GroupUser.class);
                       alGroupUser.add(groupUser);
                   }
               }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    // return codeList
    public ArrayList<String> getCodeFromFirebase() {
        return codeList;
    }

    // get user search  by userid
    public User getUserByUserID(final String userID) {
        User u = new User();
        for (int i = 0; i < alUser.size(); i++) {
            if (alUser.get(i).getUserId().equals(userID)) {
                u = alUser.get(i);
            }
        }
        return u;
    }

    // get user order by group id
    public ArrayList<User> getUserOrderByGroupID(String groupID) {
        ArrayList<User> alUs = new ArrayList<>();
            for(int i=0;i<alGroupUser.size();i++){
                if(alGroupUser.get(i).getGroupId().equals(groupID)){
                    User u = getUserByUserID(alGroupUser.get(i).getUserId());
                    alUser.add(u);
                }
            }
        return alUser;
    }



}