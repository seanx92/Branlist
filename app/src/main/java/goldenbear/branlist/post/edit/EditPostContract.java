package goldenbear.branlist.post.edit;

import goldenbear.branlist.basetemplate.BaseController;
import goldenbear.branlist.basetemplate.BaseView;
import goldenbear.branlist.data.post.PostType;

/**
 * Created by metaphoenix on 11/17/16.
 */
public interface EditPostContract {

    interface View extends BaseView<Controller> {

    }

    interface Controller extends BaseController {
        void savePost(String title, String description, PostType type);
    }
}
