package goldenbear.branlist.utils;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Map;

import goldenbear.branlist.base.BaseParseObject;

/**
 * Created by metaphoenix on 11/16/16.
 */
public class ParseHelper {
    private static ParseHelper ourInstance = new ParseHelper();

    private ParseHelper() {

    }

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

    public static ParseHelper getInstance() {
        return ourInstance;
    }
}
