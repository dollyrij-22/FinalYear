package com.example.dr.finalyear;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by DR on 3/28/2017.
 */
public class NavigationDrawer extends AppCompatActivity {
    public static final String UPLOAD_URL = "http://attendance-dr22libraryapp.rhcloud.com/logout.php";
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    String name;
    TextView userTV;
    String user = "username";
    String dept = "department";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new Home());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Home");
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new Home());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Home");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.aboutus_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new AboutUs());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("AboutUs");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();

                        break;
                    case R.id.account_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new HomeFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Account Details");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.logout_id:
                        logout();
                        break;
                    case R.id.terms_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new TermsAndConditions());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("TermsAndConditions");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.help_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new HelpCenter());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Help Center");
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                }
                return true;
            }
        });
        View header=navigationView.getHeaderView(0);
        userTV = (TextView) header.findViewById(R.id.nav_header_text);
        name=getIntent().getStringExtra("name");
        userTV.setText("Welcome !!! " + name);
    }
    private void logout(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        logoutData();

                        Intent intent = new Intent(getApplicationContext(), login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        Toast.makeText(getApplicationContext(), "Logged out successfully !!!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    private void logoutData() {
        final String username = getIntent().getStringExtra("username");
        final String depart = getIntent().getStringExtra("department");

        class BackGround extends AsyncTask<Void,Void, String> {
            RequestHandler rh = new RequestHandler();
            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> data = new HashMap<>();
                data.put(dept, depart);
                data.put(user,username);
                String result = rh.sendPostRequest(UPLOAD_URL, data);
                return result;
            }
        }
        BackGround b = new BackGround();
        b.execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuLogout) {
            logout();
        }
        if (id == R.id.myAccount) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new HomeFragment());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("Account Details");
            item.setChecked(true);
            drawerLayout.closeDrawers();
        }
        if (id == R.id.aboutus) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_container, new AboutUs());
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("AboutUs");
            item.setChecked(true);
            drawerLayout.closeDrawers();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
    @Override
    public void onBackPressed() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        logoutData();

                        Intent intent = new Intent(getApplicationContext(), login.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        Toast.makeText(getApplicationContext(), "Logged out successfully !!!", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
