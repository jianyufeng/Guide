package com.frame.guide.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;


/**
 * Created by Administrator on 2017/10/12.
 */

public class GuideViewPager extends ViewPager {
    private Bitmap bg;
    private Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);

    public GuideViewPager(Context context) {
        super(context);
    }

    public GuideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (this.bg!=null){
            int wtdth = bg.getWidth();
            int height = bg.getHeight();
            int count = getAdapter().getCount();
            int x = getScrollX();
            //子view中背景图片需要显示的宽度，放大背景或缩小背景
            int n = height * getWidth()/getHeight();//放大或缩小后一页显示的宽度
            /***
             * (width - n)/(n-1)表示除去显示第一个viewpager页面用去的背景，
             * 剩余viewpager需要显示的背景图片的宽度
             * getWidth() viewpager的宽度 即屏幕宽度。
             * ((width - n) / (count - 1)) /getWidth() 表示 viewpager滑动滑动一个像素时，背景滑动的
             * 距离
             * x * ((width - n) / (count - 1)) /  getWidth() 表示viewpager滑动x个像素时，背景图片滑动的宽度
             * 背景图片滑动的宽度的宽度可以理解为背景图片滑动到达的位置。
             */
            int w = x * (wtdth - n)/(count-1)/getWidth();
            canvas.drawBitmap(bg,new Rect(w,0,w+n,height),new Rect(x,0,x+getWidth(),getHeight()),p);
        }
        super.dispatchDraw(canvas);
    }

    public void setBackGround(Bitmap paramBitmap){
        this.bg = paramBitmap;
        this.p.setFilterBitmap(true);

    }
}
