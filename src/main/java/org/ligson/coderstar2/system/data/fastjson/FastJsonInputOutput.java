package org.ligson.coderstar2.system.data.fastjson;


import com.alibaba.fastjson.JSONObject;
import org.ligson.coderstar2.system.data.ObjectInputOutput;

/**
 * Created by ligson on 2016/1/5.
 * fastjson
 */
public class FastJsonInputOutput implements ObjectInputOutput {
    @Override
    public <T> T deserialize(byte[] buff, Class<T> clazz) {
        return JSONObject.parseObject(buff, clazz);
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSONObject.toJSONBytes(obj);
    }
}
