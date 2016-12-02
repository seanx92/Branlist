package goldenbear.branlist.data.post;

import goldenbear.branlist.basetemplate.BaseParseQueryFilter;

/**
 * Created by metaphoenix on 11/19/16.
 */
public class PostFilter extends BaseParseQueryFilter {

    public void setType(PostType postType) {
        addWhereEqualToConstraint("type", postType.name());
    }

    public void setId(String postId) {
        addWhereEqualToConstraint("id", postId);
    }

    public void setSubmitter(String submitter) {
        addWhereEqualToConstraint("submitter", submitter);
    }

    public void setQuery(String query) {
        addWhereContainsConstraint("title", query);
        addWhereContainsConstraint("description", query);
    }

    public void setOrderByDescending(String key) {
        addOrderByDescending(key);
    }

    public Class getObjectClass() {
        return Post.class;
    }

    public String getObjectName() {
        return "Post";
    }
}
