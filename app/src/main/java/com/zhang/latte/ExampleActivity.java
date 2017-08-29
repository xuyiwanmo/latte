package com.zhang.latte;

import com.zhang.latte_core.activities.ProxyActivity;
import com.zhang.latte_core.delegate.LatteDelagate;

/**
 * Created by 德医互联 on 2017/8/29.
 */

public class ExampleActivity extends ProxyActivity {
    @Override
    public LatteDelagate setRootDelegate() {
        return new ExampleDelegate();
    }
}
