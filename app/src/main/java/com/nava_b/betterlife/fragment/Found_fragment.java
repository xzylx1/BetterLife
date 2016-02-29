package com.nava_b.betterlife.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.nava_b.betterlife.BetterLifeApplication;
import com.nava_b.betterlife.R;
import com.nava_b.betterlife.core.ActionCallbackListener;
import com.nava_b.betterlife.modle.CirclesBean;
import com.nava_b.betterlife.modle.ConfigInfo;
import com.nava_b.betterlife.modle.ConfigResult;
import com.nava_b.betterlife.modle.MerchantBean;
import com.nava_b.betterlife.tools.MerchantAdapter;
import com.warmtel.expandtab.ExpandPopTabView;
import com.warmtel.expandtab.KeyValueBean;
import com.warmtel.expandtab.PopOneListView;
import com.warmtel.expandtab.PopTwoListView;

import java.util.ArrayList;
import java.util.List;


public class Found_fragment extends Fragment
{
    @Bind(R.id.near_expandpoptabview)
    ExpandPopTabView nearExpandpoptabview;
    @Bind(R.id.found_page_listview)
    ListView foundPageListview;
    private MerchantAdapter mMerchantAdapter;

    public static Fragment newInstance()
    {
        Fragment fragment = new Found_fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_found_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mMerchantAdapter = new MerchantAdapter(getActivity());
        foundPageListview.setAdapter(mMerchantAdapter);

        setExpandPopTabViewData();
        setListViewData();
    }

    public void setExpandPopTabViewData() {
        BetterLifeApplication.mAction.getNearbyConfig(new ActionCallbackListener<ConfigResult>()
        {
            @Override
            public void onStart(ConfigResult data)
            {
                setmExpandPopTabData(data);
            }

            @Override
            public void onSuccess(ConfigResult data)
            {
                setmExpandPopTabData(data);
            }

            @Override
            public void onFailure(String errorEvent, String message)
            {
                Toast.makeText(getActivity(), errorEvent, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setmExpandPopTabData(ConfigResult data){
        ConfigInfo info = data.getInfo();

        List<KeyValueBean> mParentLists = new ArrayList<>();//父区域
        List<ArrayList<KeyValueBean>> mChildrenListLists = new ArrayList<>();//子区域
        for (CirclesBean circlesBean : info.getAreaKey()) {
            KeyValueBean keyValueBean = new KeyValueBean();
            keyValueBean.setKey(circlesBean.getKey());
            keyValueBean.setValue(circlesBean.getValue());
            mParentLists.add(keyValueBean);
            mChildrenListLists.add((ArrayList<KeyValueBean>) circlesBean.getCircles());
        }
        nearExpandpoptabview.removeAllViews();
        addItem(nearExpandpoptabview, info.getDistanceKey(), "", "距离");
        addItem(nearExpandpoptabview, info.getIndustryKey(), "", "行业");
        addItem(nearExpandpoptabview, info.getSortKey(), "", "排序");
        addItem(nearExpandpoptabview, mParentLists, mChildrenListLists, "锦江区", "合江亭", "区域");
    }

    /**
     * 设置列表数据
     */
    public void setListViewData() {
        BetterLifeApplication.mAction.getNearbyAround(new ActionCallbackListener<ArrayList<MerchantBean>>()
        {
            @Override
            public void onStart(ArrayList<MerchantBean> data)
            {
                mMerchantAdapter.setListData(data);
            }

            @Override
            public void onSuccess(ArrayList<MerchantBean> data)
            {
                mMerchantAdapter.setListData(data);
            }

            @Override
            public void onFailure(String errorEvent, String message)
            {
                Toast.makeText(getActivity(), errorEvent, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void addItem(ExpandPopTabView expandTabView, List<KeyValueBean> lists, String defaultSelect, String defaultShowText) {
        PopOneListView popOneListView = new PopOneListView(getActivity());
        popOneListView.setDefaultSelectByValue(defaultSelect);
        popOneListView.setCallBackAndData(lists, expandTabView, new PopOneListView.OnSelectListener()
        {
            @Override
            public void getValue(String key, String value)
            {
                //弹出框选项点击选中回调方法
                Toast.makeText(getActivity(), value, Toast.LENGTH_SHORT).show();
            }
        });
        expandTabView.addItemToExpandTab(defaultShowText, popOneListView);
    }

    public void addItem(ExpandPopTabView expandTabView, List<KeyValueBean> parentLists,
                        List<ArrayList<KeyValueBean>> childrenListLists, String defaultParentSelect, String defaultChildSelect, String defaultShowText) {
        PopTwoListView popTwoListView = new PopTwoListView(getActivity());
        popTwoListView.setDefaultSelectByValue(defaultParentSelect, defaultChildSelect);
        popTwoListView.setCallBackAndData(expandTabView, parentLists, childrenListLists, new PopTwoListView.OnSelectListener() {
            @Override
            public void getValue(String showText, String parentKey, String childrenKey) {
                Toast.makeText(getActivity(), showText, Toast.LENGTH_SHORT).show();
            }
        });
        expandTabView.addItemToExpandTab(defaultShowText, popTwoListView);
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }


    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
