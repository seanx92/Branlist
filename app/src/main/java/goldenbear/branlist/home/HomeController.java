package goldenbear.branlist.home;

import android.app.Activity;
import android.support.annotation.NonNull;

import goldenbear.branlist.post.PostActivity;
import goldenbear.branlist.utils.ParseHelper;

/**
 * Created by metaphoenix on 11/17/16.
 */
public class HomeController implements HomeContract.Controller {

    private final HomeContract.View mHomeView;
    private String mSubmitter, mQuery;

    public HomeController(@NonNull HomeContract.View homeView) {
        mHomeView = homeView;
        mHomeView.setController(this);
    }

    public void addPost() {
        mHomeView.showAddPost();
    }

    public void viewPost(String id) {
        mHomeView.showViewPost(id);
    }

    public String getSubmitter() {
        return mSubmitter;
    }

    public void setSubmitter(String submitter) {
        if ((mSubmitter == null && submitter == null) ||
                (mSubmitter != null && mSubmitter.equals(submitter))) {
            return;
        }
        mSubmitter = submitter;
        mHomeView.initializeFragments();
    }

    public String getQuery() {
        return mQuery;
    }

    @Override
    public void start() {
    }

    public void result(int requestCode, int resultCode) {
        if (requestCode == PostActivity.REQUEST_ADD_POST) {
            if (resultCode == Activity.RESULT_OK) {
                mHomeView.refreshPost();
            }
        } else if (requestCode == PostActivity.REQUEST_VIEW_POST) {

        }
    }

    public void deletePost(String postId) {
        ParseHelper.deleteObject("Post", postId);
        mHomeView.refreshPost();
    }

    public void searchPost(String query) {
        mQuery = query;
        mHomeView.initializeFragments();
    }

    public void editPost(String postId) {
        mHomeView.showEditPost(postId);
    }
}
