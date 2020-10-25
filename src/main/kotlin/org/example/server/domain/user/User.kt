package org.example.server.domain.user

import org.example.server.domain.BaseTimeEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User() : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    lateinit var name: String

    @Column(nullable = false)
    lateinit var email: String

    @Column
    lateinit var picture: String

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    lateinit var role: Role

    constructor(name: String, email: String, picture: String, role: Role) : this() {
        this.name = name
        this.email = email
        this.picture = picture
        this.role = role
    }

    fun update(name: String, picture: String): User {
        this.name = name
        this.picture = picture
        return this
    }
}

enum class Role(
    val key: String,
    val title: String
) {
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자")
}
