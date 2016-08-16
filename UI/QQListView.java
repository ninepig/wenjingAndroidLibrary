package uml.yangwenjing.wenjingandroidlibrary.UI;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import uml.yangwenjing.wenjingandroidlibrary.R;

/**
 * Created by yamengwenjing on 2016/7/30.
 *
 * 向左移动 可以 删除当前list的自定义view
 *
 */
public class QQListView extends ListView{
    // tag
    private static final String TAG ="wenjingListView";

    // 用户滑动的最小距离
    private int touchSlop;

    private boolean isSliding;

    private int xDown;

    private int yDown;

    private int xMove;

    private int yMove;

    private LayoutInflater mInflater;

    private PopupWindow mPopupWindows;

    private int mPopupWindowsHeight;

    private int mPopupWindowsWeight;

    private Button mDelButton;

    private DelButonClickListener mListener;

    private View mCurrentView;

    private int mCurrentViewPos;

    public QQListView(Context context){
        super(context);
    }


    public QQListView(Context context, AttributeSet attrs, int defStyle){
//        super(context,attrs,defStyle);
        this(context,attrs);
    }

    public QQListView(Context context,AttributeSet attrs) {
        super(context, attrs);
        // 获取layoutInflater
        mInflater = LayoutInflater.from(context);
        //获取默认的触发滑动时间
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();

        View view = mInflater.inflate(R.layout.delete_btn,null);
        mDelButton  = (Button) view.findViewById(R.id.id_item_btn);
        mPopupWindows = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        /*
        要想得到popWindows的 宽高，需要先调用下measure，（具体为什么需要看下自定义view）
         */
        mPopupWindows.getContentView().measure(0,0);
        mPopupWindowsHeight = mPopupWindows.getContentView().getMeasuredHeight();
        mPopupWindowsWeight = mPopupWindows.getContentView().getMeasuredWidth();
    }

    /*
    view的touch分发事件，用switch的形式，手指按下的时候，如果有windows在显示，则让popwindows消失，同时return false 表示没有消耗这个动作，
    但结束了这次判断,即这次动作结束了
    如果没有windows在显示，则记录当前view（手指按下的位置）
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev){

        int action = ev.getAction();
        int x = (int)ev.getX();
        int y = (int)ev.getY();

        switch (action){

            case MotionEvent.ACTION_DOWN:
                xDown = x;
                yDown = y;

                if(mPopupWindows.isShowing()){
                    dismissPopWindows();
                    return false;
                }
                //这个函数是对于list之中 将指向的点，map to list的postion的函数 很流弊
                mCurrentViewPos = pointToPosition(xDown,yDown);
                View view =getChildAt(mCurrentViewPos - getFirstVisiblePosition());
                mCurrentView = view;
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                yMove = y;
                int dx = xMove -xDown;
                int dy = yMove -yDown;

                if(xMove<xDown && Math.abs(dx)>touchSlop && Math.abs(dy)<touchSlop){
                    isSliding = true;
                }
            break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public  boolean onTouchEvent(MotionEvent ev){

        int action =ev.getAction();
        /*
        如果从右到左的滑动才响应
         */
        if(isSliding){

            switch (action){
                case MotionEvent.ACTION_MOVE:
                    int[] location = new int[2];
                    mCurrentView.getLocationOnScreen(location);
                    mPopupWindows.setAnimationStyle(R.style.popwindow_delete_btn_anim_style);
                    mPopupWindows.update();

                    mPopupWindows.showAtLocation(mCurrentView, Gravity.LEFT | Gravity.TOP,
                            location[0] + mCurrentView.getWidth(),
                            location[1] + mCurrentView.getHeight() / 2 - mPopupWindowsHeight / 2);
                    mDelButton.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(mListener !=null){
                                mListener.clickDeleteButton(mCurrentViewPos);
                                mPopupWindows.dismiss();
                            }
                        }
                    });
                    break;
                case MotionEvent.ACTION_UP:
                    //手指抬起了，滑动结束
                    isSliding = false;
            }
                //这个很关键，true表示 只要滑动的时候就会消耗掉这个事件，
                // 避免发生和ItemClick的冲突，即如果你在滑动的时候又点击了 会触发itemClick，为了效果，必须把这个事件消耗掉
            return true;
        }



        return super.onTouchEvent(ev);
    }


    private void dismissPopWindows() {
        if(mPopupWindows != null &&mPopupWindows.isShowing()){
            mPopupWindows.dismiss();
        }
    }

    public void setDelButtonClickListener(DelButonClickListener listener){
        mListener = listener;
    }

    interface  DelButonClickListener{
        public void clickDeleteButton(int position);
    }
}
