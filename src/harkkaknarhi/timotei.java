/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkaknarhi;

import java.util.Random;

/**
 *
 * @author Qnaerhi
 */
public class timotei {
    public boolean testaa(paketti pk) {
        dataBase db = new dataBase();
        opjekt o = db.getOpjekt("esineID", Integer.toString(pk.op));
        luokka l = db.getLuokka(pk.lk);
        int s1 = o.breakability;
        int s2 = l.sarkyvyys;
        System.out.println("sarkyvyys: "+s2);
        double aggressio = Math.random(); //bipolaarinen TIMOTEI-mies
        float f = (float)aggressio;
        float onnenluku = f*s1;
        if (s2 == 2) {
            if(onnenluku < 1.0) {
                return true;
            } else{
                return false;
            }
        } if (s2 == 1) {
            if(onnenluku<10.0) {
                 return true;
            } else {

                return false;
            }
        } if (s2 == 3) {
            onnenluku = onnenluku * o.wdt * o.dpt * o.hgt;
            float timoteiStr = onnenluku*o.wgt;
            if(onnenluku<1000000000.0 && timoteiStr<150000.0) {

                return true;
            } else {

                return false;  
            }
        } else {
             System.out.println("onnenluku on:"+onnenluku);
            return false;
        }
    }
}
        

