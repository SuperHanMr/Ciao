package com.example.hanyonghui.ciao.view.views;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.hanyonghui.ciao.R;

/**
 * Created by xhs on 2016/10/23.
 *  侧滑删除
 */

public class
SwipeLayout extends FrameLayout {

    private ViewDragHelper viewDragHelper;
    private ViewDragHelper.Callback callback;
    private View mFrontView;
    private View mBackView;
    private int mFrontViewWidth;
    private int mBackViewWidth;
    private int mHeight;
    private float downX;

    public SwipeLayout(Context context) {
        this(context,null);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //3.重写回调的方法
        //返回值为true代表可以进行滑动，返回值为false代表不可以进行滑动
        //ViewDragHelper只对儿子起作用，对孙子不起作用
        //返回的值决定了要移动的位置，如果不重写这个方法则默认的返回值为0，两个孩子都不能进行移动操作
        //return super.clampViewPositionHorizontal(child, left, dx);
        callback = new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                //返回值为true代表可以进行滑动，返回值为false代表不可以进行滑动
                //ViewDragHelper只对儿子起作用，对孙子不起作用
                return true;
            }

            //返回的值决定了要移动的位置，如果不重写这个方法则默认的返回值为0，两个孩子都不能进行移动操作
            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
               // return super.clampViewPositionHorizontal(child, left, dx);
                if(child==mFrontView){
                    if(left<-(mBackViewWidth)){//设置卡片向左拖动的最大位置在哪里
                        left=-(mBackViewWidth);
                    }else if(left>0){//限定卡片向右的位置
                        left=0;
                    }
                }
                return left;
                //return 0;
            }

            //位置发生改变的时候，把水平方向的偏移量传递给另一个布局
            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                //强制刷新
                if(changedView==mFrontView){
                     //拖拽的是前布局，将刚刚发生的偏移量传递给后布局
                    mBackView.offsetLeftAndRight(dx);
                }else if(changedView==mBackView){
                    mFrontView.offsetLeftAndRight(dx);
                }
                invalidate();//这个事件的内部本身带有强制刷新，为兼容低版本进行一次强制刷新
            }

            //松手的时候会执行的方法
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                //xvel 向右是+，向左是-
                if(xvel==0&&mFrontView.getLeft()<-mBackViewWidth*0.5){//如果当前没有发生移动，并且已经滑动到的位置超过了删除按钮的一半，则打开按钮
                    openAnimation();
                }else if(xvel<0){//如果当前正在向左进行滑动
                    closeAnimation();
                }else{//剩下的情况都是向右进行滑动
                    closeAnimation();
                }
            }
        };

        //1.创建ViewDragHelper,
        viewDragHelper = ViewDragHelper.create(this, callback);

    }

    //关闭侧滑删除的动画
    private void closeAnimation() {
        int finalLeft=0;
        if(viewDragHelper.smoothSlideViewTo(mFrontView,finalLeft,0)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    //打开侧滑删除的动画
    private void openAnimation() {
        int finalLeft=-mBackViewWidth;
        if(viewDragHelper.smoothSlideViewTo(mFrontView,finalLeft,0)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    //2.转交触摸事件拦截判断，处理触摸事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //DragHelper会根据不同的拖拽状态判断当前是否进行拦截
//
//        if(ev.getX()>0&&ev.getX()<250||ev.getX()>850&&ev.getX()<1000||Math.abs(downX-ev.getX())<10&&Math.abs(getResources().getDimension()-mFrontView.getLeft())<10||
//                Math.abs(downX-ev.getX())<10&&Math.abs(getResources().getDimension()-mFrontView.getLeft())<10){
//            return false;
//        }else{
//            return true;
//        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }

        try{
            //DragHelper会自动根据按下的位置和移动的位置，计算出空间需要移动的距离
            if(viewDragHelper!=null){
                viewDragHelper.processTouchEvent(event);
            }else{
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;//当前View消费这个事件

    }

    //当矩形的位置发生变化的时候出发的事件
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mFrontViewWidth = mFrontView.getMeasuredWidth();
        mHeight = mBackView.getMeasuredHeight();
        mBackViewWidth=mBackView.getMeasuredWidth();
    }

    //当填充完布局之后再找他的孩子
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mFrontView = getChildAt(0);   //卡片
        mBackView = getChildAt(1);    //删除
        mBackViewWidth= mBackView.getMeasuredWidth();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mBackViewWidth = mBackView.getMeasuredWidth();
        mHeight = mBackView.getMeasuredHeight();
    }

    //维持平滑动画继续
    @Override
    public void computeScroll() {
        super.computeScroll();
        if(viewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    //设置布局显示的位置
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //默认侧滑删除是出于关闭的状态
        layoutContent(false);
    }

    /**
     * @param isOpen
     */
    private void layoutContent(boolean isOpen) {
        //设置前布局的位置
        Rect rect=computFrontRect(isOpen);
        mFrontView.layout(rect.left,rect.top,rect.right,rect.bottom);

        //根据前布局的位置设置后布局的位置
        Rect backRect=computeBackRectViaFront(rect);
        mBackView.layout(backRect.left,backRect.top,backRect.right,backRect.bottom);

        //切换任意布局的位置
        bringChildToFront(mFrontView);
    }

    /**
     * 根据前布局计算出后布局的位置
     * @param rect
     * @return
     */
    private Rect computeBackRectViaFront(Rect rect) {
        int left=rect.right;
        return new Rect(left,0,left+mFrontViewWidth,0+mHeight);
    }

    private Rect computFrontRect(boolean isOpen) {
        int left=0;
        if(isOpen){
            left=-mBackViewWidth;
        }
        return new Rect(left,0,left+mFrontViewWidth,mHeight);
    }
}
