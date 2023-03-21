package com.svalero.bestread.model;

import com.svalero.bestread.contract.BestReadContract;
import com.svalero.bestread.domain.Book;

import java.util.List;

public class BestReadModel implements BestReadContract.Model {
    @Override
    public List<Book> loadAllBooks() {
        return null;
    }

    @Override
    public Book getByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getById(long id) {
        return null;
    }

    @Override
    public void deleteByTitle(String title) {

    }

    @Override
    public void insert(Book book) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public void update(Book book) {

    }
}
