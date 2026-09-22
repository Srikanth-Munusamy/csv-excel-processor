package com.training.codingstandards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SecurityUtilTest {

    @Test
    void hashesIdentifiersWithStableSha256Digest() {
        String hash = SecurityUtil.hashIdentifier("1001asha@example.com");

        assertEquals(64, hash.length());
        assertEquals(hash, SecurityUtil.hashIdentifier("1001asha@example.com"));
        assertNotEquals(hash, SecurityUtil.hashIdentifier("1002asha@example.com"));
    }

    @Test
    void createsDifferentCryptographicSessionTokens() {
        String first = SecurityUtil.sessionToken();

        assertEquals(64, first.length());
        assertNotEquals(first, SecurityUtil.sessionToken());
    }

    @Test
    void rejectsAdminPasswordWhenNoEnvironmentSecretIsConfigured() {
        assertFalse(SecurityUtil.isAdmin("anything"));
        assertFalse(SecurityUtil.isAdmin(null));
    }
}