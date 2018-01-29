package io.crscube.coop.cubetoken.cooperation.token.model;

import com.nimbusds.jwt.JWTClaimsSet;
import io.crscube.coop.cubetoken.cooperation.user.model.User;
import io.crscube.coop.cubetoken.cooperation.user.model.UserTokenType;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project cooperation
 * @since 2018-01-23
 *
 * JWT 토큰의 Source 로 활용하기 위해 구현해야하는 인터페이스
 *
 * Token value 로부터 파싱 된 JWTClaimSet 을 통해
 * T 타입의 Source 를 반환하는 static method 를 구현하기를 권장 함
 * {@link User#getConvertedSource(JWTClaimsSet, UserTokenType)}
 *
 * 해당 메소드는 반드시 getClaimedBuilder 에 대응하는 필드를 기반으로 T 인스턴스를 변환해야 한다.
 *
 */
public interface TokenSource<T> {

    /**
     * 토큰의 타입 T에 따라 구현체에서 사용할 필드로 구성 된
     * Builder 을 반환한다
     *
     * @param type 생성 할 토큰의 타입
     * @return 토큰의 타입에 따라 build 된 ClaimSet Builder
     */
    JWTClaimsSet.Builder getClaimedBuilder(T type);
}
