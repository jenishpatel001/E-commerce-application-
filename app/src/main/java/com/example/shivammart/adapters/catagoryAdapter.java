package com.example.shivammart.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shivammart.R;
import com.example.shivammart.activities.ShowAllActivity;
import com.example.shivammart.models.CategoryModel;

import java.util.List;
import java.util.Locale;

public class catagoryAdapter extends RecyclerView.Adapter<catagoryAdapter.ViewHolder> {

    private Context context;

    public catagoryAdapter(Context context, List<CategoryModel> list) {
        this.context = context;
        this.list = list;
    }

    private List<CategoryModel>list;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from( parent.getContext() ).inflate( R.layout.catagory_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull catagoryAdapter.ViewHolder holder, int position) {
        Glide.with( context ).load( list.get( position ).getImg_url() ).into( holder.catImg );
        holder.catName.setText( list.get( position ).getName() );

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowAllActivity.class );
                intent.putExtra( "type",list.get( position ).getType() );
                context.startActivity( intent );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView catImg;
        TextView catName,new_price;
        public ViewHolder(@NonNull  View itemView) {
            super( itemView );
            catImg = itemView.findViewById( R.id.cat_img );
            catName = itemView.findViewById( R.id.cat_name );
            new_price = itemView.findViewById( R.id.new_price );

        }
    }
}
