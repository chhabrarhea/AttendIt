package com.example.attendit.util;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MarginItemDecorator extends RecyclerView.ItemDecoration {

    private int spacing;

    public MarginItemDecorator(int spacing) {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getAdapter()==null)
            Log.i("adapter","null");
        if (parent.getAdapter()!=null && parent.getChildLayoutPosition(view)==parent.getAdapter().getItemCount()-1)
        {
            outRect.top=spacing;
            outRect.bottom=spacing;
            outRect.left=spacing;
            outRect.right=spacing;
            Log.i("adapter",""+parent.getChildAdapterPosition(view));
        }
        else {
            outRect.top=spacing;
            outRect.left=spacing;
            outRect.right=spacing;
            Log.i("adapter"," " +parent.getChildAdapterPosition(view));
        }
    }
}