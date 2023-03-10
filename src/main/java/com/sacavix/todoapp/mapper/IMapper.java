package com.sacavix.todoapp.mapper;

public interface IMapper <I, O>{
    O mapearEntidad(I in);
    I mapearDto(O out);
}
