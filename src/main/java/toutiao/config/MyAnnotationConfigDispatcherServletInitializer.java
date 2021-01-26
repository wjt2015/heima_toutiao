package toutiao.config;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import toutiao.filter.MyCorsFilter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.EnumSet;


@Slf4j
public class MyAnnotationConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ServiceConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"*.json"};
    }

    @Override
    protected Filter[] getServletFilters() {
        /**
         * 添加corsFilter;
         */
/*        CorsConfigurationSource corsConfigurationSource = new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                if (CorsUtils.isCorsRequest(request)) {
                    corsConfiguration.setAllowedOrigins(Lists.newArrayList(CorsConfiguration.ALL));
                    return corsConfiguration;
                }
                return null;
            }
        };

        MyCorsFilter corsFilter = new MyCorsFilter();
        return new Filter[]{corsFilter};
        */

        Filter[] filters = new Filter[]{new MyCorsFilter()};
        log.info("filters={};", filters);
        return filters;
    }

    @Override
    protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
        FilterRegistration.Dynamic dynamic = super.registerServletFilter(servletContext, filter);
        dynamic.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "*");
        return dynamic;
    }

}
