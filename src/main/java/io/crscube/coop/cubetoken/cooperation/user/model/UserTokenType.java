package io.crscube.coop.cubetoken.cooperation.user.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project cooperation
 * @since 2018-01-23
 */
public enum UserTokenType {

    ENCRYPT_TOKEN(0),
    ACCESS_TOKEN(1);

    private static final Map<Integer, UserTokenType> typeMap;

    static {
        Map<Integer, UserTokenType> map = new HashMap<>();

        for (UserTokenType type : values())
            map.put(type.getCode(), type);

        typeMap = Collections.unmodifiableMap(map);
    }

    public static UserTokenType get(int code) {
        return typeMap.get(code);
    }

    private Integer code;

    UserTokenType(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
