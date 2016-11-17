package goldenbear.branlist.post;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import goldenbear.branlist.R;
import goldenbear.branlist.utils.ActivityUtils;

public class PostActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_POST = 1;

    private EditPostController mEditPostController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_act);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        // Fill the fragment
        EditPostFragment editPostFragment =
                (EditPostFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (editPostFragment == null) {
            editPostFragment = EditPostFragment.newInstance();

            actionBar.setTitle("New Post");
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    editPostFragment, R.id.contentFrame);
        }

        // Create the controller
        mEditPostController = new EditPostController(editPostFragment);

        editPostFragment.setController(mEditPostController);
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
