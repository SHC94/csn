package com.csn.csn.Search;

import javax.persistence.Entity;

@Entity
public class NewsItem extends Item {

    private String title;
    private String origin;
    private String description;
}