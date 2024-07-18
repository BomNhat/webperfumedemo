package web.Webperfume.service;


import web.Webperfume.model.CartItem;
import web.Webperfume.model.Order;
import web.Webperfume.model.OrderDetail;
import web.Webperfume.repository.OrderDetailRepository;
import web.Webperfume.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private web.Webperfume.service.CartService cartService; // Assuming you have a CartService

    @Transactional
    public Order createOrder(String customerName, String shippingAddress, String notes, String phoneNumber, String email, String paymentMethod, List<CartItem> cartItems) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setNotes(notes);
        order.setPhoneNumber(phoneNumber);
        order.setEmail(email);
        order.setPaymentMethod(paymentMethod);
        order.setShippingAddress(shippingAddress);


        order = orderRepository.save(order);
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }
        // Optionally clear the cart after order placement
        cartService.clearCard();
        return order;
    }
}
