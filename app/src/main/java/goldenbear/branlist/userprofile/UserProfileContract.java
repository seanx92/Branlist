package goldenbear.branlist.userprofile;

import goldenbear.branlist.basetemplate.BaseController;
import goldenbear.branlist.basetemplate.BaseView;
import goldenbear.branlist.data.user.User;

/**
 * Created by metaphoenix on 11/29/16.
 */

public interface UserProfileContract {

    interface View extends BaseView<UserProfileContract.Controller> {
        void setEditable();

        void setUneditable();
    }

    interface Controller extends BaseController {
        void loadData();

        void setEditable();

        void setUneditable();

        void saveChanges();

        void setUser(User user);
    }
}
