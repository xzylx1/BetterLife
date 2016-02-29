package com.nava_b.betterlife.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.nava_b.betterlife.R;
import com.nava_b.betterlife.modle.MerchantBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by xhit-nava on 2016/2/29.
 */
public class MerchantAdapter extends BaseAdapter
{
    private ArrayList<MerchantBean> merchantList = new ArrayList<>();
    private static final int TYPE_ONE = 0;
    private static final int TYPE_TWO = 1;
    private LayoutInflater layoutInflater;
    private Context context;

    public MerchantAdapter(Context context)
    {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setListData(ArrayList<MerchantBean> list)
    {
        this.merchantList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return merchantList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return merchantList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

//    @Override
//    public int getViewTypeCount() {
//        return 2;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        NewsItem mItem = (NewsItem) getItem(position);
//        if(mItem.getImgextra()!=null){
//            return TYPE_TWO;
//        }else{
//            return TYPE_ONE;
//        }
//    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
//        if(getItemViewType(position) == TYPE_TWO){
//            return getTypeTwoView(position,convertView,parent);
//        }else{
//            return getTypeOneView(position,convertView,parent);
//        }
        ViewHolder holder;
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.item_merchat_layout, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MerchantBean merchant = (MerchantBean) getItem(position);
        //            AsyncMemoryFileCacheImageLoader.getInstance(context).loadBitmap(
        //                    getResources(), merchant.getPicUrl(), holder.iconImg);
        if(merchant!=null){
            Picasso.with(context).load(merchant.getPicUrl()).into(holder.merchantIconImg);
            holder.merchantNameTxt.setText(merchant.getName());
            holder.merchantCouponTxt.setText(merchant.getCoupon());
            holder.merchantLoactionTxt.setText(merchant.getLocation());
            holder.merchantDistanceTxt.setText(merchant.getDistance());
        }
        if (merchant.getCardType().equalsIgnoreCase("YES")) {
            holder.merchantCardImg.setVisibility(View.VISIBLE);
        }else {
            holder.merchantCardImg.setVisibility(View.GONE);
        }

        if (merchant.getGroupType().equalsIgnoreCase("YES")) {
            holder.merchantGroupImg.setVisibility(View.VISIBLE);
        }else {
            holder.merchantGroupImg.setVisibility(View.GONE);
        }

        if (merchant.getCouponType().equalsIgnoreCase("YES")) {
            holder.merchantCounpImg.setVisibility(View.VISIBLE);
        }else {
            holder.merchantCounpImg.setVisibility(View.GONE);
        }

        return convertView;
    }

    static class ViewHolder
    {
        @Bind(R.id.merchant_icon_img)// 图标
                ImageView merchantIconImg;
        @Bind(R.id.merchant_name_txt)// 标题
                TextView merchantNameTxt;
        @Bind(R.id.merchant_coupon_txt)// 打折信息
                TextView merchantCouponTxt;
        @Bind(R.id.merchant_loaction_txt) // 地址
                TextView merchantLoactionTxt;
        @Bind(R.id.merchant_distance_txt)// 距离
                TextView merchantDistanceTxt;
        @Bind(R.id.merchant_counp_img)// 卡
                ImageView merchantCounpImg;
        @Bind(R.id.merchant_card_img) // 团
                ImageView merchantCardImg;
        @Bind(R.id.merchant_group_img)// 券
                ImageView merchantGroupImg;

        ViewHolder(View view)
        {
            ButterKnife.bind(this, view);
        }
    }
}
