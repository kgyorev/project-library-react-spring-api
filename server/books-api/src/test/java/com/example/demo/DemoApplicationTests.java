package com.example.demo;

import com.example.demo.entities.Book;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Order;
import com.example.demo.enums.Status;
import com.example.demo.services.BookService;
import com.example.demo.services.CustomerService;
import com.example.demo.services.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {

	@Autowired
	private BookService bookService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;

	private Order orderNew;
	private Book bookNow;
	private Customer customerNew;



	@Before
	public void setUp() {



		this.bookNow = this.bookService.create("book1","autor1",10,"description one");;
		this.customerNew = this.customerService.create("name12","1111");
		this.orderNew = this.orderService.create(this.customerNew,this.bookNow);;




	}

	@Test
	public void createBook() {
	    Book book = this.bookService.create("book99","autor99",10,"sfsfsfsfs");
		Assert.assertEquals(book.getTitle(),"book99");
		Assert.assertEquals(book.getAuthor(),"autor99");
		Assert.assertEquals(Long.valueOf(book.getAvailableItems()),Long.valueOf(10));
		Assert.assertEquals(Long.valueOf(book.getOrderedItems()),Long.valueOf(0));
		Assert.assertEquals(book.getDescription(),"sfsfsfsfs");
	}
	@Test
	public void createCustomer() {
		Customer customer = this.customerService.create("name1","2342342343");
		Assert.assertEquals(customer.getName(),"name1");
		Assert.assertEquals(customer.getFnumber(),"2342342343");
	}
	@Test
	public void createOrder() {
		Order order = this.orderService.create(this.customerNew,this.bookNow);
		Assert.assertEquals(order.getBook().getTitle(),"book1");
		Assert.assertEquals(order.getCustomer().getFnumber(),"1111");
		Assert.assertEquals(Long.valueOf(order.getBook().getAvailableItems()),Long.valueOf(8));
		Assert.assertEquals(Long.valueOf(order.getBook().getOrderedItems()),Long.valueOf(2));
		Assert.assertEquals(order.getStatus(), Status.PENDING);
	}
	@Test
	public void returnBook() {
		Order orderReturn = this.orderService.returnBook(this.orderNew.getId());
		Assert.assertEquals(Long.valueOf(orderReturn.getBook().getAvailableItems()),Long.valueOf(10));
		Assert.assertEquals(Long.valueOf(orderReturn.getBook().getOrderedItems()),Long.valueOf(0));
		Assert.assertEquals(orderReturn.getStatus(), Status.FINISHED);
	}

}
