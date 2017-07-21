package com.wongxd.partymanage.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;

import com.wongxd.partymanage.R;

public class TriangleView extends View {
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath = new Path();
    private int mColor;
    /**
     * 三角形朝向
     */
    private int mGravity;

    public TriangleView(Context context) {
        this(context, null);
    }

    public TriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TriangleView);

        mColor = ta.getColor(R.styleable.TriangleView_color, Color.WHITE);
        int orientation = ta.getInt(R.styleable.TriangleView_gravity, 1);
        switch (orientation) {
            case 1:
                mGravity = Gravity.LEFT;
                break;
            case 2:
                mGravity = Gravity.RIGHT;
                break;
            case 3:
                mGravity = Gravity.TOP;
                break;
            case 4:
                mGravity = Gravity.BOTTOM;
                break;
        }
        ta.recycle();
    }

    public void setColor(int color) {
        mColor = color;
        invalidate();
    }

    public void setGravity(int gravity) {
        mGravity = gravity;
        invalidate();
    }

    public int getColor() {
        return mColor;
    }

    public int getGravity() {
        return mGravity;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        mPaint.setColor(mColor);

        mPath.reset();
        if (mGravity == Gravity.TOP) {//三角形朝上
            mPath.moveTo(width / 2, 0);
            mPath.lineTo(0, height);
            mPath.lineTo(width, height);
            mPath.close();
        } else if (mGravity == Gravity.BOTTOM) {//三角形朝下
            mPath.moveTo(0, 0);
            mPath.lineTo(width, 0);
            mPath.lineTo(width / 2, height);
            mPath.close();
        } else if (mGravity == Gravity.LEFT) {//三角形朝左
            mPath.moveTo(0, height / 2);
            mPath.lineTo(width, height);
            mPath.lineTo(width, 0);
            mPath.close();
        } else if (mGravity == Gravity.RIGHT) {//三角形朝右
            mPath.moveTo(width, height / 2);
            mPath.lineTo(0, height);
            mPath.lineTo(0, 0);
            mPath.close();
        }


        canvas.drawPath(mPath, mPaint);
    }
}
