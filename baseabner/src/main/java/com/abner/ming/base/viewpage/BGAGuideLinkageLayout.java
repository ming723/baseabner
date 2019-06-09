package com.abner.ming.base.viewpage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class BGAGuideLinkageLayout extends FrameLayout {
    public BGAGuideLinkageLayout(Context context) {
        super(context);
    }

    public BGAGuideLinkageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BGAGuideLinkageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            try {
                child.dispatchTouchEvent(ev);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
