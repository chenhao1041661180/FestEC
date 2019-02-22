package com.rocky.latte.ec.main.sort.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.rocky.latte.core.delegates.LatteDelegate;
import com.rocky.latte.core.net.RestClient;
import com.rocky.latte.core.net.callback.ResponseCallback;
import com.rocky.latte.ec.R;
import com.rocky.latte.ec.R2;

import java.util.List;

import butterknife.BindView;

/**
 * 模块说明：
 *
 * @author 陈浩
 * @date 2019/2/20
 */

public class SortContentDelegate extends LatteDelegate {
    @BindView(R2.id.rv_sort_content)
    RecyclerView mRecycleView = null;
    private static final String ARG_CONTENT_ID = "CONTENT_ID";
    private int mContentId = -1;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sort_content;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mContentId = args.getInt(ARG_CONTENT_ID);
        }
    }

    public static SortContentDelegate newInstance(int contentId) {
        final Bundle bundle = new Bundle();
        bundle.putInt(ARG_CONTENT_ID, contentId);
        SortContentDelegate sortContentDelegate = new SortContentDelegate();
        sortContentDelegate.setArguments(bundle);
        return sortContentDelegate;
    }

    private void initData() {
        RestClient.builder()
                .url("mock/data/sort_content_data_1.json?contentId=" + mContentId)
                .callback(new ResponseCallback() {
                    @Override
                    public void onSuccess(String message) {
                        List<SectionBean> lists = SectionDataConverter.convert(message);
                        final SectionContentAdapter adapter = new SectionContentAdapter(R.layout.item_section_content,
                                R.layout.item_section_header,lists);
                        mRecycleView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailed() {

                    }

                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build().get();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(manager);
        initData();
    }
}
