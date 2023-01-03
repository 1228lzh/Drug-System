package com.zh.drug.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * @Author: ZH
 * @Date: 2022-12-28 23:18
 */
public class MpGenerator {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/drug?characterEncoding=utf-8&useSSL=false";
        String username="root";
        String password="123456";
        FastAutoGenerator.create(url, username, password)
                // 全局配置
                .globalConfig(builder -> {
                    builder.author("lzh") // 设置作者
                            .fileOverride() //3.5.3已弃用，若要使用可回退至3.5.1
                            .disableOpenDir() //禁止打开输出目录
                            .outputDir("E:\\课程\\大三\\数据库课设\\drug3\\drug\\src\\main\\java\\"); // 指定输出目录
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.zh.drug") // 设置父包名
                            //.moduleName("drug") // 设置父包模块名
                            .entity("pojo")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\课程\\大三\\数据库课设\\drug3\\drug\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok(); //启动lombok
                    builder.mapperBuilder().enableMapperAnnotation().build(); //启用@mapper注释
                    builder.controllerBuilder().enableHyphenStyle().enableRestStyle(); //启用驼峰转连字符样式
                    builder.controllerBuilder().enableFileOverride();// 3.5.3 指定覆盖已生成文件
                    builder.entityBuilder().enableFileOverride();
                    builder.serviceBuilder().enableFileOverride();
                    builder.mapperBuilder().enableFileOverride();
                    builder.addInclude("warehouse") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀

                })
                .templateConfig(builder -> {
                    builder.controller("/templates/controller.java.vm");
                })
                // 模版配置
                //.templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
