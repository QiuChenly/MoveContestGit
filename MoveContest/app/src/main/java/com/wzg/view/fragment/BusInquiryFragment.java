package com.wzg.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.wzg.R;
import com.wzg.model.Bus;
import com.wzg.model.CarNumber;
import com.wzg.model.Sites;
import com.wzg.model.adapter.BusInquiryAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * 公交查询
 *
 *
 */

public class BusInquiryFragment extends Fragment {


    private List<Bus> mBuses;
    private List<Sites> mSitesList;
    private ExpandableListView mExpandableListView;
    private BusInquiryAdapter mBusInquiryAdapter;
    private Button mDetails;
    private AlertDialog.Builder mBuilder;
    private AlertDialog mDialog;
    private Button mReBtn;
    private TableLayout mTableLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        initData();
        View view = inflater.inflate(R.layout.fragment_bus, container, false);

        mExpandableListView = view.findViewById(R.id.site_listview);

        mBusInquiryAdapter = new BusInquiryAdapter(mSitesList, view.getContext());
        mExpandableListView.setAdapter(mBusInquiryAdapter);
        mExpandableListView.setGroupIndicator(null);


        // 详情按钮
        mDetails = view.findViewById(R.id.btn_details);

        mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopup(view);
            }
        });

        return view;
    }


    /**
     *
     * 加载数据
     *
     */
    private void initData(){

        mSitesList = new ArrayList<>();
        {
            List<CarNumber> carNumbers = new ArrayList<>();
            CarNumber carNumber = new CarNumber("1号(101人)", "5分钟到达", "距离站台100米");
            CarNumber carNumber2 = new CarNumber("2号(101人)", "6分钟到达", "距离站台1000米");
            carNumbers.add(carNumber);
            carNumbers.add(carNumber2);
            Sites sites = new Sites("中医院站", carNumbers);
            mSitesList.add(sites);
        }
        {
            List<CarNumber> carNumbers = new ArrayList<>();
            CarNumber carNumber = new CarNumber("1号(101人)", "5分钟到达", "距离站台300米");
            CarNumber carNumber2 = new CarNumber("2号(101人)", "7分钟到达", "距离站台1200米");
            carNumbers.add(carNumber);
            carNumbers.add(carNumber2);
            Sites sites = new Sites("联想大厦站", carNumbers);
            mSitesList.add(sites);
        }



        mBuses = new ArrayList<>();

        for(int i = 0;i<=10;i++){
            Bus bus = new Bus(i+1, i+1, (i+1) * (i+1));
            mBuses.add(bus);

        }

    }


    /**
     * 创建弹出框
     *
     */
    private void createPopup(View view){



        mBuilder = new AlertDialog.Builder(view.getContext());

        View inflate = LayoutInflater.from(view.getContext()).inflate(R.layout.bus_dislog, null);

        mBuilder.setView(inflate);
        mDialog = mBuilder.show();

        // 返回按钮
        mReBtn = inflate.findViewById(R.id.re_btn);

        mReBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });


        mTableLayout = inflate.findViewById(R.id.table);

        // 全部列自动填充空白
        mTableLayout.setStretchAllColumns(true);

        // 生成3行，4列表单
        for(int row = 0; row<mBuses.size(); row++){
            TableRow tableRow = new TableRow(view.getContext());
            tableRow.setBackgroundColor(Color.rgb(222,220,210));
            for(int col = 0;col<3;col++){

                TextView textView = new TextView(view.getContext());
                textView.setBackgroundResource(R.drawable.shapee);
                if(col == 0){
                    textView.setText(String.valueOf(mBuses.get(row).getId()));

                }else if(col ==1){
                    textView.setText(String.valueOf(mBuses.get(row).getBusId()));

                }else {
                    textView.setText(String.valueOf(mBuses.get(row).getPeople()));

                }
                tableRow.addView(textView);

            }
            // 新建的tableRow添加到TableLayout
            mTableLayout.addView(tableRow,new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

        }

    }

}
