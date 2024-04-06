package com.example.shivammart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shivammart.R;
import com.example.shivammart.models.NewProductsModel;
import com.example.shivammart.models.PopularProductsModel;
import com.example.shivammart.models.ShowAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.StringValue;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailed_image;
    TextView rating, name, description, price;
    Button addtocart, buynow;
    ImageView additems, removeitems;

    //New Products
    NewProductsModel newProductsModel = null;

    //popular products
    PopularProductsModel popularProductsModel = null;

    //Show All
    ShowAllModel showAllModel = null;

    FirebaseAuth auth;
    private FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_detailed );

        firestore = FirebaseFirestore.getInstance();

        auth = FirebaseAuth.getInstance();

        final Object obj = getIntent().getSerializableExtra( "detailed" );

        if (obj instanceof NewProductsModel) {
            newProductsModel = (NewProductsModel) obj;
        } else if (obj instanceof PopularProductsModel) {
            popularProductsModel = (PopularProductsModel) obj;
        } else if (obj instanceof ShowAllModel) {
            showAllModel = (ShowAllModel) obj;


        }


        detailed_image = findViewById( R.id.detailed_image );
//          rating = findViewById( R.id.rating );
        name = findViewById( R.id.detailed_name );
        description = findViewById( R.id.detailed_desc );
        price = findViewById( R.id.detailed_price );
        addtocart = findViewById( R.id.add_to_cart );
        buynow = findViewById( R.id.buy_now );
        additems = findViewById( R.id.add_item );
        removeitems = findViewById( R.id.remove_item );

        //New Products

        if (newProductsModel != null) {
            Glide.with( getApplicationContext() ).load( newProductsModel.getImg_url() ).into( detailed_image );
            name.setText( newProductsModel.getName() );
//            rating.setText( newProductsModel.getRating() );
            description.setText( newProductsModel.getDescription() );
            price.setText( String.valueOf( newProductsModel.getPrice() ) );
            name.setText( newProductsModel.getName() );
        }
        if (popularProductsModel != null) {
            Glide.with( getApplicationContext() ).load( popularProductsModel.getImg_url() ).into( detailed_image );
            name.setText( popularProductsModel.getName() );
//            rating.setText( newProductsModel.getRating() );
            description.setText( popularProductsModel.getDescription() );
            price.setText( String.valueOf( popularProductsModel.getPrice() ) );
            name.setText( popularProductsModel.getName() );

            if (showAllModel != null) {
                Glide.with( getApplicationContext() ).load( showAllModel.getImg_url() ).into( detailed_image );
                name.setText( showAllModel.getName() );
//            rating.setText( newProductsModel.getRating() );
                description.setText( showAllModel.getDescription() );
                price.setText( String.valueOf( showAllModel.getPrice() ) );
                name.setText( showAllModel.getName() );
            }
            addtocart.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addtoCart();
                }
            } );


        }
    }

    private void addtoCart() {

        String savecurrentTime = null,saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format( calForDate.getTime() );

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentDate = currentTime.format( calForDate.getTime() );

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put( "productName",name.getText().toString() );
        cartMap.put( "productPrice",price.getText().toString());
        cartMap.put( "currentTime",savecurrentTime);
        cartMap.put( "productPrice",saveCurrentDate);

        firestore.collection( "AddToCart" ).document(auth.getCurrentUser().getUid()).collection( "user" ).add( cartMap ).addOnCompleteListener( new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete( Task<DocumentReference> task) {
                Toast.makeText( DetailedActivity.this, "Added To A Cart", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}