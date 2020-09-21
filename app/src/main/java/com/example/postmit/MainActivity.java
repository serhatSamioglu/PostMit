package com.example.postmit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        init();
    }

    /*@Override
    public void onBackPressed() {
        //super.onBackPressed();
    }*/

    private void init(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){

            case R.id.nav_anasayfa:{

                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.main, true)
                        .build();

                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.home,null,
                            navOptions);
                break;
            }

            case R.id.nav_products:{
                if(isValidDestination(R.id.products)) {
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.products);
                }
                break;
            }

            case R.id.nav_mits:{
                if(isValidDestination(R.id.mits)) {
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.mits);
                }
                break;
            }

            case R.id.nav_contact:{
                if(isValidDestination(R.id.contact)) {
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.contact);
                }
                break;
            }

            case R.id.nav_settings:{
                if(isValidDestination(R.id.settings)) {
                    Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.settings);
                }
                break;
            }
        }
        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private boolean isValidDestination(int destination){
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination().getId();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:{
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                else{
                    return false;
                }
            }

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.nav_host_fragment),drawerLayout);
    }
}
