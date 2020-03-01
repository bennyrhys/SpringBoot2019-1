package com.bennyrhys.elasticsearch.repository;

import com.bennyrhys.elasticsearch.bean.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

//两个范型，数据的类型，主键的类型  写bean book
public interface BookRepository extends ElasticsearchRepository<Book,String> {
    //基本的增删该查都有 ctrl+f12
     public List<Book> findByBookNameLike(String name);

}
