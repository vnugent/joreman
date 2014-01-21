package org.vnguyen.joreman;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * helps get pass self-signed certificate warning
 * @author stackoverflow
 *
 */
public class HTTPHelper {
	final static Logger logger = LoggerFactory.getLogger(HTTPHelper.class);
	
	public static SSLContext createTrustfulSSLContex() {
	    try {
	        SSLContext ctx = SSLContext.getInstance("TLS");
	        X509TrustManager tm = new X509TrustManager() {
	            public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException { }
	 
	            public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException { }
	 
	            public X509Certificate[] getAcceptedIssuers() {
	            	return  new X509Certificate[0];
	            }
	        };
	        ctx.init(null, new TrustManager[]{tm}, null);
	        
	        return ctx;
	    } catch (Exception ex) {
	    	logger.error("Error while creating SSL Context",ex);
	    	
	        return null;
	    }
	}
	
	public static ClientConnectionManager makeConnManagerTrustful(ClientConnectionManager cm){
		SSLContext sslContext = HTTPHelper.createTrustfulSSLContex();
		SSLSocketFactory ssf = new SSLSocketFactory(sslContext);
		ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        SchemeRegistry sr = cm.getSchemeRegistry();
        sr.register(new Scheme("https", ssf, 443));
        
        return cm;
	}
}
