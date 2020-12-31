package club.banyaun.jwtsec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author : WangYB
 * @time: 2020/12/24  19:17
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    /**
     *消息转换器，防止中文乱码
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        super.configureMessageConverters(converters);
        //防止中文乱码
        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
    }

}
