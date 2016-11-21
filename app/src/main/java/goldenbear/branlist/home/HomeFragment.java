package goldenbear.branlist.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;

import goldenbear.branlist.R;
import goldenbear.branlist.data.post.PostType;
import goldenbear.branlist.home.recycler.RecyclerViewFragment;
import goldenbear.branlist.post.PostActivity;

public class HomeFragment extends Fragment implements HomeContract.View {

    private HomeContract.Controller mController;
    private MaterialViewPager mViewPager;

    private RecyclerViewFragment[] recyclerViewFragments;
    private int currentPage;

    public HomeFragment() {
        // Required empty public constructor

        recyclerViewFragments = new RecyclerViewFragment[PostType.values().length + 1];
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
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setHomeButtonEnabled(true);
        }

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (recyclerViewFragments[position] == null) {
                    recyclerViewFragments[position] = RecyclerViewFragment.newInstance(position);
                }
                return recyclerViewFragments[position];
            }

            @Override
            public int getCount() {
                return PostType.values().length + 1;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                if (position == 0) {
                    return "All";
                } else {
                    return PostType.values()[position - 1].toString();
                }
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(R.color.blue, "");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(R.color.green, "");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(R.color.cyan, "");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(R.color.red, "");
                }
                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getViewPager().addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                refreshPost();
            }
        });

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
        recyclerViewFragments[currentPage].refreshPost();
    }
}
