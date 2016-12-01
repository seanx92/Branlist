package goldenbear.branlist.data.post;

import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.v7.widget.AppCompatSpinner;

import com.parse.ParseObject;

import goldenbear.branlist.basetemplate.BaseParseObject;

/**
 * Created by metaphoenix on 11/17/16.
 */

@InverseBindingMethods({
        @InverseBindingMethod(type = AppCompatSpinner.class, attribute = "android:selectedItemPosition"),
})
public final class Post extends BaseParseObject {

    public final ObservableField<Integer> typeInt = new ObservableField<Integer>();

    public Post(String title, String description, PostType type, String submitter) {
        keyValueMap.put("title", title);
        keyValueMap.put("description", description);
        keyValueMap.put("type", type.name());
        keyValueMap.put("submitter", submitter);
        typeInt.set(type.ordinal());
        addTypeCallBack();
    }

    public Post() {
        keyValueMap.put("title", defaultValue);
        keyValueMap.put("description", defaultValue);
        keyValueMap.put("type", PostType.getTypeFromPosition(1));
        keyValueMap.put("submitter", defaultValue);
        typeInt.set(0);
        addTypeCallBack();
    }

    public static String getBriefDescription(String description, int maxChars) {
        description = description.trim();
        if (description.length() < maxChars) {
            return description;
        } else {
            return description.substring(0, maxChars - 1) + "...";
        }
    }

    private void addTypeCallBack() {
        typeInt.addOnPropertyChangedCallback(
                new ObservableInt.OnPropertyChangedCallback() {
                    @Override
                    public void onPropertyChanged(Observable sender, int propertyId) {
                        int position = (int) (((ObservableField) sender).get());
                        keyValueMap.put("type", PostType.getTypeFromPosition(position + 1).name());
                    }
                }
        );
    }

    public String getTitle() {
        return (String) keyValueMap.get("title");
    }

    public String getDescription() {
        return (String) keyValueMap.get("description");
    }

    public String getBriefDescription(int maxChars) {
        return getBriefDescription(getDescription(), maxChars);
    }

    public PostType getType() {
        return PostType.valueOf((String) keyValueMap.get("type"));
    }

    public String getSubmitter() {
        return (String) keyValueMap.get("submitter");
    }

    public void setSubmitter(String submitter) {
        keyValueMap.put("submitter", submitter);
    }

    public String getObjectName() {
        return "Post";
    }

    public void setAttributes(ParseObject parseObject) {
        setTrivialAttributes(parseObject);
        keyValueMap.put("title", parseObject.getString("title"));
        keyValueMap.put("description", parseObject.getString("description"));
        keyValueMap.put("type", parseObject.getString("type"));
        keyValueMap.put("submitter", parseObject.getString("submitter"));
        typeInt.set(PostType.valueOf(parseObject.getString("type")).ordinal());
    }

}
