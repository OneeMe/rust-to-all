package com.onee.rusty;

import android.content.Context;

import com.facebook.react.ReactRootView;

public class RustRootView extends ReactRootView {
    private RustRootViewDelegate delegate;
    private int rustRootViewTag;

    public void setDelegate(RustRootViewDelegate delegate) {
        this.delegate = delegate;
    }

    public void setRustRootViewTag(int rustRootViewTag) {
        this.rustRootViewTag = rustRootViewTag;
    }

    public interface RustRootViewDelegate {
        public void updateLayoutSpec(int tag, int widthMeasureSpec, int heightMeasureSpec);
    }
    public RustRootView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (this.delegate != null) {
            this.delegate.updateLayoutSpec(this.rustRootViewTag, widthMeasureSpec, heightMeasureSpec);
        }
    }
}
