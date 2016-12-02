package goldenbear.branlist.utils;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import goldenbear.branlist.basetemplate.BaseParseObject;
import goldenbear.branlist.basetemplate.BaseParseQueryFilter;
import goldenbear.branlist.data.user.User;

/**
 * Created by metaphoenix on 11/16/16.
 */
public class ParseHelper {

    public static ParseUser getCurrentUser() {
        ParseUser currentUser = ParseUser.getCurrentUser();
        return currentUser;
    }

    public static void getUser(final User user) {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", user.getUserName());
        query.getFirstInBackground(new GetCallback<ParseUser>() {
            public void done(ParseUser parseUser, ParseException e) {
                user.setAttributes(parseUser);
            }
        });
    }

    public static void saveUser(final User user) {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", user.getUserName());
        query.getFirstInBackground(new GetCallback<ParseUser>() {
            public void done(ParseUser parseUser, ParseException e) {
                Map<String, Object> map = user.getMap();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    parseUser.put(entry.getKey(), entry.getValue());
                }
                parseUser.saveInBackground();
            }
        });
    }

    public static void saveObject(BaseParseObject parseObject) {
        Map<String, Object> map = parseObject.getMap();
        ParseObject pObject = new ParseObject(parseObject.getObjectName());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            pObject.put(entry.getKey(), entry.getValue());
        }
        pObject.saveInBackground();
    }

    public static void updateObject(final BaseParseObject parseObject) {
        ParseQuery parseQuery = new ParseQuery(parseObject.getObjectName());
        parseQuery.getInBackground(parseObject.getObjectId(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    Map<String, Object> map = parseObject.getMap();
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        object.put(entry.getKey(), entry.getValue());
                    }
                    object.saveInBackground();
                } else {
                    Log.e("Parse Server Error", e.getLocalizedMessage());
                }
            }
        });
    }

    public static ParseQueryAdapter.QueryFactory<ParseObject> getQueryFactory
            (final BaseParseQueryFilter filter) {
        ParseQueryAdapter.QueryFactory<ParseObject> queryFactory =
                new ParseQueryAdapter.QueryFactory<ParseObject>() {
                    public ParseQuery<ParseObject> create() {
                        ParseQuery query = new ParseQuery(filter.getObjectName());
                        Map<String, String> whereContainsMap = filter.getWhereContainsMap();
                        if (whereContainsMap != null) {
                            List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
                            for (Map.Entry<String, String> entry : whereContainsMap.entrySet()) {
                                ParseQuery tempQuery = new ParseQuery(filter.getObjectName());
                                tempQuery.whereContains(entry.getKey(), entry.getValue());
                                Log.e(entry.getKey().toString(), entry.getValue().toString());
                                queries.add(tempQuery);
                            }
                            query = ParseQuery.or(queries);
                        }
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

    public static void loadObject(final BaseParseObject parseObject) {
        ParseQuery parseQuery = new ParseQuery(parseObject.getObjectName());
        parseQuery.getInBackground(parseObject.getObjectId(), new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    parseObject.setAttributes(object);
                } else {
                    Log.e("Parse Server Error", e.getLocalizedMessage());
                }
            }
        });
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

    public static void deleteObject(String objectName, String objectId) {
        ParseQuery parseQuery = new ParseQuery(objectName);
        try {
            ParseObject parseObject = parseQuery.get(objectId);
            parseObject.deleteInBackground();
        } catch (ParseException e) {
            Log.e("ParseException", e.toString());
        }
    }

    public static void logout() {
        ParseUser.logOut();
    }
}
