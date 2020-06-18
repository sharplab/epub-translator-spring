package net.sharplab.epubtranslator.app.service;

import net.sharplab.epubtranslator.core.provider.epub.DefaultEPubContentFileProvider;
import net.sharplab.epubtranslator.core.provider.epub.EPubChapterProvider;
import net.sharplab.epubtranslator.core.service.EPubTranslatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class EPubTranslatorAppServiceImplTest {

    @TempDir
    Path tempDir;

    @Test
    void test() throws IOException {
        File srcFile = new ClassPathResource("/FileFixtures/webauthn4j-intro.en.epub").getFile();
        File dstFile = Files.createTempFile(tempDir, "test", ".epub").toFile();

        EPubTranslatorService ePubTranslatorService = (ePubFile, srcLang, dstLang) -> ePubFile;
        EPubTranslatorAppServiceImpl target = new EPubTranslatorAppServiceImpl(ePubTranslatorService, Arrays.asList(new DefaultEPubContentFileProvider(), new EPubChapterProvider()));
        target.translateEPubFile(new EPubTranslateParameters(srcFile, dstFile, "en", "ja"));
        assertThat(dstFile).exists();
    }

}