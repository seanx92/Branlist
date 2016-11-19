package goldenbear.branlist.post;

import goldenbear.branlist.base.BaseController;
import goldenbear.branlist.base.BaseView;
import goldenbear.branlist.data.Post;

/**
 * Created by metaphoenix on 11/17/16.
 */
public interface PostContract {

    interface View extends BaseView<Controller> {

    }

    interface Controller extends BaseController {
        void savePost(String title, String description, Post.Type type);
    }
}
