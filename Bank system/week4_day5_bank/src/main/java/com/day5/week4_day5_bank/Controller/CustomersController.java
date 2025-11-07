package com.day5.week4_day5_bank.Controller;

import Api.ApiResponse;
import com.day5.week4_day5_bank.Model.Customers;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/customersbank")
public class CustomersController {

    ArrayList<Customers> customers=new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Customers> getCustomers(){
        return customers;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody Customers customer){
        this.customers.add(customer);
        return new ApiResponse("The customer have been added successfully");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customers customer){
        if (index<0|| index>=this.customers.size()){
            return new ApiResponse("Account not found");
        }
        this.customers.set(index,customer);
        return new ApiResponse("The customer have been updated successfully");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index){
        if (index<0|| index>=this.customers.size()){
            return new ApiResponse("Account not found");
        }
        this.customers.remove(index);
        return new ApiResponse("The customer have been deleted successfully");
    }

    @PutMapping("/deposit/{index}/{amount}")
    public ApiResponse depositToCustomer(@PathVariable int index,@PathVariable int amount){
        if (index<0|| index>=this.customers.size()){
            return new ApiResponse("Account not found");
        }
        this.customers.get(index).setBalance(this.customers.get(index).getBalance()+amount);
        return new ApiResponse("The amount have been deposited into the account successfully");
    }

    @PutMapping("/withdraw/{index}/{amount}")
    public ApiResponse withdrawFromCustomer(@PathVariable int index,@PathVariable int amount){
        if (index<0|| index>=this.customers.size()){
            return new ApiResponse("Account not found");
        }
        if (amount<=this.customers.get(index).getBalance()){
            this.customers.get(index).setBalance(this.customers.get(index).getBalance()-amount);
            return new ApiResponse("The amount have been withdrawn from the account successfully");
        }
        return new ApiResponse("You don't have sufficient funds for this transaction");
    }



}
