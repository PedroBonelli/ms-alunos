package com.github.pedrobonelli.ms_alunos.service.exception;

import javax.xml.crypto.Data;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String msg){
        super(msg);
    }

}
