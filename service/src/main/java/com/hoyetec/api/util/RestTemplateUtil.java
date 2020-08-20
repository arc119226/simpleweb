package com.hoyetec.api.util;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * rest template 工具類
 * @author arc
 *
 */
public class RestTemplateUtil {

	private static final String https="https";
	
	private static RestTemplate httpsTemplate;
	private static RestTemplate httpTemplate;
	
	/**
	 * 依據傳入的網址取得 處理http/https的RestTemplate
	 * @param api
	 * @return
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static RestTemplate createRestTemplate(String api)throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		if (api.startsWith(https)) {
			if(httpsTemplate==null) {
				TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
				
				SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
						.loadTrustMaterial(null, acceptingTrustStrategy)
						.build();
				
				SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
				
				CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf)
						.build();
				
				HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
				requestFactory.setHttpClient(httpClient);
				
				RestTemplate restTemplate = new RestTemplate(requestFactory);
				httpsTemplate = restTemplate;
			}
			return httpsTemplate;
		} else {
			if(httpTemplate==null) {
				httpTemplate=new RestTemplate();
			}
			return httpTemplate;
		}
	}
}
