package goldenbear.branlist.userprofile;

import android.support.annotation.NonNull;

import goldenbear.branlist.data.user.User;
import goldenbear.branlist.utils.ParseHelper;

/**
 * Created by metaphoenix on 11/29/16.
 */

public class UserProfileController implements UserProfileContract.Controller {

    private final UserProfileContract.View mProfileView;
    private User mUser;

    public UserProfileController(@NonNull UserProfileContract.View view) {
        mProfileView = view;
        mProfileView.setController(this);
    }

    public void setUser(User user) {
        mUser = user;
    }

    public void loadData() {
        ParseHelper.getUser(mUser);
    }

    public void saveChanges() {
        ParseHelper.saveUser(mUser);
    }

    public void setEditable() {
        mProfileView.setEditable();
    }

    public void setUneditable() {
        mProfileView.setUneditable();
    }

    @Override
    public void start() {
    }
}
