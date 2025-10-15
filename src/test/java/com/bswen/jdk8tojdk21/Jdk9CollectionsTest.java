package com.bswen.jdk8tojdk21;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JDK 9 Collections Tests")
class Jdk9CollectionsTest {

    @Test
    @DisplayName("Test List.of() method")
    void testListOf() {
        // 测试List.of()创建不可变列表
        var emptyList = List.of();
        var singleElementList = List.of("one");
        var multipleElementsList = List.of("one", "two", "three");
        
        // 验证列表大小
        assertEquals(0, emptyList.size());
        assertEquals(1, singleElementList.size());
        assertEquals(3, multipleElementsList.size());
        
        // 验证列表内容
        assertEquals("one", singleElementList.get(0));
        assertEquals("two", multipleElementsList.get(1));
        
        // 验证列表类型
        assertTrue(emptyList instanceof List);
        assertTrue(singleElementList instanceof List);
        assertTrue(multipleElementsList instanceof List);
        
        // 验证不可变性 - 尝试修改应抛出UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> {
            emptyList.add("something");
        });
        
        assertThrows(UnsupportedOperationException.class, () -> {
            singleElementList.add("two");
        });
        
        assertThrows(UnsupportedOperationException.class, () -> {
            multipleElementsList.remove(0);
        });
    }
    
    @Test
    @DisplayName("Test Map.of() method")
    void testMapOf() {
        // 测试Map.of()创建不可变映射
        var emptyMap = Map.of();
        var singleEntryMap = Map.of("key1", "value1");
        var multipleEntriesMap = Map.of("key1", "value1", "key2", "value2", "key3", "value3");
        
        // 验证映射大小
        assertEquals(0, emptyMap.size());
        assertEquals(1, singleEntryMap.size());
        assertEquals(3, multipleEntriesMap.size());
        
        // 验证映射内容
        assertEquals("value1", singleEntryMap.get("key1"));
        assertEquals("value2", multipleEntriesMap.get("key2"));
        
        // 验证映射类型
        assertTrue(emptyMap instanceof Map);
        assertTrue(singleEntryMap instanceof Map);
        assertTrue(multipleEntriesMap instanceof Map);
        
        // 验证不可变性 - 尝试修改应抛出UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> {
            emptyMap.put("key", "value");
        });
        
        assertThrows(UnsupportedOperationException.class, () -> {
            singleEntryMap.put("key2", "value2");
        });
        
        assertThrows(UnsupportedOperationException.class, () -> {
            multipleEntriesMap.remove("key1");
        });
    }
    
    @Test
    @DisplayName("Test List.of() with duplicate elements")
    void testListOfWithDuplicates() {
        // List.of()允许重复元素
        var listWithDuplicates = List.of("a", "b", "a", "c", "b");
        
        assertEquals(5, listWithDuplicates.size());
        assertEquals("a", listWithDuplicates.get(0));
        assertEquals("a", listWithDuplicates.get(2));
        assertEquals("b", listWithDuplicates.get(1));
        assertEquals("b", listWithDuplicates.get(4));
    }
    
    @Test
    @DisplayName("Test Map.of() with duplicate keys throws exception")
    void testMapOfWithDuplicateKeys() {
        // Map.of()不允许重复键，应该在创建时就抛出IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            Map.of("key1", "value1", "key1", "value2");
        });
    }
    
    @Test
    @DisplayName("Test null key handling in Map.of()")
    void testMapOfWithNullKey() {
        // Map.of()不允许null键，应该抛出NullPointerException
        // 注意：这个异常是在创建Map时抛出的，而不是在调用put方法时
        assertThrows(NullPointerException.class, () -> {
            Map.of(null, "value");
        });
    }
    
    @Test
    @DisplayName("Test List.of() and Map.of() with generics")
    void testGenerics() {
        // 测试泛型类型推断
        List<String> stringList = List.of("a", "b", "c");
        List<Integer> integerList = List.of(1, 2, 3);
        
        Map<String, Integer> stringIntMap = Map.of("one", 1, "two", 2);
        Map<Integer, String> intStringMap = Map.of(1, "one", 2, "two");
        
        assertEquals("b", stringList.get(1));
        assertEquals(Integer.valueOf(2), integerList.get(1));
        assertEquals(1, stringIntMap.get("one"));
        assertEquals("two", intStringMap.get(2));
    }
}