package com.webpa.mobile.domain.usecase.user

import com.webpa.mobile.domain.datastore.SessionStore
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val sessionStore: SessionStore
) {
    suspend operator fun invoke() {
        sessionStore.clear()
    }
}
