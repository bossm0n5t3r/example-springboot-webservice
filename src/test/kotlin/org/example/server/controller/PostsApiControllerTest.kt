package org.example.server.controller

import org.assertj.core.api.Assertions.assertThat
import org.example.server.controller.dto.PostsSaveRequestDto
import org.example.server.domain.posts.Posts
import org.example.server.domain.posts.PostsRepository
import org.junit.After
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {
    @LocalServerPort
    var port: Int = 0

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var postsRepository: PostsRepository

    @Test
    fun `Posts_등록된다`() {
        // given
        val title = "title"
        val content = "content"
        val requestDto = PostsSaveRequestDto(
            title = title,
            content = content,
            author = "author"
        )
        val url = "http://localhost:$port/api/v1/posts"

        // when
        val responseEntity: ResponseEntity<Long> = restTemplate.postForEntity(
            url,
            requestDto,
            Long::class.java
        )

        // then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)
        val all: List<Posts> = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(title)
        assertThat(all[0].content).isEqualTo(content)
    }

    @After
    fun tearDown() {
        postsRepository.deleteAll()
    }
}
