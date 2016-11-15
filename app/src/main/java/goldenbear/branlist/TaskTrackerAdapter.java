package goldenbear.branlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by metaphoenix on 11/14/16.
 */
public class TaskTrackerAdapter extends BaseAdapter {
    private ArrayList<TaskEntryData> entries;
    private Context context;
    private LayoutInflater layoutInflater;

    public TaskTrackerAdapter(Context context, boolean isInitiate) {
        super();
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        entries = new ArrayList<TaskEntryData>();
        if (isInitiate) {
            initiateEntries();
        }
    }

    public void addEntry(TaskEntryData entry) {
        entries.add(entry);
    }

    private void initiateEntries() {
        entries.add(new TaskEntryData("Task 1", "Description 1", "Mary"));
        entries.add(new TaskEntryData("Task 2", "Description 2", "John"));
        entries.add(new TaskEntryData("Task 3", "Description 3", "Emmy"));
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Object getItem(int index) {
        return entries.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int index, View view, ViewGroup parent) {
        view = layoutInflater.inflate(R.layout.task_entry, null);
        TaskEntryData entry = entries.get(index);
        TextView titleEntry = (TextView) view.findViewById(R.id.titleEntry);
        titleEntry.setText(entry.getTitle());
        return view;
    }
}
