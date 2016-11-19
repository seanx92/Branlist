package goldenbear.branlist.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import goldenbear.branlist.R;
import goldenbear.branlist.post.PostActivity;

public class HomeFragment extends Fragment implements HomeContract.View {

    ParseQueryAdapter<ParseObject> postAdapter;
    private HomeContract.Controller mController;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void setController(@NonNull HomeContract.Controller controller) {
        mController = controller;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.home_frag, container, false);

        // Init post list
        postAdapter = new ParseQueryAdapter<ParseObject>(this.getActivity(), "Post") {
            @Override
            public View getItemView(ParseObject object, View v, ViewGroup parent) {
                if (v == null) {
                    v = View.inflate(getContext(), R.layout.post_row, null);
                }
                super.getItemView(object, v, parent);

                TextView titleView = (TextView) v.findViewById(R.id.title_entry);
                titleView.setText(object.getString("title"));

                TextView submitterView = (TextView) v.findViewById(R.id.submitter_entry);
                submitterView.setText(object.getString("submitter"));
                return v;
            }
        };
        ListView postList = (ListView) root.findViewById(R.id.post_list);
        postList.setAdapter(postAdapter);

        // Set up floating action button
        FloatingActionButton fab =
                (FloatingActionButton) getActivity().findViewById(R.id.fab_add_post);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.addNewPost();
            }
        });

        return root;
    }

    public void showAddPost() {
        Intent intent = new Intent(getContext(), PostActivity.class);
        startActivityForResult(intent, PostActivity.REQUEST_ADD_POST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mController.result(requestCode, resultCode);
    }

    @Override
    public void refreshPost() {
        postAdapter.loadObjects();
    }
}
