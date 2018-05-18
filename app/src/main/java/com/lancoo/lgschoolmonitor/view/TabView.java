package com.lancoo.lgschoolmonitor.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lancoo.lgschoolmonitor.R;


/**
 * @author Hinata-Liu
 * @date 2018/3/5 19:09.
 */

public class TabView extends LinearLayout {
    private int drawablePadding = 0;
    private int itemPaddingTop = 0;
    private int itemPadingBottom = 0;

    public void setDrawablePadding(int drawablePadding) {
        this.drawablePadding = drawablePadding;
    }

    public void setItemPaddingTop(int itemPaddingTop) {
        this.itemPaddingTop = itemPaddingTop;
    }

    public void setItemPadingBottom(int itemPadingBottom) {
        this.itemPadingBottom = itemPadingBottom;
    }

    public TabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabView(Context context) {
        super(context);
    }

    /**
     * 添加Item
     *
     * @param view
     */
    public void addItem(View view) {
        LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        addView(view, params);
        view.setOnClickListener(new ItemClickListener());
    }

    public TextView[] textViews;
    /**
     * @param resId
     * 图片或者selector对应的id
     * @param str
     * 文字信息
     * @param color
     * color文件夹下的selector 或 颜色值
     */
    int imgID = 0x12;

    public void addItem(int resId, int textColor, int background, int pos) {
        // 1.创建相对布局
        RelativeLayout layout = new RelativeLayout(getContext());

        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setPadding(0, itemPaddingTop, 0, itemPadingBottom);
        // 添加imageview

        ImageView ivIcon = new ImageView(getContext());
        ivIcon.setId(imgID);
        ivIcon.setImageResource(resId);
        if (background != -1) {
            layout.setBackgroundResource(background);
        }
        layout.setBackgroundResource(R.color.white);
        layout.addView(ivIcon);

        // 添加textview
        ColorStateList colorStateList = null;
        try {
            colorStateList = getResources().getColorStateList(textColor);
        } catch (Exception e) {
            colorStateList = null;
        }
        //
        /*
		 * RelativeLayout.LayoutParams layoutParams=new
		 * RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
		 * LayoutParams.WRAP_CONTENT);
		 * //layoutParams.addRule(RelativeLayout.ABOVE, imgID);
		 * layoutParams.addRule(RelativeLayout.RIGHT_OF, imgID);
		 * layoutParams.setMargins(0, 1, 20, 0);
		 */

        // 设置布局位置
        RelativeLayout.LayoutParams ivParams = (RelativeLayout.LayoutParams) ivIcon
                .getLayoutParams();
        ivParams.addRule(RelativeLayout.ALIGN_RIGHT);
        // 添加dot
        TextView textView = new TextView(getContext());
        textView.setId(imgID + pos + 1);
	/*	if (pos == 0)
			textView.setBackgroundResource((pos == 0 ? R.drawable.info_num
					: R.drawable.common_no_read));
		else*/
//        setRightImg(textView, R.drawable.common_no_read);

        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);

        layout.addView(textView);

        RelativeLayout.LayoutParams tvParams = (RelativeLayout.LayoutParams) textView
                .getLayoutParams();
        // tvParams.addRule(RelativeLayout.ABOVE, imgID);
        tvParams.addRule(RelativeLayout.ALIGN_RIGHT, imgID);
        //tvParams.setMargins(dip2px(getContext(), 10), 0, 0, dip2px(getContext(), 20));
        textView.setVisibility(View.INVISIBLE);
        textViews[pos] = textView;

