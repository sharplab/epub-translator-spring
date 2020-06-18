package net.sharplab.epubtranslator.app.config;

import net.sharplab.epubtranslator.app.EPubTranslatorSetting;
import net.sharplab.epubtranslator.core.driver.translator.DeepLTranslator;
import net.sharplab.epubtranslator.core.driver.translator.Translator;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(value = "net.sharplab.epubtranslator.core")
public class AppConfig {

    public AppConfig(EPubTranslatorSetting ePubTranslatorSetting) {
        this.ePubTranslatorSetting = ePubTranslatorSetting;
    }

    private EPubTranslatorSetting ePubTranslatorSetting;

    @Bean
    Translator translator(RestTemplate restTemplate){
        return new DeepLTranslator(restTemplate, ePubTranslatorSetting.getApiKey());
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplateBuilder().build();
    }
}
