package com.example.coding;

import java.lang.reflect.Array;
import java.util.*;
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
//        Find the frequency of each element in a list

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
//        Group objects by a specific field (e.g., age)

//        class Person {
//            String name;
//            int age;
//            Person(String name, int age) { this.name = name; this.age = age; }
//            @Override public String toString() { return name + ":" + age; }
//        }
//
//        List<Person> people = Arrays.asList(
//                new Person("Alice", 30),
//                new Person("Pranesh", 30),
//                new Person("Bob", 25),
//                new Person("Charlie", 35)
//        );
//
//        List<Person> sortedByAge = people.stream().sorted((p1,p2)->Integer.compare(p1.age, p2.age)).toList();
//        List<Person> sortedByName = people.stream().sorted((p1,p2)->p1.name.compareTo(p2.name)).toList();
//        System.out.println(sortedByName);
//        System.out.println(sortedByAge);

//        Map<Integer, List<Person>> group = people.stream().collect(Collectors.groupingBy(p->p.age));
//        System.out.println(group);
//
//        Map<Integer, List<String>> byAge = people.stream()
//                .collect(Collectors.groupingBy(
//                        p -> p.age,
//                        TreeMap::new,                       // keeps ages sorted
//                        Collectors.mapping(p -> p.name, Collectors.toList())
//                ));
//        System.out.println(byAge);

//**********************************************************************************************************************

//        Remove duplicates from a list using Streams

//        List<Integer> list = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6);
//        List<Integer> ans = list.stream().collect(Collectors.groupingBy(
//                i->i,
//                LinkedHashMap::new,
//                Collectors.counting()
//        )).entrySet().stream().map(e->e.getKey()).toList();
//
//        List<Integer> ans1 = list.stream().distinct().toList();
//        System.out.println(ans1);

//**********************************************************************************************************************

//        Reverse a string using Stream operations

//        String str = "Pranesh";
//        String reverse = str.chars()
//                .mapToObj(c -> String.valueOf((char) c))
//                .reduce("",(s, c)-> c + s);
//        System.out.println(reverse);

//**********************************************************************************************************************

//        Find the second highest number in a list

//        List<Integer> list = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6);
//        Integer num =  list.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(null);
//        System.out.println(num);

//**********************************************************************************************************************

//        Check if two strings are anagrams using Streams
//        String s1 = "Listen";
//        String s2 = "Silent";
//        boolean areAnagrams = Arrays.equals(
//                s1.toLowerCase().chars()
//                        .filter(c -> !Character.isWhitespace(c) && Character.isLetterOrDigit(c))
//                        .sorted()
//                        .toArray(),
//                s2.toLowerCase().chars()
//                        .filter(c -> !Character.isWhitespace(c) && Character.isLetterOrDigit(c))
//                        .sorted()
//                        .toArray()
//        );
//        System.out.println(areAnagrams);

//**********************************************************************************************************************

//        Find the longest string in a list

//        List<String> list = Arrays.asList("apple", "banana", "cherry", "dragonfruit", "fig");
//
//        String longest = list.stream()
//                .max(Comparator.comparingInt(String::length))
//                .orElse(null);
//        System.out.println(longest);

//**********************************************************************************************************************

//        Partition a list into even and odd numbers

//        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6);
//        Map<Boolean, List<Integer>> nums = numbers.stream().collect(Collectors.partitioningBy(n->n%2==0));
//        System.out.println(nums);

//**********************************************************************************************************************

//        group words by their length
//        String str[] = {"java", "spring", "boot", "api"};
//        Map<Integer, List<String>> group = Arrays.stream(str).collect(Collectors.groupingBy(s->s.length()));
//        System.out.println(group);

//**********************************************************************************************************************
//        Print the series using Java 8 only

//        int arr[] = {11, 12, 15, 20, 27};
//        Arrays.stream(arr).forEach(n -> System.out.println(n));

//**********************************************************************************************************************

//        Find the 2nd most repeated character in a string

//        String str = "aaaabbccbdd";
//        char s = str.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(c->c,
//                LinkedHashMap::new,
//                Collectors.counting())).entrySet().stream().skip(1).findFirst().orElse(null).getKey();
//        System.out.println(s);

//**********************************************************************************************************************

//        Find out all the numbers starting with 1 using stream function

        int arr[] = {11, 18, 20, 24, 85, 66, 13};
        Arrays.stream(arr).filter(n->Integer.toString(n).startsWith("1")).forEach(System.out::println);





    }

}
