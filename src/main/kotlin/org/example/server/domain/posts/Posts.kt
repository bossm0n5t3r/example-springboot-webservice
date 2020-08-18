package org.example.server.domain.posts

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Posts() {
    constructor(title: String, content: String, author: String) : this() {
        this.title = title
        this.content = content
        this.author = author
    }

    @Column(length = 500, nullable = false)
    lateinit var title: String

    @Column(columnDefinition = "TEXT", nullable = false)
    lateinit var content: String

    lateinit var author: String

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}
