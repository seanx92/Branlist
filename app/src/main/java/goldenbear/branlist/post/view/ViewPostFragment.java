package goldenbear.branlist.post.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import goldenbear.branlist.R;

public class ViewPostFragment extends Fragment implements ViewPostContract.View {

    private ViewPostContract.Controller mController;

    private TextView mTitle, mType, mDescription, mSubmitter, mDate;

    public ViewPostFragment() {
        // Required empty public constructor
    }

    public static ViewPostFragment newInstance(Bundle bundle) {
        ViewPostFragment fragment = new ViewPostFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mController.start();
    }

    @Override
    public void setController(@NonNull ViewPostContract.Controller controller) {
        mController = controller;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.view_post_frag, container, false);
        mTitle = (TextView) root.findViewById(R.id.view_post_title);
        mType = (TextView) root.findViewById(R.id.view_post_type);
        mDescription = (TextView) root.findViewById(R.id.view_post_description);
        mSubmitter = (TextView) root.findViewById(R.id.view_post_submitter);
        mDate = (TextView) root.findViewById(R.id.view_post_created_date);

        mController.showData(getArguments().getString("postId"));
        return root;
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setType(String type) {
        mType.setText(type);
    }

    public void setDescription(String description) {
        mDescription.setText(description);
    }

    public void setSubmitter(String submitter) {
        mSubmitter.setText(submitter);
    }

    public void setDate(String date) {
        mDate.setText(date);
    }

}
