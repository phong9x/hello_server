package org.trams.hello.web.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.parser.ParseException;

public class RequestUtils {
	
	public static String get(String urlStr) {
		try {
			// 1. URL
			URL url = new URL(urlStr);

			// 2. Open connection
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// 3. Specify POST method
			conn.setRequestMethod("GET");

			int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// 7. Print result
			System.out.println(response.toString());
			return response.toString();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
