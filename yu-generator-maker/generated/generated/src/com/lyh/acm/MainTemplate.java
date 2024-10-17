package com.lyh.acm;

import java.util.Scanner;

/**
 * ACM输入模版（多数之和）
 * @author liyuhang
 */
public class MianTemplate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()){
            int n = scanner.nextInt();

            int arr[] = new int[n];
            for (int i = 0;i < n; i++){
                arr[i] = scanner.nextInt();
            }

            int sum = 0;
            for (int num : arr){
                sum += num;
            }
            System.out.println("abc:" + sum);
            }
        scanner.close();
    }
}