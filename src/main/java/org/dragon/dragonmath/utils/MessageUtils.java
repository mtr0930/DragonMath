package org.dragon.dragonmath.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

@Slf4j
public class MessageUtils {

    public static String getMessage(String code){
        try{
            MessageSource ms = BeanUtils.getBean(MessageSource.class);
            return ms.getMessage(code, null, Locale.KOREA);
        }catch (NoSuchMessageException e){
            log.error(e.getMessage(), e);
            return code;
        }
    }
}
