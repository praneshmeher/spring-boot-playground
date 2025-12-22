package com.example.coding;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Playground {

    public static void main(String[] args){

//        Find the maximum and minimum values in a list using Streams

//        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
//        Integer max = list.stream().mapToInt(i -> i).max().orElse(0);
//        System.out.println(max);

//        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);
//        Integer max = list.stream().mapToInt(i -> i).min().orElse(0);
//        System.out.println(max);

//**********************************************************************************************************************

//        Count the occurrences of each character in a string

//        String str = "geeksforgeeks";
//        Map<Character, Long> counts1 = str.chars()
//                .mapToObj(c->(char) c)
//                .collect(Collectors.groupingBy(
//                        c-> c,
//                        LinkedHashMap::new,
//                        Collectors.counting()
//                ));
//        Map<Character, Long> counts2 = str.chars()
//                .mapToObj(c -> (char) c)
//                .collect(Collectors.toMap(
//                        c -> c,
//                        c -> 1L,
//                        Long::sum,
//                        LinkedHashMap::new
//                ));
//        System.out.println(counts1);
//        System.out.println(counts2);

//**********************************************************************************************************************

//        Find the first non-repeated character in a string

//        String str = "geeksforgeeks";
//        Map<Character, Integer> map = new LinkedHashMap<>();
//        for(int i=0; i<str.length(); i++){
//            map.put(str.charAt(i), map.containsKey(str.charAt(i)) ? map.get(str.charAt(i))+1:1);
//        }
//        for(Map.Entry<Character, Integer> entry : map.entrySet()){
//            if(entry.getValue()==1){
//                System.out.println(entry.getKey());
//                break;
//            }
//        }

//        String str = "geeksforgeeks";
//        Map<Character, Long> map = str.chars()
//                .mapToObj(c->(char) c)
//                .collect(Collectors.groupingBy(
//                        c-> c,
//                        LinkedHashMap::new,
//                        Collectors.counting()
//                ));
//
//        Map.Entry<Character, Long> entry = map.entrySet()
//                .stream()
//                .filter(e->e.getValue()==1)
//                .findFirst().orElse(null);
//        System.out.println(entry.getKey());

//**********************************************************************************************************************

//        Identify duplicate elements in a list

//        List<Integer> list = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6);
//        List<Integer> ans = list.stream()
//                .collect(Collectors.groupingBy(
//                        i->i,
//                        LinkedHashMap::new,
//                        Collectors.counting()
//                ))
//                .entrySet()
//                .stream()
//                .filter(e->e.getValue()>1)
//                .map(Map.Entry::getKey)
//                .toList();
//        System.out.println(ans);

//**********************************************************************************************************************

//        Sort a list of objects based on a specific field

//        class Person {
//            String name;
//            int age;
//            Person(String name, int age) { this.name = name; this.age = age; }
//            @Override public String toString() { return name + ":" + age; }
//        }
//
//        List<Person> people = Arrays.asList(
//                new Person("Alice", 30),
//                new Person("Bob", 25),
//                new Person("Charlie", 35)
//        );
//
//        List<Person> sortedByAge = people.stream().sorted((p1,p2)->Integer.compare(p1.age, p2.age)).toList();
//        List<Person> sortedByName = people.stream().sorted((p1,p2)->p1.name.compareTo(p2.name)).toList();
//        System.out.println(sortedByName);
//        System.out.println(sortedByAge);

    }

}
