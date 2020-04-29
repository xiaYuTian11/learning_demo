package com.tmw.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Plugins;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;
import com.sojpt.boot.ActiveRecordPluginProperties;
import com.sojpt.boot.SoJptConfig;
import com.tmw.mapping.JFinalMappingKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.sql.Connection;

/**
 * @author TMW
 * @since 2020/3/24 21:40
 */
@Configuration(value = "JFinalConfig")
public class JFinalConfig extends SoJptConfig {

    @Autowired
    private ActiveRecordPluginProperties arpProperties;
    @Value("${arp.driver}")
    private String dbDriver;
    @Autowired
    private Environment environment;

    @Override
    public void configConstant(Constants constants) {
        // 设置当前是否为开发模式
        constants.setDevMode(arpProperties.getIsDevMode());
    }

    @Override
    public void configEngine(Engine engine) {

    }

    @Override
    public void configPlugin(Plugins plugins) {
        DruidPlugin dp = new DruidPlugin(arpProperties.getJdbcUrl(), arpProperties.getUsername(), arpProperties.getPassword());

        dp.addFilter(new StatFilter());
        // dp.setDriverClass("com.mysql.cj.jdbc.Driver");
        // dp.setDriverClass(dbDriver);
        dp.setDriverClass(environment.getProperty("arp.driver"));
        WallFilter wall = new WallFilter();
        dp.addFilter(wall);
        plugins.add(dp);


        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.setTransactionLevel(Connection.TRANSACTION_READ_COMMITTED);
        arp.setShowSql(arpProperties.getIsDevMode());
        arp.setDialect(new MysqlDialect());

        // ******** 在此添加dao层sql文件 *********//*
        //arp.addSqlTemplate("sql/all_sqls.sql");

        JFinalMappingKit.mapping(arp);

        // 初始化任务调度插件,参数为配置文件名
        // me.add(new Cron4jPlugin(sysProp));

        plugins.add(arp);
    }

    @Override
    public void configInterceptor(Interceptors interceptors) {

    }

    @Override
    public void configHandler(Handlers handlers) {

    }
}
