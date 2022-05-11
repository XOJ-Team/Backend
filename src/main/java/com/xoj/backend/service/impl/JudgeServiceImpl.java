package com.xoj.backend.service.impl;

import com.xoj.backend.dto.SubmitRecordsCreateDto;
import com.xoj.backend.entity.JudgeUpstream;
import com.xoj.backend.param.PlaygroundParam;
import com.xoj.backend.service.JudgeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
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

    @Value("${judge-api.url}")
    private String UPSTREAM;

    @Value("${judge-api.key}")
    private String API_KEY;

    @Value("${judge-api.enable-base64}")
    private boolean ENABLE_BASE64;

    private final static String ENDPOINT = "/submissions/";

    private final static String JUDGE_OPTIONS = "?base64_encoded=true";

    private final static HttpHeaders headers = new HttpHeaders();

    public JudgeServiceImpl() {
    }

    @PostConstruct
    public void setupAuth() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Auth-Token", API_KEY);
    }

    @Override
    public UUID submitUpstream(PlaygroundParam param) {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("language_id", param.getLanguage_id());
        bodyMap.put("source_code", param.getSource_code());
        bodyMap.put("stdin", Base64.getEncoder().encodeToString(param.getStdin().getBytes(StandardCharsets.UTF_8)));
        if (param.getExpected_output() != null) {
            bodyMap.put("expected_output", Base64.getEncoder().encodeToString(param.getExpected_output().getBytes(StandardCharsets.UTF_8)));
        }
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(bodyMap, headers);

        JudgeUpstream requestResult = restTemplate
                .postForObject(UPSTREAM + ENDPOINT + JUDGE_OPTIONS, entity, JudgeUpstream.class);
        assert requestResult != null;
        return requestResult.getToken();
    }

    @Override
    public JudgeUpstream lookupUpstream(UUID token) {
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<JudgeUpstream> lookupResult = restTemplate
                .exchange(UPSTREAM + ENDPOINT + token.toString() + JUDGE_OPTIONS,
                        HttpMethod.GET, requestEntity, JudgeUpstream.class);
        while (lookupResult.getBody().getStatus().getId() == 1 || lookupResult.getBody().getStatus().getId() == 2) {
            lookupResult = restTemplate.exchange(UPSTREAM + ENDPOINT + token.toString() + JUDGE_OPTIONS,
                            HttpMethod.GET, requestEntity, JudgeUpstream.class);
        }
        return lookupResult.getBody();
    }

    @Override
    public SubmitRecordsCreateDto dtoConversion(PlaygroundParam param, JudgeUpstream result) {
        SubmitRecordsCreateDto dto = new SubmitRecordsCreateDto();
        dto.setQuestionId(param.getQuestion_id());
        dto.setResult(result.getStatus().getId());
        dto.setCodes(param.getSource_code());
        dto.setMemoryCost(result.getMemory());
        dto.setTimeCost(Math.round(result.getTime() * 1000));
        return dto;
    }

}
