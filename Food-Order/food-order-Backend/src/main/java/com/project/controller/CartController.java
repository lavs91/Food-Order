package com.project.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entity.Cart;
import com.project.service.CartService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/cart")
public class CartController {
	@Autowired
	private CartService cartService;

//to add carts
	@PostMapping("{customerId}/{dishId}")
	public ResponseEntity<Cart> addCart(@Valid @RequestBody Cart cart, @PathVariable long dishId,@PathVariable long customerId) {
		System.out.println("****");
		return new ResponseEntity<Cart>(cartService.addCart(cart, dishId,customerId), HttpStatus.CREATED);
	}

	// to getdish in cart
	@GetMapping("/list")
	public List<Cart> getAllCarts() {
		return cartService.getAllCarts();
	}
	

	// to get cart details
	@GetMapping("/Cart/{id}")
	public ResponseEntity<Cart> getCartById(@PathVariable("id") long cartId) {
		return new ResponseEntity<Cart>(cartService.getCartById(cartId), HttpStatus.OK);
	}

	// update cart
	@PutMapping("/{cartId}")
	public ResponseEntity<Cart> updateCart(@Valid @PathVariable("cartId") long cartId, @RequestBody Cart cart) {
		return new ResponseEntity<Cart>(cartService.updateCart(cart, cartId), HttpStatus.OK);
	}

	@DeleteMapping("/{cartId}")
	public ResponseEntity<Boolean> deleteCart(@PathVariable long cartId) {
		cartService.deleteCart(cartId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
	}
	
	

}