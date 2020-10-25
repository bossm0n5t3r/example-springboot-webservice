package org.example.server.config.auth

import org.example.server.config.auth.dto.OAuthAttributes
import org.example.server.config.auth.dto.SessionUser
import org.example.server.domain.user.User
import org.example.server.domain.user.UserRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.util.Collections
import javax.servlet.http.HttpSession

@Service
class CustomOAuth2UserService(
    private val userRepository: UserRepository,
    private val httpSession: HttpSession
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val delegate = DefaultOAuth2UserService()
        val oAuth2User = delegate.loadUser(userRequest)
        val registrationId = userRequest
            .clientRegistration
            .registrationId
        val userNameAttributeName = userRequest
            .clientRegistration
            .providerDetails
            .userInfoEndpoint
            .userNameAttributeName
        val attributes = OAuthAttributes.of(
            registrationId = registrationId,
            userNameAttributeName = userNameAttributeName,
            attributes = oAuth2User.attributes
        )
        val user = saveOfUpdate(attributes)
        httpSession.setAttribute("user", SessionUser(user))
        return DefaultOAuth2User(
            Collections.singleton(
                SimpleGrantedAuthority(user.role.key)
            ),
            attributes.attributes,
            attributes.nameAttributeKey
        )
    }

    private fun saveOfUpdate(attributes: OAuthAttributes): User {
        val user = userRepository.findByEmail(attributes.email)
            .map { entity ->
                entity.update(attributes.name, attributes.picture)
            }
            .orElse(attributes.toEntity())
        return userRepository.save(user)
    }
}
