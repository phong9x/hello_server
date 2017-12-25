package org.trams.hello.common;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bryanlee on 05/01/2017.
 */
public class Sms {

    final static RestTemplate template = new RestTemplate();

    protected static Logger log = LoggerFactory.getLogger(Sms.class);
    
    public static boolean send(String phone, String content) {
    	log.debug("============================================================================");
    	log.debug("send sms");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", "AimmedHello");
        params.add("title", "Hello");
        params.add("to", phone);
        params.add("message", content);
        log.debug("message: " + content);
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        try {
        	log.debug("reqeuset: http://api.aimmed.co.kr/hello/mms.asp");
            ResponseEntity<String> response = template.exchange(new URI("http://api.aimmed.co.kr/hello/mms.asp"), HttpMethod.POST, entity, String.class);
            log.debug("response: " + response);
            log.debug("============================================================================");
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                return true;
            }
            return false;
        } catch (Exception e) {
        	e.printStackTrace();
            return false;
        }
    }

}
