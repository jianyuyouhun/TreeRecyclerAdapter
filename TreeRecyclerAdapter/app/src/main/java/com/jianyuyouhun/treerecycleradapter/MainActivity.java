package com.jianyuyouhun.treerecycleradapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jianyuyouhun.treerecycleradapter.adapter.TreeAdapter;
import com.jianyuyouhun.treerecycleradapter.entity.ParentEntity;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TreeAdapter adapter;
    List list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
    public void initView(){
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));//间距设置，完全copy了别人的代码。。
        recyclerView.setItemAnimator(new SlideInUpAnimator());//这是一个开源的动画效果，非常棒的哦
        list = new ArrayList();
        adapter = new TreeAdapter(this, list, R.layout.layout_treerecycler_item,
                new int[]{R.id.parent_name, R.id.child_name});
        //这里的点击事件很重要
        adapter.setOnItemClickLitener(new TreeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (list.get(position) instanceof ParentEntity){//判断是否为父
                    ParentEntity parent = (ParentEntity) list.get(position);
                    if ((position + 1) == list.size()) {//判断是否为最后一个元素
                        adapter.addAllChild(parent.getChildren(), position + 1);
                    } else {
                        if (list.get(position + 1) instanceof ParentEntity) {//如果是父则表示为折叠状态需要添加儿子
                            adapter.addAllChild(parent.getChildren(), position + 1);
                        } else if (list.get(position + 1) instanceof ParentEntity.ChildEntity) {//如果是儿子则表示为展开状态需要删除儿子
                            adapter.deleteAllChild(position + 1, parent.getChildren().size());
                        }
                    }
                }else {//是儿子你想干啥就干啥吧
                    ParentEntity.ChildEntity child = (ParentEntity.ChildEntity) list.get(position);
                    Toast.makeText(getApplicationContext(), child.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    public void initData(){
        for (int i = 0; i < 10; i++){
            ParentEntity parent = new ParentEntity();
            parent.setId(i);
            parent.setName("我是父" + i);
            List<ParentEntity.ChildEntity> children = new ArrayList<>();
            for (int j = 0; j < 4; j++){
                ParentEntity.ChildEntity child = new ParentEntity.ChildEntity();
                child.setId(j);
                child.setName("我是父"+i+"的儿子" + j);
                children.add(child);
            }
            parent.setChildren(children);
            list.add(parent);
        }
        adapter.notifyDataSetChanged();
    }

}
