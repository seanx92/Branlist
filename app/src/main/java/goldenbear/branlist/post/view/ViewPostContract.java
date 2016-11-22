package goldenbear.branlist.post.view;

import goldenbear.branlist.basetemplate.BaseController;
import goldenbear.branlist.basetemplate.BaseView;

/**
 * Created by metaphoenix on 11/21/16.
 */
public interface ViewPostContract {
    interface View extends BaseView<Controller> {
        void setTitle(String title);

        void setType(String type);

        void setDescription(String description);

        void setSubmitter(String submitter);

        void setDate(String date);
    }

    interface Controller extends BaseController {
        void showData(String postId);
    }
}
