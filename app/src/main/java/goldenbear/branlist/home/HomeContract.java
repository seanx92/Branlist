package goldenbear.branlist.home;

import goldenbear.branlist.basetemplate.BaseController;
import goldenbear.branlist.basetemplate.BaseView;

/**
 * Created by metaphoenix on 11/17/16.
 */
public interface HomeContract {

    interface View extends BaseView<Controller> {
        void showAddPost();

        void showViewPost(String id);
        void refreshPost();
    }

    interface Controller extends BaseController {
        void addPost();

        void viewPost(String id);
        void result(int requestCode, int resultCode);
    }
}
