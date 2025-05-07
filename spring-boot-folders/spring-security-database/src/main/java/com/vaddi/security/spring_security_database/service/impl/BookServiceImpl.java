package com.vaddi.security.spring_security_database.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vaddi.security.spring_security_database.dto.BookDto;
import com.vaddi.security.spring_security_database.entity.Book;
import com.vaddi.security.spring_security_database.mapper.BookMapper;
import com.vaddi.security.spring_security_database.repository.BookRepository;
import com.vaddi.security.spring_security_database.service.BookService;

@Service
public class BookServiceImpl  implements BookService {

    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository)
    {
        this.bookRepository = bookRepository;
    }
    @Override
    public BookDto getBook(String bookId) {

        Book book = bookRepository.findBookByBookId(bookId);
        BookDto bookDto = BookMapper.toDto(book);
        return bookDto;
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();
        for(Book book: books)
        {
            bookDtoList.add(BookMapper.toDto(book));
        }
        return bookDtoList;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = bookRepository.insert(BookMapper.toEntity(bookDto));
        return BookMapper.toDto(book);
    }

    @Override
    public BookDto updateBookName(BookDto bookDto) {
        bookRepository.updateBookNameByBookId(bookDto.bookId(), bookDto.name());
        Book book = bookRepository.findBookByBookId(bookDto.bookId());
        return BookMapper.toDto(book);
    }

    @Override
    public void deleteBookByBookId(String bookId) {
        bookRepository.deleteBookByBookId(bookId);
    }
}
