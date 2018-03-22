package com.seven.seven.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.seven.seven.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoshengsheng on 2016/11/25.
 */
public class MarqueeView extends SurfaceView implements SurfaceHolder.Callback {
    public Context mContext;

    private float mTextSize = 36; //字体大小

    private int mTextColor = Color.RED; //字体的颜色

    private int mBackgroundColor = Color.WHITE;//背景色

    private boolean mIsRepeat;//是否重复滚动

    private int mStartPoint;// 开始滚动的位置  0是从最左面开始    1是从最末尾开始

    private int mDirection;//滚动方向 0 向左滚动   1向右滚动

    private int mSpeed;//滚动速度

    private SurfaceHolder holder;

    private TextPaint mTextPaint;

    private MarqueeViewThread mThread;

    private String margueeString;

    private int textWidth = 0, textHeight = 0;

    private int ShadowColor = Color.BLACK;

    public int currentX = 0;// 当前x的位置

    public int sepX = 2;//每一步滚动的距离

    public MarqueeView(Context context) {
        this(context, null);
    }

    public MarqueeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MarqueeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeView, defStyleAttr, 0);
        mTextColor = a.getColor(R.styleable.MarqueeView_textColor, Color.RED);
        mTextSize = a.getDimension(R.styleable.MarqueeView_textSize, 36);
        mBackgroundColor = a.getColor(R.styleable.MarqueeView_marqueebackground, Color.WHITE);
        mIsRepeat = a.getBoolean(R.styleable.MarqueeView_isRepeat, false);
        mStartPoint = a.getInt(R.styleable.MarqueeView_startPoint, 0);
        mDirection = a.getInt(R.styleable.MarqueeView_direction, 0);
        mSpeed = a.getInt(R.styleable.MarqueeView_speed, 20);
        a.recycle();

        holder = this.getHolder();
        holder.addCallback(this);
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
//        setZOrderOnTop(true);//使surfaceview放到最顶层
//        getHolder().setFormat(PixelFormat.TRANSPARENT);//使窗口支持透明度

    }

    public void setText(List<String> msg) {
        if (msg != null && msg.size() > 0) {
            measurementsText(msg);
        } else {
            throw new RuntimeException("Marquee text is null");
        }
    }

    private List<Integer> overIndex = new ArrayList<>();

    protected void measurementsText(List<String> msg) {
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setStrokeWidth(0.5f);
//        mTextPaint.setFakeBoldText(true);
        // 设定阴影(柔边, X 轴位移, Y 轴位移, 阴影颜色)
//        mTextPaint.setShadowLayer(5, 3, 3, ShadowColor);
        margueeString = getFinalContent(msg);
        textWidth = (int) mTextPaint.measureText(margueeString) + (int) mTextPaint.measureText(getSpace());
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        textHeight = (int) fontMetrics.bottom;
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = 0;
        if (wm != null) {
            width = wm.getDefaultDisplay().getWidth();
        }
        if (mStartPoint == 0)
            currentX = 0;
        else
            currentX = width - getPaddingLeft() - getPaddingRight();
    }

    @NonNull
    private String getFinalContent(List<String> msg) {
        overIndex.clear();
        String space = getSpace();
        int spaceWidth = (int) mTextPaint.measureText(space);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < msg.size(); i++) {
            String content = msg.get(i);
            sb.append(content);
            if (i != msg.size() - 1) {
                sb.append(space);
            }
            String s = sb.toString();
            int textWidth = (int) mTextPaint.measureText(s);
            if (i == msg.size() - 1)
                textWidth = textWidth + (spaceWidth * 2);
            if (textWidth % 2 != 0) {
                textWidth += 1;
            }
            overIndex.add(textWidth);
        }
        return sb.toString();
    }

    @NonNull
    private String getSpace() {
        StringBuilder k = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            k.append("     ");
        }
        return k.toString();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.holder = holder;
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mThread != null)
            mThread.isRun = true;
        startScroll();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mThread != null)
            mThread.isRun = false;
        stopScroll();
    }

    /**
     * 开始滚动
     */
    public void startScroll() {
        if (mThread != null && mThread.isRun)
            return;
        mThread = new MarqueeViewThread(holder);//创建一个绘图线程
        mThread.start();
    }

    /**
     * 停止滚动
     */
    public void stopScroll() {
        if (mThread != null) {
            mThread.isRun = false;
            mThread.interrupt();
        }
        mThread = null;
    }

    /**
     * 线程
     */
    class MarqueeViewThread extends Thread {

        private SurfaceHolder holder;

        public boolean isRun;//是否在运行


        public MarqueeViewThread(SurfaceHolder holder) {
            this.holder = holder;
            isRun = true;
        }

        public void onDraw() {
            try {
                synchronized (holder) {
                    if (TextUtils.isEmpty(margueeString)) {
                        Thread.sleep(1000);//睡眠时间为1秒
                        return;
                    }

                    Canvas canvas = holder.lockCanvas();
                    canvas.drawColor(Color.WHITE);
                    int paddingLeft = getPaddingLeft();
                    int paddingTop = getPaddingTop();
                    int paddingRight = getPaddingRight();
                    int paddingBottom = getPaddingBottom();

                    int contentWidth = getWidth() - paddingLeft - paddingRight;
                    int contentHeight = getHeight() - paddingTop - paddingBottom;

                    int centeYLine = paddingTop + contentHeight / 2;//中心线

                    if (overIndex != null && overIndex.size() > 0) {
                        for (int i = 0; i < overIndex.size(); i++) {
                            int over = overIndex.get(i);
                            if (-currentX == (over - contentWidth + 100)) {
                                Message message = new Message();
                                message.what = ROLL_INDEX_OVER;
                                message.arg1 = (i + 1) % overIndex.size();
                                mHandler.sendMessage(message);
                            }
                        }
                    }

                    if (mDirection == 0) {//向左滚动
                        if (currentX <= -textWidth) {
                            if (!mIsRepeat) {//如果是不重复滚动
                                mHandler.sendEmptyMessage(ROLL_OVER);
                            }
                            currentX = contentWidth;
                        } else {
                            currentX -= sepX;
                        }
                    } else {//  向右滚动
                        if (currentX >= contentWidth) {
                            if (!mIsRepeat) { //如果是不重复滚动
                                mHandler.sendEmptyMessage(ROLL_OVER);
                            }
                            currentX = -textWidth;
                        } else {
                            currentX += sepX;
                        }
                    }

//                    CommonLogger.e("mViewPager","currentX : "+currentX);

                    if (canvas != null)
                        canvas.drawColor(mBackgroundColor);//绘制透明色
                    canvas.drawText(margueeString, currentX, centeYLine + dip2px(getContext(), textHeight) / 2, mTextPaint);
                    holder.unlockCanvasAndPost(canvas);//结束锁定画图，并提交改变。

                    int a = textWidth / margueeString.trim().length();
                    int b = a / sepX;
                    int c = mSpeed / b == 0 ? 1 : mSpeed / b;
                    Thread.sleep(c);//睡眠时间为移动的频率

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            while (isRun) {
                onDraw();
            }
        }

    }

    public static final int ROLL_OVER = 100;

    private static final int ROLL_INDEX_OVER = 200;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case ROLL_OVER:
                    stopScroll();
                    if (mOnMargueeListener != null) {
                        mOnMargueeListener.onRollOver();
                    }
                    break;

                case ROLL_INDEX_OVER:
                    if (mOnMargueeListener != null) {
                        mOnMargueeListener.onRollIndex(msg.arg1);
                    }
                    break;
            }
        }
    };

    /**
     * dip转换为px
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void reset() {
        int contentWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        if (mStartPoint == 0)
            currentX = 0;
        else
            currentX = contentWidth;
    }

    /**
     * 滚动回调
     */
    public interface OnMargueeListener {
        void onRollOver();//滚动完毕

        void onRollIndex(int position);//滚动某个位置完毕
    }

    OnMargueeListener mOnMargueeListener;

    public void setOnMargueeListener(OnMargueeListener mOnMargueeListener) {
        this.mOnMargueeListener = mOnMargueeListener;
    }
}
