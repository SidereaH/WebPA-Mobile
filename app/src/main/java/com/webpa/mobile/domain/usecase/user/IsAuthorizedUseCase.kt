package com.webpa.mobile.domain.usecase.user

import com.webpa.mobile.domain.datastore.SessionStore
import javax.inject.Inject

class IsAuthorizedUseCase @Inject constructor(
    private val sessionStore: SessionStore
) {
    suspend operator fun invoke(): Boolean =
        sessionStore.getAccessToken() != null
}
