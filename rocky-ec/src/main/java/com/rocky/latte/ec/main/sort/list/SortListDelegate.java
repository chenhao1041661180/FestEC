package com.rocky.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.core.net.RestClient;
import com.rocky.latte.core.net.callback.ResponseCallback;
import com.rocky.latte.ec.R;
import com.rocky.latte.ec.R2;
import com.rocky.latte.ec.main.sort.SortDelegate;
import com.rocky.latte.ui.recycler.MultipleItemEntity;

import java.util.List;
import butterknife.BindView;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/20
 */

public class SortListDelegate extends LatteDelegate {
    @BindView(R2.id.rv_sort_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort_list;
    }

    private void init() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        init();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        RestClient.builder()
                .url("mock/data/sort_list_data.json")
                .loader(getContext())
                .callback(new ResponseCallback() {
                    @Override
                    public void onSuccess(String response) {
//                        LatteLogger.i("xx",response);
                        final List<MultipleItemEntity> data =
                                new SortListDataConverter().setJsonData(response).convert();
                        final SortDelegate delegate = getParentDelegate();
                        final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                        mRecyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailed() {

                    }

                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
