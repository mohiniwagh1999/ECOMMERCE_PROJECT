package com.order.controller;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.order.client.AddressClient;
import com.order.client.CustomerClient;
import com.order.entity.OrderItems;
import com.order.entity.Orders;
import com.order.request.CheckoutDetails;
import com.order.request.Customer;
import com.order.request.LoginRequest;
import com.order.request.OrderData;
import com.order.request.PaymentInfo;
import com.order.response.AddressReponse;
import com.order.response.CustomerResponse;
import com.order.response.DashboardResponse;
import com.order.response.NotificationResponse;
import com.order.response.OrderResponse;
import com.order.service.OrderItemService;
import com.order.service.OrderService;
import com.order.service.TrackingNumGenerator;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;



@RestController
@CrossOrigin(origins="http://localhost:4200")
public class OrderController {
	@Autowired
 private CustomerClient client;
	
	@Autowired
	private AddressClient client1;
	
	private  RazorpayClient client2;
	@Value("${razorpay.key.id}")
	private String razorPayKey;  
	@Value("${razorpay.secret.key}")
	private String razorPaySecretKey;
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderItemService itemservice;
	
	@PostMapping("/api/checkdetails")
	public OrderResponse getCheckoutDetails(@RequestBody CheckoutDetails detail)
	throws RazorpayException{
		
		Customer customer=detail.getCustomer();
		CustomerResponse response=client.saveCustomer(customer);
		
		Address address=detail.getAddress();
		AddressReponse aresponse=client1.saveAddress(address);
		
		OrderData orderData=detail.getOrderData();
		System.out.println(orderData.getTotalPrice());
		JSONObject orderReq=new JSONObject();
		
		orderReq.put("amount",orderData.getTotalPrice()*100);
		orderReq.put("currency","INR");
		
		this.client2=new RazorpayClient(razorPayKey,razorPaySecretKey);
		Order razorPayOrder=client2.orders.create(orderReq);
		
		Orders orders=Orders.builder()
				.OrderTrackingNum(TrackingNumGenerator.generateTrakingnum())
				.TotalQuantity(orderData.getTotalQuantity())
				.TotalPrice(orderData.getTotalPrice())
				.razorPayPaymentId(razorPayOrder.get("id"))
				.OrderStatus(razorPayOrder.get("status"))
				.customerId(response.getId())
				.addressId(aresponse.getId())
				.build();
		
		Orders saveOrderDetails=orderService.saveOrder(orders);
		List<OrderItems> orderItems=detail.getOrderItems();
		for(OrderItems item:orderItems)
		{
			OrderItems itemsDetails =OrderItems.builder()
					.imageUrl(item.getImageUrl())
					.quantity(item.getQuantity())
					.order(saveOrderDetails)
					.productId(item.getProductId())
					.price(item.getPrice())
					.build();
			itemservice.saveOrderItem(itemsDetails);
		}
		
		 return OrderResponse.builder()
	                .orderTrackingNumber(saveOrderDetails.getOrderTrackingNum())
	                .razorPayPaymentId(saveOrderDetails.getRazorPayPaymentId())
	                .build();
	    }
		
	 @PostMapping("/update-payment-status")
	    public void updatePaymentStatus(@RequestBody PaymentInfo paymentInfo){
	        Optional<Orders> orderByPaymentOrderId = orderService.findByRazorPayPaymentId(paymentInfo.getOrderId());
	        if (orderByPaymentOrderId.isPresent()){
	            Orders orderData = orderByPaymentOrderId.get();
	            orderData.setOrderStatus(paymentInfo.getStatus());
	            orderService.saveOrder(orderData);
	        }
	    }
	 @PostMapping("/find-customer")
	    public List<DashboardResponse> findCustomerByEmailAndPassword(@RequestBody LoginRequest loginRequest){


	        CustomerResponse customerByEmailAndPassword = client.findCustomerByEmailAndPassword(loginRequest);
	        if (customerByEmailAndPassword != null){
	           List<Orders> orderDataCustomerId = orderService.findByCustomerId(customerByEmailAndPassword.getId());

	          return orderDataCustomerId.stream().map(DashboardResponse::fromOrder).toList();
	        }
	        return null;
	    }
	 @PostMapping("/find-customer-by-email/{email}")
	    public boolean findCustomerByEmail(@PathVariable String email){
	        boolean customerByEmail = client.findCustomerByEmail(email);
	        if (customerByEmail)
	            return true;
	        return false;
		
		
	}
	 
	  @GetMapping("/orderData")
	    public List<NotificationResponse> getOrdersByDeliveryDate(){
	        List<Orders> ordersByDeliveryDate = orderService.getOrdersByDeliveryDate();

	      return  ordersByDeliveryDate.stream().map(NotificationResponse::fromOrderData).toList();
	    }

	
 
}
