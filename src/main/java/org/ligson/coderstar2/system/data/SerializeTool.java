package org.ligson.coderstar2.system.data;


import org.ligson.coderstar2.system.data.kryo.KryoInputOutput;

/**
 * 序列化反序列化工具类
 * @author Administrator
 * 2015年12月22日上午11:31:30
 */
public class SerializeTool {

	static ObjectInputOutput inputOutput = new KryoInputOutput();
	
	public static void setInputOutput(ObjectInputOutput inputOutput) {
		SerializeTool.inputOutput = inputOutput;
	}
	
	public static byte[] serialize(Object obj) {
		return inputOutput.serialize(obj);
	}
	
	public static <T> T deserialize(byte[] buff, Class<T> clazz) {
		return inputOutput.deserialize(buff, clazz);
	}
	
}
