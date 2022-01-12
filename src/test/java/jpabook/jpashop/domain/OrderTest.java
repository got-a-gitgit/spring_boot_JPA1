package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderTest {


    @Test
    public void createOrderTest() throws Exception{
        //given
        Member member = createMember();
        Item item = createBook("해리포터",10000,10);
        Delivery delivery =createDelivery(member);
        int orderCount = 2;

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), orderCount);
        Order order = Order.createOrder(member, delivery, orderItem);

        //when
        System.out.println(order.getTotalPrice());
        System.out.println(order.getOrderItems());
        System.out.println(orderItem.getItem());
        System.out.println(orderItem.getCount());

        //then

    }

    private Delivery createDelivery(Member member) {
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);
        delivery.setId(1L);
        return delivery;
    }


    private Item createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("Jo");
        member.setAddress(new Address("서울","개운사2길","123-123"));
        return member;
    }

}