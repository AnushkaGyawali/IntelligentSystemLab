/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.areainheritance;

/**
 *
 * @author Dell
 */

import java.util.*;



public class AreaInheritance {
    public static void main (String[]args){
        Scanner scn = new Scanner (System.in);
        System.out.println("Enter length:");
        int length = scn.nextInt();
        System.out.println("Enter breadth:");
        int length = scn.nextInt();
        Triangle tr = newTriangle(length,breadth);
        System.out.println("Area of Rectangle is:"+tr.AreaOfRectangle());
        System.out.println("Area of Triangle is:"+tr.AreaOfTriangle());
    }
    
}
