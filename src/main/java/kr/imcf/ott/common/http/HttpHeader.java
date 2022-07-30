package kr.imcf.ott.common.http;


import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
public class HttpHeader {
    private MediaType mediaType;
    private Map<String, String> keyValue;

    public HttpHeader(MediaType mediaType){
        this.mediaType = mediaType;
        this.keyValue = new HashMap<String, String>();
    }

    public void addHeader(String key, String value){
        this.keyValue.put(key, value);
    }

    public void addHeader(Map<String, String> newKeyValue){
        newKeyValue.forEach(
                (key, value) -> this.keyValue.merge(key, value, (v1, v2) -> v2)
        );
    }

    public HttpHeaders build(){
        HttpHeaders build = new HttpHeaders();
        this.keyValue.forEach(build::add);
        return build;
    }

}