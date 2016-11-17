package goldenbear.branlist.home;

import android.support.annotation.NonNull;

/**
 * Created by metaphoenix on 11/17/16.
 */
public class HomeController implements HomeContract.Controller {

    private final HomeContract.View mHomeView;

    public HomeController(@NonNull HomeContract.View homeView) {
        mHomeView = homeView;
        mHomeView.setController(this);
    }

    public void addNewPost() {
        mHomeView.showAddPost();
    }

    @Override
    public void start() {
    }
}
