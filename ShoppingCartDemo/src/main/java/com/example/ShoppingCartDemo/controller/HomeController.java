package com.example.ShoppingCartDemo.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ShoppingCartDemo.entity.Basket;
import com.example.ShoppingCartDemo.entity.Customer;
import com.example.ShoppingCartDemo.entity.Items;
import com.example.ShoppingCartDemo.entity.ShoppingCart;

@Controller
@RequestMapping("/shopping")
public class HomeController {
	
	ShoppingCart shoppingCart=new ShoppingCart("Big Bazer");
	
	@PostConstruct
	public void addcustomers() {
		
		
			shoppingCart.add_item(new Items("Juice",45.5,120));
	        shoppingCart.add_item(new Items("Shampoo",122.5,520));
	        shoppingCart.add_item(new Items("Biscuits",12.5,320));
	        shoppingCart.add_customer(new Customer("Ishan","0715822454",new Basket()));
	        shoppingCart.add_customer(new Customer("Reshmika","0715822454",new Basket()));
	        shoppingCart.add_customer(new Customer("Ish","0715822454",new Basket()));

	}
	
	@GetMapping("/init")
	public String homepage(Model model) {
		
		model.addAttribute("customers", shoppingCart.getCustomers());
		
		return "home";
	}
	
	
	@GetMapping("/shop")
	public String cust_items(@RequestParam("customer")String name,Model model) {
		
		int pos=shoppingCart.check_cust(name);
		
		if(pos>=0) {
			model.addAttribute("customer",name);
			model.addAttribute("items",shoppingCart.getItems());
			return "cust_list";
		}
		return "redirect:init";
	}
	
	
	@GetMapping("/addcustomer")
	public String add_cust(Model model) {
		
		model.addAttribute("customer", new Customer());
		
		return "cust_form";
	}
	
	@PostMapping("/savecust")
	public String save_cust(@ModelAttribute("customer")Customer customer) {
		if(customer.getName()!=null && customer.getContact()!=null) {
			
			int pos=shoppingCart.check_cust(customer.getName());
			Customer tosave=null;
			
			if(pos>=0) {
				tosave=shoppingCart.getCustomers().get(pos);
				tosave.setName(customer.getName());
				tosave.setContact(customer.getContact());
				
				return "redirect:init";
			}else {
				tosave=customer;
				shoppingCart.add_customer(tosave);
			}
		}
		return "redirect:init";
		
	}
	
	@GetMapping("/additems")
	public String add_item(Model model) {
		model.addAttribute("Item",new Items());
		
		return "item_form";
		
	}
	

}
