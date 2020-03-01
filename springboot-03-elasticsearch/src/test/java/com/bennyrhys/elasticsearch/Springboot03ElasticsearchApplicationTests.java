package com.bennyrhys.elasticsearch;

import com.bennyrhys.elasticsearch.bean.Article;
import com.bennyrhys.elasticsearch.bean.Book;
import com.bennyrhys.elasticsearch.repository.BookRepository;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class Springboot03ElasticsearchApplicationTests {

    @Autowired
    JestClient jestClient;

    ///////////////
    // elasticsearch
    @Autowired
    BookRepository bookRepository;


    //存储
    @Test
    public void test02(){
//        Book book = new Book();
//        book.setId("2");
//        book.setBookName("游记 ");
//        book.setAuthor("承恩");
//        //文档存在哪个类型下要标注去
//        bookRepository.index(book);//存 6。x要求index一种类型的数据
        for (Book book : bookRepository.findByBookNameLike("游")) {
            System.out.println(book);
        }
        ;
    }

    ///////////////


    @Test
    void contextLoads() {
        //1、给es索引（文档）中保存一个文档
        Article article = new Article();
        article.setId(1);
        article.setTitle("好消息");
        article.setAuthor("zhangsan");
        article.setContent("hello world");

        //构建一个索引功能
        //id()上边有就没写
        Index index = new Index.Builder(article).index("bennyrhys").type("news").id("1").build();

        try {
            //执行
            jestClient.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //搜索
    @Test
    public void search(){
        //查询表达式
        String json="{\n" +
                "    \"query\" : {\n" +
                "        \"match\" : {\n" +
                "            \"about\" : \"hello\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        //构建搜索功能 这个地方没法配置到id
        Search search = new Search.Builder(json).addIndex("bennyrhys").addType("news").build();
        //执行
        try {
            SearchResult searchResult = jestClient.execute(search);
            System.out.println(searchResult.getJsonString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
