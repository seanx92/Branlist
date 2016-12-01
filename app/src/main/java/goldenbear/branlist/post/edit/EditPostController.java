package goldenbear.branlist.post.edit;

import android.support.annotation.NonNull;

import goldenbear.branlist.data.post.Post;
import goldenbear.branlist.utils.ParseHelper;

/**
 * Created by metaphoenix on 11/17/16.
 */
public class EditPostController implements EditPostContract.Controller {

    private final EditPostContract.View mPostView;
    private Post mPost;

    public EditPostController(@NonNull EditPostContract.View postView) {
        mPostView = postView;
        mPostView.setController(this);
    }

    @Override
    public void start() {
    }

    public void setPost(Post post) {
        mPost = post;
    }

    public void savePost() {
        String currentUserName = ParseHelper.getCurrentUser().getUsername();
        mPost.setSubmitter(currentUserName);
        ParseHelper.saveObject(mPost);
    }

    public void updatePost() {
        ParseHelper.updateObject(mPost);
    }

    public void loadData() {
        ParseHelper.loadObject(mPost);
    }
}
