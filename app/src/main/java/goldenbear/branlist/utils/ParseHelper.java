package goldenbear.branlist.utils;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.Map;

import goldenbear.branlist.basetemplate.BaseParseObject;
import goldenbear.branlist.basetemplate.BaseParseQueryFilter;

/**
 * Created by metaphoenix on 11/16/16.
 */
public class ParseHelper {

    public static ParseUser getCurrentUser() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        return currentUser;
    }

    public static void saveObject(BaseParseObject parseObject) {
        Map<String, Object> map = parseObject.getMap();
        ParseObject pObject = new ParseObject(parseObject.getObjectName());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            pObject.put(entry.getKey(), entry.getValue());
        }
        pObject.saveInBackground();
    }

    public static ParseQueryAdapter.QueryFactory<ParseObject> getQueryFactory
            (final BaseParseQueryFilter filter) {
        ParseQueryAdapter.QueryFactory<ParseObject> queryFactory =
                new ParseQueryAdapter.QueryFactory<ParseObject>() {
                    public ParseQuery<ParseObject> create() {
                        ParseQuery query = new ParseQuery(filter.getObjectName());
                        Map<String, Object> whereEqualsToMap = filter.getWhereEqualToMap();
                        if (whereEqualsToMap != null) {
                            for (Map.Entry<String, Object> entry : whereEqualsToMap.entrySet()) {
                                query.whereEqualTo(entry.getKey(), entry.getValue());
                            }
                        }
                        String orderByDescendingKey = filter.getOrderByDesendingKey();
                        if (orderByDescendingKey != null) {
                            query.addDescendingOrder(orderByDescendingKey);
                        }
                        return query;
                    }
                };
        return queryFactory;
    }

    public static void getObject(BaseParseObject object, String objectId) {
        ParseQuery parseQuery = new ParseQuery(object.getObjectName());
        try {
            ParseObject parseObject = parseQuery.get(objectId);
            object.setAttributes(parseObject);
        } catch (ParseException e) {
            Log.e("ParseException", e.toString());
        }
    }

    public static void logout() {
        ParseUser.logOut();
    }
}
