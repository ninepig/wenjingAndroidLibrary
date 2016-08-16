package uml.yangwenjing.wenjingandroidlibrary.UI;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Scroller;

/**
 * Created by yamengwenjing on 2016/8/1.
 * 这个项目学习到了上滚下滚的一些常用的方法，可以算是一种类库，何时激活翻页等
 * 还有就是viewGroup自己写的时候 要如何 onMeasure onLayout等等
 */
public class VerticalLinearLayout extends ViewGroup{


    /**
     * 屏幕的高度
     */
    private int mScreenHeight;
    /**
     * 手指按下时的getScrollY
     */
    private int mScrollStart;
    /**
     * 手指抬起时的getScrollY
     */
    private int mScrollEnd;
    /**
     * 记录移动时的Y
     */
    private int mLastY;
    /**
     * 滚动的辅助类
     */
    private Scroller mScroller;
    /**
     * 是否正在滚动
     */
    private boolean isScrolling;
    /**
     * 加速度检测
     */
    private VelocityTracker mVelocityTracker;
    /**
     * 记录当前页
     */
    private int currentPage = 0;

    private OnPageChangeListener mOnPageChangeListener;

    public VerticalLinearLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        /*
        get windows size
         */
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        // screen height
        mScreenHeight = outMetrics.heightPixels;
        //init
        mScroller = new Scroller(context);

    }
    /*
    对于大部分自定义的viewGroup， 这是一个比较标准的形式
     */
    @Override
    protected  void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int count = getChildCount();
        for (int i = 0; i < count; ++i)
        {
            View childView = getChildAt(i);
            /*
            上面这个是默认的，对大部分通用适用，下面是针对这个project，
            个人理解，以屏幕高度为参数传入。应为是在onlayout的时候，因为我们要初始化4次，如果还是继续用初始化的高度（屏幕高度会变），会有问题
             */
//            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            measureChild(childView, widthMeasureSpec,mScreenHeight);

        }

    }


/*
      对于viewGoupr 来说 onlayout 要实现，因为要对child进行layout实现
 */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // changed 表示
        if(changed){
            int childCount = getChildCount();
            MarginLayoutParams lp = (MarginLayoutParams)getLayoutParams();
            // 设置主布局的高度
            lp.height = mScreenHeight*childCount;
            setLayoutParams(lp);

            for (int i = 0; i < childCount; i++)
            {
                View child = getChildAt(i);
                if (child.getVisibility() != View.GONE)
                {
                    child.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);// 调用每个自布局的layout
                }
            }
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 如果实在滚动的时候，则直接交给系统处理，即不处理这个touchEvent
        if(isScrolling)
            return  super.onTouchEvent(event);

        int action = event.getAction();
        int y = (int) event.getY();
        obtainVelocity(event);
        switch (action){
            case MotionEvent.ACTION_DOWN:
                mScrollStart = getScrollY();
                //按下去的拿下的Y轴的位置
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                int dy = mLastY - y;
                //边界检查
                int scrollY = getScrollY();
/*
    这两段代码应该可以一直使用，上拉下拉的公式
 */

                // 已经到达顶端，下拉多少，就往上滚动多少
                if (dy < 0 && scrollY + dy < 0)
                {
                    dy = -scrollY;
                }
                // 已经到达底部，上拉多少，就往下滚动多少
                if (dy > 0 && scrollY + dy > getHeight() - mScreenHeight)
                {
                    dy = getHeight() - mScreenHeight - scrollY;
                }
                scrollBy(0, dy);
                mLastY = y;
                break;

            case MotionEvent.ACTION_UP:
                mScrollEnd = getScrollY();
                int dScrollY =mScrollEnd - mScrollStart;
                if(wantScrollToNext()){
                    if(shouldScrollToNext()){
                        mScroller.startScroll(0, getScrollY(), 0, mScreenHeight - dScrollY);
                    }else{
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);

                    }
                }

                if (wantScrollToPre())// 往下滑动
                {
                    if (shouldScrollToPre())
                    {
                        mScroller.startScroll(0, getScrollY(), 0, -mScreenHeight - dScrollY);

                    } else
                    {
                        mScroller.startScroll(0, getScrollY(), 0, -dScrollY);
                    }
                }
                isScrolling = true;
                // 重新绘制 但是只走draw 不会走measure 和 layout
                postInvalidate();
                recycleVelocity();
                break;
        }


        return true;
    }

    /**
     * 根据滚动距离判断是否能够滚动到下一页
     *
     * @return
     */
    private boolean shouldScrollToNext()
    {
        return mScrollEnd - mScrollStart > mScreenHeight / 2 || Math.abs(getVelocity()) > 600;
    }

    /**
     * 根据用户滑动，判断用户的意图是否是滚动到下一页
     *
     * @return
     */
    private boolean wantScrollToNext()
    {
        return mScrollEnd > mScrollStart;
    }

    /**
     * 根据滚动距离判断是否能够滚动到上一页
     *
     * @return
     */
    private boolean shouldScrollToPre()
    {
        return -mScrollEnd + mScrollStart > mScreenHeight / 2 || Math.abs(getVelocity()) > 600;
    }

    /**
     * 根据用户滑动，判断用户的意图是否是滚动到上一页
     *
     * @return
     */
    private boolean wantScrollToPre()
    {
        return mScrollEnd < mScrollStart;
    }

    /*
    这个方法重写 来判断是否停止滚动要触发什么事件
     */
    @Override
    public void computeScroll()
    {
        super.computeScroll();
        if (mScroller.computeScrollOffset())
        {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        } else
        {

            int position = getScrollY() / mScreenHeight;

            Log.e("xxx", position + "," + currentPage);
            if (position != currentPage)
            {
                if (mOnPageChangeListener != null)
                {
                    currentPage = position;
                    mOnPageChangeListener.onPageChange(currentPage);
                }
            }

            isScrolling = false;
        }

    }

    /**
     * 获取y方向的加速度
     *
     * @return
     */
    private int getVelocity()
    {
        mVelocityTracker.computeCurrentVelocity(1000);
        return (int) mVelocityTracker.getYVelocity();
    }

    /**
     * 释放资源
     */
    private void recycleVelocity()
    {
        if (mVelocityTracker != null)
        {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    /**
     * 初始化加速度检测器
     *
     * @param event
     */
    private void obtainVelocity(MotionEvent event)
    {
        if (mVelocityTracker == null)
        {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    /**
     * 设置回调接口
     *
     * @param onPageChangeListener
     */
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener)
    {
        mOnPageChangeListener = onPageChangeListener;
    }

    /**
     * 回调接口
     *
     * @author wenjingyang
     *
     */
    public interface OnPageChangeListener
    {
        void onPageChange(int currentPage);
    }


}
