package goldenbear.branlist.post;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import goldenbear.branlist.R;
import goldenbear.branlist.post.edit.EditPostController;
import goldenbear.branlist.post.edit.EditPostFragment;
import goldenbear.branlist.post.view.ViewPostController;
import goldenbear.branlist.post.view.ViewPostFragment;
import goldenbear.branlist.utils.ActivityUtils;

public class PostActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_POST = 1;
    public static final int REQUEST_VIEW_POST = 2;
    private ActionBar actionBar;

    private EditPostController mEditPostController;
    private ViewPostController mViewPostController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_act);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        int requestCode = bundle.getInt("requestCode");
        if (requestCode == REQUEST_ADD_POST) {
            inflateEditPostFragment();
        } else if (requestCode == REQUEST_VIEW_POST) {
            inflateViewPostFragment(bundle);
        }
    }

    private void inflateViewPostFragment(Bundle bundle) {
        // Fill the fragment
        ViewPostFragment viewPostFragment =
                (ViewPostFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);

        if (viewPostFragment == null) {
            viewPostFragment = ViewPostFragment.newInstance(bundle);

            actionBar.setTitle("Post");
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    viewPostFragment, R.id.contentFrame);
        }

        // Create the controller
        mViewPostController = new ViewPostController(viewPostFragment);

        viewPostFragment.setController(mViewPostController);
    }

    private void inflateEditPostFragment() {
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
