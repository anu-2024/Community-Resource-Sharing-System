
package com.community.resources.model;

import jakarta.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String description;
    private String condition;
    private String location;
    private String donorName;
    private String contact;
    private String status;

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getName(){ return name; }
    public void setName(String name){ this.name=name; }

    public String getCategory(){ return category; }
    public void setCategory(String category){ this.category=category; }

    public String getDescription(){ return description; }
    public void setDescription(String description){ this.description=description; }

    public String getCondition(){ return condition; }
    public void setCondition(String condition){ this.condition=condition; }

    public String getLocation(){ return location; }
    public void setLocation(String location){ this.location=location; }

    public String getDonorName(){ return donorName; }
    public void setDonorName(String donorName){ this.donorName=donorName; }

    public String getContact(){ return contact; }
    public void setContact(String contact){ this.contact=contact; }

    public String getStatus(){ return status; }
    public void setStatus(String status){ this.status=status; }
}
