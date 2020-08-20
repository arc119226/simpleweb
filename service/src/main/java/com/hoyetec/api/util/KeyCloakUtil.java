package com.hoyetec.api.util;

import java.security.Principal;
import java.text.ParseException;
import java.util.Map;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * keycloak token擷取資訊
 *
 */
public class KeyCloakUtil {
	private static final Logger logger = LoggerFactory.getLogger(KeyCloakUtil.class);

	public static String getUsername(Principal principal) throws ParseException {
        KeycloakPrincipal<?> kcPrincipal = (KeycloakPrincipal<?>) principal;
        KeycloakSecurityContext kcContext = kcPrincipal.getKeycloakSecurityContext();
        AccessToken accessToken = kcContext.getToken();
        String username = accessToken.getPreferredUsername();
        return username;
    }

    public static String getSubject(Principal principal) throws ParseException {
        KeycloakPrincipal<?> kcPrincipal = (KeycloakPrincipal<?>) principal;
        KeycloakSecurityContext kcContext = kcPrincipal.getKeycloakSecurityContext();
        AccessToken accessToken = kcContext.getToken();
        String sub = accessToken.getSubject();
        return sub;
    }

    public static String getToken(Principal principal) throws ParseException {
        KeycloakPrincipal<?> kcPrincipal = (KeycloakPrincipal<?>) principal;
        KeycloakSecurityContext kcContext = kcPrincipal.getKeycloakSecurityContext();
        String access_token_str = kcContext.getTokenString();
        return access_token_str;
    }

    public static String getAttribute(Principal principal, String attributeName) throws ParseException {
        KeycloakPrincipal<?> kcPrincipal = (KeycloakPrincipal<?>)principal;
        KeycloakSecurityContext kcContext = kcPrincipal.getKeycloakSecurityContext();
        AccessToken accessToken = kcContext.getToken();
        Map<String, Object> otherClaims = accessToken.getOtherClaims();
        return otherClaims.get(attributeName).toString();
    }


    public static String getAttribute(String tokenString, String attributeName) throws ParseException, VerificationException {
    	AccessToken accessToken = TokenVerifier.create(tokenString, AccessToken.class).getToken();
        Map<String, Object> otherClaims = accessToken.getOtherClaims();
        return otherClaims.get(attributeName).toString();
    }
}
