package com.tmw.mapping;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.tmw.entity.User;

/**
 * @author TMW
 * @since 2020/3/24 21:44
 */
public class JFinalMappingKit {
    public static void mapping(ActiveRecordPlugin arp) {
        arp.addMapping("user", "id", User.class);
    }
}
