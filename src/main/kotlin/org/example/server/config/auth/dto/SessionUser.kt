package org.example.server.config.auth.dto

import org.example.server.domain.user.User
import java.io.Serializable

class SessionUser() : Serializable {
    lateinit var name: String
    lateinit var email: String
    lateinit var picture: String

    constructor(user: User) : this() {
        this.name = user.name
        this.email = user.email
        this.picture = user.picture
    }
}
