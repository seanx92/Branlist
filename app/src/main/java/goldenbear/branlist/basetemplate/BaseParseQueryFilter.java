package goldenbear.branlist.basetemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by metaphoenix on 11/19/16.
 */
public abstract class BaseParseQueryFilter {
    private Map<String, Object> whereEqualToMap;
    private Map<String, Collection<Object>> whereContainedInMap;
    private Map<String, String> whereContainsMap;
    private String orderByDesendingKey;

    public void addWhereEqualToConstraint(String key, Object value) {
        if (whereEqualToMap == null) {
            whereEqualToMap = new HashMap<>();
        }
        whereEqualToMap.put(key, value);
    }

    public void addWhereContainedInConstraint(String key, Collection<Object> value) {
        if (whereContainedInMap == null) {
            whereContainedInMap = new HashMap<>();
        }
        whereContainedInMap.put(key, value);
    }

    public void addWhereContainsConstraint(String key, String value) {
        if (whereContainsMap == null) {
            whereContainsMap = new HashMap<>();
        }
        whereContainsMap.put(key, value);
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

    public Map<String, String> getWhereContainsMap() {
        return whereContainsMap;
    }

    public Map<String, Collection<Object>> getWhereContainedInMap() {
        return whereContainedInMap;
    }

    public abstract String getObjectName();
}
