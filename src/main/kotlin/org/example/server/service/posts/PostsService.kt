package org.example.server.service.posts

import org.example.server.controller.dto.PostsSaveRequestDto
import org.example.server.domain.posts.PostsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostsService(
    private var postsRepository: PostsRepository
) {
    @Transactional
    fun save(requestDto: PostsSaveRequestDto): Long {
        return postsRepository.save(requestDto.toEntity()).id!!
    }
}
