package com.example.hanyonghui.ciao.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hanyonghui.ciao.APP;
import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.CodeBean;
import com.example.hanyonghui.ciao.bean.bean.PumpStiingBean;
import com.example.hanyonghui.ciao.bean.bean.PumpSttingTimeBean;
import com.example.hanyonghui.ciao.bean.bean.TaskListBena;
import com.example.hanyonghui.ciao.bean.request.NetworkReuset;
import com.example.hanyonghui.ciao.bean.request.RequestUrls;
import com.example.hanyonghui.ciao.utils.GetNumUtlis;
import com.example.hanyonghui.ciao.utils.KeyUtils;
import com.example.hanyonghui.ciao.utils.LogUtils;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.utils.SPUtils;
import com.example.hanyonghui.ciao.view.adapter.PumpTowStiingRecyclerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.zhy.autolayout.AutoLayoutActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Cre
 * <p>
 * ated by hanyonghui on 2017/8/26.
 */

public class PumpTowStiingActivity extends AutoLayoutActivity implements PumpTowStiingRecyclerAdapter.OnRecyclerItemListener {

    @BindView(R.id.tv_renwu_mingchen)
    TextView tvRenwuMingchen;
    private String title;

    @BindView(R.id.pumptow_recycler)
    RecyclerView pumptowRecycler;
    @BindView(R.id.pumptow_title)
    TextView pumptowTitle;
    @BindView(R.id.checkbox_yi)
    CheckBox checkboxYi;
    @BindView(R.id.checkbox_er)
    CheckBox checkboxEr;
    @BindView(R.id.checkbox_san)
    CheckBox checkboxSan;
    @BindView(R.id.checkbox_si)
    CheckBox checkboxSi;
    @BindView(R.id.checkbox_wu)
    CheckBox checkboxWu;
    @BindView(R.id.checkbox_liu)
    CheckBox checkboxLiu;
    @BindView(R.id.checkbox_ri)
    CheckBox checkboxRi;
    private PumpTowStiingRecyclerAdapter adapterRecycler;
    private int item = 0;
    private List<PumpStiingBean> beanList;
    private LinearLayoutManager mMabager;
    private List<String> lists;
    private String type;

    private HttpParams params = new HttpParams();
    private String jsonList;
    private String id;


    private List<CheckBox> checkBoxes;

    private int week[];

