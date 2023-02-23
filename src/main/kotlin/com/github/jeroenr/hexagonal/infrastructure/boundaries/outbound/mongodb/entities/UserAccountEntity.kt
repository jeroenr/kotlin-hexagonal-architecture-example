package com.github.jeroenr.hexagonal.infrastructure.boundaries.outbound.mongodb.entities

import com.github.jeroenr.hexagonal.domain.model.Currency
import com.github.jeroenr.hexagonal.domain.model.Money
import com.github.jeroenr.hexagonal.domain.model.UserAccountDto
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.Instant
import javax.persistence.Id

/**
 * Sample entity
 */
@Document
data class UserAccountEntity(
    @Id
    val id: String,
    var name: String,
    var balance: BigDecimal,
    var currency: String,
    var createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now()
) {
    fun toUserAccountDto() =
        UserAccountDto(id, name, Money(balance, Currency.valueOf(currency)))

    companion object {
        fun of(userAccount: UserAccountDto) =
            UserAccountEntity(
                userAccount.id,
                userAccount.name,
                userAccount.balance.amount,
                userAccount.balance.currency.name
            )
    }
}
