package com.hqqm.mde.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file.upload")
@Data
public class FileUploadProperties {
    String filesLocation;
    String imagesLocation;
}
