package com.vaddi.security.spring_security_database.mapper;


import com.vaddi.security.spring_security_database.dto.BookDto;
import com.vaddi.security.spring_security_database.entity.Book;

public class BookMapper {

	public static BookDto toDto(Book bookRepo) {

		BookDto bookDto = new BookDto(bookRepo.bookId(), 
				bookRepo.name(), bookRepo.price(),
				bookRepo.author(), bookRepo.description());

		return bookDto;
	}

	public static Book  toEntity(BookDto bookDto) {

		Book bookRepo = new Book(bookDto.bookId(), 
				bookDto.name(), bookDto.price(),
				bookDto.author(), bookDto.description());
		return bookRepo;
	}
}
