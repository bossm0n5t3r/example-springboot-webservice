package org.example.server.controller

import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@WebMvcTest
class HelloControllerTest(
    @Autowired
    private val mvc: MockMvc
) {
    @Test
    fun `hello가 리턴된다`() {
        val hello = "hello"

        mvc.perform(get("/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string(hello))
    }
}
