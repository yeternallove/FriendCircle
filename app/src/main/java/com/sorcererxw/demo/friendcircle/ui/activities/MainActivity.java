package com.sorcererxw.demo.friendcircle.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sorcererxw.demo.friendcircle.R;
import com.sorcererxw.demo.friendcircle.models.GeneralBean;
import com.sorcererxw.demo.friendcircle.models.HeadBean;
import com.sorcererxw.demo.friendcircle.ui.adapters.FriendCircleAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FriendCircleAdapter adapter = new FriendCircleAdapter(this,
                new HeadBean(
                        "https://s-media-cache-ak0.pinimg.com/564x/a7/7c/11/a77c11589d8be49f9bad3cd52778a176.jpg",
                        "https://avatars3.githubusercontent.com/u/11501524?v=3&s=466",
                        "SorcererXW"),
                Arrays.asList(
                        new GeneralBean(
                                "http://uupaper.oss-cn-qingdao.aliyuncs.com/b6eee5a620d6e14f4f8e5786f24244f7.jpeg",
                                "Jack",
                                "lala",
                                new ArrayList<String>(),
                                new Date(System.currentTimeMillis()-2000000000)),
                        new GeneralBean(
                                "http://uupaper.oss-cn-qingdao.aliyuncs.com/9c5b17a57bbf9c3279f9e2faf3b3e118.jpeg",
                                "Nina",
                                "lala",
                                new ArrayList<String>(),
                                new Date())
                ));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
    }
}
