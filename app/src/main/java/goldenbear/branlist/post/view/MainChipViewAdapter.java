package goldenbear.branlist.post.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.plumillonforge.android.chipview.Chip;
import com.plumillonforge.android.chipview.ChipViewAdapter;

import java.util.List;

import goldenbear.branlist.R;

/**
 * Created by YaoXin on 2016/11/30.
 */

public class MainChipViewAdapter extends ChipViewAdapter {
    private Context context;
    private List<Chip> chips;

    public MainChipViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    public void addChip(Chip chip) {
        chips.add(chip);
    }

    @Override
    public int getLayoutRes(int position) {
        Tag tag = (Tag) getChip(position);

        switch (tag.getType()) {
            case 0:
                return R.layout.chip_services;
            case 1:
                return R.layout.chip_sales;
            case 2:
                return R.layout.chip_jobs;
            case 3:
                return R.layout.chip_housing;
            case 4:
                return R.layout.chip_activities;
            case 5:
                return R.layout.chip_others;
            default:
                return R.layout.chip_services;
        }
    }

    @Override
    public int getBackgroundColor(int position) {
        Tag tag = (Tag) getChip(position);
        return getColor(R.color.white);
    }

    @Override
    public int getBackgroundColorSelected(int position) {
        return 0;
    }

    @Override
    public int getBackgroundRes(int position) {
        return 0;
    }

    @Override
    public void onLayout(View view, int position) {
        Tag tag = (Tag) getChip(position);
        ((TextView) view.findViewById(R.id.Chip_Text)).setTextColor(getColor(R.color.white));
    }

}