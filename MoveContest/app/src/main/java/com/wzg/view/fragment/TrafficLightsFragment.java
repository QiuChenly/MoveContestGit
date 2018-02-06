package com.wzg.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.wzg.R;
import com.wzg.model.RedGreen;

import java.util.ArrayList;
import java.util.List;

/**
 * 红绿灯管理
 */

public class TrafficLightsFragment extends Fragment {

    private Spinner mSpinner;
    private TableLayout mTable;
    private List<RedGreen> mRedGreen;
    private CheckBox mCheckBox;
    private Button mButton;
    private View mInflate;
    private AlertDialog.Builder mBuilder;
    private Button mDetermineBtn;
    private Button mRetBtn;
    private AlertDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRedGreen = new ArrayList<>();

        initData(mRedGreen);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_trafficlights, container, false);

        initSpinner(view);

        createTable(view);


        return view;
    }

    /**
     * 初始化数据
     *
     * @param redGreen
     */
    private void initData(List<RedGreen> redGreen) {


        RedGreen redGreen1 = new RedGreen(1, 8, 9, 9);
        redGreen.add(redGreen1);

        RedGreen redGreen2 = new RedGreen(2, 8, 8, 8);
        redGreen.add(redGreen2);

        RedGreen redGreen3 = new RedGreen(3, 7, 8, 7);
        redGreen.add(redGreen3);

        RedGreen redGreen4 = new RedGreen(4, 9, 9, 9);
        redGreen.add(redGreen4);

        RedGreen redGreen5 = new RedGreen(5, 8, 8, 8);
        redGreen.add(redGreen5);
    }

    /**
     * 创建表格
     *
     * @param view
     */
    private void createTable(View view) {

        mTable = view.findViewById(R.id.table);
        // 全部列自动填充空白
        mTable.setStretchAllColumns(true);

        for (int row = 0; row < mRedGreen.size(); row++) {
            TableRow tableRow = new TableRow(view.getContext());
            tableRow.setBackgroundColor(Color.rgb(222, 220, 210));

            TextView textView1 = new TextView(view.getContext());
            TextView textView2 = new TextView(view.getContext());
            TextView textView3 = new TextView(view.getContext());
            TextView textView4 = new TextView(view.getContext());
            textView1.setBackgroundResource(R.drawable.shapee);
            textView1.setText(String.valueOf(mRedGreen.get(row).getIntersection()));
            textView1.setGravity(Gravity.CENTER);
            textView2.setText(String.valueOf(mRedGreen.get(row).getRed()));
            textView2.setGravity(Gravity.CENTER);
            textView2.setBackgroundResource(R.drawable.shapee);

            textView3.setText(String.valueOf(mRedGreen.get(row).getYellow()));
            textView3.setGravity(Gravity.CENTER);
            textView3.setBackgroundResource(R.drawable.shapee);

            textView4.setText(String.valueOf(mRedGreen.get(row).getGreen()));
            textView4.setGravity(Gravity.CENTER);
            textView4.setBackgroundResource(R.drawable.shapee);


            tableRow.addView(textView1);
            tableRow.addView(textView2);
            tableRow.addView(textView3);
            tableRow.addView(textView4);


            mCheckBox = new CheckBox(view.getContext());
            tableRow.addView(mCheckBox);

            mButton = new Button(view.getContext());
            mButton.setText("设置");

            tableRow.addView(mButton);


            // 新建的tableRow添加到TableLayout
            mTable.addView(tableRow, new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));


            mButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Toast.makeText(view.getContext(), "调用了...", Toast.LENGTH_SHORT).show();

                    mBuilder = new AlertDialog.Builder(view.getContext());

                    mInflate = LayoutInflater.from(view.getContext()).inflate(R.layout.redgreen_dislog, null);

                    mBuilder.setView(mInflate);
                    mDialog = mBuilder.show();

                    // 确定按钮
                    mDetermineBtn = mInflate.findViewById(R.id.determine_btn);

                    mDetermineBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mDialog.dismiss();
                        }
                    });

                    // 返回按钮
                    mRetBtn = mInflate.findViewById(R.id.ret_btn);

                    mRetBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mDialog.dismiss();

                        }
                    });

                }
            });

        }


    }

    /**
     * 初始化Spinner控件
     *
     * @param view
     */
    private void initSpinner(View view) {

        mSpinner = view.findViewById(R.id.spinner);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] languages = getResources().getStringArray(R.array.languages);
                Toast.makeText(view.getContext(), "你点击的是:" + languages[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


}
