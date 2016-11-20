package goldenbear.branlist.data.post;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import goldenbear.branlist.basetemplate.BaseParseObject;

/**
 * Created by metaphoenix on 11/17/16.
 */
public final class Post implements BaseParseObject {
    @NonNull
    private final String mId;

    @NonNull
    private final String mTitle;

    @Nullable
    private final String mDescription;

    @NonNull
    private final PostType mType;
    @NonNull
    private final String mSubmitter;

    private Date mDate;

    public Post(@NonNull String title, @Nullable String description, @NonNull PostType type,
                @NonNull String submitter) {
        mTitle = title;
        mDescription = description;
        mType = type;
        mSubmitter = submitter;
        mId = UUID.randomUUID().toString();
    }

    @NonNull
    public String getId() {
        return mId;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    @NonNull
    public PostType getType() {
        return mType;
    }

    @NonNull
    public Date getDate() {
        return mDate;
    }

    @NonNull
    public String getSubmitter() {
        return mSubmitter;
    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("id", mId);
        map.put("title", mTitle);
        map.put("description", mDescription);
        map.put("type", mType.name());
        map.put("submitter", mSubmitter);

        return map;
    }

    public String getObjectName() {
        return "Post";
    }

    public class Filter {

    }
}
