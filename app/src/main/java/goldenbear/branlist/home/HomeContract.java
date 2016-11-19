package goldenbear.branlist.home;

import goldenbear.branlist.base.BaseController;
import goldenbear.branlist.base.BaseView;

/**
 * Created by metaphoenix on 11/17/16.
 */
public interface HomeContract {

    interface View extends BaseView<Controller> {
        void showAddPost();

        void refreshPost();
    }

    interface Controller extends BaseController {
        void addNewPost();

        void result(int requestCode, int resultCode);
    }
}
