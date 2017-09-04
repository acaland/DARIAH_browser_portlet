package it.infn.ct;

import com.liferay.util.bridges.mvc.MVCPortlet;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.IOException;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;
import java.security.cert.X509Certificate;

/**
 * Portlet implementation class BrowserPortlet
 */
public class BrowserPortlet extends MVCPortlet {


    static {
        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                public java.security.cert.X509Certificate[]
                        getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(
                        final X509Certificate[] certs,
                        final String authType) {
                }
                public void checkServerTrusted(
                        final X509Certificate[] certs,
                        final String authType) {
                }
    } };
    SSLContext sc;
    try {
        sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts,
                new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(
                sc.getSocketFactory());
    } catch (NoSuchAlgorithmException e) {
    } catch (KeyManagementException e) {
    }

    HostnameVerifier allHostsValid = new HostnameVerifier() {
        public boolean verify(
                final String hostname, final SSLSession session) {
            return true;
        }
    };

    HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
    }
 
    public static String login() {
    	
    		String responseOutput = "{ \"error\": \"no login\" }"; 
    		
        try {
    // 1. URL
            URL url = new URL("https://glibrary.ct.infn.it:3500/v2/users/login");
        
            // 2. Open connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
            // 3. Specify POST method
            conn.setRequestMethod("POST");
        
            // 4. Set the headers
            conn.setRequestProperty("Content-Type", "application/json");
            
            conn.setDoOutput(true);
            String input = "{ \"username\": \"dariahadmin\", \"password\": \"d4r142016\"}";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { //success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                responseOutput = response.toString();
            } else {
            	responseOutput = "{\"error\": \"login error\"}";
            }
        } catch (MalformedURLException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return responseOutput;
    }
}
