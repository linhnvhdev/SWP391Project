/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author Linhnvhdev
 */
public class Validation {
    public static boolean isNumber(String s){
        for(int i = 0;i < s.length();i++){
            if(!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }
}
