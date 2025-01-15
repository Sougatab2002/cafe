package com.cts.RestImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.POJO.Order;
import com.cts.Service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderRestImpl {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/get")
	public  ResponseEntity<List<Order>> getAllOrder() {
		return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
	}
	
	@PostMapping("/add/{productId}")
	public ResponseEntity<Order> addOrder(@PathVariable("productId") Integer id ){
		return new ResponseEntity<>(orderService.addOrder(id),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Integer id){
		orderService.deleteOrder(id);
		return new ResponseEntity<>("Order: "+ id +" has been deleted",HttpStatus.OK);
	}
}
