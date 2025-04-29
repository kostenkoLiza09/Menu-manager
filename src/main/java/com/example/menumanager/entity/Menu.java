package com.example.menumanager.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("menus")
public record Menu(  String id,
                     String name,
                     double price,
                     String mainDish,
                     String sideDish,
                     String beverage) {
}
