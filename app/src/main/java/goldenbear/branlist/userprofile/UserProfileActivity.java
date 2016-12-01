package goldenbear.branlist.userprofile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import goldenbear.branlist.R;
import goldenbear.branlist.utils.ActivityUtils;

public class UserProfileActivity extends AppCompatActivity {

    public static int REQUEST_MY_PROFILE = 1;

    private ActionBar actionBar;
    private UserProfileController mController;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_act);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);

        Bundle bundle = getIntent().getExtras();
        inflateFragment(bundle);
    }

    private void inflateFragment(Bundle bundle) {
        // Fill the fragment
        UserProfileFragment userProfileFragment = (UserProfileFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (userProfileFragment == null) {
            userProfileFragment = UserProfileFragment.newInstance(bundle);
            actionBar.setTitle("My Profile");
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    userProfileFragment, R.id.contentFrame);
        }

        // Create the controller
        mController = new UserProfileController(userProfileFragment);

        userProfileFragment.setController(mController);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                this.setResult(Activity.RESULT_CANCELED);
                this.finish();
                break;
            case R.id.edit_user_profile:
                editMode();
                break;
            case R.id.accept_change:
                normalMode();
                mController.saveChanges();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_profile_menu, menu);
        normalMode();
        return true;
    }

    public void normalMode() {
        if (menu == null) return;
        menu.setGroupVisible(R.id.edit_user_profile_group, true);
        menu.setGroupVisible(R.id.accept_change_group, false);
        mController.setUneditable();
    }

    public void editMode() {
        if (menu == null) return;
        menu.setGroupVisible(R.id.edit_user_profile_group, false);
        menu.setGroupVisible(R.id.accept_change_group, true);
        mController.setEditable();
    }
}
