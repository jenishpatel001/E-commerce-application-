package com.example.shivammart.adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.shivammart.R;

public class SlidAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlidAdapter(Context context) {
        this.context = context;
    }

    int imagesArray[] = {
            R.drawable.product,
            R.drawable.delivery,
            R.drawable.service

    };

    int headingArray[] = {
            R.string.first_slide,
            R.string.second_slide,
            R.string.third_slide
    };

    int descriptionArray[] = {
            R.string.description,
            R.string.description,
            R.string.description
    };

    @Override
    public int getCount() {
        return headingArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (ConstraintLayout) object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
       layoutInflater = (LayoutInflater) context.getSystemService( context.LAYOUT_INFLATER_SERVICE );
       View view = layoutInflater.inflate( R.layout.slider_layout,container,false );

        ImageView imageView = view.findViewById( R.id.slider_img );
        TextView heading = view.findViewById( R.id.heading );
       TextView description = view.findViewById( R.id.description );

        imageView.setImageResource( imagesArray[position] );
        heading.setText( headingArray[position] );
       description.setText( descriptionArray[position] );


        container.addView( view );

        return view;
    }

    @Override
    public void destroyItem( ViewGroup container, int position, Object object) {
       container.removeView( (ConstraintLayout)object );
    }
}
