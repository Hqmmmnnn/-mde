package com.hqqm.mde.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtTokenProperties {
    String secret;
    long expiration ;
}
