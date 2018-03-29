package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.PumpStiingBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/17.
 * Holle Android
 */

public class DengRenuAdapter extends RecyclerView.Adapter<DengRenuAdapter.MyDengRenWuViewhoder> {


    private Context context;
    private int seebar;

    private List<PumpStiingBean> mBean;

    public DengRenuAdapter(Context context) {
        this.context = context;
        mBean = new ArrayList<>();
    }



    @Override
    public MyDengRenWuViewhoder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyDengRenWuViewhoder myDengRenWuViewhoder = new MyDengRenWuViewhoder(LayoutInflater.from(context).inflate(R.layout.item_dengrenwu,parent,false));

        return myDengRenWuViewhoder;
    }

    @Override
    public void onBindViewHolder(MyDengRenWuViewhoder holder, int position) {



        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seebar = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarItem.onIntemNum(seebar);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBean.size();
    }


    public interface OnIntemSeekBar{
        void onIntemNum(int seekbar);
    }

    private OnIntemSeekBar seekBarItem;
    public void getSeekBar(OnIntemSeekBar seekBar){
        this.seekBarItem = seekBar;
    }

    public void addItem(List<PumpStiingBean> mBean){
        this.mBean= mBean;
        notifyDataSetChanged();
    }

    class MyDengRenWuViewhoder extends RecyclerView.ViewHolder{

        private final EditText editText;
        private final TextView tvStart;
        private final ImageView ivDelete;
        private final SeekBar seekBar;

        public MyDengRenWuViewhoder(View itemView) {
            super(itemView);
            editText = (EditText) itemView.findViewById(R.id.pumptow_item_edtime);
            tvStart = (TextView) itemView.findViewById(R.id.pumptow_item_tv_start);
            ivDelete = (ImageView) itemView.findViewById(R.id.pump_item_delete);
            seekBar = (SeekBar) itemView.findViewById(R.id.seekbar);


            tvStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tiemdata();
                }
            });
        }

        private void tiemdata() {
            //时间选择器
            TimePickerView pvTime = new TimePickerView.Builder(context, new TimePickerView.OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    //Sun Aug 27 06:30:00 GMT+00:00 2017
                    String substring = date.toString().trim().substring(11, 16);
                    tvStart.setText(substring);
                }
            }).setType(new boolean[]{false,false,false,true,true,true}).build();
            pvTime.show();
        }
    }

}
