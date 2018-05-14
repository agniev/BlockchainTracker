package com.example.blockchaintracker.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;


public enum SPWrapper {

    INSTANCE;

    private final static String SP_NAME = "sp_blockchain_tracker";

    private SharedPreferences sp;

    public void init(Context context) {
        sp = context.getApplicationContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public boolean contains(String key) {
        return sp.contains(key);
    }

    public boolean containsAll(String... keys) {
        for (String key : keys)
            if (!sp.contains(key))
                return false;
        return true;
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void putLong(String key, long value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void putStringSet(String key, Set<String> value) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    /**
     * @param key - имя ключа
     * @return значение по ключу или 0, если ключ не существует
     */
    public int getInt(String key) {
        return sp.getInt(key, 0);
    }

    /**
     * @param key      - имя ключа
     * @param defValue - значение по-умолчанию
     * @return значение по ключу или значение по-умолчанию, если ключ не
     * существует
     */
    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    /**
     * @param key - имя ключа
     * @return значение по ключу или 0, если ключ не существует
     */
    public long getLong(String key) {
        return sp.getLong(key, 0);
    }

    /**
     * @param key      - имя ключа
     * @param defValue - значение по-умолчанию
     * @return значение по ключу или значение по-умолчанию, если ключ не
     * существует
     */
    public long getLong(String key, long defValue) {
        return sp.getLong(key, defValue);
    }

    /**
     * @param key - имя ключа
     * @return значение по ключу или пустая строка, если ключ не существует
     */
    public String getString(String key) {
        return sp.getString(key, "");
    }

    /**
     * @param key      - имя ключа
     * @param defValue - значение по-умолчанию
     * @return значение по ключу или значение по-умолчанию, если ключ не
     * существует
     */
    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    /**
     * @param key - имя ключа
     * @return значение по ключу или false, если ключ не существует
     */
    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    /**
     * @param key      - имя ключа
     * @param defValue - значение по-умолчанию
     * @return значение по ключу или значение по-умолчанию, если ключ не
     * существует
     */
    public boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    /**
     * @param key      - имя ключа
     * @param defValue - значение по-умолчанию
     * @return значение по ключу или значение по-умолчанию, если ключ не
     * существует
     */
    public Set<String> getStringSet(String key, Set<String> defValue) {
        return sp.getStringSet(key, defValue);
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public void clean() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    public void remove(String... keys) {
        SharedPreferences.Editor editor = sp.edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.apply();
    }
}
