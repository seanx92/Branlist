package goldenbear.branlist.home.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import goldenbear.branlist.R;
import goldenbear.branlist.basetemplate.BaseParseQueryFilter;
import goldenbear.branlist.data.post.Post;
import goldenbear.branlist.utils.ParseHelper;

/**
 * Created by metaphoenix on 11/19/16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ParseQueryAdapter<ParseObject> parseAdapter;

    private ViewGroup parseParent;

    private RecyclerViewAdapter recyclerViewAdapter = this;

    public RecyclerViewAdapter(Context context, ViewGroup parentIn, BaseParseQueryFilter filter) {
        parseParent = parentIn;

        ParseQueryAdapter.QueryFactory<ParseObject> queryFactory =
                ParseHelper.getQueryFactory(filter);
        parseAdapter = new ParseQueryAdapter<ParseObject>(context, queryFactory) {
            @Override
            public View getItemView(ParseObject object, View v, ViewGroup parent) {
                if (v == null) {
                    v = View.inflate(getContext(), R.layout.post_card, null);
                }
                super.getItemView(object, v, parent);

                TextView titleView = (TextView) v.findViewById(R.id.title_entry);
                titleView.setText(object.getString("title"));

                TextView descriptionView = (TextView) v.findViewById(R.id.description_entry);
                String description = object.getString("description");
                descriptionView.setText(Post.getBriefDescription(description, 30));

                TextView dateView = (TextView) v.findViewById(R.id.date_entry);
                Date updatedAt = object.getUpdatedAt();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateView.setText(dateFormat.format(updatedAt));
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
        holder.setId(parseAdapter.getItem(position).getObjectId());
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
        private String mId;

        public ViewHolder(View v) {
            super(v);
            v.setTag(this);
            cardView = v;
        }

        public String getId() {
            return mId;
        }

        public void setId(String id) {
            mId = id;
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
