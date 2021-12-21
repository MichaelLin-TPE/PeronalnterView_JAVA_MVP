package com.money.peronainterviewproject_java_mvp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.money.peronainterviewproject_java_mvp.json.WeatherTime;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter {

    private static final int TIME = 0;

    private static final int PHOTO = 1;

    private ArrayList<WeatherTime> timeArrayList;

    private int timeIndex;

    private OnWeatherTimeItemClickListener onWeatherTimeItemClickListener;

    public void setOnWeatherTimeItemClickListener(OnWeatherTimeItemClickListener onWeatherTimeItemClickListener){
        this.onWeatherTimeItemClickListener = onWeatherTimeItemClickListener;
    }

    public void setTimeArrayList(ArrayList<WeatherTime> timeArrayList){
        this.timeArrayList = timeArrayList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        if (viewType == TIME){

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_item_layout,parent,false);
            return new TimeViewHolder(view);

        }

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item_layout,parent,false);

        return new PhotoViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {

        if (position % 2 == 1){
            return PHOTO;
        }

        return TIME;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Log.i("Michael","position : "+position);

        if (holder instanceof TimeViewHolder){
            if (timeIndex == timeArrayList.size()){
                timeIndex = 0;
            }
            ((TimeViewHolder)holder).showView(timeArrayList.get(position - timeIndex));
            timeIndex ++;
            ((TimeViewHolder)holder).setOnWeatherTimeItemClickListener(onWeatherTimeItemClickListener);

            return;

        }
        if (holder instanceof PhotoViewHolder){
            ((PhotoViewHolder)holder).showView();
        }

    }

    @Override
    public int getItemCount() {
        return timeArrayList.size()*2;
    }


    public static class TimeViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTime;
        private final TextView tvTemp;

        private final ConstraintLayout root;

        private OnWeatherTimeItemClickListener onWeatherTimeItemClickListener;

        public TimeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTemp = itemView.findViewById(R.id.item_temp);
            tvTime = itemView.findViewById(R.id.item_time);
            root = itemView.findViewById(R.id.item_root);

        }

        public void showView(WeatherTime weatherTime) {
            tvTime.setText(String.format("%s\n%s",weatherTime.getStartTime() ,weatherTime.getEndTime()));

            if (weatherTime.getParameter() == null){
                return;
            }
            tvTemp.setText(String.format("%s",weatherTime.getParameter().getParameterName()+weatherTime.getParameter().getParameterUnit()));
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onWeatherTimeItemClickListener.onTimeClick(weatherTime);
                }
            });
        }

        public void setOnWeatherTimeItemClickListener(OnWeatherTimeItemClickListener onWeatherTimeItemClickListener) {
            this.onWeatherTimeItemClickListener = onWeatherTimeItemClickListener;
        }
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivPhoto;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.item_photo);
        }

        public void showView() {

        }
    }

    public interface OnWeatherTimeItemClickListener{
        void onTimeClick(WeatherTime time);
    }


}
