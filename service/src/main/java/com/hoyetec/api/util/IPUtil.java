package com.hoyetec.api.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 取得遠端真實IP
 * @author arcliu
 *
 */
public class IPUtil {
	public static String getRemoteAddr(HttpServletRequest request) {
		String xForwardedFor = request.getHeader("x-forwarded-for");
		String xRealIp = request.getHeader("X-Real-IP");

		String returnIp = null;

		if (xForwardedFor != null) {
			returnIp = xForwardedFor;
		} else if (xRealIp != null) {
			returnIp = xRealIp;
		} else {
			returnIp = request.getRemoteAddr();
		}
		returnIp = getFirstIp(returnIp);
		return returnIp;
	}
	private static String getFirstIp(String ip) {
		String[] ipList = ip.replace(" ", "").split(",");
		String returnIp = null;
		if (ipList.length > 0) {
			returnIp = ipList[0];
		}
		return returnIp;
	}
}
