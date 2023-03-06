package com.oli.order

import com.oli.event.RabbitMQBroker
import com.oli.orderdetails.OrderDetails
import com.oli.proxies.Address
import com.oli.proxies.Customer
import com.oli.proxies.CustomerServiceProxyImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

object OrderRoutingConstants {
    const val ORDER_ROUTE_PATH = "/order"
}

fun Route.orderRouting() {
    val orderService by inject<OrderService>()

    route(OrderRoutingConstants.ORDER_ROUTE_PATH) {

        post {
            val orderDetails: OrderDetails = call.receive()
            TODO("Not yet implemented")
            /*
            // val createdOrder = orderService.createOrder(orderDetails.userId, orderDetails.orderingDate)
            return@post if (createdOrder == null) {
                call.respondText("Unable to create the order", status = HttpStatusCode.Conflict)
            } else {
                call.respondText("Created order with orderID ${createdOrder.id}", status = HttpStatusCode.Created)
            }
             */
        }

        delete("{id}") {
            val id: String = call.parameters["id"] ?: return@delete call.respondText(
                "Missing orderId",
                status = HttpStatusCode.BadRequest
            )
            val success: Boolean = orderService.deleteOrder(id) == 1

            return@delete if (success) {
                call.respondText("Order with orderID $id is deleted.", status = HttpStatusCode.OK)
            } else {
                call.respondText("Unable to delete order with orderID $id", status = HttpStatusCode.NotFound)
            }
        }

        get("{id}") {
            val id: String = call.parameters["id"] ?: return@get call.respondText(
                "Missing orderId",
                status = HttpStatusCode.BadRequest
            )
            val order = orderService.readOrder(id)

            return@get if (order == null) {
                call.respondText("Unable to retrieve order with id $id", status = HttpStatusCode.NotFound)
            } else {
                call.respond(order)
            }
        }
    }
}
