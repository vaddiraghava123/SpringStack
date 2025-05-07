package com.vaddi.security.spring_security_database.entity;

public record Book(String bookId,
					String name,
					String price,
					String author,
					String description) {

}
