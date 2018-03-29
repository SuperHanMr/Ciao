package com.example.hanyonghui.ciao.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hanyonghui.ciao.R;
import com.example.hanyonghui.ciao.bean.bean.PumpStiingBean;
import com.example.hanyonghui.ciao.utils.MyToast;
import com.example.hanyonghui.ciao.view.adapter.DengRenuAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/12/10.
 * Holle Android
 * <p>
 * 灯任务设置
 * 6：00 起始时间A(0-23) 结束时间 B(24-A) 18:4
 * 起始时间 B            结束时间 C(24-B)22 :2
 * 9
 */


public class DengMissionSttingActivity extends AppCompatActivity {


    @BindView(R.id.pumptow_recycler)
    RecyclerView pumptowRecycler;

    private int item;
    private List<PumpStiingBean> beanList;
    private DengRenuAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deng_renwu);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        beanList = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        pumptowRecycler.setLayoutManager(manager);

        adapter = new DengRenuAdapter(this);

        pumptowRecycler.setAdapter(adapter);

        adapter.getSeekBar(new DengRenuAdapter.OnIntemSeekBar() {
            @Override
            public void onIntemNum(int seekbar) {
                MyToast.massageToast(DengMissionSttingActivity.this,seekbar);
            }
        });


    }


    private void addAssignment() {
        ++item;
        if (item >= 10) {
            MyToast.show(this, "不可再添加任务");
            return;
        }
        beanList.add(new PumpStiingBean("6:00", "5",0));
        adapter.addItem(beanList);
    }


    @OnClick({R.id.deng_ren_back, R.id.preservation, R.id.cancel,R.id.pumptow_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.deng_ren_back:
                finish();
                break;
            case R.id.preservation:
                MyToast.show(this,"提交成功");
                finish();
                break;
            case R.id.cancel:
                finish();
                break;
            case R.id.pumptow_add:
                addAssignment();
                break;
        }
    }
}