        // 2.添加到Tab中
        addView(layout);
        LayoutParams params = (LayoutParams) layout.getLayoutParams();
        params.width = 0;
        params.height = LayoutParams.WRAP_CONTENT;
        params.weight = 1;
        // 3.添加监听
        layout.setOnClickListener(new ItemClickListener());
    }

    public void showTabMessageNum(int num) {

		/*
		 * View child = getChildAt(0); TextView
		 * textView=(TextView)child.findViewById(imgID+1);
		 * textView.setVisibility(View.VISIBLE); textView.setText(num+"");
		 */

        if (textViews[0].getVisibility() == View.VISIBLE && !"".equals(textViews[0].getText().toString())) {
            int number = Integer.parseInt(textViews[0].getText().toString());
            textViews[0].setText((num + number) + "");
        } else
            textViews[0].setText(num + "");
        textViews[0].setVisibility(View.VISIBLE);
    }

    public void showSubMessageNum() {


        int num = Integer.parseInt(textViews[0].getText().toString());
        if (num > 0) num--;
        if (num < 1) textViews[0].setVisibility(View.INVISIBLE);
        textViews[0].setText(num + "");
        //textViews[0].setVisibility(View.VISIBLE);
    }

    public void showTabDot(int pos) {
        if (pos < textViews.length)
            textViews[pos].setVisibility(View.VISIBLE);
    }

    public void hideTabDot(int pos) {
        if (pos < textViews.length)
            textViews[pos].setVisibility(View.INVISIBLE);
    }

//    public void setRightImg(TextView textView, int id) {
//        @SuppressWarnings("deprecation")
//        BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(
//                id);
//        int wh = (int) getResources().getDimension(R.dimen.dot_drawable_wh);
//        drawable.setBounds(0, 0, wh, wh);// 通过getMinimumWidth得到图片最小宽度，否则会被扩大
//        textView.setCompoundDrawables(null, null, drawable, null);
//        // textView.set
//    }

    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 添加多个Item
     *
     * @param ids   drawable 数组
     * @param strs  文字数组
     * @param color 文字颜色
     */
    public void addItems(int[] ids, int textColor) {
        textViews = new TextView[ids.length];
        for (int i = 0; i < ids.length; i++) {
            addItem(ids[i], textColor, -1, i);
        }
    }

    /**
     * 添加多个Item
     *
     * @param drawables xml资源中图片集合
     * @param strArrays xml资源中文本集合
     * @param color     文本颜色
     */
    public void addItems(int drawables, int textColor) {

        TypedArray ar = getResources().obtainTypedArray(drawables);
        int len = ar.length();
        int[] resIds = new int[len];
        for (int i = 0; i < len; i++)
            resIds[i] = ar.getResourceId(i, 0);
        ar.recycle();

        addItems(resIds, textColor);
    }

    @SuppressWarnings("ResourceType")
    public void addItems(int drawables, int textColor, int background) {
        TypedArray ar = getResources().obtainTypedArray(drawables);
        int len = ar.length();
        int[] resIds = new int[len];
        for (int i = 0; i < len; i++)
            resIds[i] = ar.getResourceId(i, 0);
        ar.recycle();
        for (int i = 0; i < resIds.length; i++) {
            addItem(resIds[i], textColor, background, i);
        }
    }

    // 设置当前选中
    public void setCurrent(int position) {
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (i == position) {
                child.setSelected(true);
            } else
                child.setSelected(false);
        }
    }

    ;

    private OnTabItemClickListener listener;// 事件监听对象

    /**
     * 设置切换监听
     *
     * @param listener
     */
    public void setOnTabItemClickListener(OnTabItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * Item监听回调接口
     *
     * @author Hinata-Liu
     * @version 创建时间：2016年8月10日 上午9:02:52
     */
    public interface OnTabItemClickListener {
        /**
         * 点击触发事件
         *
         * @param position 从左至右位置
         */
        public void onTabItemClick(int position);
    }

    /**
     * 点击监听内部类
     *
     * @author Hinata-Liu
     * @version 创建时间：2016年8月10日 上午9:02:52
     */
    private class ItemClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                if (v == child) {
                    child.setSelected(true);
                    listener.onTabItemClick(indexOfChild(v));
                } else
                    child.setSelected(false);
            }
        }
    }
}
