package net.sharplab.epubtranslator.app.cli;

import com.beust.jcommander.Parameter;

import java.io.File;
import java.util.List;
import java.util.Objects;

/**
 * Represents command line arguments
 */
public class CliArgs {
    @Parameter
    private List<String> parameters;

    @Parameter(names = {"--src"}, description = "source file", required = true)
    private File src;

    @Parameter(names = {"--dst"}, description = "destination file")
    private File dst;

    @Parameter(names = { "--srcLang" }, description = "source language")
    private String srcLang;

    @Parameter(names = { "--dstLang" }, description = "destination language")
    private String dstLang;

    @Parameter(names = {"--help", "-h"}, description = "print help", help = true)
    private boolean help;

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public File getSrc() {
        return src;
    }

    public void setSrc(File src) {
        this.src = src;
    }

    public File getDst() {
        return dst;
    }

    public void setDst(File dst) {
        this.dst = dst;
    }

    public String getSrcLang() {
        return srcLang;
    }

    public void setSrcLang(String srcLang) {
        this.srcLang = srcLang;
    }

    public String getDstLang() {
        return dstLang;
    }

    public void setDstLang(String dstLang) {
        this.dstLang = dstLang;
    }

    public boolean isHelp() {
        return help;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CliArgs cliArgs = (CliArgs) o;
        return help == cliArgs.help &&
                Objects.equals(parameters, cliArgs.parameters) &&
                Objects.equals(src, cliArgs.src) &&
                Objects.equals(dst, cliArgs.dst) &&
                Objects.equals(srcLang, cliArgs.srcLang) &&
                Objects.equals(dstLang, cliArgs.dstLang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameters, src, dst, srcLang, dstLang, help);
    }
}
