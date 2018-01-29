package io.crscube.coop.cubetoken.cooperation.token.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project cooperation
 * @since 2018-01-23
 *
 * Token 의 발급처가 일치하지 않을 때 발생하는 Exception
 */
public class TokenIssuerNotEqualException extends AccessTokenInvalidException {

    public TokenIssuerNotEqualException(String msg) {
        super(msg);
    }

    public TokenIssuerNotEqualException(String msg, Throwable cause) {
        super(msg, cause);
    }

}