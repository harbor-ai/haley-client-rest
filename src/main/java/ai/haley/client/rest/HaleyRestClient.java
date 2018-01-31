package ai.haley.client.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HaleyRestClient {
    
	private CloseableHttpClient httpclient;
	private URL url;
	private String serverURL;
	
	public HaleyRestClient(String serverURL, String username, String password) throws MalformedURLException {
		if(serverURL == null) {
			throw new NullPointerException("serverURL is required");
		}
		if(username == null) {
			throw new NullPointerException("username is required");
		}
		if(password == null) {
			throw new NullPointerException("password is required");
		}
		if(serverURL.endsWith("/")) {
			serverURL.substring(0, serverURL.length()-1);
		}
		this.serverURL = serverURL;
		this.url = new URL(serverURL);
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(this.url.getHost(), AuthScope.ANY_PORT),
                new UsernamePasswordCredentials(username, password));
		
        this.httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
	}
	
	/**
	 * Returns raw json data if HTTP status code 200, exceptions thrown on any other error
	 * @param path
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public String postJsonData(String path, String jsonData) throws Exception {
		
		if(!path.startsWith("/")) throw new Exception("Path must start with a slash");
		
		HttpPost post = new HttpPost(this.serverURL + path);
		post.setEntity(new StringEntity(jsonData, ContentType.APPLICATION_JSON));
		
		CloseableHttpResponse response = null;
		
		
		try {
			
			response = httpclient.execute(post);
			
			String body = null;
			
			try {
				body = EntityUtils.toString(response.getEntity());
			} catch(Exception e) {
				
			}
			
			if(body == null) body = "";
			
			if( response.getStatusLine().getStatusCode() != 200 ) throw new Exception("" + response.getStatusLine() + " - " + body);
			
			return body;
			
			
		} finally {
			
			if(response != null) {
				try { response.close(); } catch(Exception e) {}
			}
			
		}

		
		
	}
	
	
	
}
