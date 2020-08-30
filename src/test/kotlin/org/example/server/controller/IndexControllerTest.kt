package org.example.server.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {
    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `메인페이지_로딩`() {
        // when
        val body = restTemplate.getForObject(
            "/",
            String::class.java
        )

        // then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스")
    }
}
