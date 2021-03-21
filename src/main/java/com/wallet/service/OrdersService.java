package com.wallet.service;

import com.wallet.dao.OrdersDao;
import com.wallet.model.Orders;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class OrdersService {
    public class OrderService {
        private OrdersDao orderDao = new OrdersDao();

        @GET
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public List<Orders> getAllOrders() {
            return orderDao.getAllOrders();
        }

        @GET
        @Path("/{orderId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Orders getOrderById(@PathParam("orderId") int orderId) {
            return orderDao.getOrderById(orderId);
        }

        @POST
        @Produces(MediaType.APPLICATION_JSON)
        public String createOrder(Orders order) {
            return orderDao.createOrder(order);
        }

        @PUT
        @Path("/{orderId}")
        @Produces(MediaType.APPLICATION_JSON)
        public String updateOrder(Orders order, @PathParam("orderId") int orderId) {
            return orderDao.updateOrder(order);
        }

        @DELETE
        @Path("/{orderId}")
        @Produces(MediaType.APPLICATION_JSON)
        public void deleteUser(@PathParam("orderId") int orderId) {
            orderDao.deleteOrderById(orderId);
        }
    }
}

