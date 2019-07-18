package com.example.hijab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame,new home());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home");


        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();

                        if (id == R.id.nav_home) {
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame,new home());
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle("Home");
                            item.setCheckable(true);


                        } else if (id == R.id.nav_category) {
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame,new category());
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle("Category");
                            item.setCheckable(true);


                        } else if (id == R.id.nav_inbox) {
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame,new inbox());
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle("Inbox");
                            item.setCheckable(true);

                        } else if (id == R.id.nav_cart) {
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame,new cart());
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle("Cart");
                            item.setCheckable(true);


                        } else if (id == R.id.nav_me) {
                            fragmentTransaction = getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.frame,new me());
                            fragmentTransaction.commit();
                            getSupportActionBar().setTitle("Me");
                            item.setCheckable(true);


                        }


                        return true;
                    }
                });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_view, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            Toast.makeText(getApplicationContext(),"Search",Toast.LENGTH_SHORT).show();

        }
        else if(id==R.id.nav_favourite){
            Toast.makeText(getApplicationContext(),"Wishlist",Toast.LENGTH_SHORT).show();

        }


        return super.onOptionsItemSelected(item);
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.nav_order) {
            Toast.makeText(getApplicationContext(),"Order",Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.nav_setting) {
            Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_SHORT).show();

        }
         else if (id == R.id.nav_sandook) {
             Toast.makeText(getApplicationContext(),"Sandook",Toast.LENGTH_SHORT).show();

         }
        else if (id == R.id.nav_logout) {
            Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.nav_help) {
            Toast.makeText(getApplicationContext(),"Help",Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.nav_faq) {
            Toast.makeText(getApplicationContext(),"FAQs",Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.nav_about) {
            Toast.makeText(getApplicationContext(),"About us",Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.nav_share) {
            Toast.makeText(getApplicationContext(),"Share",Toast.LENGTH_SHORT).show();

        }
        else if (id == R.id.nav_rate) {
            Toast.makeText(getApplicationContext(),"Rate us",Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
