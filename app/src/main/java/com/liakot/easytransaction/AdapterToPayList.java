package com.liakot.easytransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterToPayList extends RecyclerView.Adapter<AdapterToPayList.ViewHolder>{
    ArrayList<ClassAddCustomer> arrayList;
    Context activityContext;
    Animation recyclerAnim;
    byte[] pictureByte;

    public AdapterToPayList(Context context, ArrayList<ClassAddCustomer> list)
    {
        activityContext = context;
        arrayList = list;
    }

    //------To hold every list item view------
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name, address, amount;
        CircleImageView picture;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.toPayName);
            address = itemView.findViewById(R.id.toPayAddress);
            picture = itemView.findViewById(R.id.toPayPicture);
            amount = itemView.findViewById(R.id.toPayAmount);

        }
    }

    @NonNull
    @Override
    public AdapterToPayList.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.item_to_pay, viewGroup, false);

        return new AdapterToPayList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterToPayList.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.itemView.setTag(arrayList.get(position));
        Bitmap bitmap;

        //------convert byte to string------
        pictureByte = arrayList.get(position).getPicture();
        if (pictureByte != null) {
            bitmap = BitmapFactory.decodeByteArray(pictureByte, 0, pictureByte.length);
            holder.picture.setImageBitmap(bitmap);
        } else {
            holder.picture.setImageResource(R.drawable.icon_profile_24);
        }

        holder.name.setText(arrayList.get(position).getName());
        holder.address.setText(arrayList.get(position).getAddress());
        holder.amount.setText(String.valueOf(arrayList.get(position).getAmount()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activityContext, ActivityTransaction.class);
                intent.putExtra("Name", arrayList.get(position).getName());
                intent.putExtra("Phone", arrayList.get(position).getPhone());
                intent.putExtra("Amount", arrayList.get(position).getAmount());
                intent.putExtra("Address", arrayList.get(position).getAddress());
                intent.putExtra("Picture", arrayList.get(position).getPicture());
                intent.putExtra("Type", "ToPay");
                activityContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
