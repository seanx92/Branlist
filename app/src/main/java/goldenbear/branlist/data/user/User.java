package goldenbear.branlist.data.user;

import com.parse.ParseObject;
import com.parse.ParseUser;

import goldenbear.branlist.basetemplate.BaseParseObject;

/**
 * Created by metaphoenix on 11/30/16.
 */

public class User extends BaseParseObject {
    public User(String username) {
        keyValueMap.put("username", username);
        keyValueMap.put("name", defaultValue);
        keyValueMap.put("email", defaultValue);
        keyValueMap.put("phoneNumber", defaultValue);
    }

    public String getUserName() {
        return keyValueMap.get("username").toString();
    }

    public String getObjectName() {
        return "User";
    }

    public void setAttributes(ParseObject parseUser) {
        keyValueMap.put("name", parseUser.getString("name"));
        keyValueMap.put("email", ((ParseUser) parseUser).getEmail());
        keyValueMap.put("phoneNumber", parseUser.getString("phoneNumber"));
    }
}
