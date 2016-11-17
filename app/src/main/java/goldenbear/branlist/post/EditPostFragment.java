package goldenbear.branlist.post;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import goldenbear.branlist.R;

/**
 * Created by metaphoenix on 11/17/16.
 */
public class EditPostFragment extends Fragment implements PostContract.View {

    private PostContract.Controller mController;

    private TextView mTitle;

    private TextView mDescription;

    public EditPostFragment() {
        // Required empty public constructor
    }

    public static EditPostFragment newInstance() {
        return new EditPostFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mController.start();
    }

    @Override
    public void setController(@NonNull PostContract.Controller controller) {
        mController = controller;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.edit_post_frag, container, false);
        mTitle = (TextView) root.findViewById(R.id.edit_post_title);
        mDescription = (TextView) root.findViewById(R.id.edit_post_description);

        return root;
    }
}
