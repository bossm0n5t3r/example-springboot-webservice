package org.example.server.controller.dto

import org.example.server.domain.posts.Posts

class PostsSaveRequestDto(
    val title: String,
    val content: String,
    val author: String
) {
    fun toEntity(): Posts {
        return Posts(
            title = title,
            content = content,
            author = author
        )
    }
}
