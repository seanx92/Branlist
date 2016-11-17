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

import goldenbear.branlist.R;
import goldenbear.branlist.TaskTrackerAdapter;
import goldenbear.branlist.post.PostActivity;

public class HomeFragment extends Fragment implements HomeContract.View {

    private HomeContract.Controller mController;

    private ListView taskList;
    private TaskTrackerAdapter taskTrackerAdapter;

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

        // Init task list
        taskList = (ListView) root.findViewById(R.id.taskList);
        taskTrackerAdapter = new TaskTrackerAdapter(this.getActivity(), true);
        taskList.setAdapter(taskTrackerAdapter);

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
}
