package taesu.faster.coop.cooperation.token.handler.impl;

import io.crscube.coop.cubetoken.cooperation.token.handler.impl.AccessTokenHandlerImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import io.crscube.coop.cubetoken.cooperation.CooperationApplication;
import io.crscube.coop.cubetoken.cooperation.token.exception.TokenExpirationException;
import io.crscube.coop.cubetoken.cooperation.token.exception.TokenIssuerNotEqualException;
import io.crscube.coop.cubetoken.cooperation.token.handler.TokenHandler;
import io.crscube.coop.cubetoken.cooperation.user.model.User;
import io.crscube.coop.cubetoken.cooperation.user.model.UserTokenType;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project cooperation
 * @since 2018-01-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = {CooperationApplication.class, AccessTokenHandlerImpl.class})
@Slf4j
public class AccessTokenHandlerImplTest {

    @Autowired
    private TokenHandler<User, UserTokenType> accessTokenHandler;

    @Before
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void equalTokenSourceAndParsedTokenSource() {
        User user = new User(13L, "test");

        String token = accessTokenHandler.generateTokenValue(user, UserTokenType.ACCESS_TOKEN);
        User parsed = accessTokenHandler.parseTokenValue(token);

        assertEquals("userId is same :", parsed.getUserId(), user.getUserId());
        assertEquals("userKey is same :", parsed.getUserKey(), user.getUserKey());
    }

    @Test(expected = TokenExpirationException.class)
    public void testForExpiredToken(){
        User user = new User(13L, "test");

        ZonedDateTime current = ZonedDateTime.now(ZoneOffset.UTC);
        ZonedDateTime expirationTime = current.minusHours(10L);

        String token = accessTokenHandler.generateTokenValue(user, UserTokenType.ACCESS_TOKEN, expirationTime);
         accessTokenHandler.parseTokenValue(token);
    }

    @Test(expected = TokenIssuerNotEqualException.class)
    public void testForInvalidTokenIssuer(){
        String invalidIssuerToken = "eyJjdHkiOiJKV1QiLCJlbmMiOiJBMjU2R0NNIiwiYWxnIjoiZGlyIn0.._J9Fu1TRAKj3uDM8.98J5p-haUSZt0DTAzl8oLLmiI59ytMVIsqqkWI2MoQlQSo-uV8rP1I2I-T7CLn2MBQ0gFl0JjiAAH5UW3U8g35Irs6qVVT-ACerWjL_bt0vPR0wCemTrUyuHHOzZEIwLWoH-50sYUQKoBv9gATHb9wqrNuovCTHXADHpBTHG0NEgymVJ-jLXWq56LWjwCHB-7p4xE9-jSqMJqDFc4azlrlF2TcmYy8YwZZPR8PhogdeKqFLeiB0za5MxyrixKBaRvqgS4_wFBy3j_8z2kpUygd4RNA.Abb6awaX0V-7_Xq44cMgHA";
        accessTokenHandler.parseTokenValue(invalidIssuerToken);
    }

    @Test
    public void testForUnlimitedExpirationTimeToken(){
        User user = new User(13L, "test");
        String token = accessTokenHandler.generateTokenValue(user, UserTokenType.ACCESS_TOKEN, null);
        accessTokenHandler.parseTokenValue(token);
    }
}