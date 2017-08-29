package com.zhang.latte_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.zhang.latte_core.R;
import com.zhang.latte_core.delegate.LatteDelagate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 容器Activity
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelagate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void  initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container=new ContentFrameLayout(this);
        container.setId(R.id.delegateId);
        setContentView(container);
        if (savedInstanceState==null) {
            loadRootFragment(R.id.delegateId,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //这两个方法不一定执行+
        System.gc();
        System.runFinalization();
    }
}
