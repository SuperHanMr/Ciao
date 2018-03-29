package com.example.hanyonghui.ciao.bean.connector;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.hanyonghui.ciao.view.adapter.MeEauipmentRecyclerViewAdapter;

/**
 * Created by hanyonghui on 2017/9/14.
 */

public class SimpleTouchHelperCallback extends ItemTouchHelper.Callback {


    private MeEauipmentRecyclerViewAdapter adapter ;

    public SimpleTouchHelperCallback(MeEauipmentRecyclerViewAdapter recyclerViewAdapter) {
        this.adapter = recyclerViewAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        final int dragMode = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int moveMode = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragMode, moveMode);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        adapter.onItemDismiss(viewHolder.getAdapterPosition());


    }
}
