package br.com.erudio.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exceptions.RequiredObjectIsNullException;
import br.com.erudio.model.Book;
import br.com.erudio.repositories.BookRepository;
import br.com.erudio.services.BookServices;
import br.com.erudio.unittests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

	MockBook input;
	
	@InjectMocks
	private BookServices service;
	
	@Mock
	BookRepository repository;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Book> list = input.mockEntityList();
		
		// Quando 'repository.findById(1L)' for invocado, pela chamada de 'service.findById(1L)',
		// da linha subsequente, o Mockito retorna
		when(repository.findAll()).thenReturn(list);
		
		var books = service.findAll();
		
		assertNotNull(books);
		assertEquals(14, books.size());
		
		var bookOne = books.get(1);
		
		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", bookOne.getAuthor());
		assertEquals(new Date(2023, 1, 1, 0, 0), bookOne.getLaunch_date());
		assertEquals(11.0, bookOne.getPrice());
		assertEquals("Title Test1", bookOne.getTitle());
		
		var bookFour = books.get(4);
		
		assertNotNull(bookFour);
		assertNotNull(bookFour.getKey());
		assertNotNull(bookFour.getLinks());
		assertTrue(bookFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
		assertEquals("Author Test4", bookFour.getAuthor());
		assertEquals(new Date(2022, 1, 1, 0, 0), bookFour.getLaunch_date());
		assertEquals(10.0, bookFour.getPrice());
		assertEquals("Title Test4", bookFour.getTitle());
		
		var bookSeven = books.get(7);
		
		assertNotNull(bookSeven);
		assertNotNull(bookSeven.getKey());
		assertNotNull(bookSeven.getLinks());
		assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
		assertEquals("Author Test7", bookSeven.getAuthor());
		assertEquals(new Date(2023, 1, 1, 0, 0), bookSeven.getLaunch_date());
		assertEquals(11.0, bookSeven.getPrice());
		assertEquals("Title Test7", bookSeven.getTitle());
	}

	@Test
	void testFindById() {
		Book book = input.mockEntity(1);
		book.setId(1L);
		
		// Quando 'repository.findById(1L)' for invocado, pela chamada de 'service.findById(1L)',
		// da linha subsequente, o Mockito retorna
		when(repository.findById(1L)).thenReturn(Optional.of(book));
		
		var result = service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		assertEquals(new Date(2023, 1, 1, 0, 0), result.getLaunch_date());
		assertEquals(11.0, result.getPrice());
		assertEquals("Title Test1", result.getTitle());
	}

	@Test
	void testCreate() {
		Book entity = input.mockEntity(1);
		
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.create(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		assertEquals(new Date(2023, 1, 1, 0, 0), result.getLaunch_date());
		assertEquals(11.0, result.getPrice());
		assertEquals("Title Test1", result.getTitle());
	}
	
	
	@Test
	void testCreateWithNullBook() {
		// Expressão lambda invocando o serviço '() -> {...}'
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {		
		service.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testUpdate() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		Book persisted = entity;
		persisted.setId(1L);
		
		BookVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("Author Test1", result.getAuthor());
		assertEquals(new Date(2023, 1, 1, 0, 0), result.getLaunch_date());
		assertEquals(11.0, result.getPrice());
		assertEquals("Title Test1", result.getTitle());
	}

	@Test
	void testUpdateWithNullBook() {
		// Expressão lambda invocando o serviço '() -> {...}'
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {		
		service.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

	
	@Test
	void testDelete() {
		Book book = input.mockEntity(1);
		book.setId(1L);
		
		// Entender!!!
		when(repository.findById(1L)).thenReturn(Optional.of(book));
		
		service.delete(1L);
	}

}
