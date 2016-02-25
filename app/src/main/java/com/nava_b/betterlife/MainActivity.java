package com.nava_b.betterlife;

import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.nava_b.betterlife.fragment.Found_fragment;
import com.nava_b.betterlife.fragment.Home_fragment;
import com.nava_b.betterlife.fragment.Mine_fragment;
import com.nava_b.betterlife.fragment.Tuan_fragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity
{
    @Bind(R.id.fragment_viewpager)
    ViewPager fragmentViewpager;
    @Bind(R.id.tab_home_radio_btn)
    RadioButton tabHomeRadioBtn;
    @Bind(R.id.tab_tuan_radio_btn)
    RadioButton tabTuanRadioBtn;
    @Bind(R.id.tab_found_radio_btn)
    RadioButton tabFoundRadioBtn;
    @Bind(R.id.tab_mine_radio_btn)
    RadioButton tabMineRadioBtn;
    @Bind(R.id.tab_radio_group)
    RadioGroup tabRadioGroup;
    private Fragment fragment;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private static final int TAB_HOME = 0;  //主页
    private static final int TAB_TUAN = 1; //闪团
    private static final int TAB_FOUND = 2; //发现
    private static final int TAB_MY = 3;//我的

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentList.add(Home_fragment.newInstance());
        fragmentList.add(Tuan_fragment.newInstance());
        fragmentList.add(Found_fragment.newInstance());
        fragmentList.add(Mine_fragment.newInstance());

        MyFragmentPagerViewAdatper adapter = new MyFragmentPagerViewAdatper(getSupportFragmentManager());
        fragmentViewpager.setAdapter(adapter);
        adapter.setDataFragmentList(fragmentList);



        tabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch (checkedId)
                {
                    case R.id.tab_home_radio_btn:
                        fragmentViewpager.setCurrentItem(0);
                        break;
                    case R.id.tab_tuan_radio_btn:
                        fragmentViewpager.setCurrentItem(1);
                        break;
                    case R.id.tab_found_radio_btn:
                        fragmentViewpager.setCurrentItem(2);
                        break;
                    case R.id.tab_mine_radio_btn:
                        fragmentViewpager.setCurrentItem(3);
                        break;
                }
            }
        });

        fragmentViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                switch (position)
                {
                    case TAB_HOME:
                        tabHomeRadioBtn.setChecked(true);
                        break;
                    case TAB_TUAN:
                        tabTuanRadioBtn.setChecked(true);
                        break;
                    case TAB_FOUND:
                        tabFoundRadioBtn.setChecked(true);
                        break;
                    case TAB_MY:
                        tabMineRadioBtn.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });

    }

    public class MyFragmentPagerViewAdatper extends FragmentPagerAdapter
    {
        ArrayList<Fragment> dataFragmentList = new ArrayList<Fragment>();

        public void setDataFragmentList(ArrayList<Fragment> fragmentList)
        {
            dataFragmentList = fragmentList;
            notifyDataSetChanged();
        }

        public MyFragmentPagerViewAdatper(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            return dataFragmentList.get(position);
        }

        @Override
        public int getCount()
        {
            return dataFragmentList.size();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
