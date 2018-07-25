package ru.job4j.dao;

import ru.job4j.Model;

import java.util.List;

interface ABaseDaoEntity<T extends Model> {
    int insert(T model);

    List<T> getAll();
}