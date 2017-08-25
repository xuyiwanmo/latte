package com.zhang.latte_ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by 德医互联 on 2017/8/25.
 */

public enum  EcIcons implements Icon{
    icon_scan('\ue606'),
    icon_ali_pay('\ue606');
    private char character;

    private EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace("_","-");
    }

    @Override
    public char character() {
        return 0;
    }
}
