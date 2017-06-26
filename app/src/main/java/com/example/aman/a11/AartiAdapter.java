package com.example.aman.a11;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aman on 08/06/2017.
 */

public class AartiAdapter extends RecyclerView.Adapter<AartiAdapter.MyAartiViewHolder> {
    private List<Aarti> aList;
    private final onClickListner listner;
    public class MyAartiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView A1,A2,A3;
        public MyAartiViewHolder(View view){
            super(view);
            A1=(TextView)view.findViewById(R.id.g1);
            A2=(TextView)view.findViewById(R.id.g2);
            A3=(TextView)view.findViewById(R.id.g3);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            listner.onItemClickListner(pos);
        }
    }

    public AartiAdapter(List<Aarti> aList, onClickListner listner) {
        this.aList = aList;
        this.listner = listner;
    }

    @Override
    public MyAartiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu3,parent,false);
        return new MyAartiViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyAartiViewHolder holder, int position) {
        Aarti obj=aList.get(position);
        holder.A1.setText(obj.getA2());
        holder.A2.setText(obj.getA2());
        holder.A3.setText(obj.getA3());
    }

    @Override
    public int getItemCount() {
        return aList.size();
    }

    public interface onClickListner{
        void onItemClickListner(int pos);
    }
}
