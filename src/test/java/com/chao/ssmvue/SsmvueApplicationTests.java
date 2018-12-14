package com.chao.ssmvue;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.chao.ssmvue.base.security.entity.User;
import com.chao.ssmvue.base.security.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SsmvueApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        Page page = new Page(1,1);
        List<User> list = (List<User>) userMapper.selectPage(page,null);
        System.out.println(page);
        System.out.println(page);
        System.out.println(list);
    }

    //代码自动生成
    public static void main(String[] args) {
        GlobalConfig globalConfig = new GlobalConfig();
        //全局的配置
        globalConfig.setActiveRecord(true)
                .setAuthor("LuZichao") //作者
                .setOutputDir("F:\\IdeaProject\\ssmvue\\src\\main\\java") //生成路径
                .setFileOverride(true) //文件覆盖
                .setIdType(IdType.AUTO) //主键策略
                .setActiveRecord(false) //是否开启AR模式
                .setEnableCache(false) //是否在xml开启二级缓存
                .setServiceName("%sService")
                .setBaseResultMap(true) //结果映射
                .setBaseColumnList(true); //基本的sql片段

        //数据源的配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://127.0.0.1:3306/ssmvue?useUnicode=true&characterEncoding=utf8")
                .setUsername("root")
                .setPassword("luzichao123");

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true) //全局大小写
               // .setd  //指定数据库表名是否使用下划线
                .setEntityColumnConstant(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setLogicDeleteFieldName("status")
                .setInclude("user", "role", "menu", "shortcut","role_menu","user_role"); //要生成的表

        //包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.chao.ssmvue.base.business")
                .setMapper("mapper")
                .setController("controller")
                .setService("service")
                .setEntity("entity")
                .setXml("mapper");

        //整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);

        //执行
        autoGenerator.execute();


    }

}
