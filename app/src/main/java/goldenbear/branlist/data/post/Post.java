package goldenbear.branlist.data.post;

import com.parse.ParseObject;

import goldenbear.branlist.basetemplate.BaseParseObject;

/**
 * Created by metaphoenix on 11/17/16.
 */
public final class Post extends BaseParseObject {

    public Post(String id, String title, String description, PostType type, String submitter) {
        keyValueMap.put("title", title);
        keyValueMap.put("description", description);
        keyValueMap.put("type", (type == null) ? type : type.name());
        keyValueMap.put("submitter", submitter);
    }

    public Post() {
        this(null, null, null, null, null);
    }

    public static String getBriefDescription(String description, int maxChars) {
        description = description.trim();
        if (description.length() < maxChars) {
            return description;
        } else {
            return description.substring(0, maxChars - 1) + "...";
        }
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

    public String getObjectName() {
        return "Post";
    }

    public void setAttributes(ParseObject parseObject) {
        setTrivialAttributes(parseObject);
        keyValueMap.put("title", parseObject.getString("title"));
        keyValueMap.put("description", parseObject.getString("description"));
        keyValueMap.put("type", parseObject.getString("type"));
        keyValueMap.put("submitter", parseObject.getString("submitter"));
    }
}
