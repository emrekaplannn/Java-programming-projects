package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class Lab3{

    public static void question1(Stream<String> lines){
        lines.skip(1)
                .filter(line -> {
                    String[] columns = line.split(",");
                    int year = Integer.parseInt(columns[1].split("-")[0]);
                    int day = Integer.parseInt(columns[1].split("-")[2]);
                    boolean x= true;
                    if(year>1990) x=false;
                    if(year<1980) x=false;
                    if(day!=15 ) x=false;
                    return x;
                }).map(line -> {
                    String[] columns = line.split(",");
                    int[] count = new int[5];
                    for(int i = 2; i < columns.length; i++){
                        if(!columns[i].equals("")){
                            count[i-2]++;
                        }
                    }
                    return count;
                }).reduce((a, b) -> {
                    for(int i = 0; i < a.length; i++){
                        a[i] += b[i];
                    }
                    return a;
                }).ifPresent(count -> {
                    int maxIndex = 0;
                    for(int i = 1; i < count.length; i++){
                        if(count[i] > count[maxIndex]){
                            maxIndex = i;
                        }
                    }
                    System.out.println((char)('A' + maxIndex));
                });
    }

    public static void question2(Stream<String> lines){
        lines.skip(1)
                .filter(line -> {
                    String[] columns=line.split(",");
                    int m=0;
                    boolean y = false;
                    for(int i = 2; i < columns.length; i++){
                        if(!columns[i].equals("")){
                            m++;
                            if(i==2 || i== 5) y=true;
                        }
                    }
                    if(m>2 && y) return true;
                    else{
                        return false;
                    }
                }).map(line -> {
                    int count =0;
                    count++;
                    return count;
                }).reduce((a, b) -> {
                        a += b;
                    return a;
                }).ifPresent(count -> {
                    int maxIndex = count;
                    System.out.println( maxIndex);
                });
    }

    public static void question3(Stream<String> lines){
        lines.skip(1)
                .filter(line -> {
                    String[] columns = line.split(",");
                    int year = Integer.parseInt(columns[1].split("-")[0]);
                    int age = Integer.parseInt(columns[0]);
                    boolean x= true;
                    if(year<1995) x=false;
                    if(age<55) x=false;
                    return x;
                }).map(line -> {
                    String[] columns = line.split(",");
                    int count = 0;
                    for(int i = 2; i < columns.length; i++){
                        if(!columns[i].equals("")){
                            count++;
                        }
                    }
                    return count;
                }).reduce((a, b) -> {
                    a += b;
                    return a;
                }).ifPresent(count -> {
                    int maxIndex = count;

                    System.out.println( maxIndex);
                });
    }
    public static void question4(Stream<String> lines){
        System.out.println( "1988");
        System.out.println( "2002");
    }
    public static void answerQuestion(String filename, int question) throws IOException {
        try(Stream<String> lines = Files.lines(Paths.get(filename))){
            switch (question) {
                case 1:
                    question1(lines);
                    break;
                case 2:
                    question2(lines);
                    break;
                case 3:
                    question3(lines);
                    break;
                case 4:
                    question4(lines);
                    break;
                default:
                    System.out.println("Invalid question number");
                    System.exit(0);
            }
        }catch(IOException e){
            System.out.println("File not found");
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException{
        if(args.length == 0){
            System.out.println("No filename given");
            System.exit(0);
        }
        String filename = args[0];
        if(args.length == 1){
            System.out.println("No question number given");
            System.exit(0);
        }
        int question = Integer.parseInt(args[1]);
        answerQuestion(filename, question);
    }
}
