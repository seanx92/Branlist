package goldenbear.branlist.userprofile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import goldenbear.branlist.R;
import goldenbear.branlist.data.user.User;
import goldenbear.branlist.databinding.UserProfileFragBinding;

public class UserProfileFragment extends Fragment implements UserProfileContract.View {

    User user;
    private UserProfileContract.Controller mController;
    private EditText mName, mEmail, mPhoneNumber;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    public static UserProfileFragment newInstance(Bundle bundle) {
        UserProfileFragment fragment = new UserProfileFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setController(@NonNull UserProfileContract.Controller controller) {
        mController = controller;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new User(getArguments().getString("userName"));
        mController.setUser(user);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UserProfileFragBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.user_profile_frag, container, false);
        View root = binding.getRoot();
        binding.setUser(user);

        // Inflate the layout for this fragment
        //View root = inflater.inflate(R.layout.user_profile_frag, container, false);
        mName = (EditText) root.findViewById(R.id.user_profile_name);
        mEmail = (EditText) root.findViewById(R.id.user_profile_email);
        mPhoneNumber = (EditText) root.findViewById(R.id.user_profile_phone_number);

        mController.loadData();
        return root;
    }

    public void setEditable() {
        mName.setFocusableInTouchMode(true);
        mEmail.setFocusableInTouchMode(true);
        mPhoneNumber.setFocusableInTouchMode(true);
    }

    public void setUneditable() {
        mName.setFocusable(false);
        mEmail.setFocusable(false);
        mPhoneNumber.setFocusable(false);
    }
}
