package com.bwie.headerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 自定义控件
 */
public class MainActivity extends LinearLayout {


    private View headerView;
    private ImageView header_arrow;
    private ProgressBar header_progressbar;
    private TextView header_hint_textview;
    private TextView header_time;
    private RotateAnimation animation_up;
    private RotateAnimation animation_down;
    /**
     * 初始状态 ---- 正常状态
     */
    public static final int STATE_NORMAL = 1;

    /**
     * 准备刷新状态 ---- 松开刷新数据
     */
    public static final int STATE_READY = 2;

    /**
     * 正在刷新
     */
    public static final int STATE_REFRESHING = 3;
    /**
     * 状态值
     */
    private int mState = STATE_NORMAL;

    // xml需要
    public MainActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    // xml需要
    public MainActivity(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // new 属性
    public MainActivity(Context context) {
        this(context, null);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        headerView = View.inflate(getContext(), R.layout.xlistview_header, null);

        //设置初始的布局参数
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
        this.addView(headerView, params);

        /**
         * 初始化控件
         */
        //小箭头图片
        header_arrow = (ImageView) headerView.findViewById(R.id.xlistview_header_arrow);

        //进度条
        header_progressbar = (ProgressBar) headerView.findViewById(R.id.xlistview_header_progressbar);

        //提示语textview
        header_hint_textview = (TextView) headerView.findViewById(R.id.xlistview_header_hint_textview);

        //时间
        header_time = (TextView) headerView.findViewById(R.id.xlistview_header_time);

        /** 小箭头图片下拉时向上逆时针旋转180度
         *  Animation.RELATIVE_TO_SELF --------- 相对于自己旋转
         */
        //初始化向上旋转的动画
        animation_up = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //动画旋转时间
        animation_up.setDuration(100);
        //动画结束是否显示
        animation_up.setFillAfter(true);

        //初始化向下旋转的动画
        animation_down = new RotateAnimation(-180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //动画旋转时间
        animation_down.setDuration(100);
        //动画结束是否显示
        animation_down.setFillAfter(true);

    }

    public void setState(int state) {
        if (state == mState) {
            return;
        }
        if (state == STATE_REFRESHING) {
            //显示进度条
            header_progressbar.setVisibility(View.VISIBLE);
            //小箭头隐藏
            header_arrow.setVisibility(View.INVISIBLE);
            //清除动画
            header_arrow.clearAnimation();
        }
    }

}


