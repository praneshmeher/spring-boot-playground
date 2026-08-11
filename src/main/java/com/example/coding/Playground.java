package com.example.coding;

import org.springframework.context.annotation.Bean;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Playground {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        Find the maximum and minimum values in a list using Streams

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8);

        Integer max = list.stream().max((n1,n2)->Integer.compare(n1,n2)).get();
        System.out.println(max);

        Integer min = list.stream().min((n1,n2)->Integer.compare(n1,n2)).get();
        System.out.println(min);

        Integer max1 = list.stream().mapToInt(i -> i).max().orElse(0);
        System.out.println(max1);

        Integer min1 = list.stream().mapToInt(i -> i).min().orElse(0);
        System.out.println(min1);

//**********************************************************************************************************************

//        Count the occurrences of each character in a string
//        Find the frequency of each element in a list

        String str = "geeksforgeeks";

        Map<Character, Long> counts1 = str.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(
                c -> c,
                LinkedHashMap::new,     // ()-> new LinkedHashMap<>()
                Collectors.counting()
        ));
        System.out.println(counts1);

        Map<Character, Integer> counts2 = str.chars().mapToObj(c -> (char) c).collect(Collectors.toMap(
                c -> c,
                c -> 1,
                Integer::sum            // (a,b) -> Integer.sum(a,b)
        ));
        System.out.println(counts2);

        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        System.out.println(map2);

//**********************************************************************************************************************

//        Find the first non-repeated character in a string

//        String str = "geeksforgeeks";
//
//        Character s = str.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(
//                c -> c,
//                LinkedHashMap::new,
//                Collectors.counting()
//        )).entrySet().stream().filter(e -> e.getValue() == 1).findFirst().get().getKey();
//        System.out.println(s);
//
//        Map<Character, Integer> map = new LinkedHashMap<>();
//        for (int i = 0; i < str.length(); i++) {
//            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 1) + 1);
//        }
//        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
//            if (entry.getValue() == 1) {
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
//
//        Map<Integer, Long> map = list.stream().collect(Collectors.groupingBy(
//                i->i,
//                HashMap::new,
//                Collectors.counting()
//        )).entrySet().stream().filter(e->e.getValue()>1).collect(Collectors.toMap(
//                Map.Entry::getKey,
//                Map.Entry::getValue
//        ));
//
//        System.out.println(map);

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

        class Person {
            String name;
            int age;
            Person(String name, int age) { this.name = name; this.age = age; }
            @Override public String toString() { return name + ":" + age; }
        }

        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Pranesh", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35)
        );

        List<Person> listp = people.stream().sorted((p1, p2)-> Integer.compare(p1.age, p2.age)).toList();
        System.out.println(listp);

        List<Person> sortedByAge = people.stream().sorted((p1,p2)->Integer.compare(p1.age, p2.age)).toList();
        System.out.println(sortedByAge);

        List<Person> sortedByName = people.stream().sorted((p1,p2)->p1.name.compareTo(p2.name)).toList();
        System.out.println(sortedByName);

        Map<Integer, List<Person>> pMap = people.stream().collect(Collectors.groupingBy(p->p.age));
        System.out.println(pMap);

        Map<Integer, List<Person>> group = people.stream().collect(Collectors.groupingBy(p->p.age));
        System.out.println(group);

        Map<Integer, List<Person>> pmap = people.stream().collect(Collectors.groupingBy(
                p->p.age,
                TreeMap::new,
                Collectors.toList()
        ));

        System.out.println(pmap);

        Map<Integer, List<String>> byAge = people.stream()
                .collect(Collectors.groupingBy(
                        p -> p.age,
                        TreeMap::new,                       // keeps ages sorted
                        Collectors.mapping(p -> p.name, Collectors.toList())
                ));
        System.out.println(byAge);

//**********************************************************************************************************************

//        Remove duplicates from a list using Streams

        List<Integer> duplicates = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6);

        List<Integer> ansduplicates = duplicates.stream().distinct().collect(Collectors.toList());
        System.out.println(ansduplicates);

//**********************************************************************************************************************


//        Reverse a string

        String name = "My name is Pranesh";

        String reverse = name.chars().mapToObj(c -> String.valueOf((char) c)).reduce("", (c1, c2) -> c2 + c1);
        System.out.println(reverse);

        String ans = "";
        for (int i = name.length() - 1; i >= 0; i--) {
            ans = ans + name.charAt(i);
        }
        System.out.println(ans);

//**********************************************************************************************************************

//        Find the second highest number in a list

        List<Integer> secondhighestlist = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6);

        Integer numsecondhighest = secondhighestlist.stream().distinct().sorted((a, b) -> Integer.compare(b, a)).skip(1).findFirst().orElse(null);
        System.out.println(numsecondhighest);

        int max2 = secondhighestlist.stream().max((a, b) -> Integer.compare(a, b)).get();
        int secondMax = secondhighestlist.stream().filter(n -> n != max2).max((a, b) -> Integer.compare(a, b)).get();
        System.out.println(secondMax);

