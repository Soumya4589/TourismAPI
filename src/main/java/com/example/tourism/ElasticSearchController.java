package com.example.tourism;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class ElasticSearchController {
    @Autowired
    private ElasticSearchQuery elasticSearchQuery;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getCustomer/{customerId}")
    public ResponseEntity<Object> getCustomerById(@PathVariable Integer customerId) throws IOException{
        String custId= String.valueOf(customerId);
        Customer customer=elasticSearchQuery.getDocumentById(custId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getCustomer")
    public ResponseEntity<Object> getAllCustomers() throws IOException{
        List<Customer> customers= elasticSearchQuery.viewAllDocuments();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/createOrUpdate")
    public ResponseEntity<String> createOrUpdateCustomer(@RequestBody Customer customer)throws IOException{
        String response= elasticSearchQuery.createOrUpdateInfo(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<Object> deleteCustomerById(@PathVariable Integer customerId) throws IOException{
        String custId=String.valueOf(customerId);
        String response= elasticSearchQuery.deleteDocumentById(custId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
