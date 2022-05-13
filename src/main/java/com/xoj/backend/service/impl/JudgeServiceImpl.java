package com.xoj.backend.service.impl;

import com.xoj.backend.common.LanguageEnum;
import com.xoj.backend.dto.SubmitRecordsCreateDto;
import com.xoj.backend.param.JudgeParam;
import com.xoj.backend.param.PlaygroundRequestParam;
import com.xoj.backend.service.JudgeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
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

    public JudgeServiceImpl() {}

    @PostConstruct
    public void setupAuth() {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Auth-Token", API_KEY);
    }

    @Override
    public UUID submitUpstream(JudgeParam param) {
        HttpEntity<JudgeParam> entity = new HttpEntity<>(param, headers);
        JudgeParam requestResult = restTemplate
                .postForObject(UPSTREAM + ENDPOINT + JUDGE_OPTIONS, entity, JudgeParam.class);
        assert requestResult != null;
        return requestResult.getToken();
    }

    @Override
    public JudgeParam lookupUpstream(UUID token) {
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<JudgeParam> lookupResult = restTemplate
                .exchange(UPSTREAM + ENDPOINT + token.toString() + JUDGE_OPTIONS,
                        HttpMethod.GET, requestEntity, JudgeParam.class);
        while (lookupResult.getBody().getStatus().getId() == 1 || lookupResult.getBody().getStatus().getId() == 2) {
            lookupResult = restTemplate.exchange(UPSTREAM + ENDPOINT + token + JUDGE_OPTIONS,
                            HttpMethod.GET, requestEntity, JudgeParam.class);
        }
        return lookupResult.getBody();
    }

    @Override
    public SubmitRecordsCreateDto dtoConversion(PlaygroundRequestParam param, JudgeParam result) {
        SubmitRecordsCreateDto dto = new SubmitRecordsCreateDto();
        dto.setQuestionId(param.getQuestion_id());
        dto.setCodes(param.getSource_code());
        dto.setLang(LanguageEnum.getDescription(param.getLanguage_id()));
        dto.setResult(result.getStatus().getId());
        if (result.getStatus().getId() != 3) {
            return dto;
        }
        dto.setMemoryCost(result.getMemory());
        dto.setTimeCost((int) (result.getTime() * 1000));
        return dto;
    }

}
