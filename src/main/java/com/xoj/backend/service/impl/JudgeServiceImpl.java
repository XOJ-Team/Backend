package com.xoj.backend.service.impl;

import com.xoj.backend.base.RestResponse;
import com.xoj.backend.entity.JudgeUpstream;
import com.xoj.backend.param.PlaygroundParam;
import com.xoj.backend.service.JudgeService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 1iin
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    private final static RestTemplate restTemplate = new RestTemplate();

    private final static String UPSTREAM = "https://runner.xoj.codes";

    private final static String ENDPOINT = "/submissions/";

    private final static String JUDGE_OPTIONS = "?base64_encoded=true";

    private final static HttpHeaders headers = new HttpHeaders();

    public JudgeServiceImpl() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Auth-Token", "[YOUR_API_KEY_HERE]");
    }

    @Override
    public UUID submitUpstream(PlaygroundParam param) {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("language_id", param.getLanguage_id());
        bodyMap.put("source_code", Base64.getEncoder().encodeToString(param.getSource_code().getBytes(StandardCharsets.UTF_8)));
        bodyMap.put("stdin", Base64.getEncoder().encodeToString(param.getStdin().getBytes(StandardCharsets.UTF_8)));
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(bodyMap, headers);
        JudgeUpstream requestResult = restTemplate
                .postForObject(UPSTREAM + ENDPOINT + JUDGE_OPTIONS, entity, JudgeUpstream.class);
        assert requestResult != null;
        return requestResult.getToken();
    }

    @Override
    public RestResponse<JudgeUpstream> lookupUpstream(UUID token) {
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<JudgeUpstream> lookupResult = restTemplate
                .exchange(UPSTREAM + ENDPOINT + token.toString() + JUDGE_OPTIONS,
                        HttpMethod.GET, requestEntity, JudgeUpstream.class);
        while (lookupResult.getBody().getStatus().getId() == 1) {
            lookupResult = restTemplate.exchange(UPSTREAM + ENDPOINT + token.toString() + JUDGE_OPTIONS,
                            HttpMethod.GET, requestEntity, JudgeUpstream.class);
        }

        return RestResponse.ok(lookupResult.getBody());
    }


}
