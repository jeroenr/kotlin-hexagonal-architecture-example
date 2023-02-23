package com.github.jeroenr.hexagonal.infrastructure.boundaries.inbound.rest

import com.github.jeroenr.hexagonal.domain.model.DepositRequestDto
import com.github.jeroenr.hexagonal.domain.model.DepositResponseDto
import com.github.jeroenr.hexagonal.domain.services.DepositOrchestrationService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.apache.logging.log4j.kotlin.logger
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/my/api")
@Tag(name = "MY-API-TAG", description = "Some description")
class DepositController(
    private val depositOrchestrationService: DepositOrchestrationService
) {

    /**
     * This is a sample of the POST Endpoint
     */
    @Operation(summary = "Make a deposit", description = "Make a deposit", tags = ["TAG"])
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Everything went fine", content = [Content()]),
            ApiResponse(responseCode = "403", description = "You don't have access", content = [Content()]),
        ]
    )
    @PostMapping(value = ["/deposit"], produces = [MediaType.APPLICATION_JSON_VALUE])
    suspend fun makeDeposit(@RequestBody depositRequestDto: DepositRequestDto): DepositResponseDto? {
        logger().info("Requested Deposit for user ${depositRequestDto.userId}.")

        return depositOrchestrationService.deposit(depositRequestDto)
    }
}
