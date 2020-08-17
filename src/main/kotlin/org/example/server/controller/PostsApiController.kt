package org.example.server.controller

import org.example.server.controller.dto.PostsSaveRequestDto
import org.example.server.service.posts.PostsService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostsApiController(
    private var postsService: PostsService
) {
    @RequestMapping("/api/v1/posts")
    fun save(@RequestBody requestDto: PostsSaveRequestDto): Long {
        return postsService.save(requestDto)
    }
}
