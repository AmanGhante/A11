package com.example.aman.a11;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aman on 06/05/2017.
 */

public class GaneshAdapter extends RecyclerView.Adapter<GaneshAdapter.MyGaneshViewHolder> {

    Context mContext;


    private List<Ganesh> GaAarti;
    private final onClickListner listner;
    GradientDrawable magnitudeCircle;
    public class MyGaneshViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView G1,G2,G3;
        public MyGaneshViewHolder(View view){
            super(view);

            //magnitudeCircle = (GradientDrawable) G1.getBackground();

            G1=(TextView)view.findViewById(R.id.g1);
            G2=(TextView)view.findViewById(R.id.g2);
            G3=(TextView)view.findViewById(R.id.g3);
            view.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            listner.onItemClickListner(pos);
        }
    }
    public GaneshAdapter(List<Ganesh> GaAarti, onClickListner listner){
        this.GaAarti=GaAarti;
        this.listner = listner;
    }
    @Override
    public MyGaneshViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu1,parent,false);
        return new MyGaneshViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyGaneshViewHolder holder, int position) {
        Ganesh ganesh=GaAarti.get(position);
        holder.G1.setText(ganesh.getG2());
        holder.G2.setText(ganesh.getG2());
        holder.G3.setText(ganesh.getG3());

//        int magnitudeColor = getMagnitudeColor(position);
        // Set the color on the magnitude circle
  //      magnitudeCircle.setColor(magnitudeColor);
    }

    @Override
    public int getItemCount() {
        return GaAarti.size();
    }

    public interface onClickListner{
        void onItemClickListner(int pos);
    }

/*
    // Set the proper background color on the magnitude circle.
    // Fetch the background from the TextView, which is a GradientDrawable.
    GradientDrawable magnitudeCircle = (GradientDrawable) G1.getBackground();
    // Get the appropriate background color based on the current earthquake magnitude
    int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());
    // Set the color on the magnitude circle
            magnitudeCircle.setColor(magnitudeColor);

    private int getMagnitudeColor(int magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(mContext,magnitudeColorResourceId);
    }*/
}
