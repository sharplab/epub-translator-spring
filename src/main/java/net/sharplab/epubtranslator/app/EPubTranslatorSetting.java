package net.sharplab.epubtranslator.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EPubTranslatorSetting {

    @Value("${ePubTranslator.language.source}")
    private String defaultSrcLang;
    @Value("${ePubTranslator.language.destination}")
    private String defaultDstLang;

    @Value("${ePubTranslator.deepL.apiKey}")
    private String deepLApiKey;

    public String getDefaultSrcLang() {
        return defaultSrcLang;
    }

    public String getDefaultDstLang() {
        return defaultDstLang;
    }

    public String getApiKey() {
        return deepLApiKey;
    }
}
