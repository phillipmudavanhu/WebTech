
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;  


import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class aufgabe07{

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		aufgabe07 http = new aufgabe07();
		http.sendGet();

	}

	public class WebUtils {

    private Map<String, String> getHeadersInfo(HttpServletRequest request) {

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }

}

	// HTTP GET request
	private  void sendGet() throws Exception {

		//The args url without the query
		String APIurl = "http://www.args.me/api/v1/_search?query="; 


		System.out.print("Bitte die Anfrage eingeben : "); 
		Scanner sc=new Scanner(System.in);   
   		String queryUser=sc.next();  


		//Full URL to be sent to the HTTP Connention is created below:
		String url = APIurl+queryUser;  
		

		//Creating a new URL Object that takes our URL as a parameter & Connection 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL            : " + url);
		System.out.println("\nResponse Code                           : " + responseCode);
		System.out.println("\nThe Results of the search from the ARGS : "); 



		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null){
			response.append(inputLine);
		}

		in.close();
		
		//print result
		System.out.println(response.toString());

	}



}