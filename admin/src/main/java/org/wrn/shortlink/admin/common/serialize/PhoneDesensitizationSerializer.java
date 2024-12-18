package org.wrn.shortlink.admin.common.serialize;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author: Admin
 * @Desc:
 * @create: 2024-12-18 22:43
 **/
public class PhoneDesensitizationSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String mobilePhone = DesensitizedUtil.mobilePhone(s);
        jsonGenerator.writeString(mobilePhone);
    }
}
