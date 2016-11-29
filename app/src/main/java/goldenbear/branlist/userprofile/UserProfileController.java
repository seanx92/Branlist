package goldenbear.branlist.userprofile;

import android.support.annotation.NonNull;

/**
 * Created by metaphoenix on 11/29/16.
 */

public class UserProfileController implements UserProfileContract.Controller {

    private final UserProfileContract.View mView;

    public UserProfileController(@NonNull UserProfileContract.View view) {
        mView = view;
        mView.setController(this);
    }

    @Override
    public void start() {
    }
}
