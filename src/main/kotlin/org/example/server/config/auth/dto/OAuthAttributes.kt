package org.example.server.config.auth.dto

import org.example.server.domain.user.Role
import org.example.server.domain.user.User

@Suppress("UNCHECKED_CAST")
class OAuthAttributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val name: String,
    val email: String,
    val picture: String
) {
    companion object {
        private val NAVER = "naver"

        fun of(registrationId: String, userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            if (NAVER == registrationId) {
                return ofNaver("id", attributes)
            }

            return ofGoogle(userNameAttributeName, attributes)
        }

        private fun ofGoogle(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            return OAuthAttributes(
                name = attributes["name"].toString(),
                email = attributes["email"].toString(),
                picture = attributes["picture"].toString(),
                attributes = attributes,
                nameAttributeKey = userNameAttributeName
            )
        }

        private fun ofNaver(userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
            val response: Map<String, Any> = attributes["response"] as Map<String, Any>
            return OAuthAttributes(
                name = response["name"]?.toString() ?: "",
                email = response["email"]?.toString() ?: "",
                picture = response["profileImage"]?.toString() ?: "",
                attributes = response,
                nameAttributeKey = userNameAttributeName
            )
        }
    }

    fun toEntity(): User {
        return User(
            name = name,
            email = email,
            picture = picture,
            role = Role.GUEST
        )
    }
}
