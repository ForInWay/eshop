package com.morgan.eshop.utils;

/**
 * @Description:
 * @Date:2020/8/22
 * @User:morgan.b.chen
 */
public class HashUtils {

    public static int hash(String key){
        int h;
        // 采用key的高16位与key的低16位进行异或操作，尽量减少产生hash碰撞
        int hash = key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        return hash;
    }
}
