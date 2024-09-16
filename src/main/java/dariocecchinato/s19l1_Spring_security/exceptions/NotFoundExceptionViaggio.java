package dariocecchinato.s19l1_Spring_security.exceptions;

import java.time.LocalDate;

public class NotFoundExceptionViaggio extends RuntimeException {

    public NotFoundExceptionViaggio(String destinazione, LocalDate dataViaggio) {
        super("Il viaggio per " + destinazione + " del " + dataViaggio + " non è stato trovato.");
    }
}