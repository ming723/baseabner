package com.abner.ming.base.viewpage.transformer;

import android.support.v4.view.ViewCompat;
import android.view.View;

public class StackPageTransformer extends BGAPageTransformer {

    @Override
    public void handleInvisiblePage(View view, float position) {
    }

    @Override
    public void handleLeftPage(View view, float position) {
    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewCompat.setTranslationX(view, -view.getWidth() * position);
    }

}