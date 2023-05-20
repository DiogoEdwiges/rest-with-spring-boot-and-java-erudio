package br.com.erudio.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.mapper.DozerMapper;
import br.com.erudio.model.Book;
import br.com.erudio.unittests.mapper.mocks.MockBook;

public class DozerConverterTest_Book {
    
    MockBook inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockBook();
    }

    @Test
    public void parseEntityToVOTest() {
        BookVO output = DozerMapper.parseObject(inputObject.mockEntity(), BookVO.class);
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Author Test0", output.getAuthor());
        assertEquals(new Date(2022, 1, 1, 0, 0), output.getLaunch_date());
        assertEquals(10.0, output.getPrice());
        assertEquals("Title Test0", output.getTitle());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<BookVO> outputList = DozerMapper.parseListObjects(inputObject.mockEntityList(), BookVO.class);
        BookVO outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("Author Test0", outputZero.getAuthor());
        assertEquals(new Date(2022, 1, 1, 0, 0), outputZero.getLaunch_date());
        assertEquals(10.0, outputZero.getPrice());
        assertEquals("Title Test0", outputZero.getTitle());
        
        BookVO outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("Author Test7", outputSeven.getAuthor());
        assertEquals(new Date(2023, 1, 1, 0, 0), outputSeven.getLaunch_date());
        assertEquals(11.0, outputSeven.getPrice());
        assertEquals("Title Test7", outputSeven.getTitle());
        
        BookVO outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        assertEquals("Author Test12", outputTwelve.getAuthor());
        assertEquals(new Date(2022, 1, 1, 0, 0), outputTwelve.getLaunch_date());
        assertEquals(10.0, outputTwelve.getPrice());
        assertEquals("Title Test12", outputTwelve.getTitle());
    }

    @Test
    public void parseVOToEntityTest() {
        Book output = DozerMapper.parseObject(inputObject.mockVO(), Book.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Author Test0", output.getAuthor());
        assertEquals(new Date(2022, 1, 1, 0, 0), output.getLaunch_date());
        assertEquals(10.0, output.getPrice());
        assertEquals("Title Test0", output.getTitle());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Book> outputList = DozerMapper.parseListObjects(inputObject.mockVOList(), Book.class);
        Book outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Author Test0", outputZero.getAuthor());
        assertEquals(new Date(2022, 1, 1, 0, 0), outputZero.getLaunch_date());
        assertEquals(10.0, outputZero.getPrice());
        assertEquals("Title Test0", outputZero.getTitle());
        
        Book outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Author Test7", outputSeven.getAuthor());
        assertEquals(new Date(2023, 1, 1, 0, 0), outputSeven.getLaunch_date());
        assertEquals(11.0, outputSeven.getPrice());
        assertEquals("Title Test7", outputSeven.getTitle());
        
        Book outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Author Test12", outputTwelve.getAuthor());
        assertEquals(new Date(2022, 1, 1, 0, 0), outputTwelve.getLaunch_date());
        assertEquals(10.0, outputTwelve.getPrice());
        assertEquals("Title Test12", outputTwelve.getTitle());
    }
}
