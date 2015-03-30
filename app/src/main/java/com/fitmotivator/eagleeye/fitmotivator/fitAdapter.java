package com.fitmotivator.eagleeye.fitmotivator;

/**
 * Created by Hafiz on 2/12/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class fitAdapter extends RecyclerView.Adapter<fitAdapter.MyViewHolder> {


    List<fitMotInformation> data = Collections.emptyList();
    private LayoutInflater inflator;
    private Context context;
    private ClickListerner clickListerner;

    public fitAdapter(Context context, List<fitMotInformation> data) {
        this.context = context;
        inflator = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflator.inflate(R.layout.custom_rows, parent, false);
        Log.d("FitMot", "onCreateHolder called ");

        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        fitMotInformation current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
        Log.d("FitMot", "onBindViewHolder called " + position);

    }

    public void setClickListerner(ClickListerner clickListerner){
        this.clickListerner = clickListerner;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);

            icon.setOnClickListener(this);
            title.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            context.startActivity(new Intent(context, Day_Picker_Activity.class));

            if(clickListerner != null)
            {
                clickListerner.itemClicked(v, getPosition());
            }
        }
    }

    public interface ClickListerner{

        public void itemClicked(View view, int position);

    }
}
