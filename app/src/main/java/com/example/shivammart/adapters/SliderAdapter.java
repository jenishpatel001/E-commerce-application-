package com.example.shivammart.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shivammart.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder> {

    int[] images;

    public SliderAdapter(int[] images){
        this.images = images;

    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.activity_slider_item,parent,false );
        return new Holder( view );
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {
    viewHolder.image_view.setImageResource( images[position] );
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class Holder extends SliderViewAdapter.ViewHolder {
ImageView image_view;
        public Holder(View itemView) {
            super( itemView );
            image_view = itemView.findViewById( R.id.image_vw );
        }
    }
}