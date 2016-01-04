package org.ligson.coderstar2.system.data.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import org.ligson.coderstar2.system.data.ObjectInputOutput;
import org.objenesis.strategy.StdInstantiatorStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * kryo 序列化反序列化实现类
 *
 * @author Administrator
 *         2015年12月22日上午11:30:53
 */
public class KryoInputOutput implements ObjectInputOutput {

    private static final Logger logger = LoggerFactory.getLogger(KryoInputOutput.class);

    private static Kryo kryo = null;

    private static Kryo getKryo() {
        if (kryo == null) {
            synchronized (KryoInputOutput.class) {
                if (kryo == null) {
                    kryo = new Kryo();
                }
            }
        }
        kryo.setRegistrationRequired(false);
        kryo.setReferences(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        kryo.register(ArrayList.class,new JavaSerializer());
        return kryo;
    }

    @Override
    public <T> T deserialize(byte[] buff, Class<T> clazz) {
        Input input = null;
        try {
            input = new Input(buff);
            return getKryo().readObject(input, clazz);
        } catch (Exception e) {
            logger.error("kryo deserialize failed", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (KryoException e) {
                    logger.error("kryo deserialize input close failed", e);
                }
            }
        }

        return null;
    }

    @Override
    public byte[] serialize(Object obj) {
        ByteArrayOutputStream baos = null;
        Output output = null;
        try {
            baos = new ByteArrayOutputStream();
            output = new Output(baos);
            getKryo().writeObject(output, obj);
            byte[] buff = output.toBytes();

            return buff;
        } catch (Exception e) {
            logger.error("kryo serialize failed", e);
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    logger.error("kryo serialize baos close failed", e);
                }
            }
            if (output != null) {
                try {
                    output.close();
                } catch (KryoException e) {
                    logger.error("kryo serialize output close failed", e);
                }
            }
        }

        return null;
    }

}
