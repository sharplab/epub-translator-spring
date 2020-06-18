package net.sharplab.epubtranslator.app.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import net.sharplab.epubtranslator.app.EPubTranslatorSetting;
import net.sharplab.epubtranslator.app.service.EPubTranslateParameters;
import net.sharplab.epubtranslator.app.service.EPubTranslatorAppService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.File;



@Configuration
public class EPubTranslatorCommandLineRunner implements CommandLineRunner {

    private EPubTranslatorAppService ePubTranslatorAppService;
    private EPubTranslatorSetting ePubTranslatorSetting;

    public EPubTranslatorCommandLineRunner(EPubTranslatorAppService ePubTranslatorAppService, EPubTranslatorSetting ePubTranslatorSetting) {
        this.ePubTranslatorAppService = ePubTranslatorAppService;
        this.ePubTranslatorSetting = ePubTranslatorSetting;
    }

    @Override
    public void run(String... args) {
        CliArgs cliArgs = parseArgs(args);
        if(cliArgs == null){
            return;
        }
        translate(cliArgs);
    }

    CliArgs parseArgs(String[] args){
        CliArgs cliArgs = new CliArgs();
        JCommander jCommander =
                JCommander.newBuilder()
                        .addObject(cliArgs)
                        .build();
        try{
            jCommander.parse(args);
        }
        catch (ParameterException ex){
            System.out.println(ex.getMessage());
            ex.usage();
            return null;
        }
        if(cliArgs.isHelp()){
            jCommander.usage();
            return null;
        }

        if(cliArgs.getSrcLang() == null){
            cliArgs.setSrcLang(ePubTranslatorSetting.getDefaultSrcLang());
        }
        if(cliArgs.getDstLang() == null){
            cliArgs.setDstLang(ePubTranslatorSetting.getDefaultDstLang());
        }
        if(cliArgs.getDst() == null){
            File dst = constructDstFileFromSrcFile(cliArgs.getSrc(), cliArgs.getDstLang());
            cliArgs.setDst(dst);
        }
        return cliArgs;
    }

    /**
     * translates an epub file
     * @param cliArgs command line arguments
     */
    void translate(CliArgs cliArgs){
        EPubTranslateParameters ePubTranslateParameters =
                new EPubTranslateParameters(cliArgs.getSrc(), cliArgs.getDst(), cliArgs.getSrcLang(), cliArgs.getDstLang());
        ePubTranslatorAppService.translateEPubFile(ePubTranslateParameters);
    }

    File constructDstFileFromSrcFile(File src, String dstLang){
        String srcFileName = src.getName();
        String dstFileName;
        if(!srcFileName.contains(".")) {
            dstFileName = srcFileName + "." + dstLang;
        }
        else {
            dstFileName = srcFileName.substring(0, srcFileName.lastIndexOf(".")) + "." + dstLang + srcFileName.substring(srcFileName.lastIndexOf("."), srcFileName.length());
        }
        return new File(src.getParent(), dstFileName);
    }
}
