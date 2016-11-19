package goldenbear.branlist.post;

import android.support.annotation.NonNull;

import goldenbear.branlist.data.Post;
import goldenbear.branlist.utils.ParseHelper;

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

    public void savePost(String title, String description, Post.Type type) {
        String currentUserName = ParseHelper.getCurrentUser().getUsername();
        Post newPost = new Post(title, description, type, currentUserName);
        ParseHelper.saveObject(newPost);
    }
}
