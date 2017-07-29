package com.wongxd.partymanage.peoplestalk.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by zyj on 2017/7/28.
 */

public class NotesTextView extends TextView {
    public NotesTextView(Context context) {
        super(context);
    }

    public NotesTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NotesTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
