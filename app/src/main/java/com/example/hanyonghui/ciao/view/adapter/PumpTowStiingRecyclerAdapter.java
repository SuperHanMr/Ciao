package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.SPUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by hanyonghui on 2017/8/26.
 *描述 泵 阀 灯每条任务设置
 *
 */

public class PumpTowStiingRecyclerAdapter extends RecyclerView.Adapter<PumpTowStiingRecyclerAdapter.MyPumpHodlerView> {


    private Context context;
    private List<PumpStiingBean> mBean;

    private List<String> seekBarList;

    private int seeleBarProgress;
    public PumpTowStiingRecyclerAdapter(Context context) {
        this.context = context;
        mBean = new ArrayList<>();
        seekBarList = new ArrayList<>();


    }



    @Override
    public MyPumpHodlerView onCreateViewHolder(ViewGroup parent, int viewType) {
        MyPumpHodlerView view = new MyPumpHodlerView(LayoutInflater.from(context).inflate(R.layout.pumptow_item,null));
        return view;
    }
    @Override
    public void onBindViewHolder(final MyPumpHodlerView holder, final int position) {


        if(SPUtils.getInt(KeyUtils.EQUIPMENTTYPE)==6){
            holder.seekBar.setVisibility(View.VISIBLE);
            holder.seekBar.setProgress(mBean.get(position).getSeekBar());
            holder.tvshijian.setText("时/每次");
        }else {
            holder.seekBar.setVisibility(View.GONE);
            holder.tvshijian.setText("秒/每次");
        }



        if (holder.editText.getTag() instanceof TextWatcher) {
            holder.editText.removeTextChangedListener((TextWatcher) holder.editText.getTag());
        }
        if (holder.tvStart.getTag() instanceof TextWatcher) {
            holder.tvStart.removeTextChangedListener((TextWatcher) holder.tvStart.getTag());
        }

       holder.tvStart.setText(mBean.get(position).getStarted());
       holder.editText.setText(mBean.get(position).getEnd());
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                mBean.get(position).setEnd(holder.editText.getText().toString().trim());
                mBean.get(position).setStarted(holder.tvStart.getText().toString().trim());

            }
        };

        holder.tvStart.addTextChangedListener(textWatcher);
        holder.tvStart.setTag(textWatcher);
        holder.editText.addTextChangedListener(textWatcher);
        holder.editText.setTag(textWatcher);



        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBean.remove(position);
                onListenerItem.OnListener(mBean.size());
                seekBarList.remove(position);
                notifyDataSetChanged();
            }
        });




        holder.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seeleBarProgress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                MyToast.massageToast(context, seeleBarProgress);
//               seekBarList.add(String.valueOf(seeleBarProgress));
//                listener.onRecyclerText(seekBarList);

            }
        });

        holder.seekBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListenerItem.OnListener(position);
            }
        });

    }


    private OnListenerItem onListenerItem = null;

   public interface OnListenerItem{
        void OnListener(int item);
    }

    private OnRecyclerItemListener listener;

    public interface OnRecyclerItemListener{
        void onRecyclerText(List<String> seeBarList);
    }

    public void setOnRecyclerItem(OnRecyclerItemListener listener){
        this.listener = listener;
    }


    public void setOnListenerItem(OnListenerItem listenerItem){
        this.onListenerItem = listenerItem;
    }

    @Override
    public int getItemCount() {
       if (mBean!=null&mBean.size()!=0){
           return mBean.size();
       }
       return 0;
    }


    public void addItem(List<PumpStiingBean> mBean){
        this.mBean = mBean;
        for (int i = 0; i < mBean.size(); i++) {
            PumpStiingBean bean = mBean.get(i);
            seekBarList.add(String.valueOf(bean.getSeekBar()));
        }

        notifyDataSetChanged();
    }


    class MyPumpHodlerView extends RecyclerView.ViewHolder{


        private final EditText editText;
        private final TextView tvStart;
        private final ImageView ivDelete;
        private final SeekBar seekBar;
        private final TextView tvLiangdu;
        private final TextView tvshijian;

        public MyPumpHodlerView(View itemView) {
            super(itemView);

            editText = (EditText) itemView.findViewById(R.id.pumptow_item_edtime);
            tvStart = (TextView) itemView.findViewById(R.id.pumptow_item_tv_start);
            ivDelete = (ImageView) itemView.findViewById(R.id.pump_item_delete);
            seekBar = (SeekBar) itemView.findViewById(R.id.seekbar);
            tvLiangdu = (TextView) itemView.findViewById(R.id.tv_liangdu);
            tvshijian = (TextView) itemView.findViewById(R.id.tv_renwu_shijian);
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
