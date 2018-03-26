package com.whoislcj.kpreference

import android.renderscript.Element

/**
 * Created by whoislcj on 2018/3/26.
 */
interface IPreference {

    /**
     * 保存一个数据
     * @param key 键
     * @param value 值
     */
    fun put(key: String, value: Any);

    /**
     * 保存一个Map集合
     * @param map
     */
    fun putAll(map: Map<String, Any>);

    /**
     * 保存一个List集合
     * @param key
     * @param list
     */
    fun putAll(key: String, list: List<String>);

    /**
     * 保存一个List集合，并且自定保存顺序
     * @param key
     * @param list
     * @param comparator
     */
    fun putAll(key: String, list: List<String>, comparator: Comparator<String>);

    /**
     * 根据key取出一个数据
     * @param key 键
     */
    fun <T> get(key: String, type: IPreference.DataType): T;

    /**
     * 取出全部数据
     */
    fun getAll(): Map<String, *>;

    /**
     * 取出一个List集合
     * @param key
     * @return
     */
    fun getAll(key: String): List<String>;

    /**
     * 移除一个数据
     * @param key
     * @return
     */
    fun remove(key: String);

    /**
     * 移除一个集合的数据
     * @param keys
     * @return
     */
    fun removeAll(keys: List<String>);

    /**
     * 移除一个集合的数据
     * @param keys
     * @return
     */
    fun removeAll(vararg keys: String);

    /**
     * 是否存在key
     * @return
     */
    fun contains(key: String): Boolean;

    /**
     * 清除全部数据
     */
    fun clear();

    /**
     * 获取String类型的数据
     * @param key
     * @return
     */
    fun getString(key: String): String;

    /**
     * 获取Float类型的数据
     * @param key
     * @return
     */
    fun getFloat(key: String): Float;

    /**
     * 获取int类型的数据
     * @return
     */
    fun getInteger(key: String): Int;

    /**
     * 获取long类型的数据
     * @param key
     * @return
     */
    fun getLong(key: String): Long;

    /**
     * 获取Set类型的数据
     * @param key
     * @return
     */
    fun getSet(key: String): Set<String>;

    /**
     * 获取boolean类型的数据
     * @param key
     * @return
     */
    fun getBoolean(key: String): Boolean;

    /**
     * 枚举：存储或取出的数据类型
     */
    enum class DataType {
        INTEGER, LONG, BOOLEAN, FLOAT, STRING, STRING_SET
    }

}