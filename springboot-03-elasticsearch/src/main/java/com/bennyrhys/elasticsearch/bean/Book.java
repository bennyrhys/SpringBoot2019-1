package com.bennyrhys.elasticsearch.bean;


import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "bennyrhys",type = "book ")//测试类存储要指定文档名
//get/set tostring
public class Book {
    private String id;//不写主键吗 ，默认主键？？
    private String bookName;
    private String author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
