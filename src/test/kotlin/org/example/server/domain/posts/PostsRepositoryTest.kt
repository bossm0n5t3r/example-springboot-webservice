package org.example.server.domain.posts

import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostsRepositoryTest {
    @Autowired
    lateinit var postsRepository: PostsRepository

    @Test
    fun `게시글저장_불러오기`() {
        // given
        val title = "테스트 게시글"
        val content = "테스트 본문"

        postsRepository.save(
            Posts(
                title = title,
                content = content,
                author = "bossm0n5t3r@gmail.com"
            )
        )

        // when
        val postsList = postsRepository.findAll()

        // then
        val posts = postsList[0]
        assertThat(posts.title).isEqualTo(title)
        assertThat(posts.content).isEqualTo(content)
    }

    @After
    fun cleanup() {
        postsRepository.deleteAll()
    }
}
