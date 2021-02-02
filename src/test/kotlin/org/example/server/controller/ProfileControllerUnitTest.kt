package org.example.server.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.mock.env.MockEnvironment

class ProfileControllerUnitTest {
    @Test
    fun `real_profile이 조회된다`() {
        // given
        val expectedProfile = "real"
        val env = MockEnvironment()
        env.addActiveProfile(expectedProfile)
        env.addActiveProfile("oauth")
        env.addActiveProfile("real-db")

        val controller = ProfileController(env)

        // when
        val profile = controller.profile()

        // then
        assertThat(profile).isEqualTo(expectedProfile)
    }

    @Test
    fun `real_profile이 없으면 첫 번째가 조회된다`() {
        // given
        val expectedProfile = "oauth"
        val env = MockEnvironment()
        env.addActiveProfile(expectedProfile)
        env.addActiveProfile("real-db")

        val controller = ProfileController(env)

        // when
        val profile = controller.profile()

        // then
        assertThat(profile).isEqualTo(expectedProfile)
    }

    @Test
    fun `active_profile이 없으면 default가 조회된다`() {
        // given
        val expectedProfile = "default"
        val env = MockEnvironment()

        val controller = ProfileController(env)

        // when
        val profile = controller.profile()

        // then
        assertThat(profile).isEqualTo(expectedProfile)
    }
}
