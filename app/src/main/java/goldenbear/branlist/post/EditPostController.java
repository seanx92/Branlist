package goldenbear.branlist.post;

import android.support.annotation.NonNull;

/**
 * Created by metaphoenix on 11/17/16.
 */
public class EditPostController implements PostContract.Controller {

    private final PostContract.View mPostView;

    public EditPostController(@NonNull PostContract.View postView) {
        mPostView = postView;
        mPostView.setController(this);
    }

    @Override
    public void start() {
    }
}
