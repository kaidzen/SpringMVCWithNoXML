package org.hardcrystal.springmvc.configuration;

import org.hardcrystal.springmvc.converter.RoleToUserProfileConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by Administrator on 21.01.2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.hardcrystal.springmvc")
public class AppConfig extends WebMvcConfigurerAdapter{

    @Autowired
    RoleToUserProfileConverter roleToUserProfileConverter;

    /**
     * Configure ViewResolvers to deliver preferred views
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }


}
