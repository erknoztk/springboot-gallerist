package com.erkanozturk.handler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError<E> {

          private Integer Status;

          private Exception<E> exception;
}
