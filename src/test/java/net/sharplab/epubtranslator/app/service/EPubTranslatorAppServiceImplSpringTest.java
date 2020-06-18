package net.sharplab.epubtranslator.app.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EPubTranslatorAppServiceImplSpringTest {

    @TempDir
    Path tempDir;

    @Autowired
    EPubTranslatorAppServiceImpl target;

    @Disabled
    @Test
    void test() throws IOException {
        File srcFile = new ClassPathResource("/FileFixtures/webauthn4j-intro.en.epub").getFile();
        File dstFile = Files.createTempFile(tempDir, "test", ".epub").toFile();

        target.translateEPubFile(new EPubTranslateParameters(srcFile, dstFile, "en", "ja"));
        assertThat(dstFile).exists();
    }

}
