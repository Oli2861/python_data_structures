package com.oli.event

import com.oli.orderdetails.MenuItem
import com.oli.proxies.Customer
import kotlinx.serialization.Serializable

interface CreateOrderSagaEvent: SagaEvent

@Serializable
data class ReplyEvent(
    override val sagaId: Int,
    val result: Boolean
): CreateOrderSagaEvent

@Serializable
data class VerifyCustomerCommandEvent(
    override val sagaId: Int,
    val customer: Customer
) : CreateOrderSagaEvent

@Serializable
data class CreateTicketCommandEvent(
    override val sagaId: Int,
    val customerId: Int,
    val menuItems: List<MenuItem>
) : CreateOrderSagaEvent

@Serializable
data class AuthorizationCommandEvent(
    override val sagaId: Int,
    val userId: Int,
    val paymentInfo: String
) : CreateOrderSagaEvent

@Serializable
data class ApproveTicketCommandEvent(
    override val sagaId: Int
) : CreateOrderSagaEvent

@Serializable
data class RejectTicketCommandEvent(
    override val sagaId: Int
) : CreateOrderSagaEvent
