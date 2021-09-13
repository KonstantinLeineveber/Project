package com.example.servingwebcontent.util.userUtils;

import com.example.servingwebcontent.user.order.Order;
import com.example.servingwebcontent.user.order.OrderState;
//import by.yurachel.springapp.model.phone.Phone;
import com.example.servingwebcontent.util.orderUtils.OrderUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component(value = "userUtils")
public class UserUtils {
    private final OrderUtils orderUtils;

    public UserUtils(OrderUtils orderUtils) {
        this.orderUtils = orderUtils;
    }


    public void editOrder(List<Order> orders, Order order) {
        orders.forEach(o -> {
            if (o.getId() == order.getId()) {
                o.setState(order.getState());
            }
        });
    }


    public Order findOrder(List<Order> orders, long id) {
        return orders.stream().filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
    }


    public void addOrder(List<Order> orders, Order order) {
        orders.add(order);
    }


//    public boolean containsPhoneInPreparatoryOrder(List<Order> orders, long id) {
//
//        return orders.stream().filter(order2 -> OrderState.PREPARATORY.equals(order2.getState()))
//                .findFirst()
//                .map(order1 -> orderUtils.containsPhone(order1.getPhones(), id))
//                .orElse(false);
//    }


    public List<Order> getOrdersWithoutPreparatory(List<Order> orders) {
        return orders.stream().filter(order -> order.getState() != OrderState.PREPARATORY)
                .filter(order -> order.getState() != OrderState.DELETED)
                .collect(Collectors.toList());
    }


    public Order getPreparatoryOrder(List<Order> orders) {
        return orders.stream().filter(order -> order.getState().toString().equals("PREPARATORY"))
                .findFirst()
                .orElse(null);
    }

    public void deleteOrder(List<Order> orders, long id) {
        orders.removeIf(order -> order.getId() == id);
    }

//    public void addToBookMarks(List<Phone> phones, Phone phone) {
//        phones.add(phone);
//    }
//
//    public void deleteFromBookmarks(List<Phone> phones, long id) {
//         phones.removeIf(phone -> phone.getId() == id);
//    }
//
//    public boolean containsPhoneInBookmark(List<Phone> phones, long id) {
//        return phones.stream().anyMatch(phone -> phone.getId() == id);
//    }
}
