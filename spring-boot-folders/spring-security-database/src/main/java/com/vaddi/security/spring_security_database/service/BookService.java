package com.vaddi.security.spring_security_database.service;

import java.util.List;

import com.vaddi.security.spring_security_database.dto.BookDto;

public interface BookService {

	public BookDto getBook(String bookId);

	public List<BookDto> getAllBooks();

	public BookDto createBook(BookDto bookDto);

	public BookDto updateBookName(BookDto bookDto);

	public void deleteBookByBookId(String bookId);

}
