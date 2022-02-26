package com.demo.springbootapp.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No Users Found")
public class NoUsersException extends RuntimeException {
}
