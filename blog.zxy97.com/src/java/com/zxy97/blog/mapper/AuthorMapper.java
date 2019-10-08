package com.zxy97.blog.mapper;

import com.zxy97.blog.model.Author;

public interface AuthorMapper {
    public void authorRegister(Author author);
    public String authorRegisterCheck(String username);
    public Author authorLogin(Author author);
    public Author getAuthor(String username);
}
