package org.ligson.coderstar.search;


import org.ligson.coderstar2.system.data.ObjectInputOutput;
import org.ligson.coderstar2.system.data.SerializeTool;
import org.ligson.coderstar2.system.data.java.JavaInputOutput;
import org.ligson.coderstar2.system.data.kryo.KryoInputOutput;
import org.ligson.coderstar2.user.domains.User;
import org.omg.CORBA.Object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ligso on 2016/1/4.
 * ligosn
 */
public class STest {
    static ObjectInputOutput inputOutput = new JavaInputOutput();
    public static void main(String args[]){


        ArrayList<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1111);
        userList.add(user1);
        User user2 = new User();
        user2.setId(1113);
        userList.add(user2);
        byte[] buffer = SerializeTool.serialize(userList);
        System.out.println(buffer);
        ArrayList<User> user = SerializeTool.deserialize(buffer,ArrayList.class);
        System.out.println(user);

        byte[] buffer2  = inputOutput.serialize(userList);
        List list = inputOutput.deserialize(buffer2,List.class);
        System.out.println(list);
    }
}
