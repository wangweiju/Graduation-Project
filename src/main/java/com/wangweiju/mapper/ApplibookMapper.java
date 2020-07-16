package com.wangweiju.mapper;


import com.wangweiju.pojo.Applibook;
import com.wangweiju.pojo.Applibooks;
import com.wangweiju.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ApplibookMapper {
    List<Applibook> queryApplibookList();
    List<Applibook> queryApplibookByAuthor(String author);
    List<Applibook> queryApplibookByStastus(String status);
    List<Book> queryBookList();
    List<Book> queryBookByAuthort(String author);
    List<Book> queryBookListByDate01();
    List<Book> queryBookListByDate02();
    List<Book> queryBookListByDate03();
    List<Book> queryBookListByDate04();
    List<Book> queryBookListByDate05();
    List<Book> queryBookListByDate06();
    List<Book> queryBookListByDate07();
    Applibook queryApplibookById(int id);
    int addApplibook(Applibooks applibooks);
    int updateApplibook(Applibook applibook);
    int addBook(Applibook applibook);
}
