package goldenbear.branlist.basetemplate;

import com.parse.ParseObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by metaphoenix on 11/18/16.
 */

abstract public class BaseParseObject {
    protected Map<String, Object> keyValueMap = new HashMap<>();

    public Map<String, Object> getMap() {
        return keyValueMap;
    }

    public String getObjectId() {
        return (String) keyValueMap.get("objectId");
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
