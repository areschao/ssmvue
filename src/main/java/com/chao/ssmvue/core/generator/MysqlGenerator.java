package com.chao.ssmvue.core.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class MysqlGenerator {

    /**
     * 包名
     */
    private static final String PACKAGE_NAME = "com.chao.ssmvue.base.business";

    /**
     * 模块名称
     */
    private static final String MODULE_NAME = "log";

    /**
     * 要生成的表名
     */
    private static final String[] AUTO_TABLES = {
            "log"
//            "user", "role", "menu", "shortcut", "role_menu", "user_role"
    };

    /**
     * 输出文件的路径
     */
    private static final String OUT_PATH = "F:\\IdeaProject\\ssmvue\\src\\main\\java";
    /**
     * 代码生成者
     */
    private static final String AUTHOR = "LuZichao";

    /**
     * 逻辑删除字段
     */
    private static final String STATUS = "status";

    /**
     * JDBC相关配置
     */
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/ssmvue?useUnicode=true&characterEncoding=utf8";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "luzichao123";


    public static void main(String[] args) {

        assert (false); //使用前注释这里

        //定义公共填充字段
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        TableFill createField = new TableFill("createTime", FieldFill.INSERT);  //创建时间
        tableFillList.add(createField);

        GlobalConfig globalConfig = new GlobalConfig();
        //全局的配置
        globalConfig.setActiveRecord(true)
                .setAuthor(AUTHOR) //作者
                .setOutputDir(OUT_PATH) //生成路径
                .setFileOverride(false) //文件覆盖 ********这里为了不要误操作
                .setIdType(IdType.AUTO) //主键策略
                .setActiveRecord(false) //是否开启AR模式
                .setEnableCache(false) //是否在xml开启二级缓存
                .setServiceName("%sService")// 自定义文件命名， %s 会自动填充表实体属性！
                .setBaseResultMap(true) //结果映射
                .setBaseColumnList(true); //基本的sql片段

        //数据源的配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setDriverName(DRIVER)
                .setUrl(URL)
                .setUsername(USER_NAME)
                .setPassword(PASSWORD)
                // 自定义数据库表字段类型转换【可选】
                .setTypeConvert(new MySqlTypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        System.out.println("转换类型：" + fieldType);
                        // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                        // return DbColumnType.BOOLEAN;
                        // }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                });

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true) //全局大小写
                  //指定数据库表名是否使用下划线
                .setEntityColumnConstant(true) //实体是否生成字段常量
                .setSuperEntityColumns("id","create_time")//自定义公共字段的基础类
                .setNaming(NamingStrategy.underline_to_camel) //定义表字段转换成实体类生成方式
                .setLogicDeleteFieldName(STATUS) //逻辑删除字段
                .setTableFillList(tableFillList) // 公共字段填充位置
                // 自定义实体父类
                 .setSuperEntityClass("com.chao.ssmvue.core.entity.BaseEntity")
                // // 自定义 mapper 父类
                // .setSuperMapperClass("")
                // // 自定义 service 父类
                // .setSuperServiceClass("")
                 // 自定义 service 实现类父类
                 .setSuperServiceImplClass("com.chao.ssmvue.core.service.MyServiceImpl")
                // 自定义 controller 父类
                // .setSuperControllerClass("")
                // 是否为lombok模型
                .setEntityLombokModel(true)
                //生成RestController 控制器
                .setRestControllerStyle(true)
                .setInclude(AUTO_TABLES); //要生成的表

        //包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(PACKAGE_NAME)
                .setModuleName(MODULE_NAME)
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
