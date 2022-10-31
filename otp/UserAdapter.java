package com.example.mentalhealth.otp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mentalhealth.EmailActivity;
import com.example.mentalhealth.activities.ChatActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import mentalhealth.R;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<User> userList;
    private Button bookappointment;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(
                R.layout.user_display_layout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final User user = userList.get(position);
        holder.type.setText(user.getType());


//        if (user.getType().equals("patients")){
//            holder.bookappointment.setVisibility(View.VISIBLE);
//        }

        holder.username.setText(user.getName());
        holder.useremail.setText(user.getEmail());
        holder.type.setText(user.getType());
        holder.phonenumber.setText(user.getPhonenumber());


holder.bookappointment2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("mailto:" + user.getEmail().toString()));
        context.startActivity(intent);
    }
});

//        Glide.with(context).load(user.getProfilepictureurl()).into(holder.userprofileimage);


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CircleImageView userprofileimage;

        public TextView type,phonenumber,useremail,username;
        public Button bookappointment2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userprofileimage=itemView.findViewById(R.id.userProfileImage);
            phonenumber=itemView.findViewById(R.id.profile_PhoneNo);
            username=itemView.findViewById(R.id.username);
            useremail=itemView.findViewById(R.id.useremail);
            type=itemView.findViewById(R.id.profile_type);
            bookappointment2 = itemView.findViewById(R.id.buttonbookappointment);


        }
    }
}
