package com.example.aman.a11;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aman on 13/05/2017.
 */

public class DeviAdapter extends RecyclerView.Adapter<DeviAdapter.MyDeviViewHolder> {
    private List<Devi> DeAarti;
    private final onClickListner listner;
    public class MyDeviViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView D1,D2,D3;
        public MyDeviViewHolder(View view){
            super(view);
            D1=(TextView)view.findViewById(R.id.g1);
            D2=(TextView)view.findViewById(R.id.g2);
            D3=(TextView)view.findViewById(R.id.g3);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            listner.onItemClickListner(pos);
        }
    }
    public DeviAdapter(List<Devi> DeAarti, onClickListner listner){
        this.DeAarti=DeAarti;
        this.listner = listner;
    }
    @Override
    public MyDeviViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.menu2,parent,false);
        return new MyDeviViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyDeviViewHolder holder, int position) {
        Devi devi=DeAarti.get(position);
        holder.D1.setText(devi.getD2());
        holder.D2.setText(devi.getD2());
        holder.D3.setText(devi.getD3());
    }

    @Override
    public int getItemCount() {
        return DeAarti.size();
    }

    public interface onClickListner{
        void onItemClickListner(int pos);
    }
}
