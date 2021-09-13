package com.example.servingwebcontent.util.orderUtils;

//import by.yurachel.springapp.model.phone.Phone;
import org.springframework.stereotype.Component;

@Component(value = "orderUtils")
public class OrderUtils {

//    public double orderSum(List<Phone> phones) {
//        return phones.stream().mapToDouble(Phone::getPrice).sum();
//    }
//
//
//    public void addPhone(List<Phone> phones, Phone phone) {
//        phones.add(phone);
//    }
//
//
//    public boolean containsPhone(List<Phone> phones, long id) {
//        Phone phone2 = phones.stream().filter(phone -> phone.getId() == id)
//                .findFirst()
//                .orElse(null);
//
//        return phone2 != null;
//    }


//    public void deletePhone(List<Phone> phones, long id) {
//        for (Phone phone : phones) {
//            if (phone.getId() == id) {
//                phones.remove(phone);
//                return;
//            }
//        }
//    }
//
//
//    public void deleteAllPhones(List<Phone> phones, long id) {
//        phones.removeIf(phone -> phone.getId() == id);
//
//    }
//
//
//    public Map<String, Integer> orderInformation(List<Phone> phones) {
//        Map<String, Integer> order = new HashMap<>();
//
//        for (Phone phone : phones) {
//            Integer value = order.get(phone.getName());
//            if (value == null) {
//                order.put(phone.getName(), 1);
//            } else {
//                order.put(phone.getName(), ++value);
//            }
//        }
//        return order;
//    }
//
//
//    public Map<String, Map<Phone, Integer>> convertListOfPhonesIntoMap(List<Phone> userPhones) {
//        Map<String, Map<Phone, Integer>> phones = new HashMap<>();
//        for (Phone phone : userPhones) {
//            Map<Phone, Integer> s = new HashMap<>();
//            if (phones.containsKey(phone.getName())) {
//                s = phones.get(phone.getName());
//                Set<Phone> set = s.keySet();
//                Phone p = (Phone) set.toArray()[0];
//                int i = s.get(p) + 1;
//                s.put(p, i);
//            } else {
//                s.put(phone, 1);
//            }
//            phones.put(phone.getName(), s);
//        }
//        return phones;
//    }
}
