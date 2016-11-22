package goldenbear.branlist.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import goldenbear.branlist.R;
import goldenbear.branlist.login.DispatchActivity;
import goldenbear.branlist.utils.ActivityUtils;
import goldenbear.branlist.utils.ParseHelper;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private HomeContract.Controller mHomeController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_act);

        //Set up the toolbar.
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        //ab.setDisplayHomeAsUpEnabled(true);


        //Set up the navigation drawer.
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupNavigationView();
        }

        //Fill the fragment
        HomeFragment homeFragment =
                (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (homeFragment == null) {
            //Create the fragment
            homeFragment = HomeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), homeFragment, R.id.contentFrame
            );
        }

        //Create the controller
        mHomeController = new HomeController(homeFragment);
    }

    private void setupNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        // do something
                        break;
                    case R.id.nav_myprofiles:
                        // do something
                        break;
                    case R.id.nav_myposts:
                        // do something
                        break;
                    case R.id.nav_about_us:
                        // do something
                        break;
                    case R.id.nav_logout:
                        logout();
                        break;
                    default:
                        // do something
                }
                // Close the navigation drawer when an item is selected.
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void logout() {
        ParseHelper.logout();
        // FLAG_ACTIVITY_CLEAR_TASK only works on API 11, so if the user
        // logs out on older devices, we'll just exit.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            Intent intent = new Intent(HomeActivity.this, DispatchActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                setUserInfo();
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return false;
    }

    private void setUserInfo() {
        TextView userNameView = (TextView) findViewById(R.id.profile_name);
        TextView userEmailView = (TextView) findViewById(R.id.profile_email);
        userNameView.setText(ParseHelper.getCurrentUser().getUsername());
        userEmailView.setText(ParseHelper.getCurrentUser().getEmail());
    }
}
