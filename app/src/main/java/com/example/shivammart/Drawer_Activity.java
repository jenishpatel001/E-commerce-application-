package com.example.shivammart;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class Drawer_Activity extends AppCompatActivity {
    DrawerLayout dr;
    NavigationView navigationView;
    FragmentTransaction ft;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentManager fm;
    View header;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_drawer );
//       dr = findViewById( R.id.dr );
        fm = getSupportFragmentManager();
        //  navigationView = findViewById( R.id.nav );

        ft = fm.beginTransaction();
        ft.commit();


        actionBarDrawerToggle = new ActionBarDrawerToggle( this, dr, R.string.start, R.string.end );
        dr.addDrawerListener( actionBarDrawerToggle );
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor( getResources().getColor( R.color.white ) );
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        actionBarDrawerToggle.setDrawerIndicatorEnabled( true );
        navigationView.setNavigationItemSelectedListener( this::onNavigationItemSelected );
        header = navigationView.inflateHeaderView( R.layout.drawer_upperpart );


    }


    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction ft = fm.beginTransaction();
        int id = item.getItemId();
        if (id == R.id.editprofile) {

  //    } else if (id == R.id.editpreferences) {

        } else if (id == R.id.verifyprofile) {
        } else if (id == R.id.settings) {
//      } else if (id == R.id.successstories) {
        } else if (id == R.id.aboutus) {
        } else if (id == R.id.contactus) {
        } else if (id == R.id.logout1) {

        }

        ft.commit();
        dr.closeDrawer( navigationView );
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected( item )) {
            return true;
        }
        return super.onOptionsItemSelected( item );

//        public void logout () {
//            editor.putString( "userMobile", "" );
//            editor.putString( "status", "logout" );
//            editor.commit();
//            Intent intent = new Intent( Drawer_Activity.this, RegisterActivity.class );
//            startActivity( intent );
//            finish();

        }
    }


