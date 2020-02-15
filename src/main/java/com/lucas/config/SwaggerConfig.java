package com.lucas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2//开启swagger2
public class SwaggerConfig {
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("lucas2");
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("lucas3");
    }

    @Bean
    public Docket docket4(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("lucas4");
    }
    //配置swagger的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的swagger环境
        Profiles profiles=Profiles.of("dev","test");

        //只希望swagger在生产环境中使用，在发布环境中不使用
        //获取项目的环境；
        //获取的flag可知道项目的环境,然后设置enable
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2).groupName("lucas1")
                .apiInfo(apiInfo())
                /*
               enable是指关掉 swagger，则swagger指的是不能在浏览器中使用了。
                 */
                .enable(flag)
                .select()
                /*
                RequestHandlerSelectors，配置要扫描接口的方式
                RequestHandlerSelectors.any()：扫描报的全部
                RequestHandlerSelectors.basePackage("com.lucas.controller")扫描指定的包
                RequestHandlerSelectors.none()都不扫描
                RequestHandlerSelectors.withClassAnnotation扫描类上的注解:例如：
                RequestHandlerSelectors.withClassAnnotation(RestController.class)GetMapping
                RequestHandlerSelectors.withMethodAnnotation扫描方法上的注解，例如:
                RequestHandlerSelectors.withMethodAnnotation(GetMapping.class)
                 */
                .apis(RequestHandlerSelectors.basePackage("com.lucas.controller"))
                /*
                PathSelectors.ant("/lucas/**")，指的是只扫描路径lucas下的所有接口。
                (注释掉了)
                 */
                //.paths(PathSelectors.ant("/lucas/**"))
                .build();

    }
    //配置swagger信息
    /*
    这里的信息就是 http://localhost:8080/swagger-ui.html里面的swagger信息
     */
    private ApiInfo apiInfo(){
        Contact DEFAULT_CONTACT = new Contact("lucas", "www.baidu.com", "980631161@qq.com");
        return new ApiInfo(

                "lucas的swagger api文档",
                "Api Documentation by lucas",
                "1.0",
                "urn:tos",
                DEFAULT_CONTACT,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());

    }
}
