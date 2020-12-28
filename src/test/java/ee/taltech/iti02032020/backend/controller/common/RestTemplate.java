package ee.taltech.iti02032020.backend.controller.common;

import ee.taltech.iti02032020.backend.model.DailyForecast;
import ee.taltech.iti02032020.backend.model.User;
import ee.taltech.iti02032020.backend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplate {
    public static final ParameterizedTypeReference<List<String>> LIST_OF_FORECASTS = new ParameterizedTypeReference<>() {
    };

    public static final ParameterizedTypeReference<List<DailyForecast>> LIST_OF_FORECASTS_FOR_SEVEN_DAYS = new ParameterizedTypeReference<>() {
    };

    @Autowired
    protected TestRestTemplate testRestTemplate;
    @Autowired
    protected JwtTokenProvider jwtTokenProvider;

    /**
     * this was used with basic
     */
    @Deprecated
    public TestRestTemplate templateWithUser() {
        return testRestTemplate.withBasicAuth("user", "user");
    }

    /**
     * this was used with basic
     */
    @Deprecated
    public TestRestTemplate templateWithAdmin() {
        return testRestTemplate.withBasicAuth("admin", "admin");
    }

    public <T> T assertOk(ResponseEntity<T> exchange) {
        assertNotNull(exchange.getBody());
        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        return exchange.getBody();
    }

    protected HttpHeaders authorizationHeader(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtTokenProvider.createTokenForTests(username));
        return headers;
    }
}
