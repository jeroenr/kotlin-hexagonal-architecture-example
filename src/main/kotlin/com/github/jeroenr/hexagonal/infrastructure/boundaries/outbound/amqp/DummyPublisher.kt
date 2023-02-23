package com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.amqp

import com.github.jeroenr.hexagonal.domain.ports.UserAccountEventPublisherPort
import org.springframework.stereotype.Component

@Component
class DummyPublisher : UserAccountEventPublisherPort {
    override fun publishUserAccountUpdated(objectId: Any, message: Any) {

    }
}