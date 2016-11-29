package goldenbear.branlist.userprofile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import goldenbear.branlist.R;
import goldenbear.branlist.utils.ActivityUtils;

public class UserProfileActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private UserProfileController mController;

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

        inflateFragment();
    }

    private void inflateFragment() {
        // Fill the fragment
        UserProfileFragment userProfileFragment = (UserProfileFragment)
                getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (userProfileFragment == null) {
            userProfileFragment = new UserProfileFragment();
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
        }
        return super.onOptionsItemSelected(item);
    }
}
