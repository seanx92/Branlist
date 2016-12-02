package goldenbear.branlist.home.recycler;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import goldenbear.branlist.R;
import goldenbear.branlist.data.post.PostFilter;
import goldenbear.branlist.data.post.PostType;
import goldenbear.branlist.home.HomeContract;
import goldenbear.branlist.utils.ParseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    static final boolean GRID_LAYOUT = false;
    private HomeContract.Controller mController;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    public static RecyclerViewFragment newInstance(Bundle bundle) {
        RecyclerViewFragment f = new RecyclerViewFragment();
        f.setArguments(bundle);

        return f;
    }

    public void setController(@NonNull HomeContract.Controller controller) {
        mController = controller;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recycler_view_frag, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager;

        if (GRID_LAYOUT) {
            layoutManager = new GridLayoutManager(getActivity(), 2);
        } else {
            layoutManager = new LinearLayoutManager(getActivity());
        }
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        PostFilter postFilter = new PostFilter();
        int pagePosition = getArguments().getInt("pagePosition");
        if (pagePosition > 0) {
            postFilter.setType(PostType.getTypeFromPosition(pagePosition));
        }
        final String submitter = getArguments().getString("submitter");
        if (submitter != null) {
            postFilter.setSubmitter(submitter);
        }
        final String query = getArguments().getString("query");
        if (query != null && query.length() > 0) {
            postFilter.setQuery(query);
        }
        postFilter.setOrderByDescending("createdAt");

        mAdapter = new RecyclerViewAdapter(this.getContext(), mRecyclerView, postFilter);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this.getContext(),
                        mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        RecyclerViewAdapter.ViewHolder holder =
                                (RecyclerViewAdapter.ViewHolder) view.getTag();
                        String id = holder.getId();
                        mController.viewPost(id);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        if (submitter == null ||
                                !submitter.equals(ParseHelper.getCurrentUser().getUsername())) {
                            return;
                        }
                        final RecyclerViewAdapter.ViewHolder holder =
                                (RecyclerViewAdapter.ViewHolder) view.getTag();
                        PopupMenu popupMenu = new PopupMenu(getContext(), view, Gravity.CENTER);
                        popupMenu.inflate(R.menu.edit_post_menu);
                        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.edit_post:
                                        mController.editPost(holder.getId());
                                        return true;
                                    case R.id.delete_post:
                                        mController.deletePost(holder.getId());
                                        return true;
                                    default:
                                        return false;
                                }
                            }
                        });
                        popupMenu.show();
                    }
        }));
    }

    public void refreshPost() {
        ((RecyclerViewAdapter) mAdapter).loadObjects();
        mAdapter.notifyDataSetChanged();
    }
}
