package org.ligson.coderstar2.system.data;

public interface ObjectInputOutput {

	/**
	 * 反序列化
	 * @param buff
	 * @param clazz
	 * @return
	 * @date 2015年12月22日 上午11:31:15
	 */
	public <T> T deserialize(byte[] buff, Class<T> clazz);
	
	/**
	 * 序列化
	 * @param obj
	 * @return
	 * @date 2015年12月22日 上午11:31:24
	 */
	public byte[] serialize(Object obj);
	
}
