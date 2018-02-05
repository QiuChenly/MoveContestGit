package com.wzg.model.adapter;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzg.R;
import com.wzg.model.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZhiGang on 2018/2/2.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ViewHolder> {

    private static final String TAG = "AccountAdapter";
    private List<Account> mAccountList = new ArrayList<>();
    private Button mDetermine;
    private Button mCanel;
    private AlertDialog mDialog;
    private AlertDialog.Builder mBuilder;
    private EditText mMoneyEdit;
    private TextView mCarNumbers;
    private String carNumbers = "";
    private String mMoneyStr;

    public AccountAdapter(List<Account> accountList) {
        mAccountList = accountList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_accout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final Account account = mAccountList.get(position);
        holder.mCarId.setText(account.getCarId());
        holder.mCarImg.setImageResource(account.getCarImg());
        holder.mCarNumber.setText(account.getCarNumber());
        holder.mCarMaster.setText(account.getCarMaster());
        holder.mBalance.setText(account.getBalance());
        String oldBalance = account.getBalance().split(":")[1].split("元")[0];
        Integer oldInteger = Integer.valueOf(oldBalance);
        isMoneyNotice(holder, oldInteger);

        // 复选框
        holder.mBatch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                account.setCheckState(holder.mBatch.isChecked());
            }
        });


        // 充值按钮
        holder.mRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ejectDialog(view, holder);
            }
        });


    }


    @Override
    public int getItemCount() {
        return mAccountList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mCarId;
        private final ImageView mCarImg;
        private final TextView mCarNumber;
        private final TextView mCarMaster;
        private final TextView mBalance;
        private final CheckBox mBatch;
        private final Button mRecharge;
        private final LinearLayout mUserItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mCarId = itemView.findViewById(R.id.car_id);
            mCarImg = itemView.findViewById(R.id.car_img);
            mCarNumber = itemView.findViewById(R.id.car_number);
            mCarMaster = itemView.findViewById(R.id.car_master);
            mBalance = itemView.findViewById(R.id.balance);
            mBatch = itemView.findViewById(R.id.batch_state);
            mRecharge = itemView.findViewById(R.id.recharge_btn);
            mUserItem = itemView.findViewById(R.id.userItem);

        }
    }


    /**
     * 创建弹出框
     *
     * @param view
     */
    private void ejectDialog(View view, final ViewHolder holder) {


        mBuilder = new AlertDialog.Builder(view.getContext());

        final View inflate = LayoutInflater.from(view.getContext()).inflate(R.layout.recharge_dialog, null);

        mBuilder.setView(inflate);

        mDialog = mBuilder.show();


        mMoneyEdit = inflate.findViewById(R.id.moneyText);

        // 充值按钮
        mDetermine = inflate.findViewById(R.id.determine);

        mDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMoneyStr = mMoneyEdit.getText().toString().trim();

                for (int i = 0; i < mAccountList.size(); i++) {
                    Account account = mAccountList.get(i);
                    if (account.isCheckState() == true) {
                        String oldBalance = account.getBalance().split(":")[1].split("元")[0];
                        Integer oldInteger = Integer.valueOf(oldBalance);
                        Integer nowInteger = Integer.valueOf(mMoneyStr);
                        oldInteger += nowInteger;
                        account.setBalance("余额:" + oldInteger + "元");
                        notifyDataSetChanged();
                    }
                }
                carNumbers = "";
                mDialog.dismiss();
            }
        });


        // 返回按钮
        mCanel = inflate.findViewById(R.id.canel);
        mCanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carNumbers = "";

                mDialog.dismiss();
            }
        });


        mCarNumbers = inflate.findViewById(R.id.carNumberText);
        for (int i = 0; i < mAccountList.size(); i++) {
            Account account = mAccountList.get(i);
            if (account.isCheckState() == true) {
                carNumbers += account.getCarNumber();
                carNumbers += " ";
            }

        }
        // 设置车牌号
        mCarNumbers.setText(carNumbers);


    }


    /**
     * 判断余额是否小于5元
     *
     * @param holder
     */
    private void isMoneyNotice(ViewHolder holder, Integer moneyInteger) {

        // 余额小于5元的显示警告颜色
        if (moneyInteger <= 5) {
            holder.mUserItem.setBackgroundColor(Color.parseColor("#ffcc00"));
        } else {
            holder.mUserItem.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

}
