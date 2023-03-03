package com.example.tourism;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "customers")
public class Customer {
    @Id
    private String id;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type = FieldType.Integer, name = "no_of_adults")
    private Integer no_of_adults;

    @Field(type = FieldType.Integer, name = "no_of_children")
    private Integer no_of_children;

    @Field(type = FieldType.Text, name = "place_id")
    private String place_id;

    @Field(type = FieldType.Long, name = "contact")
    private Long contact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo_of_adults() {
        return no_of_adults;
    }

    public void setNo_of_adults(int no_of_adults) {
        this.no_of_adults = no_of_adults;
    }

    public int getNo_of_children() {
        return no_of_children;
    }

    public void setNo_of_children(int no_of_children) {
        this.no_of_children = no_of_children;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

}
