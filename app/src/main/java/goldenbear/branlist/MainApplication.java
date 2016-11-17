package goldenbear.branlist;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by metaphoenix on 11/17/16.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Required - Initialize the Parse SDK
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getResources().getString(R.string.parse_app_id))
                .server(getResources().getString(R.string.parse_server))
                .build()
        );

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
    }
}
