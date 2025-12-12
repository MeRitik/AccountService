package com.ritik.accountservice.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "cards")
public record AccountsContactInfoDTO(
        String message,
        Map<String, String> contactDetails,
        List<String> onCallSupport) {
}
