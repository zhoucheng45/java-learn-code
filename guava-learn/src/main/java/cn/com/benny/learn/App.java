package cn.com.benny.learn;

import com.google.common.collect.*;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

////        String[] strArr = {"a","b","dd","aa"};
//
//        List<String> collect = Arrays.stream(strArr).sorted().collect(Collectors.toList());
////        collect.stream().forEach(System.out::println);
//
//        List<String>[] a = new List[2];
//        a[0] = new ArrayList<>();
//        a[0].add("12");
//        a[0].add("a2");
//        a[0].add("b2");
//        a[0].add("sad");
//        a[1] = new ArrayList<>();
//        a[1].add("12");
//        a[1].add("a2");
//        a[1].add("b2");
//        a[1].add("sad");
//
////        IntStream.of(1,23,12,43,12)..sorted();
//        List<Stream<String>> collect = Arrays.stream(a).map(b -> b.stream().sorted()).collect(Collectors.toList());
//        System.out.println(collect.size());
//
//        List<String> collect1 = Arrays.stream(a).flatMap(b -> b.stream().sorted()).collect(Collectors.toList());
//        collect1.stream().forEach(System.out::println);
//
//        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3);
//        Iterables.concat(Ints.asList(1,2,3,4), Ints.asList(12,32,1,2,4));


        ArrayList<Integer> integers1 = Lists.newArrayList(1, 2, 3, 4, 5);
        integers1.subList(0,integers1.size()).stream().forEach(System.out::println);

    }
}