//**********************************************************************************************************************

//        Check if two strings are anagrams using Streams

        String s111 = "Listen";
        String s222 = "Silent";

        int[] arr11 = s111.toLowerCase().chars().filter(c -> Character.isLetterOrDigit(c) && !Character.isWhitespace(c)).sorted().toArray();
        int[] arr21 = s222.toLowerCase().chars().filter(c -> Character.isLetterOrDigit(c) && !Character.isWhitespace(c)).sorted().toArray();
        System.out.println(Arrays.equals(arr11, arr21));

        boolean areAnagrams = Arrays.equals(
                s111.toLowerCase().chars().filter(c -> !Character.isWhitespace(c) && Character.isLetterOrDigit(c)).sorted().toArray(),
                s222.toLowerCase().chars().filter(c -> !Character.isWhitespace(c) && Character.isLetterOrDigit(c)).sorted().toArray()
        );
        System.out.println(areAnagrams);

//**********************************************************************************************************************

//        Find the longest string in a list

        List<String> list11 = Arrays.asList("apple", "banana", "cherry", "dragonfruit", "fig");

        String str111 = list11.stream().max((s1, s2) -> Integer.compare(s1.length(), s2.length())).get();
        System.out.println(str111);

        String longest = list11.stream().collect(Collectors.toMap(
                ss -> ss,
                ss -> ss.length(),
                Integer::sum
        )).entrySet().stream().sorted((a, b) -> Long.compare(b.getValue(), a.getValue())).findFirst().get().getKey();
        System.out.println(longest);

//**********************************************************************************************************************

//        Partition a list into even and odd numbers

        List<Integer> numbers = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6);

        Map<Boolean, List<Integer>> map22 = numbers.stream().collect(Collectors.partitioningBy((n) -> n % 2 == 0));
        System.out.println(map22);

//**********************************************************************************************************************

//        group words by their length

        String str11[] = {"java", "spring", "boot", "api"};

        Map<Integer, List<String>> grp = Arrays.stream(str11).collect(Collectors.groupingBy(
                st -> st.length()
        ));
        System.out.println(grp);

//**********************************************************************************************************************

//        Print the series using Java 8 only

        int arr1[] = {11, 12, 15, 20, 27};
        Arrays.stream(arr1).forEach(System.out::println);
        Arrays.stream(arr1).forEach(n -> System.out.println(n));

//**********************************************************************************************************************

//        Find the 2nd most repeated character in a string

        String repeated = "aaaabbbccbdd";

        char srepeated = repeated.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(
                c -> c,
                Collectors.counting()
        )).entrySet().stream().sorted((a, b) -> Long.compare(b.getValue(), a.getValue())).skip(1).findFirst().get().getKey();
        System.out.println("2nd most repeated character " + srepeated);

        char trepeated = repeated.chars().mapToObj(c -> (char) c).collect(Collectors.toMap(
                c -> c,
                c -> 1,
                (a, b) -> a + b
        )).entrySet().stream().sorted((a, b) -> Long.compare(b.getValue(), a.getValue())).skip(2).findFirst().get().getKey();
        System.out.println("3rd most repeated character " + trepeated);

//**********************************************************************************************************************

//        Find out all the numbers starting with 1 using stream function

        int arr[] = {11, 18, 20, 24, 85, 66, 13};

        List<Integer> start1 = Arrays.stream(arr).boxed().filter(i -> Integer.toString(i).startsWith("1")).collect(Collectors.toList());
        System.out.println(start1);

        List<Integer> start2 = Arrays.stream(arr).boxed().filter(i -> {
            while (i >= 10) {
                i = i / 10;
            }
            return i == 2;
        }).collect(Collectors.toList());

        System.out.println(start2);

//**********************************************************************************************************************

        Runnable runnableTask = () -> System.out.println("Runnable running on: " + Thread.currentThread().getName());

        new Thread(runnableTask).start();

        CompletableFuture.runAsync(runnableTask).join();

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(runnableTask);

//**********************************************************************************************************************

        Callable<Integer> callable = () -> {
            System.out.println("Callable running on: " + Thread.currentThread().getName());
            return 10;
        };

        Future<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                return callable.call();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        future.get();

        ExecutorService executor1 = Executors.newSingleThreadExecutor();
        Future<Integer> future1 = executor1.submit(callable);
        future1.get();

//**********************************************************************************************************************

        List<List<Integer>> nested = Arrays.asList(List.of(1, 2), List.of(1, 2, 3));

        List<Integer> output = nested.stream().flatMap(list2 -> list2.stream()).collect(Collectors.toList());

        System.out.println(output);

//**********************************************************************************************************************

    }

}
