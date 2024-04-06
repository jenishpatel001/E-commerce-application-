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
import com.example.shivammart.activities.DetailedActivity;
import com.example.shivammart.models.NewProductsModel;

import java.util.List;

public class NewProductsAdapter extends RecyclerView.Adapter<NewProductsAdapter.viewHolder> {


    private Context context;
    private List<NewProductsModel> list;

    public NewProductsAdapter(Context context, List<NewProductsModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NewProductsAdapter.viewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        return new viewHolder( LayoutInflater.from( parent.getContext() ).inflate( R.layout.new_products,parent,false ) );
    }

    @Override
    public void onBindViewHolder( NewProductsAdapter.viewHolder holder, int position) {

        Glide.with( context ).load( list.get( position ) .getImg_url()).into( holder.newimg );
        holder.newName.setText( list.get( position ).getName() );
        holder.new_price.setText( String.valueOf(  list.get( position ).getPrice()));

        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent intent = new Intent(context, DetailedActivity.class );
                 intent.putExtra( "detailed",list.get( position ) );
                 context.startActivity( intent );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView newimg;
        TextView newName ,new_price;
        public viewHolder( View itemView) {
            super( itemView );

            newimg = itemView.findViewById( R.id.new_image );
            newName = itemView.findViewById( R.id.new_product_name );
            new_price = itemView.findViewById( R.id.new_price );


        }
    }
}
