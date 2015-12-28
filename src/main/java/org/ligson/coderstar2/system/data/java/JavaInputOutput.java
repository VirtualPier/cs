package org.ligson.coderstar2.system.data.java;

import org.ligson.coderstar2.system.data.ObjectInputOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class JavaInputOutput implements ObjectInputOutput {

    private static final Logger logger = LoggerFactory.getLogger(JavaInputOutput.class);

    @Override
    public <T> T deserialize(byte[] buff, Class<T> clazz) {
        ByteArrayInputStream ins = null;
        ObjectInputStream ois = null;
        try {
            ins = new ByteArrayInputStream(buff);
            ois = new ObjectInputStream(ins);
            return (T) ois.readObject();
        } catch (IOException e) {
            logger.error("JavaInputOutput deserialize failed", e);
        } catch (ClassNotFoundException e) {
            logger.error("JavaInputOutput deserialize failed", e);
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    logger.error("JavaInputOutput deserialize ois close failed", e);
                }
            }
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    logger.error("JavaInputOutput deserialize ins close failed", e);
                }
            }
        }

        return null;
    }

    @Override
    public byte[] serialize(Object obj) {
        ByteArrayOutputStream out = null;
        ObjectOutputStream oos = null;

        try {
            out = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(out);
            oos.writeObject(obj);
            byte[] buff = out.toByteArray();
            return buff;
        } catch (IOException e) {
            logger.error("JavaInputOutput serialize failed", e);
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    logger.error("JavaInputOutput serialize oos close failed", e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("JavaInputOutput serialize out close failed", e);
                }
            }
        }

        return null;
    }

}
