package net.sharplab.epubtranslator.core.driver.translator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.sharplab.epubtranslator.core.service.EPubTranslatorServiceImpl;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class DeepLTranslator implements Translator {

    private static final String ENDPOINT = "https://api.deepl.com/v2/translate";

    private final static List<String> IGNORE_ELEMENT_NAMES = Collections.unmodifiableList(Arrays.asList(
            "abbr", "b", "cite", "code", "data", "dfn",
            "kbd", "rp", "rt", "rtc", "ruby", "samp", "time", "var"));

    private RestTemplate restTemplate;
    private String apiKey;

    public DeepLTranslator(RestTemplate restTemplate, String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    @Override
    public List<String> translate(List<String> texts, String srcLang, String dstLang) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType(MediaType.APPLICATION_FORM_URLENCODED, StandardCharsets.UTF_8));
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("auth_key", apiKey);
        parameters.add("source_lang", srcLang);
        parameters.add("target_lang", dstLang);
        parameters.add("non_splitting_tags", String.join(",", EPubTranslatorServiceImpl.INLINE_ELEMENT_NAMES));
        parameters.add("ignore_tags", String.join(",", IGNORE_ELEMENT_NAMES));
        for (String text: texts) {
            parameters.add("text", text);
        }
        parameters.add("tag_handling", "xml");
        RequestEntity<?> requestEntity = new RequestEntity<>(parameters, httpHeaders, HttpMethod.POST, URI.create(ENDPOINT));

        ResponseEntity<DeepLTranslationResponse> response = restTemplate.exchange(requestEntity, DeepLTranslationResponse.class);
        return Objects.requireNonNull(response.getBody()).getTranslations().stream().map(DeepLTranslationResponse.Translation::getText).collect(Collectors.toList());
    }

    static class DeepLTranslationResponse {

        private List<Translation> translations;

        @JsonCreator
        public DeepLTranslationResponse(@JsonProperty("translations") List<Translation> translations) {
            this.translations = translations;
        }

        @JsonGetter("translations")
        public List<Translation> getTranslations() {
            return translations;
        }

        static class Translation {

            private String text;

            @JsonCreator
            public Translation(@JsonProperty("text") String text) {
                this.text = text;
            }

            @JsonGetter("text")
            public String getText() {
                return text;
            }
        }
    }
}
