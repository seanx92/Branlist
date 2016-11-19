package goldenbear.branlist.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import goldenbear.branlist.R;
import goldenbear.branlist.data.Post;
import goldenbear.branlist.post.PostActivity;
import goldenbear.branlist.post.recycler.RecyclerViewFragment;

public class HomeFragment extends Fragment implements HomeContract.View {

    ParseQueryAdapter<ParseObject> postAdapter;
    private HomeContract.Controller mController;
    private MaterialViewPager mViewPager;

    private RecyclerViewFragment recyclerViewFragment;

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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.home_frag, container, false);

        mViewPager = (MaterialViewPager) root.findViewById(R.id.view_pager);
        Toolbar toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                recyclerViewFragment = RecyclerViewFragment.newInstance();
                return recyclerViewFragment;
            }

            @Override
            public int getCount() {
                return Post.Type.values().length + 1;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return "All";
                } else {
                    return Post.Type.values()[position - 1].toString();
                }
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

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

    public void refreshPost() {
        recyclerViewFragment.refreshPost();
    }
}
