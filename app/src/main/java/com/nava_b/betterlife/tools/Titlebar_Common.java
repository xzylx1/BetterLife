package com.nava_b.betterlife.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nava_b.betterlife.R;

/**
 * Created by xhit-nava on 2016/2/25.
 */
public class Titlebar_Common extends RelativeLayout
{
    private int left_btn_title;
    private int right_btn_title;
    private String titlebar_title;

    public Titlebar_Common(Context context)
    {
        super(context);
        init(null,0);
    }

    public Titlebar_Common(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(attrs, 0);
    }

    public Titlebar_Common(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public void init(AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Titlebar_Common, defStyleAttr, 0);
        left_btn_title = a.getResourceId(R.styleable.Titlebar_Common_left_btn_title, 0);
        right_btn_title = a.getResourceId(R.styleable.Titlebar_Common_right_btn_title, 0);
        titlebar_title = a.getString(R.styleable.Titlebar_Common_titlebar_title);
        a.recycle();

        View v = View.inflate(getContext(), R.layout.titlebar_common, this);

        if (left_btn_title != 0) {
            ImageView leftBtn = (ImageView) v.findViewById(R.id.left_btn_title);
            leftBtn.setImageResource(left_btn_title);
        }
        if (right_btn_title != 0) {
            ImageView rightBtn = (ImageView) v.findViewById(R.id.right_btn_title);
            rightBtn.setImageResource(right_btn_title);
        }
        if (titlebar_title != "") {
            TextView titleText = (TextView) v.findViewById(R.id.titlebar_title);
            titleText.setText(titlebar_title);
        }
    }
}
