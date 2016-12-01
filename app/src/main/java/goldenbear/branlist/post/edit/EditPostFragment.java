package goldenbear.branlist.post.edit;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import goldenbear.branlist.R;
import goldenbear.branlist.data.post.Post;
import goldenbear.branlist.data.post.PostType;
import goldenbear.branlist.databinding.EditPostFragBinding;
import goldenbear.branlist.post.PostActivity;

/**
 * Created by metaphoenix on 11/17/16.
 */
public class EditPostFragment extends Fragment implements EditPostContract.View {

    private EditPostContract.Controller mController;

    private TextView mTitle, mDescription;

    private Spinner mType;

    private Post mPost;

    private int requestCode;

    public EditPostFragment() {
        // Required empty public constructor
    }

    public static EditPostFragment newInstance(Bundle bundle) {
        EditPostFragment fragment = new EditPostFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mController.start();
    }

    @Override
    public void setController(@NonNull EditPostContract.Controller controller) {
        mController = controller;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button mSaveButton = (Button) getActivity().findViewById(R.id.edit_post_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //PostType type = (PostType) mType.getSelectedItem();
                if (requestCode == PostActivity.REQUEST_ADD_POST) {
                    mController.savePost();
                } else if (requestCode == PostActivity.REQUEST_EDIT_POST) {
                    mController.updatePost();
                }
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        });

        Button mCancelButton = (Button) getActivity().findViewById(R.id.edit_post_cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestCode = getArguments().getInt("requestCode");
        mPost = new Post();
        mController.setPost(mPost);
        if (requestCode == PostActivity.REQUEST_ADD_POST) {
        } else if (requestCode == PostActivity.REQUEST_EDIT_POST) {
            mPost.setObjectId(getArguments().getString("postId"));
            mController.loadData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View root = inflater.inflate(R.layout.edit_post_frag, container, false);
        EditPostFragBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.edit_post_frag, container, false);
        View root = binding.getRoot();
        binding.setPost(mPost);

        mTitle = (TextView) root.findViewById(R.id.edit_post_title);
        mDescription = (TextView) root.findViewById(R.id.edit_post_description);

        mType = (Spinner) root.findViewById(R.id.edit_post_type);
        mType.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, PostType.values()));

        return root;
    }

}
