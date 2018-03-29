package com.example.hanyonghui.ciao.view.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hanyonghui on 2017/8/26.
 */

public class SildeView extends View {

    /**
     * 滑块当前的状态
     */
    public int currentState;
    /**
     * 未解锁
     */
    public static final int STATE_LOCK = 1;
    /**
     * 解锁
     */
    public static final int STATE_UNLOCK = 2;
    /**
     * 正在拖拽
     */
    public static final int STATE_MOVING = 3;

    private Bitmap slideUnlockBackground;
    private Bitmap slideUnlockBlock;

    private int blockBackgoundWidth;
    private int blockWidth;
    private int blockHeight;

    // 滑动的x y
    private float x;
    private float y;

    private boolean downOnBlock;


    /**
     * 通过handler来控制滑块在未解锁的时候，平缓的滑动到左端
     */
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                // 如果x还大于0，就人为的设置缓慢移动到最左端，每次移动距离设置为背景宽的/100
                if (x > 0) {
                    x = x - blockBackgoundWidth * 1.0f / 100;
                    // 刷新界面
                    postInvalidate();
                    // 设置继续移动
                    handler.sendEmptyMessageDelayed(0, 10);
                } else {
                    handler.removeCallbacksAndMessages(null);
                    currentState = STATE_LOCK;
                }
            }
        }
    };

    public SildeView(Context context) {
        this(context,null);
    }

    public SildeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SildeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 默认滑动解锁为未解锁状态
        currentState = STATE_LOCK;
        // 命名空间
        String namespace = "http://schemas.android.com/apk/res-auto";

        // 取出自定义属性中背景图片
        int slideUnlockBackgroundResource = attrs.getAttributeResourceValue(
                namespace, "slideUnlockBackgroundResource", -1);
        // 取出自定义属性中滑块图片
        int slideUnlockBlockResource = attrs.getAttributeResourceValue(
                namespace, "slideUnlockBlockResource", -1);

        /**
         * 当取出自定义属性的背景时，设置背景
         */
        setSlideUnlockBackground(slideUnlockBackgroundResource);

        /**
         * 当取出自定义属性的滑块时，设置滑块的图片
         */
        setSlideUnlockBlock(slideUnlockBlockResource);

        /**
         * 执行onDraw方法，进行界面绘制
         */
        postInvalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 在一开始的使用将背景图绘制出来
        canvas.drawBitmap(slideUnlockBackground, 0, 0, null);
        /**
         * 判断当前状态 绘制图案
         */
        switch (currentState){
            // 如果是未解锁，就将滑块绘制到最左端
            case STATE_LOCK:
                canvas.drawBitmap(slideUnlockBlock, 0, 0, null);
                break;
            // 已解锁，计算出
            case STATE_UNLOCK:
                int unlockX = blockBackgoundWidth - blockWidth;
                canvas.drawBitmap(slideUnlockBlock, unlockX, 0, null);
                break;
            // 绘制正在拖动
            case STATE_MOVING:
                if (x < 0) {
                    x = 0;
                } else if (x > blockBackgoundWidth - blockWidth) {
                    x = blockBackgoundWidth - blockWidth;
                }
                canvas.drawBitmap(slideUnlockBlock, x, 0, null);
                break;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //设置控件的宽高为滑块背景图的宽高
        setMeasuredDimension(slideUnlockBackground.getWidth(),
                slideUnlockBackground.getHeight());
    }



    // 处理onToch事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            // 当手指按下的时候 判断手指按下的位置是否再滑块上边
            case MotionEvent.ACTION_DOWN:
                if (currentState != STATE_MOVING){
                    // 判断一下，如果当前正在移动，则不执行触摸操作
                    // 获取相对于背景的左上角的x，y值
                    x = event.getX();
                    y = event.getY();
                    // 先计算滑块的中心点的 x，y坐标
                    // 先计算出滑块的中心点的x，y坐标
                    float blockCenterX = blockWidth * 1.0f / 2;
                    float blockCenterY = blockHeight * 1.0f / 2;
                    downOnBlock = isDownOnBlock(blockCenterX, x, blockCenterY, y);
                    // 调用onDraw方法
                    postInvalidate();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                // 如果手指确定按在滑块上，就视为开始拖拽滑块
                if (true) {
                    // 获取相对于背景的左上角的x，y值
                    x = event.getX();
                    y = event.getY();
                    currentState = STATE_MOVING;
                    // 调用onDraw方法
                    postInvalidate();
                }
                break;

            case MotionEvent.ACTION_UP:
                if (currentState == STATE_MOVING) {
                        // 当手指抬起的时候，应该是让滑块归位的
                        //说明未解锁
                    if (x < blockBackgoundWidth - blockWidth) {
                        handler.sendEmptyMessageDelayed(0, 10);
                        // 通过回调设置已解锁
                        onUnLockListener.setUnLocked(false);

                        Log.d("onUnLockListener",false+"");
                    } else {
                        currentState = STATE_UNLOCK;
                        // 通过回调设置未解锁
                        Log.d("onUnLockListener",true+"");
                        onUnLockListener.setUnLocked(true);
                    }

                    downOnBlock = false;
                        // 调用onDraw方法
                        postInvalidate();
                }
                break;
        }
        return true;
    }

    // 监听回调
    private OnUnLockListener onUnLockListener;

    public interface OnUnLockListener {
         void setUnLocked(boolean lock);
    }

    public void setOnUnLockListener(OnUnLockListener onUnLockListener) {
        this.onUnLockListener = onUnLockListener;
    }


    public boolean isDownOnBlock(float x1, float x2, float y1, float y2) {
        float sqrt = (float) Math.sqrt(Math.abs(x1 - x2) * Math.abs(x1 - x2)
                + Math.abs(y1 - y2) * Math.abs(y1 - y2));
        if (sqrt <= blockWidth / 2) {
            return true;
        }
        return false;
    }
    /**
     * 设置背景图
     * @param slideUnlockBackgroundResource
     */
    public void setSlideUnlockBackground(int slideUnlockBackgroundResource) {
        slideUnlockBackground = BitmapFactory.decodeResource(getResources(),
                slideUnlockBackgroundResource);
        // 获取背景图的宽和高
        blockBackgoundWidth = slideUnlockBackground.getWidth();
    }

    /**
     * 设置滑块图
     * @param slideUnlockBlockResource
     */
    public void setSlideUnlockBlock(int slideUnlockBlockResource) {
        slideUnlockBlock = BitmapFactory.decodeResource(getResources(),
                slideUnlockBlockResource);
        // 获取滑块的宽和高
        blockWidth = slideUnlockBlock.getWidth();
        blockHeight = slideUnlockBlock.getHeight();
    }
}
