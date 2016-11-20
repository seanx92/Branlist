package goldenbear.branlist.home.recycler;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import goldenbear.branlist.R;
import goldenbear.branlist.data.post.PostFilter;
import goldenbear.branlist.data.post.PostType;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    static final boolean GRID_LAYOUT = false;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    public static RecyclerViewFragment newInstance(int typeNum) {
        RecyclerViewFragment f = new RecyclerViewFragment();

        Bundle args = new Bundle();
        args.putInt("postType", typeNum);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        int typePosition = getArguments().getInt("postType");
        if (typePosition > 0) {
            postFilter.setTypeFilter(PostType.getTypeFromPosition(typePosition));
        }
        postFilter.setOrderByDescending("createdAt");

        mAdapter = new RecyclerViewAdapter(this.getContext(), mRecyclerView, postFilter);

        mRecyclerView.setAdapter(mAdapter);
    }

    public void refreshPost() {
        ((RecyclerViewAdapter) mAdapter).loadObjects();
        mAdapter.notifyDataSetChanged();
    }
}
