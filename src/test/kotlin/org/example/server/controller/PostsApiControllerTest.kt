package org.example.server.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.example.server.controller.dto.PostsSaveRequestDto
import org.example.server.controller.dto.PostsUpdateRequestDto
import org.example.server.domain.posts.Posts
import org.example.server.domain.posts.PostsRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {
    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var postsRepository: PostsRepository

    @Autowired
    private lateinit var context: WebApplicationContext

    private lateinit var mvc: MockMvc

    @BeforeEach
    fun setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply<DefaultMockMvcBuilder>(springSecurity())
            .build()
    }

    @Test
    @WithMockUser(roles = ["USER"])
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
        mvc.perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(requestDto))
        )
            .andExpect(status().isOk)

        // then
        val all: List<Posts> = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(title)
        assertThat(all[0].content).isEqualTo(content)
    }

    @Test
    @WithMockUser(roles = ["USER"])
    fun `Posts_수정된다`() {
        // TODO: OAuth2 구현으로 테스트가 깨짐. 임시 비활성화 상태
        // given
        val savedPosts = postsRepository.save(
            Posts(
                title = "title",
                content = "content",
                author = "author"
            )
        )

        val updatedId = savedPosts.id!!
        val expectedTitle = "title2"
        val expectedContent = "content2"

        val requestDto = PostsUpdateRequestDto(
            title = expectedTitle,
            content = expectedContent
        )

        val url = "http://localhost:$port/api/v1/posts/$updatedId"

        // when
        mvc.perform(
            put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapper().writeValueAsString(requestDto))
        )
            .andExpect(status().isOk)

        // then
        val all: List<Posts> = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(expectedTitle)
        assertThat(all[0].content).isEqualTo(expectedContent)
    }

    @AfterEach
    fun tearDown() {
        postsRepository.deleteAll()
    }
}
