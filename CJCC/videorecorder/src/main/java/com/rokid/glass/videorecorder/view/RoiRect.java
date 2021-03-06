package com.rokid.glass.videorecorder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.rokid.glass.videorecorder.R;


public class RoiRect extends View {
    private static int PADDING = 40;
    private static final int LINE_WIDTH = 32;
    private static final int RADIUS = 15;
    private Paint paint = new Paint();
    private int color_white;
    public RoiRect(Context context) {
        this(context,null);
    }

    public RoiRect(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoiRect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        color_white = context.getResources().getColor(R.color.color_white);
    }

    private void initPaint() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(7);
        paint.setColor(color_white);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect  = new Rect();
        rect.left = PADDING;
        rect.right = getWidth() - PADDING;
        rect.top = PADDING;
        rect.bottom = getHeight() - PADDING;

        drawRountRect(canvas,rect,paint);
    }

    /**
     *
     * @param canvas 画布
     * @param rect 画质的矩形
     * @param paint 画笔
     */
    private void drawRountRect(Canvas canvas, Rect rect, Paint paint) {
        canvas.save();
        canvas.translate((rect.left + rect.right) / 2f, (rect.top + rect.bottom) / 2f);
        drawRectLeft(canvas, 0, rect.width(), rect.height(), paint);
        drawRectRight(canvas, 0, rect.width(), rect.height(), paint);
        drawRectLeft(canvas, 180, rect.width(), rect.height(), paint);
        drawRectRight(canvas, 180, rect.width(), rect.height(), paint);
        canvas.restore();
    }

    /**
     *
     * @param canvas 画布
     * @param angle 画的角度
     * @param width 横线宽度
     * @param height 横线高度
     * @param paint 画笔，决定画笔的颜色
     */
    private void drawRectLeft(Canvas canvas, int angle, int width, int height, Paint paint) {

        canvas.save();
        canvas.rotate(angle);

        Path path = new Path();
        path.moveTo(-width / 2, height / 2 - LINE_WIDTH);
        path.lineTo(-width / 2, height / 2 - RADIUS);

        RectF rectF = new RectF(-width / 2f, height / 2f - 2 * RADIUS,
                -width / 2f + 2 * RADIUS, height / 2f);
        path.addArc(rectF, 90, 90f);

        path.moveTo(-width / 2 + RADIUS, height / 2);
        path.lineTo(-width / 2 + LINE_WIDTH, height / 2);
        canvas.drawPath(path, paint);

        canvas.restore();

    }


    /**
     *
     * @param canvas 画布
     * @param angle 画的角度
     * @param width 横线宽度
     * @param height 横线高度
     * @param paint 画笔，决定画笔的颜色
     */
    private void drawRectRight(Canvas canvas, int angle, int width, int height, Paint paint) {

        canvas.save();
        canvas.rotate(angle);

        Path path = new Path();
        path.moveTo(width / 2, height / 2 - LINE_WIDTH);
        path.lineTo(width / 2, height / 2 - RADIUS);
        RectF rectF = new RectF(width / 2f - 2 * RADIUS, height / 2f - 2 * RADIUS,
                width / 2f, height / 2f);
        path.addArc(rectF, 90, -90f);

        path.moveTo(width / 2 - RADIUS, height / 2);
        path.lineTo(width / 2 - LINE_WIDTH, height / 2);
        canvas.drawPath(path, paint);
        canvas.restore();

    }

    public Rect getRect(){
        Rect rect = new Rect();
        getGlobalVisibleRect(rect);
        return rect;
    }
}
