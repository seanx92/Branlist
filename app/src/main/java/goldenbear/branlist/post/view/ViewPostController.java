package goldenbear.branlist.post.view;

import android.support.annotation.NonNull;

import goldenbear.branlist.data.post.Post;
import goldenbear.branlist.utils.ParseHelper;

/**
 * Created by metaphoenix on 11/21/16.
 */
public class ViewPostController implements ViewPostContract.Controller {

    private final ViewPostContract.View mPostView;

    public ViewPostController(@NonNull ViewPostContract.View postView) {
        mPostView = postView;
        mPostView.setController(this);
    }

    public void showData(String postId) {
        Post post = new Post();
        ParseHelper.getObject(post, postId);
        mPostView.setTitle(post.getTitle());
        mPostView.setDescription(post.getDescription());
        mPostView.setSubmitter(post.getSubmitter());
        mPostView.setDate(post.getCreatedDate().toString());
        mPostView.setType(post.getType().toString());
    }

    @Override
    public void start() {
    }
}
