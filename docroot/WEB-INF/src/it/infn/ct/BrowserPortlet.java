package it.infn.ct;

import com.liferay.util.bridges.mvc.MVCPortlet;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.io.IOException;

/**
 * Portlet implementation class BrowserPortlet
 */
public class BrowserPortlet extends MVCPortlet {
 
    public static String login() {
    	
    		String responseOutput = "{ \"error\": \"no login\" }"; 
    		
        try {
    // 1. URL
            URL url = new URL("http://glibrary.ct.infn.it:3500/v2/users/login");
        
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
