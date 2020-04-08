package com.rakin.blooddonation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rakin.blooddonation.DB.DonorDatabase;
import com.rakin.blooddonation.Entities.donorInfo;

import java.util.List;

public class donorRVadapter extends RecyclerView.Adapter<donorRVadapter.donorViewHolder>{
    private Context context;
    private List<donorInfo> donor_info;


    public donorRVadapter (Context context, List<donorInfo> donor_info) {
        this.context = context;
        this.donor_info = donor_info;
    }

    @NonNull
    @Override
    public donorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.donor_row,parent,false);
        return new donorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final donorViewHolder holder, final int position) {
        holder.nameTV.setText("Name:"+" "+donor_info.get(position).getDonor_name());
        holder.bgTV.setText("Blood Group:"+" "+donor_info.get(position).getBlood_grp());
        holder.areaTV.setText("Area:"+" "+donor_info.get(position).getArea());
        holder.phoneTV.setText("Phone:"+" "+donor_info.get(position).getPhone());

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = holder.phoneTV.getText().toString();
                String[] data = phone.split(" ", 2);
                Uri phoneUri = Uri.parse("tel:"+data[1]);
                Intent dialIntent = new Intent(Intent.ACTION_DIAL,phoneUri);
                context.startActivity(dialIntent);

            }
        });
        holder.sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = holder.phoneTV.getText().toString();
                String[] data = phone.split(" ", 2);
                Uri smsUri = Uri.parse("smsto:"+data[1]);
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO,smsUri);
                context.startActivity(smsIntent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return donor_info.size();
    }

    public class donorViewHolder extends RecyclerView.ViewHolder{

        TextView nameTV,bgTV,areaTV,phoneTV;
        Button call,sms;

        public donorViewHolder(@NonNull final View itemView) {
            super(itemView);
            nameTV = itemView.findViewById(R.id.name_row);
            bgTV = itemView.findViewById(R.id.blood_row);
            areaTV = itemView.findViewById(R.id.area_row);
            phoneTV = itemView.findViewById(R.id.phone_row);
            call = itemView.findViewById(R.id.call_row);
            sms = itemView.findViewById(R.id.sms_row);

        }


    }
}
