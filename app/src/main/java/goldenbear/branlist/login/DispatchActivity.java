package goldenbear.branlist.login;

import com.parse.ui.ParseLoginDispatchActivity;

import goldenbear.branlist.home.HomeActivity;

public class DispatchActivity extends ParseLoginDispatchActivity {

    @Override
    protected Class<?> getTargetClass() {
        return HomeActivity.class;
    }
}
