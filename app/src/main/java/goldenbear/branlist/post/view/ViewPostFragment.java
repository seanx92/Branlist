package goldenbear.branlist.post.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.plumillonforge.android.chipview.Chip;
import com.plumillonforge.android.chipview.ChipView;
import com.plumillonforge.android.chipview.ChipViewAdapter;
import com.plumillonforge.android.chipview.OnChipClickListener;

import java.util.ArrayList;
import java.util.List;

import goldenbear.branlist.R;

public class ViewPostFragment extends Fragment implements ViewPostContract.View {

    private ViewPostContract.Controller mController;

    private TextView mTitle, mDescription, mSubmitter, mDate;

    private ChipView mType;

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
        mType = (ChipView) root.findViewById(R.id.view_post_type);
        mDescription = (TextView) root.findViewById(R.id.view_post_description);
        mSubmitter = (TextView) root.findViewById(R.id.view_post_submitter);
        mDate = (TextView) root.findViewById(R.id.view_post_created_date);

        mType.setOnChipClickListener(new OnChipClickListener() {
            @Override
            public void onChipClick(Chip chip) {
                List<Chip> chipList = new ArrayList<>();
                ChipViewAdapter adapter = new MainChipViewAdapter(getContext());
                adapter.setChipList(chipList);
                mType.setAdapter(adapter);
            }
        });
        mController.showData(getArguments().getString("postId"));
        return root;
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setType(String type) {
        List<Chip> chipList = new ArrayList<>();
        Tag t = null;
        switch (type) {
            case "Services":
                t = new Tag(type, 0);
                break;
            case "Sales":
                t = new Tag(type, 1);
                break;
            case "Jobs":
                t = new Tag(type, 2);
                break;
            case "Housing":
                t = new Tag(type, 3);
                break;
            case "Activities":
                t = new Tag(type, 4);
                break;
            case "Others":
                t = new Tag(type, 5);
                break;
            default:
                break;
        }
        chipList.add(t);
        //mType.setChipList(chipList);
        ChipViewAdapter adapter = new MainChipViewAdapter(getContext());
        adapter.setChipList(chipList);
        mType.setAdapter(adapter);
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
