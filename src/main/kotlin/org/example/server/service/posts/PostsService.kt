package org.example.server.service.posts

import org.example.server.controller.dto.PostsListResponseDto
import org.example.server.controller.dto.PostsResponseDto
import org.example.server.controller.dto.PostsSaveRequestDto
import org.example.server.controller.dto.PostsUpdateRequestDto
import org.example.server.domain.posts.PostsRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class PostsService(
    private val postsRepository: PostsRepository
) {
    @Transactional
    fun save(requestDto: PostsSaveRequestDto): Long {
        return postsRepository.save(requestDto.toEntity()).id!!
    }

    @Transactional
    fun update(id: Long, requestDto: PostsUpdateRequestDto): Long {
        val posts = postsRepository.findById(id)
            .orElseThrow {
                throw IllegalArgumentException("해당 사용자가 없습니다. id=$id")
            }

        posts.update(requestDto.title, requestDto.content)

        return id
    }

    @Transactional
    fun findById(id: Long): PostsResponseDto {
        val entity = postsRepository.findById(id)
            .orElseThrow {
                throw IllegalArgumentException("해당 사용자가 없습니다. id=$id")
            }
        return PostsResponseDto(entity)
    }

    @Transactional(readOnly = true)
    fun findAllDesc(): List<PostsListResponseDto> {
        return postsRepository.findAllDesc().stream()
            .map { posts -> PostsListResponseDto(posts) }
            .collect(Collectors.toList())
    }
}
