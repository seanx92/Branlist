package goldenbear.branlist.basetemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by metaphoenix on 11/19/16.
 */
public abstract class BaseParseQueryFilter {
    private Map<String, Object> whereEqualToMap;
    private String orderByDesendingKey;

    public void addWhereEqualToConstraint(String key, Object value) {
        if (whereEqualToMap == null) {
            whereEqualToMap = new HashMap<>();
        }
        whereEqualToMap.put(key, value);
    }

    public void addOrderByDescending(String key) {
        orderByDesendingKey = key;
    }

    public String getOrderByDesendingKey() {
        return orderByDesendingKey;
    }

    public Map<String, Object> getWhereEqualToMap() {
        return whereEqualToMap;
    }

    public abstract String getObjectName();
}
