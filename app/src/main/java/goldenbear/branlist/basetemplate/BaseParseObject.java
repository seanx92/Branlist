package goldenbear.branlist.basetemplate;

import android.databinding.ObservableArrayMap;

import com.parse.ParseObject;

import java.util.Date;
import java.util.Map;

/**
 * Created by metaphoenix on 11/18/16.
 */

abstract public class BaseParseObject {
    protected final String defaultValue = "";

    public ObservableArrayMap<String, Object> keyValueMap = new ObservableArrayMap<>();

    public Map<String, Object> getMap() {
        return keyValueMap;
    }

    public boolean hasObjectId() {
        return keyValueMap.containsKey("objectId");
    }

    public String getObjectId() {
        return (String) keyValueMap.get("objectId");
    }

    public void setObjectId(String objectId) {
        keyValueMap.put("objectId", objectId);
    }

    public Date getCreatedDate() {
        return (Date) keyValueMap.get("createdDate");
    }

    public Date getUpdatedDate() {
        return (Date) keyValueMap.get("updatedDate");
    }

    public void setTrivialAttributes(ParseObject parseObject) {
        keyValueMap.put("objectId", parseObject.getObjectId());
        keyValueMap.put("createdDate", parseObject.getCreatedAt());
        keyValueMap.put("updatedDate", parseObject.getUpdatedAt());
    }

    public abstract String getObjectName();

    public abstract void setAttributes(ParseObject parseObject);
}
