package com.example.hanyonghui.ciao.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.AddPlantsGuideBean;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hanyonghui on 2017/8/30.
 */

public class GardenerCultivateAdapter extends RecyclerView.Adapter<GardenerCultivateAdapter.MyGardenerViewHodler>  {

    private int item;
    private Context context;

    private String[] str = {"第一阶段","第二阶段","第三阶段","第四阶段","第五阶段","第六阶段",};

    private List<AddPlantsGuideBean> list;
    private String id;

    public GardenerCultivateAdapter(Context context,String id) {
        this.context = context;
        this.id = id;
        list = new ArrayList<>();
    }

    @Override
    public MyGardenerViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        MyGardenerViewHodler hodler = new MyGardenerViewHodler(LayoutInflater.from(context).inflate(R.layout.gradener_reccler_item,null));
        return hodler;
    }



    @Override
    public void onBindViewHolder(final MyGardenerViewHodler holder, final int position) {
        holder.textView.setText(str[position]);

        if (holder.editTltle.getTag() instanceof TextWatcher) {
            holder.editTltle.removeTextChangedListener((TextWatcher) holder.editTltle.getTag());
        }
        if (holder.edPhase.getTag() instanceof TextWatcher) {
            holder.edPhase.removeTextChangedListener((TextWatcher) holder.edPhase.getTag());
        }

        if (holder.edWatering.getTag() instanceof TextWatcher) {
            holder.edWatering.removeTextChangedListener((TextWatcher) holder.edWatering.getTag());
        }
        if (holder.edSpray.getTag() instanceof TextWatcher) {
            holder.edSpray.removeTextChangedListener((TextWatcher) holder.edSpray.getTag());
        }

        if (holder.edDay.getTag() instanceof TextWatcher) {
            holder.edDay.removeTextChangedListener((TextWatcher) holder.edDay.getTag());
        }


        holder.editTltle.setText(list.get(position).getTitle());
        holder.edDay.setText(list.get(position).getDay());
        holder.edPhase.setText(list.get(position).getContent());
        holder.edSpray.setText(list.get(position).getSpray());
        holder.edWatering.setText(list.get(position).getWatering());


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                list.get(position).setDay(holder.edDay.getText().toString().trim());
                list.get(position).setTitle(holder.editTltle.getText().toString().trim());
                list.get(position).setContent(holder.edPhase.getText().toString().trim());
                list.get(position).setSpray(holder.edSpray.getText().toString().trim());
                list.get(position).setWatering(holder.edWatering.getText().toString().trim());
                list.get(position).setCircleNo(position+1+"");
                list.get(position).setPtypeid(id);
                Gson gson = new Gson();
                Type type=  new TypeToken<List<AddPlantsGuideBean>>(){}.getType();
                String  jsonList = gson.toJson(list,type);
                // 接口回调
                onListenerGardenerData.onGardenerData(jsonList);
            }
        };

        holder.edDay.addTextChangedListener(textWatcher);
        holder.edDay.setTag(textWatcher);
        holder.editTltle.addTextChangedListener(textWatcher);
        holder.editTltle.setTag(textWatcher);
        holder.edPhase.addTextChangedListener(textWatcher);
        holder.edPhase.setTag(textWatcher);
        holder.edSpray.addTextChangedListener(textWatcher);
        holder.edSpray.setTag(textWatcher);
        holder.edWatering.addTextChangedListener(textWatcher);
        holder.edWatering.setTag(textWatcher);

    }


    private OnListenerGardenerData onListenerGardenerData;

    public interface OnListenerGardenerData{
        void onGardenerData(String json);
    }

    public void setOnListenerGardenerData(OnListenerGardenerData onListenerGardenerData){
        this.onListenerGardenerData = onListenerGardenerData;

    }

    @Override
    public int getItemCount() {
        return list.size()!=0?list.size():0;
    }

    public void setItem(List<AddPlantsGuideBean> list){
        if (list.size()>6){
            MyToast.show(context,"不能超过6个阶段");
            return;
        }

        this.list = list;
        notifyDataSetChanged();
    }

    public class MyGardenerViewHodler extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final EditText editTltle;
        private final EditText edPhase;
        private final EditText edWatering;
        private final EditText edSpray;
        private final EditText edDay;

        public MyGardenerViewHodler(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.gradeder_item_tv);
            editTltle = (EditText) itemView.findViewById(R.id.add_gradener_ed_title);
            edPhase = (EditText) itemView.findViewById(R.id.add_gradener_ed_phase);
            edWatering = (EditText) itemView.findViewById(R.id.add_gradener_ed_watering);
            edSpray = (EditText) itemView.findViewById(R.id.add_gradener_ed_spray);
            edDay = (EditText) itemView.findViewById(R.id.add_gradener_ed_day);
        }
    }
}
