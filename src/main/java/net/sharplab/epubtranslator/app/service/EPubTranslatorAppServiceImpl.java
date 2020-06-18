package net.sharplab.epubtranslator.app.service;

import net.sharplab.epubtranslator.core.driver.epub.EPubReader;
import net.sharplab.epubtranslator.core.driver.epub.EPubWriter;
import net.sharplab.epubtranslator.core.model.EPubFile;
import net.sharplab.epubtranslator.core.provider.epub.EPubContentFileProvider;
import net.sharplab.epubtranslator.core.service.EPubTranslatorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EPubTranslatorAppServiceImpl implements EPubTranslatorAppService {

    private final EPubTranslatorService ePubTranslatorService;
    private final EPubReader ePubReader;
    private final EPubWriter ePubWriter;

    public EPubTranslatorAppServiceImpl(EPubTranslatorService ePubTranslatorService, List<EPubContentFileProvider> ePubContentFileProviders) {
        this.ePubTranslatorService = ePubTranslatorService;
        this.ePubReader = new EPubReader(ePubContentFileProviders);
        this.ePubWriter = new EPubWriter();
    }

    @Override
    public void translateEPubFile(EPubTranslateParameters ePubTranslateParameters){
        EPubFile ePubFile = ePubReader.read(ePubTranslateParameters.srcFile);
        EPubFile translated = ePubTranslatorService.translate(ePubFile, ePubTranslateParameters.getSrcLang(), ePubTranslateParameters.getDstLang());
        ePubWriter.write(translated, ePubTranslateParameters.dstFile);
    }
}
