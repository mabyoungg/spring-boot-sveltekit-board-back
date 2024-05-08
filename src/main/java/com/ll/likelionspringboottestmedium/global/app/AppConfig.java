package com.ll.likelionspringboottestmedium.global.app;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

@Configuration
public class AppConfig {
    private static String resourcesStaticDirPath;

    public static String getResourcesStaticDirPath() {
        if (resourcesStaticDirPath == null) {
            ClassPathResource resource = new ClassPathResource("static/");
            try {
                resourcesStaticDirPath = resource.getFile().getAbsolutePath();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return resourcesStaticDirPath;
    }

    @Getter
    public static String tempDirPath;

    @Value("${custom.tempDirPath}")
    public void setTempDirPath(String tempDirPath) {
        AppConfig.tempDirPath = tempDirPath;
    }

    @Getter
    public static String genFileDirPath;

    @Value("${custom.genFile.dirPath}")
    public void setGenFileDirPath(String genFileDirPath) {
        AppConfig.genFileDirPath = genFileDirPath;
    }

    @Getter
    public static String siteName;

    @Value("${custom.site.name}")
    public void setSiteName(String siteName) {
        AppConfig.siteName = siteName;
    }

    @Getter
    public static String siteBaseUrl;

    @Value("${custom.site.baseUrl}")
    public void setSiteBaseUrl(String siteBaseUrl) {
        AppConfig.siteBaseUrl = siteBaseUrl;
    }

    @Getter
    private static String siteBackUrl;

    @Value("${custom.site.backUrl}")
    public void setSiteBackUrl(String siteBackUrl) {
        this.siteBackUrl = siteBackUrl;
    }


}