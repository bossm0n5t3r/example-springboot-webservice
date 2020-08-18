package org.example.server.controller

import org.example.server.controller.dto.PostsResponseDto
import org.example.server.controller.dto.PostsSaveRequestDto
import org.example.server.controller.dto.PostsUpdateRequestDto
import org.example.server.service.posts.PostsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class PostsApiController(
    private val postsService: PostsService
) {
    @RequestMapping("/api/v1/posts")
    fun save(@RequestBody requestDto: PostsSaveRequestDto): Long {
        return postsService.save(requestDto)
    }

    @PutMapping("/api/v1/posts/{id}")
    fun update(@PathVariable id: Long, @RequestBody requestDto: PostsUpdateRequestDto): Long {
        return postsService.update(id, requestDto)
    }

    @GetMapping("/api/vi/posts/{id}")
    fun findById(@PathVariable id: Long): PostsResponseDto {
        return postsService.findById(id)
    }
}
