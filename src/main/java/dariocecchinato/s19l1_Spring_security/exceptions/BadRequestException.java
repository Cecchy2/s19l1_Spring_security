package dariocecchinato.s19l1_Spring_security.exceptions;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String msg){
		super(msg);
	}
}
