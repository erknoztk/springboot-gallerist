package com.erkanozturk.mapper;


public interface BaseMapper<U, E, D> {

          E toModel(D dtoIU);

          U toDto(E model);

}
