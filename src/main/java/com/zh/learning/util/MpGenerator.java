package com.zh.learning.util;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.baomidou.mybatisplus.generator.config.rules.DateType.ONLY_DATE;

/**
 * @author zh
 * @version 1.0
 * @date 2020/11/2 11:33
 */
public class MpGenerator {
    private static String basePackage = "com.zh.learning";
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("zh");
        gc.setOpen(false);
        gc.setDateType(ONLY_DATE);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        gc.setEntityName("%sPo");
        gc.setMapperName("%sDao");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://10.0.0.74:3306/honeypot_security?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("goodteam");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig() {
            @Override
            public String getParent() {
                return basePackage;
            }
        };
        pc.setModuleName(scanner("模块名"));
        pc.setParent(basePackage);
        pc.setEntity("entity.po");
        pc.setMapper("dao");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        // entity
        focList.add(new FileOutConfig("/cust_template/entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath  + "/src/main/java/" + basePackage.replace(".", "/")
                        + "/entity/po/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });
        // mapper xml
        focList.add(new FileOutConfig("/cust_template/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath +"/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getMapperName() + StringPool.DOT_XML;
            }
        });
        // dao
        focList.add(new FileOutConfig("/cust_template/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/" + basePackage.replace(".", "/")
                        + "/dao/" + pc.getModuleName()
                        + "/" + tableInfo.getMapperName() + StringPool.DOT_JAVA;
            }
        });
        // service
        focList.add(new FileOutConfig("/cust_template/service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/" + basePackage.replace(".", "/")
                        + "/service/" + pc.getModuleName()
                        + "/" + tableInfo.getServiceName() + StringPool.DOT_JAVA;
            }
        });
        // serviceImpl
        focList.add(new FileOutConfig("/cust_template/serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath +  "/src/main/java/" + basePackage.replace(".", "/")
                        + "/service/impl/" + pc.getModuleName()
                        + "/" + tableInfo.getServiceImplName() + StringPool.DOT_JAVA;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setMapper(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        templateConfig.setController(null);
        templateConfig.setEntity(null);
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
