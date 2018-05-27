package com.seven.seven.user.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.util.AttributeSet;

import com.seven.seven.R;

/**
 * Created by seven
 * on 2018/5/27
 * email:seven2016s@163.com
 */

public class HeadImgView extends android.support.v7.widget.AppCompatImageView {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public HeadImgView(Context context) {
        super(context);
    }

    public HeadImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeadImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setBitmap(Bitmap bitmap) {

//        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.home_toolbar_bg);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.is_shuai);
//        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OVER);
        paint.setShader(shader1);
//        canvas.drawCircle(100,100,20,paint);
    }
}
