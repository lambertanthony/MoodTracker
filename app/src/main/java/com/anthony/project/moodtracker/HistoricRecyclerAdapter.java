package com.anthony.project.moodtracker;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoricRecyclerAdapter extends RecyclerView.Adapter<HistoricRecyclerAdapter.MyViewHolder> {

    ArrayList<Mood> moodsList;
    LayoutInflater inflater;


    public HistoricRecyclerAdapter(Context context, ArrayList<Mood> products) {
        inflater = LayoutInflater.from(context);
        this.moodsList = products;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_recyclerview, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        Log.i("Hauteur" ,"Hauteur"+view.getHeight());
        Log.i("Data from histor " ,moodsList.toString());
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Mood displayedMood = moodsList.get(position);
        holder.setData(displayedMood, position);

    }

    @Override
    public int getItemCount() {
        return moodsList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView moodState;
        ImageView commentImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            moodState = (TextView) itemView.findViewById(R.id.item_recycler_view_text);

            commentImage = (ImageView) itemView.findViewById(R.id.item_recycler_view_image);


        }

        public void setData(Mood selectedMood, int position) {
            int displayWidth = Resources.getSystem().getDisplayMetrics().widthPixels;


            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = getMoodHeight();
            switch (position){
                case 0:
                    this.moodState.setText("Il y a une semaine");
                    break;
                case 1:
                    this.moodState.setText("Il y a six jours");
                    break;
                case 2:
                    this.moodState.setText("Il y a cinq jours");
                    break;
                case 3:
                    this.moodState.setText("Il y a quatre jours");
                    break;
                case 4:
                    this.moodState.setText("Il y a trois jours");
                    break;
                case 5:
                    this.moodState.setText("Avant-hier");
                    break;
                case 6:
                    this.moodState.setText("Hier");
                    break;
            }
            switch (selectedMood.getMoodState()){
                case 0:
                    params.width = displayWidth * 20 / 100;
                    this.itemView.setBackgroundResource(R.color.faded_red);
                    break;
                case 1:
                    params.width = displayWidth * 40 / 100;
                    this.itemView.setBackgroundResource(R.color.warm_grey);
                    break;
                case 2:
                    params.width = displayWidth * 60 / 100;
                    this.itemView.setBackgroundResource(R.color.cornflower_blue_65);
                    break;
                case 3:
                    params.width = displayWidth * 80 / 100;
                    this.itemView.setBackgroundResource(R.color.light_sage);
                    break;
                case 4:
                    params.width = displayWidth;
                    this.itemView.setBackgroundResource(R.color.banana_yellow);
                    break;
                default:
                    params.width = displayWidth;
                    this.itemView.setBackgroundResource(R.color.design_default_color_background);
                    break;
            }
            this.itemView.setLayoutParams(params);
            if (!(selectedMood.getComment().equals(""))){
                commentImage.setImageResource(R.drawable.ic_comment_black_48px);
                commentImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(),"teste",Toast.LENGTH_SHORT).show();
                    }
                });

            }else{
                commentImage.setImageResource(0);
            }

            //this.productName.setText(selectedMood.getMoodState());



            //this.commentImage.setImageResource(R.drawable.ic_launcher_background);


        }


        @Override
        public void onClick(View v) {

            //Values are passing to activity & to fragment as well
            //       Toast.makeText(MainActivity.this, "Single Click on position        :"+position,
            //             Toast.LENGTH_SHORT).show();
        }

    }




    private int getMoodHeight() {
        int moodCount = 7;
        int moodHeight = 0;
        int statusBarHeight = 0;
        int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = Resources.getSystem().getDimensionPixelSize(resourceId);
        }

        // navigation bar height
        int navigationBarHeight = 0;
        int resourceNavId = Resources.getSystem().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceNavId > 0) {
            navigationBarHeight = Resources.getSystem().getDimensionPixelSize(resourceNavId);
        }


        return moodHeight = (Resources.getSystem().getDisplayMetrics().heightPixels - statusBarHeight - navigationBarHeight) / moodCount;
    }





}



