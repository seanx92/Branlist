package goldenbear.branlist.home;

import android.app.Activity;
import android.support.annotation.NonNull;

import goldenbear.branlist.post.PostActivity;

/**
 * Created by metaphoenix on 11/17/16.
 */
public class HomeController implements HomeContract.Controller {

    private final HomeContract.View mHomeView;

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
}