    private List<String> seeBarList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pumptowstiing_activity);
        ButterKnife.bind(this);
        id = getIntent().getStringExtra(KeyUtils.EQUIPMENTNAMEID);
        APP.getApp().addActivity(this);
        initView();
        initData();
    }

    private void initData() {
        beanList = new ArrayList();
        params.clear();
        params.put("did", id);
        params.put("type",type);
        NetworkReuset.getInstance().PostReuset(RequestUrls.SWITCHLIST, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                LogUtils.d(s);
                Gson gson = new Gson();
                TaskListBena taskListBena = gson.fromJson(s, TaskListBena.class);
                List<TaskListBena.CBean> c = taskListBena.getC();
                item = c.size();
                if (c.size() == 0 || c == null) {
                    return;
                }
                String weeks = c.get(0).getWeek();
                int[] num = GetNumUtlis.getNum(weeks);
                for (TaskListBena.CBean cBean : c) {
                    int hours = cBean.getHours(); // 时
                    int minute = cBean.getMinute(); // 分
                    int second = cBean.getTimeLength(); // 分
                    String started = hours + ":" + minute + "";
                    String end = second + "";
                    PumpStiingBean bean = new PumpStiingBean(started, end, cBean.getLightness());
                    beanList.add(bean);
                }

                for (int i = 0; i < num.length; i++) {
                    checkBoxes.get(num[i] - 1).setChecked(true);
                }

                adapterRecycler.addItem(beanList);

            }


        });

        lists = new ArrayList<>(); // 提交数据的集合

        // 初始化星期几的选中状态
        checkBoxes = new ArrayList<>();
        checkBoxes.add(checkboxYi);
        checkBoxes.add(checkboxEr);
        checkBoxes.add(checkboxSan);
        checkBoxes.add(checkboxSi);
        checkBoxes.add(checkboxWu);
        checkBoxes.add(checkboxLiu);
        checkBoxes.add(checkboxRi);
        // 循环遍历从服务器上获取的数据 然后 设置状态
    }

    private void initView() {
        // TODO title名称
        pumptowTitle.setText(setTites());
        adapterRecycler = new PumpTowStiingRecyclerAdapter(this);
        mMabager = new LinearLayoutManager(this);
        mMabager.setOrientation(LinearLayoutManager.VERTICAL);
        pumptowRecycler.setLayoutManager(mMabager);
        pumptowRecycler.setAdapter(adapterRecycler);
        adapterRecycler.setOnRecyclerItem(this);
        adapterRecycler.setOnListenerItem(new PumpTowStiingRecyclerAdapter.OnListenerItem() {
            @Override
            public void OnListener(int i) {
                item = i;
            }
        });
        checkboxListener();
    }

    @OnClick({R.id.pumptow_back, R.id.preservation, R.id.pumptow_add, R.id.cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pumptow_back:
                finish();
                break;
            case R.id.preservation:
                // 提交设置的参数
                commtionStiing();
                break;
            case R.id.pumptow_add:
                // 添加任务
                addAssignment();
                break;
            case R.id.cancel:
                finish();
                break;
        }
    }

    private void commtionStiing() {

        params.clear();
        params.put("type", type);
        List<PumpSttingTimeBean> beanList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lists.size(); i++) {
            if (i == lists.size() - 1) {
                sb.append(lists.get(i));
            } else {
                sb.append(lists.get(i));
                sb.append(",");
            }
        }

        String week = sb.toString().trim();
        for (int i = 0; i < item; i++) {
            View view = mMabager.findViewByPosition(i);
            LinearLayout layout = (LinearLayout) view;
            TextView textView = (TextView) layout.findViewById(R.id.pumptow_item_tv_start);
            EditText editText = (EditText) layout.findViewById(R.id.pumptow_item_edtime);

            String start = textView.getText().toString().trim();// 开始时间需要切割字符串
            String edtime = editText.getText().toString().trim();// 结束时间
            String startTime[] = start.split(":");
            // 获取每个item时间转成json字符串

            PumpSttingTimeBean bean = new PumpSttingTimeBean();

            bean.setDid(id);
            if (seeBarList != null) {
                 // 亮度
            } else {
                bean.setLightness("");
            }

            if (type==KeyUtils.TYPE_D1){
                SeekBar seekBar = (SeekBar) layout.findViewById(R.id.seekbar);
                bean.setLightness(String.valueOf(seekBar.getProgress()));
            }

            bean.setWeek(week);
            bean.setHours(startTime[0]);
            bean.setMinute(startTime[1]);
            bean.setDuration(edtime);
            beanList.add(bean);
        }

        Gson gson = new Gson();
        Type type = new TypeToken<List<PumpSttingTimeBean>>() {
        }.getType();
        jsonList = gson.toJson(beanList, type);
        LogUtils.e(jsonList);


        params.put("str", jsonList);
        NetworkReuset.getInstance().PostReuset(RequestUrls.TIMING, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                Gson gson = new Gson();
                CodeBean codeBean = gson.fromJson(s, CodeBean.class);
                switch (codeBean.getH().getS()) {
                    case 1:

                        automaticOff();
                        break;
                    case 2:
                        MyToast.show(PumpTowStiingActivity.this, "设备不存在");
                        break;
                    case 3:
                        MyToast.show(PumpTowStiingActivity.this, "设备离线状态");
                        break;
                }
            }
        });
    }


    private void automaticOff() {
        HttpParams params = new HttpParams();
        params.put("did",id);
        params.put("open",1);
        params.put("type",type);
        NetworkReuset.getInstance().PostReuset(RequestUrls.AUTOMATICSWICTH, params, new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

                Gson gson = new Gson();
                CodeBean codeBean = gson.fromJson(s, CodeBean.class);
                int s1 = codeBean.getH().getS();
                if (s1==1){
                    MyToast.show(PumpTowStiingActivity.this, "设置成功");
                    finish();
                }

            }
        });

    }


    private void addAssignment() {
        ++item;
        if (item >= 10) {
            MyToast.show(PumpTowStiingActivity.this, "不可再添加任务");
            return;
        }
        beanList.add(new PumpStiingBean("6:00", "5", 0));
        adapterRecycler.addItem(beanList);
    }

    // 星期几
    private void checkboxListener() {
        checkboxYi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lists.add("1");
                } else {
                    removeData("1");
                }
            }
        });

        checkboxEr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lists.add("2");
                } else {
                    removeData("2");
                }
            }
        });
        checkboxSan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lists.add("3");
                } else {
                    removeData("3");
                }
            }
        });
        checkboxSi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lists.add("4");
                } else {
                    removeData("4");
                }
            }
        });
        checkboxWu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lists.add("5");
                } else {
                    removeData("5");
                }
            }
        });
        checkboxLiu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lists.add("6");
                } else {
                    removeData("6");
                }
            }
        });
        checkboxRi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lists.add("7");
                } else {
                    removeData("7");
                }
            }
        });
    }

    private void removeData(String week) {
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).equals(week)) {
                lists.remove(i);
            }
        }
    }

    private String setTites() {
        switch (SPUtils.getInt(KeyUtils.EQUIPMENTTYPE)) {
            case 4:
                title = "智能自动浇水泵设置";
                type = KeyUtils.TYPE_B1;

                break;
            case 5:
                type = KeyUtils.TYPE_F1;
                title = "智能自动浇灌阀设置";

                break;
            case 1:
                title = "Ciao园叮检测仪";
                break;
            case 6:
                type = KeyUtils.TYPE_D1;
                title = "智能AI植物灯设置";
                tvRenwuMingchen.setText("设置重复光照设置");
                break;
        }
        return title;
    }

    @Override
    public void onRecyclerText(List<String> seeBarList) {
        this.seeBarList = seeBarList;
    }
}
