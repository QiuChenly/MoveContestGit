package com.wzg.model.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzg.R;
import com.wzg.model.CarNumber;
import com.wzg.model.Sites;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by WangZhiGang on 2018/2/4.
 */

public class BusInquiryAdapter extends BaseExpandableListAdapter {

    private List<Sites> mSitesList;
    private Context mContext;

    public BusInquiryAdapter(List<Sites> sitesList, Context context) {
        mSitesList = sitesList;
        mContext = context;
    }

    @Override
    public int getGroupCount() {
        return mSitesList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return mSitesList.get(i).getCarNumberList().size();
    }

    @Override
    public Object getGroup(int i) {
        return mSitesList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return mSitesList.get(i).getCarNumberList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        SiteHolder siteHolder;
        if (view == null) {
            siteHolder = new SiteHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_site, null);

            siteHolder.roundsImg = view.findViewById(R.id.rounds_img);
            siteHolder.groupingName = view.findViewById(R.id.grouping);
            view.setTag(siteHolder);

        } else {
            siteHolder = (SiteHolder) view.getTag();
        }

        siteHolder.groupingName.setText(mSitesList.get(i).getSiteName());

        if (b) {
            siteHolder.roundsImg.setImageResource(R.drawable.rounds_open);

        } else {
            siteHolder.roundsImg.setImageResource(R.drawable.rounds_close);

        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        CarNumberHolder carNumberHolder;
        if (view == null) {
            carNumberHolder = new CarNumberHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_number, null);
            carNumberHolder.carId = view.findViewById(R.id.carId);
            carNumberHolder.time = view.findViewById(R.id.time_txt);
            carNumberHolder.distance = view.findViewById(R.id.distance_txt);
            view.setTag(carNumberHolder);

        } else {
            carNumberHolder = (CarNumberHolder) view.getTag();
        }
        CarNumber carNumber = mSitesList.get(i).getCarNumberList().get(i1);

        carNumberHolder.carId.setText(carNumber.getCarId());
        carNumberHolder.time.setText(carNumber.getTime());
        carNumberHolder.distance.setText(carNumber.getDistance());


        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;

    }


    class SiteHolder {
        ImageView roundsImg;
        TextView groupingName;
    }


    class CarNumberHolder {

        TextView carId;
        TextView time;
        TextView distance;
    }

}
