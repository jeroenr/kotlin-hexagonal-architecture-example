package com.github.jeroenr.hexagonal.domain.model

/**
 * Sample API (not necessarily HTTP/REST) for querying our domain
 */

data class DepositRequestDto(val userId: String, val amount: Money)

data class DepositResponseDto(val userId: String, val oldBalance: Money, val newBalance: Money)
