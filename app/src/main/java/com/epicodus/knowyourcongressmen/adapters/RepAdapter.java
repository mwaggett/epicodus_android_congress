package com.epicodus.knowyourcongressmen.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.knowyourcongressmen.R;
import com.epicodus.knowyourcongressmen.models.Representative;

import java.util.ArrayList;
import java.util.List;

public class RepAdapter extends BaseAdapter{
    private Context mContext;
    private ArrayList<Representative> mRepresentatives;

    public RepAdapter (Context context, ArrayList<Representative> representatives) {
        mContext = context;
        mRepresentatives = representatives;
    }

    @Override
    public int getCount() {
        return mRepresentatives.size();
    }

    @Override
    public Object getItem(int position) {
        return mRepresentatives.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.rep_info, null);
            holder = new ViewHolder();
            holder.mRepLayout = (RelativeLayout) convertView.findViewById(R.id.repLayout);
            holder.mRepName = (TextView) convertView.findViewById(R.id.repName);
            holder.mRepParty = (TextView) convertView.findViewById(R.id.repParty);
            holder.mRepGender = (TextView) convertView.findViewById(R.id.repGender);
            holder.mRepBirthday = (TextView) convertView.findViewById(R.id.repBirthday);
            holder.mCallLink = (TextView) convertView.findViewById(R.id.callLink);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Representative rep = mRepresentatives.get(position);

        holder.mRepName.setText(rep.getName());
        holder.mRepParty.setText(rep.getParty());
        holder.mRepGender.setText(rep.getGender());
        holder.mRepBirthday.setText(rep.getBirthday());
        holder.mCallLink.setText(rep.getPhoneNumber());

        if (rep.getParty().equals("D")) {
            holder.mRepLayout.setBackgroundColor(Color.parseColor("#800099FF"));
        } else if (rep.getParty().equals("R")) {
            holder.mRepLayout.setBackgroundColor(Color.parseColor("#80CC0000"));
        } else {
            holder.mRepLayout.setBackgroundColor(Color.parseColor("#806C6C6C"));
        }

        holder.mCallLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneString = rep.getPhoneNumber().replaceAll("[^0-9]", "");
                Uri phoneNumber = Uri.parse("tel:" + phoneString);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, phoneNumber);

                PackageManager packageManager = mContext.getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent, 0);
                boolean isIntentSafe = activities.size() > 0;

                if (isIntentSafe) {
                    mContext.startActivity(callIntent);
                }
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        RelativeLayout mRepLayout;
        TextView mRepName;
        TextView mRepBirthday;
        TextView mRepGender;
        TextView mRepParty;
        TextView mCallLink;
    }
}
