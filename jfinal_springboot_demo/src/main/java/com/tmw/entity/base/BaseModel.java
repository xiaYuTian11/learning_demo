package com.tmw.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * @author TMW
 * @since 2020/3/25 10:09
 */
public class BaseModel<M extends BaseModel<M>> extends Model<M> implements IBean {

    public M setId(java.lang.Integer id) {
        set("id", id);
        return (M) this;
    }

    public java.lang.Integer getId() {
        return getInt("id");
    }

    public M setName(java.lang.String name) {
        set("name", name);
        return (M) this;
    }

    public java.lang.String getName() {
        return getStr("name");
    }

    public M setPassword(java.lang.String name) {
        set("password", name);
        return (M) this;
    }

    public java.lang.String getPassword() {
        return getStr("password");
    }

}
