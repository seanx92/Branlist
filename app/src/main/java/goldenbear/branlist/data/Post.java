package goldenbear.branlist.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by metaphoenix on 11/17/16.
 */
public final class Post {

    @NonNull
    private final String mTitle;

    @Nullable
    private final String mDescription;

    public Post(@NonNull String title, @Nullable String description) {
        mTitle = title;
        mDescription = description;
    }

    @NonNull
    public String getTitle() {
        return mTitle;
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }
}
