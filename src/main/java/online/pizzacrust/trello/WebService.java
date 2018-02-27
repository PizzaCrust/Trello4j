package online.pizzacrust.trello;

import com.google.gson.Gson;

import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.util.HashMap;
import java.util.Map;

public class WebService {

    private final String apiKey;
    private final String userToken;

    public WebService(String apiKey, String userToken) {
        Unirest.setObjectMapper(new ObjectMapper() {
            public <T> T readValue(String s, Class<T> aClass) {
                return new Gson().fromJson(s, aClass);
            }

            public String writeValue(Object o) {
                return new Gson().toJson(o);
            }
        });
        this.apiKey = apiKey;
        this.userToken = userToken;
    }

    public boolean isUsingAuthentication() {
        return apiKey != null && userToken != null;
    }

    private Map<String, Object> convertToMap(String[] varargs) {
        Map<String, Object> map = new HashMap<String, Object>();
        int stage = 0;
        String cache = null;
        for (String vararg : varargs) {
            if (stage == 0) {
                cache = vararg;
                stage = 1;
                continue;
            }
            map.put(cache, vararg);
            stage = 0;
            cache = null;
        }
        return map;
    }

    public <T> T getRequest(String rootUrl,
                            Class<T> clazz,
                            String... paramNameToParamVal) throws Exception {
        GetRequest getRequest = Unirest.get(rootUrl);
        if (apiKey != null && userToken != null) {
            getRequest.queryString("key", apiKey).queryString
                    ("token",
                            userToken);
        }
        return getRequest.queryString(convertToMap
                (paramNameToParamVal)).asObject
                (clazz).getBody();
    }

    public void putRequest(String rootUrl,
                           String... paramNameToParamVal) throws Exception {
        HttpRequestWithBody putRequest = Unirest.put(rootUrl);
        if (apiKey != null && userToken != null) {
            putRequest.queryString("key", apiKey).queryString("token", userToken);
        }
        putRequest.queryString(convertToMap(paramNameToParamVal)).asString();
    }

}
