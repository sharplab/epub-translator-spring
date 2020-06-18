package net.sharplab.epubtranslator.core.service;

import net.sharplab.epubtranslator.core.model.EPubFile;
import org.springframework.stereotype.Service;

@Service
public interface EPubTranslatorService {

    EPubFile translate(EPubFile ePubFile, String srcLang, String dstLang);

}
