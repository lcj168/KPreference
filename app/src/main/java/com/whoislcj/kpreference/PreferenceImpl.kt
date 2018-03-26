package com.whoislcj.kpreference

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import java.util.*

/**
 * Created by whoislcj on 2018/3/26.
 */
class PreferenceImpl : IPreference {
    private var preferences: SharedPreferences;

    companion object {
        /**
         * 默认的文件名
         */
       const val FILE_NAME = "app_shared_preferences"
    }


    /**
     * Instantiates a new Pref manager.
     */
    constructor() : this(FILE_NAME) {
    }

    /**
     * Instantiates a new Pref manager.
     *
     * @param fileName the file name
     */
    constructor(fileName: String) {
        val finalFileName = if (TextUtils.isEmpty(fileName)) FILE_NAME else fileName
        preferences = BaseApp.baseApp.getSharedPreferences(finalFileName, Context.MODE_PRIVATE)
    }

    /**
     * 保存数据
     *
     * @param editor
     * @param key
     * @param obj
     */
    private fun put(editor: SharedPreferences.Editor, key: String?, obj: Any) {
        // key 不为null时再存入，否则不存储
        if (key != null) {
            if (obj is Int) {
                editor.putInt(key, obj)
            } else if (obj is Long) {
                editor.putLong(key, obj)
            } else if (obj is Boolean) {
                editor.putBoolean(key, obj)
            } else if (obj is Float) {
                editor.putFloat(key, obj)
            } else if (obj is Set<*>) {
                editor.putStringSet(key, obj as Set<String>)
            } else if (obj is String) {
                editor.putString(key, obj.toString())
            }
        }
    }

    /**
     * 根据key和类型取出数据
     *
     * @param key
     * @return
     */
    private fun getValue(key: String, type: IPreference.DataType): Any? {
        when (type) {
            IPreference.DataType.INTEGER -> return preferences.getInt(key, -1);
            IPreference.DataType.FLOAT -> return preferences.getFloat(key, -1f);
            IPreference.DataType.BOOLEAN -> return preferences.getBoolean(key, false);
            IPreference.DataType.LONG -> return preferences.getLong(key, -1L);
            IPreference.DataType.STRING -> return preferences.getString(key, null);
            IPreference.DataType.STRING_SET -> return preferences.getStringSet(key, null);
            else -> return null;
        }
    }

    override fun put(key: String, value: Any) {
        val edit = preferences.edit()
        put(edit, key, value)
        edit.apply()
    }

    override fun putAll(map: Map<String, Any>) {
        val edit = preferences.edit()
        for ((key, value) in map) {
            put(edit, key, value)
        }
        edit.apply()
    }

    override fun putAll(key: String, list: List<String>) {
        putAll(key, list, ComparatorImpl())
    }

    override fun putAll(key: String, list: List<String>, comparator: Comparator<String>) {
        val set:java.util.TreeSet<String> = java.util.TreeSet<String>(comparator)
        set.addAll(set)
        preferences.edit().putStringSet(key, set).apply()
    }


    override fun <T> get(key: String, type: IPreference.DataType): T {
       return getValue(key,type) as T;

    }

    override fun getAll(): Map<String, *> {
       return preferences.all;
    }

    override fun getAll(key: String): List<String> {
        val list = ArrayList<String>()
        val set:HashSet<String> = get(key, IPreference.DataType.STRING_SET)
        list.addAll(set)
        return list
    }

    override fun remove(key: String) {
        preferences.edit().remove(key).apply()
    }

    override fun removeAll(keys: List<String>) {
        val edit = preferences.edit()
        for (k in keys) {
            edit.remove(k)
        }
        edit.apply()
    }

    override fun removeAll(vararg keys: String) {
        removeAll(Arrays.asList(*keys))
    }

    override fun contains(key: String): Boolean {
        return preferences.contains(key)
    }

    override fun clear() {
        preferences.edit().clear().apply();
    }

    override fun getString(key: String): String {
        return get(key,IPreference.DataType.STRING);
    }

    override fun getFloat(key: String): Float {
        return get(key,IPreference.DataType.FLOAT);
    }

    override fun getInteger(key: String): Int {
        return get(key,IPreference.DataType.INTEGER);
    }

    override fun getLong(key: String): Long {
        return get(key,IPreference.DataType.LONG);
    }

    override fun getSet(key: String): Set<String> {
        return get(key,IPreference.DataType.STRING_SET);
    }

    override fun getBoolean(key: String): Boolean {
        return get(key,IPreference.DataType.BOOLEAN);
    }

    /**
     * 默认比较器，当存储List集合中的String类型数据时，没有指定比较器，就使用默认比较器
     */
    class ComparatorImpl : java.util.Comparator<String> {

        override fun compare(lhs: String, rhs: String): Int {
            return lhs.compareTo(rhs)
        }
    }
}

