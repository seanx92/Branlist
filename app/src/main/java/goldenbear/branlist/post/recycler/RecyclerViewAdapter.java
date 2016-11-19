package goldenbear.branlist.post.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.util.List;

import goldenbear.branlist.R;

/**
 * Created by metaphoenix on 11/19/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ParseQueryAdapter<ParseObject> parseAdapter;

    private ViewGroup parseParent;

    private RecyclerViewAdapter recyclerViewAdapter = this;

    public RecyclerViewAdapter(Context context, ViewGroup parentIn) {
        parseParent = parentIn;

        parseAdapter = new ParseQueryAdapter<ParseObject>(context, "Post") {
            @Override
            public View getItemView(ParseObject object, View v, ViewGroup parent) {
                if (v == null) {
                    v = View.inflate(getContext(), R.layout.post_card, null);
                }
                super.getItemView(object, v, parent);

                TextView titleView = (TextView) v.findViewById(R.id.title_entry);
                titleView.setText(object.getString("title"));

                TextView submitterView = (TextView) v.findViewById(R.id.submitter_entry);
                submitterView.setText(object.getString("submitter"));
                return v;
            }
        };
        parseAdapter.addOnQueryLoadListener(new OnQueryLoadListener());
        parseAdapter.loadObjects();
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        parseAdapter.getView(position, holder.cardView, parseParent);
    }

    @Override
    public int getItemCount() {
        return parseAdapter.getCount();
    }

    public void loadObjects() {
        parseAdapter.loadObjects();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View cardView;

        public ViewHolder(View v) {
            super(v);
            cardView = v;
        }
    }

    public class OnQueryLoadListener implements ParseQueryAdapter.OnQueryLoadListener<ParseObject> {

        public void onLoading() {

        }

        public void onLoaded(List<ParseObject> objects, Exception e) {
            recyclerViewAdapter.notifyDataSetChanged();
        }
    }

}
