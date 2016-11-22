package goldenbear.branlist.post.edit;

import android.support.annotation.NonNull;

import java.util.UUID;

import goldenbear.branlist.data.post.Post;
import goldenbear.branlist.data.post.PostType;
import goldenbear.branlist.utils.ParseHelper;

/**
 * Created by metaphoenix on 11/17/16.
 */
public class EditPostController implements EditPostContract.Controller {

    private final EditPostContract.View mPostView;

    public EditPostController(@NonNull EditPostContract.View postView) {
        mPostView = postView;
        mPostView.setController(this);
    }

    @Override
    public void start() {
    }

    public void savePost(String title, String description, PostType type) {
        String currentUserName = ParseHelper.getCurrentUser().getUsername();
        String id = UUID.randomUUID().toString();
        Post newPost = new Post(id, title, description, type, currentUserName);
        ParseHelper.saveObject(newPost);
    }
}
