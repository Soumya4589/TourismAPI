package com.example.tourism;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ElasticSearchQuery {
    @Autowired
    private ElasticsearchClient elasticsearchClient;
    private final String indexName="customers";

    public String createOrUpdateInfo(Customer customer) throws IOException{
        IndexResponse response= elasticsearchClient
                .index(i->i.index(indexName)
                .id(customer.getId())
                .document(customer));
        if(response.result().name().equals("Created")){
            return new String(" Document has been successfully created");
        }
        else if(response.result().name().equals("Updated")){
            return new StringBuilder("Document has been successfully updated").toString();
        }
        return new StringBuilder("Error while performing the operation").toString();
    }

    public Customer getDocumentById(String customerId) throws IOException{
        Customer customer=null;
        GetResponse<Customer> response=elasticsearchClient.get(g->g.index(indexName).id(customerId),Customer.class);
        if(response.found()){
            customer= response.source();
            System.out.println("Customer Name: "  + customer.getName());
            System.out.println("No of Adults "  + customer.getNo_of_adults());
            System.out.println("No of Children: "  + customer.getNo_of_children());
            System.out.println("Place of visit "  + customer.getPlace_id());
            System.out.println("Contact "  + customer.getContact());
        }
        else {
            System.out.println("Customer Details Not Found!! ");
        }
        return customer;
    }

    public List<Customer> viewAllDocuments() throws IOException{
        SearchRequest searchRequest=SearchRequest.of(s->s.index(indexName));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest,Customer.class);
        List<Hit> hits=searchResponse.hits().hits();
        List<Customer> customers= new ArrayList<>();
        for(Hit object:hits){
            System.out.println(((Customer) object.source()));
            customers.add((Customer) object.source());
        }
        return customers;
    }

    public String deleteDocumentById(String customerId) throws IOException{
        DeleteRequest deleteRequest= DeleteRequest.of(d->d.index(indexName).id(customerId));
        DeleteResponse deleteResponse= elasticsearchClient.delete(deleteRequest);
        if(Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("Not Found")){
            return new StringBuilder("Customer with id: " + deleteResponse.id()+" has been deleted.").toString();
        }
        System.out.println("Customer Not Found");
        return new StringBuilder("Customer with id "+deleteResponse.id()+"does not exist.").toString();
    }
}


