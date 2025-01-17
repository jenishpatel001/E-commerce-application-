package com.example.shivammart.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.shivammart.R;
import com.example.shivammart.activities.ShowAllActivity;
import com.example.shivammart.adapters.NewProductsAdapter;
import com.example.shivammart.adapters.PopularProductsAdapter;
import com.example.shivammart.adapters.catagoryAdapter;
import com.example.shivammart.models.CategoryModel;
import com.example.shivammart.models.NewProductsModel;
import com.example.shivammart.models.PopularProductsModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {


    TextView catShowAll, popularShowAll, newProductAll;


    LinearLayout linearLayout;
    ProgressDialog progressDialog;
    RecyclerView catrecyclerview, newProductRecyclerview, popularRecyclerview;

    //catagory recyclerview
    catagoryAdapter catagoryAdapter;
    List<CategoryModel> categoryModelList;

    //newproductrecyclerview
    NewProductsAdapter newProductsAdapter;
    List<NewProductsModel> newProductsModelList;

    //Popular products
    PopularProductsAdapter popularProductsAdapter;
    List<PopularProductsModel> popularProductsModelList;

    //firestore
    FirebaseFirestore db;

    public HomeFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate( R.layout.fragment_home, container, false );


        db = FirebaseFirestore.getInstance();


        progressDialog = new ProgressDialog( getActivity() );

        catrecyclerview = root.findViewById( R.id.rec_category );
        newProductRecyclerview = root.findViewById( R.id.new_product_rec );
        popularRecyclerview = root.findViewById( R.id.popular_rec );

        catShowAll = root.findViewById( R.id.category_see_all );
        popularShowAll = root.findViewById( R.id.popular_see_all );
        newProductAll = root.findViewById( R.id.newProducts_see_all );


        catShowAll.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class );
                startActivity( intent );
            }
        } );
        popularShowAll.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class );
                startActivity( intent );
            }
        } );
        newProductAll.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowAllActivity.class );
                startActivity( intent );
            }
        } );


        linearLayout = root.findViewById( R.id.home_layout );
        linearLayout.setVisibility( View.GONE );
        //image slider

        ImageSlider imageSlider = root.findViewById( R.id.image_slider );
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add( new SlideModel( R.drawable.cooker, "Discount On Steel Cooker Set", ScaleTypes.CENTER_CROP ) );
        slideModels.add( new SlideModel( R.drawable.mixer, "Discount On Mixture", ScaleTypes.CENTER_CROP ) );
        slideModels.add( new SlideModel( R.drawable.discount, "50% OFF", ScaleTypes.CENTER_CROP ) );

        imageSlider.setImageList( slideModels );

        progressDialog.setTitle( "Welcome to my Ecommerce App" );
        progressDialog.setMessage( "Please wait....." );
        progressDialog.setCanceledOnTouchOutside( false );
        progressDialog.show();

        //category
        catrecyclerview.setLayoutManager( new LinearLayoutManager( getActivity(), RecyclerView.HORIZONTAL, false ) );
        categoryModelList = new ArrayList<>();
        catagoryAdapter = new catagoryAdapter( getContext(), categoryModelList );
        catrecyclerview.setAdapter( catagoryAdapter );

        db.collection( "Category" )
                .get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                CategoryModel categoryModel = document.toObject( CategoryModel.class );
                                categoryModelList.add( categoryModel );
                                catagoryAdapter.notifyDataSetChanged();
                                linearLayout.setVisibility( View.VISIBLE );
                                progressDialog.dismiss();

                            }
                        } else {
                            Toast.makeText( getActivity(), "" + task.getException(), Toast.LENGTH_SHORT ).show();

                        }
                    }
                } );

        //New products
        newProductRecyclerview.setLayoutManager( new LinearLayoutManager( getActivity(), RecyclerView.HORIZONTAL, false ) );
        newProductsModelList = new ArrayList<>();
        newProductsAdapter = new NewProductsAdapter( getContext(), newProductsModelList );
        newProductRecyclerview.setAdapter( newProductsAdapter );

        db.collection( "NewProducts" )
                .get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                NewProductsModel newProductsModel = document.toObject( NewProductsModel.class );
                                newProductsModelList.add( newProductsModel );
                                newProductsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText( getActivity(), "" + task.getException(), Toast.LENGTH_SHORT ).show();

                        }
                    }
                } );
        //Popular products

        popularRecyclerview.setLayoutManager( new GridLayoutManager( getActivity(), 2 ) );
        popularProductsModelList = new ArrayList<>();
        popularProductsAdapter = new PopularProductsAdapter( getContext(), popularProductsModelList );
        popularRecyclerview.setAdapter( popularProductsAdapter );


        db.collection( "AllProducts" )
                .get()
                .addOnCompleteListener( new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularProductsModel popularProductsModel = document.toObject( PopularProductsModel.class );
                                popularProductsModelList.add( popularProductsModel );
                                popularProductsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText( getActivity(), "" + task.getException(), Toast.LENGTH_SHORT ).show();

                        }
                    }
                } );

        return root;
    }
}