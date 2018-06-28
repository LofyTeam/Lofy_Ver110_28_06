package lofy.fpt.edu.vn.lofy_ver110.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import lofy.fpt.edu.vn.lofy_ver110.R;
import lofy.fpt.edu.vn.lofy_ver110.entities.User;

public class GroupHostAdapter extends BaseAdapter {
    ArrayList<User> alUsers;
    private LayoutInflater inflater;

    public GroupHostAdapter(Context context, ArrayList<User> alUsers) {
        this.alUsers = alUsers;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return alUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return alUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder;
        if (view == null) {
            myViewHolder = new MyViewHolder();
            view = inflater.inflate(R.layout.item_host,viewGroup,false);
            myViewHolder.imageAva =(ImageView) view.findViewById(R.id.img_item_join_host);
            myViewHolder.tvName =(TextView) view.findViewById(R.id.tv_item_join_host_name);
            view.setTag(myViewHolder);
        }else{
            myViewHolder =(MyViewHolder) view.getTag();
        }
        User user = alUsers.get(i);
        myViewHolder.imageAva.setImageResource(user.getUserAvatar());
        myViewHolder.tvName.setText(user.getUserName());

        return view;
    }

    private class MyViewHolder {
        ImageView imageAva;
        TextView tvName;
    }
}
