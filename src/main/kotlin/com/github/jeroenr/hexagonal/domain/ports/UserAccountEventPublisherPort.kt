package com.github.jeroenr.hexagonal.domain.ports

interface UserAccountEventPublisherPort {
    fun publishUserAccountUpdated(
        objectId: Any,
        message: Any
    )
}
