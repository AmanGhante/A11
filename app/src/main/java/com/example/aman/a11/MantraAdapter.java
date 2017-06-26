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

public class MantraAdapter  extends RecyclerView.Adapter<MantraAdapter.MyMantraViewHolder> {
    private List<Mantra> aList;
    private final onClickListner listner;
    public class MyMantraViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView A1,A2,A3;
        public MyMantraViewHolder(View view){
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

    public MantraAdapter(List<Mantra> aList, onClickListner listner) {
        this.aList = aList;
        this.listner = listner;
    }

    @Override
    public MyMantraViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu4,parent,false);
        return new MyMantraViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyMantraViewHolder holder, int position) {
        Mantra obj=aList.get(position);
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

