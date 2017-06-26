package com.example.aman.a11;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Aman on 29/05/2017.
 */

public class ChalisaAdaper extends RecyclerView.Adapter<ChalisaAdaper.MyChalisaViewHolder> {
    private List<Chalisa> chalisaList;
    private final onClickListner listner;
    public class MyChalisaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView C1,C2,C3;
        public MyChalisaViewHolder(View view){
            super(view);
            C1=(TextView)view.findViewById(R.id.g1);
            C2=(TextView)view.findViewById(R.id.g2);
            C3=(TextView)view.findViewById(R.id.g3);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            listner.onItemClickListner(pos);
        }
    }

    public ChalisaAdaper(List<Chalisa> chalisaList, onClickListner listner) {
        this.chalisaList = chalisaList;
        this.listner = listner;
    }

    @Override
    public MyChalisaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu5,parent,false);
        return new MyChalisaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyChalisaViewHolder holder, int position) {
        Chalisa chalisa=chalisaList.get(position);
        holder.C1.setText(chalisa.getC2());
        holder.C2.setText(chalisa.getC2());
        holder.C3.setText(chalisa.getC3());
    }

    @Override
    public int getItemCount() {
        return chalisaList.size();
    }

    public interface onClickListner{
        void onItemClickListner(int pos);
    }
}

