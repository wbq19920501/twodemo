package com.jokeep.twodemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by wbq501 on 2015-12-25 15:40.
 * twodemo
 */
public class RoundImg extends ImageView{
    /**
     * 图片
     */
    private Bitmap mSrc;
    /**
     * 圆角的大小
     */
    private int mRadius;
    /**
     * 控件的宽度
     */
    private int mWidth;
    /**
     * 控件的高度
     */
    private int mHeight;
    public RoundImg(Context context) {
        this(context, null);
    }

    public RoundImg(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundImg(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyle, 0);
//        int n = a.getIndexCount();
//        for (int i = 0; i < n; i++)
//        {
//            int attr = a.getIndex(i);
//            switch (attr)
//            {
//                case R.styleable.CustomImageView_src:
//                    mSrc = BitmapFactory.decodeResource(getResources(), a.getResourceId(attr, 0));
//                    break;
//            }
//        }
        mSrc = BitmapFactory.decodeResource(getResources(),a.getResourceId(1,0));
        a.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int min = Math.min(mWidth,mHeight);
        mSrc = Bitmap.createScaledBitmap(mSrc,min,min,false);//压缩图片以最小为准
        canvas.drawBitmap(createCircleImage(mSrc, min), 0, 0, null);
    }

    private Bitmap createCircleImage(Bitmap mSrc, int min) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Bitmap target = Bitmap.createBitmap(min,min, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(target);
        canvas.drawCircle(min/2,min/2,min/2,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mSrc,0,0,paint);
        return target;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizew = MeasureSpec.getSize(widthMeasureSpec);
        int sizeh = MeasureSpec.getSize(heightMeasureSpec);

        int modew = MeasureSpec.getMode(widthMeasureSpec);
        int modeh = MeasureSpec.getMode(heightMeasureSpec);

        if (modew == MeasureSpec.EXACTLY){
            mWidth = sizew;
        }else {
            //图片决定宽度
            int desireByImg = getPaddingLeft()+getPaddingRight()+mSrc.getWidth();
            if (modew == MeasureSpec.AT_MOST){
                mWidth = Math.min(desireByImg,sizew);
            }
        }
        if (modeh == MeasureSpec.EXACTLY){
            mHeight = sizeh;
        }else {
            int desire = getPaddingTop()+getPaddingBottom()+mSrc.getHeight();
            if (modeh == MeasureSpec.AT_MOST){
                mHeight = Math.min(desire,sizeh);
            }
        }
        setMeasuredDimension(mWidth,mHeight);
    }
}
