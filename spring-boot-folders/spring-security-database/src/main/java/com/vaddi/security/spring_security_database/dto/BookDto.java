package com.vaddi.security.spring_security_database.dto;

public record BookDto(String bookId,
					String name,
					String price,
					String author,
					String description) {

}
