package io.crscube.coop.cubetoken.cooperation.token.handler;

import io.crscube.coop.cubetoken.cooperation.token.model.TokenSource;

import java.time.ZonedDateTime;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project cooperation
 * @since 2018-01-23
 *
 * 토큰의 생성 및 생성 된 토큰을 Source 로 파싱처리하는 인터페이스
 * T: {@link TokenSource <T>} 을 구현한 Class
 * U: Token 의 타입 (Enum)
 *
 */
public interface TokenHandler<T extends TokenSource<U>, U> {

    /**
     * 만료시간이 지정되지 않은 토큰을 생성한다
     *
     * 각 구현체 별로 만료시간을 개별 지정할 수 있으며
     * 만료시간을 지정한 경우 반드시 parseTokenValue 메소드에서 만료시간이 지났는지를 검증해야 한다.
     *
     * T(source)는 반드시 getClaimedBuilder 메소드 내에서 U(tokenType)에 해당하는 case 를 구현해야 한다
     *
     * @param source Token source
     * @param tokenType Token type
     * @return Token value
     */
    String generateTokenValue(T source, U tokenType);

    /**
     * 만료시간이 지정 된 토큰을 생성한다
     *
     * expirationTime 이 null 인 경우 필요에 따라
     * 만료시간이 없는 토큰을 생성 할 수 있다
     *
     * @param source Token source
     * @param tokenType Token type
     * @param expirationTime 만료 시간
     * @return 지정 된 만료 시간으로 설정 된 Token value
     */
    String generateTokenValue(T source, U tokenType, ZonedDateTime expirationTime);

    /**
     * Token 을 파싱하여 원본 Source 를 반환한다
     *
     * @param tokenValue Token value
     * @return Token value 를 생성 하는 데 사용 된 Token source<T>
     */
    T parseTokenValue(String tokenValue);
}
