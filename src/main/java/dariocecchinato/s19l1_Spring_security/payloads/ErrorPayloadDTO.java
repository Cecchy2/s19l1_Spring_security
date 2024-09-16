package dariocecchinato.s19l1_Spring_security.payloads;

import java.time.LocalDateTime;

public record ErrorPayloadDTO(String message, LocalDateTime errorTime) {
}
