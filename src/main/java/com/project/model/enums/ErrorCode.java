package com.project.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {

    //USER CODES
    FB("Forbidden", HttpStatus.FORBIDDEN, ExitCode.KO),
    UA("Unauthorized", HttpStatus.UNAUTHORIZED, ExitCode.KO),
    INT("Invalid Token", HttpStatus.UNAUTHORIZED, ExitCode.KO),
    EXT("Expired Token", HttpStatus.UNAUTHORIZED, ExitCode.KO),
    ISE("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR, ExitCode.KO),
    IB("Invalid Body", HttpStatus.BAD_REQUEST, ExitCode.KO),
    IH("Invalid Header", HttpStatus.BAD_REQUEST, ExitCode.KO),
    BR("Bad request", HttpStatus.BAD_REQUEST, ExitCode.KO),
    RNF("The URL you have reached is not in service at this time", HttpStatus.SERVICE_UNAVAILABLE, ExitCode.KO),
    EBC("Bad Credentials", HttpStatus.BAD_REQUEST, ExitCode.KO),
    EUN("User not found", HttpStatus.BAD_REQUEST, ExitCode.KO),

    //PRENOTATION
    PNF("Prenotation not found", HttpStatus.NOT_FOUND, ExitCode.KO),
    NUP("No prenotations for user", HttpStatus.NO_CONTENT, ExitCode.KO),

    //CONCERT
    CNF("Concert not found", HttpStatus.NOT_FOUND, ExitCode.KO),
    CSO("Concert sold out", HttpStatus.BAD_REQUEST, ExitCode.KO),
    ICD("Invalid Concert date", HttpStatus.BAD_REQUEST, ExitCode.KO),

    //TICKET
    TNF("Ticket not found", HttpStatus.NOT_FOUND, ExitCode.KO),
    ITQ("Invalid ticket quantity", HttpStatus.BAD_REQUEST, ExitCode.KO);

    private String message;
    private HttpStatus status;
    private ExitCode exitCode;
}
