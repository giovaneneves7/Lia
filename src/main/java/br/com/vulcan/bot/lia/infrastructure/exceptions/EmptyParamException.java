package br.com.vulcan.bot.lia.infrastructure.exceptions;

public class EmptyParamException extends NullPointerException{

    public EmptyParamException(String message){

        super(message);

    }
}
