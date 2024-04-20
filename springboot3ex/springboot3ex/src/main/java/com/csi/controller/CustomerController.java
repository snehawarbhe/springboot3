package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Customer;
import com.csi.service.CustomerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<Customer> signUp(@Valid @RequestBody Customer customer){
        return new ResponseEntity<>(customerServiceImpl.signUp(customer), HttpStatus.CREATED);
    }

    @GetMapping("/signin/{custEmailId}/{custPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String custEmailId,@PathVariable String custPassword){
        return ResponseEntity.ok(customerServiceImpl.signIn(custEmailId,custPassword));

    }
    @GetMapping("/findbyid/{custId}")

    public ResponseEntity<Optional<Customer>> findById(@PathVariable int custId){
        return ResponseEntity.ok(customerServiceImpl.findById(custId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity.ok(customerServiceImpl.findAll());
    }

    @PutMapping("/update/{custId}")
    public ResponseEntity<Customer> update(@PathVariable int custId,@Valid @RequestBody Customer customer){
        Customer customer1=customerServiceImpl.findById(custId).orElseThrow(()-> new  RecordNotFoundException("Customer ID does not Exist"));

        customer1.setCustName(customer.getCustName());
        customer1.setCustAddress(customer.getCustAddress());
        customer1.setCustContactNumber(customer.getCustContactNumber());
        customer1.setCustDOB(customer.getCustDOB());
        customer1.setCustEmailId(customer.getCustEmailId());
        customer1.setCustPassword(customer.getCustPassword());

        return new ResponseEntity<>(customerServiceImpl.update(customer1) ,HttpStatus.CREATED);
    }
    @DeleteMapping("deletebyid/{custId}")

    public ResponseEntity<String> deleteById(@PathVariable int custId){
       customerServiceImpl.deleteById(custId);

       return ResponseEntity.ok("Data deleted successsfully");
    }
}
