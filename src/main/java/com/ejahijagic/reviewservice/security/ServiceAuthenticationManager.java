package com.ejahijagic.reviewservice.security;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceAuthenticationManager {

    private static final Integer TOKEN_ID_PART_LENGTH = 10;

    private final SecurityProperties securityProperties;


    public String createServiceToken() {
        String id = RandomStringUtils.randomAlphanumeric(TOKEN_ID_PART_LENGTH);
        return signServiceToken(id);
    }


    public String parseServiceName(String token) {
        return token.substring(0, TOKEN_ID_PART_LENGTH);
    }

    public boolean verifyServiceToken(String token) {
        String id = token.substring(0, TOKEN_ID_PART_LENGTH);
        String calculatedSignature = signServiceToken(id);

        return calculatedSignature.equals(token);
    }

    private String signServiceToken(String id) {
        return id + DigestUtils.sha1Hex(id + securityProperties.getPrivateSignatureKey());
    }
}
