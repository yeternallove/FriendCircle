package com.sorcererxw.demo.friendcircle.ui.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.sorcererxw.demo.friendcircle.util.DisplayUtil;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/10/20
 */

public class ResizingImageView extends ImageView {

    private int mMaxWidth;
    private int mMaxHeight;

    public ResizingImageView(Context context) {
        super(context);
    }

    public ResizingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResizingImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setMaxWidth(int maxWidth) {
        super.setMaxWidth(maxWidth);
        mMaxWidth = maxWidth;
    }

    @Override
    public void setMaxHeight(int maxHeight) {
        super.setMaxHeight(maxHeight);
        mMaxHeight = maxHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Drawable drawable = getDrawable();
        if (drawable != null) {

            int wMode = MeasureSpec.getMode(widthMeasureSpec);
            int hMode = MeasureSpec.getMode(heightMeasureSpec);
            if (wMode == MeasureSpec.EXACTLY || hMode == MeasureSpec.EXACTLY) {
                return;
            }

            // Calculate the most appropriate size for the view. Take into
            // account minWidth, minHeight, maxWith, maxHeigh and allowed size
            // for the view.

            int maxWidth = wMode == MeasureSpec.AT_MOST
                    ? Math.min(MeasureSpec.getSize(widthMeasureSpec), mMaxWidth)
                    : mMaxWidth;
            int maxHeight = hMode == MeasureSpec.AT_MOST
                    ? Math.min(MeasureSpec.getSize(heightMeasureSpec), mMaxHeight)
                    : mMaxHeight;

            int dWidth = DisplayUtil.dip2px(getContext(), drawable.getIntrinsicWidth());
            int dHeight = DisplayUtil.dip2px(getContext(), drawable.getIntrinsicHeight());
            float ratio = ((float) dWidth) / dHeight;

            int width = Math.min(Math.max(dWidth, getSuggestedMinimumWidth()), maxWidth);
            int height = (int) (width / ratio);

            height = Math.min(Math.max(height, getSuggestedMinimumHeight()), maxHeight);
            width = (int) (height * ratio);

            if (width > maxWidth) {
                width = maxWidth;
                height = (int) (width / ratio);
            }

            setMeasuredDimension(width, height);
        }
    }
}
