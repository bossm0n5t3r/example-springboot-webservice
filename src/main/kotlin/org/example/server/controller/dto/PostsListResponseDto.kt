package org.example.server.controller.dto

import org.example.server.domain.posts.Posts
import java.time.LocalDateTime

class PostsListResponseDto(
    entity: Posts
) {
    var id: Long = entity.id!!
    var title: String = entity.title
    var author: String = entity.author
    var modifiedDate: LocalDateTime = entity.modifiedDate
}
