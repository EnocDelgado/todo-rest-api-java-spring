package com.enocdelgado.dev.todoapp.mapper;

public interface IMapper <I, O> {
    public O map(I in);
}
