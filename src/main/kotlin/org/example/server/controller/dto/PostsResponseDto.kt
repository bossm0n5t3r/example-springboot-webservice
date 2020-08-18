package org.example.server.controller.dto

import org.example.server.domain.posts.Posts


class PostsResponseDto() {
    var id: Long = 0
    lateinit var title: String
    lateinit var content: String
    lateinit var author: String

    constructor(id: Long, title: String, content: String, author: String): this() {
        this.id = id
        this.title = title
        this.content = content
        this.author = author
    }

    constructor(entity: Posts): this() {
        this.id = entity.id!!
        this.title = entity.title
        this.content = entity.content
        this.author = entity.author
    }
}
