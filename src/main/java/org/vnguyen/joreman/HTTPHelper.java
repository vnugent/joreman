package org.vnguyen.joreman;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.auth.AUTH;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultTargetAuthenticationHandler;
import org.apache.http.message.BasicHeader;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;

/** 
 * helps get pass self-signed certificate warning
 * @author stackoverflow
 *
 */
public class HTTPHelper {
	
	public static DefaultHttpClient wrapClient(DefaultHttpClient base) {
	    try {
	        SSLContext ctx = SSLContext.getInstance("TLS");
	        X509TrustManager tm = new X509TrustManager() {
	            public void checkClientTrusted(X509Certificate[] xcs, String string) throws CertificateException { }
	 
	            public void checkServerTrusted(X509Certificate[] xcs, String string) throws CertificateException { }
	 
	            public X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	        };
	        ctx.init(null, new TrustManager[]{tm}, null);
	        SSLSocketFactory ssf = new SSLSocketFactory(ctx);
	        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        ClientConnectionManager ccm = base.getConnectionManager();
	        SchemeRegistry sr = ccm.getSchemeRegistry();
	        sr.register(new Scheme("https", ssf, 443));
	        return new DefaultHttpClient(ccm, base.getParams());
	    } catch (Exception ex) {
	        return null;
	    }
	}
	
	public static ClientExecutor basicHttpAuthExecutor(String username, String password) {
		
		DefaultHttpClient httpClient = wrapClient(new DefaultHttpClient());
		
		// foreman doesn't return www auth header so I'm manually adding it to trick Resteasy
		// there should be a better way
		httpClient.setTargetAuthenticationHandler(new DefaultTargetAuthenticationHandler() {
		    @Override
			protected Map<String, Header> parseChallenges(
		            final Header[] headers) throws MalformedChallengeException {
		    	if (headers.length==0) {
		    		Header[] h1 = new Header[] {new BasicHeader(AUTH.WWW_AUTH,"Basic realm=\"\"")};
		    		return super.parseChallenges(h1);
		    	}
				return super.parseChallenges(headers);
		    }
		});

		
	    httpClient.getCredentialsProvider().setCredentials(
	    										AuthScope.ANY,
	    										new UsernamePasswordCredentials(username, password));
		
		return new ApacheHttpClient4Executor(httpClient);		
	}

}
