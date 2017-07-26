/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package harkkaknarhi;

/**
 *
 * @author Qnaerhi
 */
public class opjekt {
    int id;
    String name;
    int wgt;
    int hgt;
    int wdt;
    int dpt;
    int breakability;

  opjekt(String n, int l, int k, int j, int i, int b) {
        name = n;
        dpt = i;
        wdt = j;
        hgt = k;
        wgt = l;
        breakability = b;
    }
    // dat dere overload
    opjekt(int p, String n, int l, int k, int j, int i, int b) {
        id = p;
        name = n;
        dpt = i;
        wdt = j;
        hgt = k;
        wgt = l;
        breakability = b;
    }
public String labelWriter(opjekt l) {
    String s = "Esineen nimi: "+l.name+"\nSärkymisherkkyys: "+l.sarky(l)+"\nPaino: "+l.wgt+"g\nTilavuus: "+l.hgt*l.wdt*l.dpt+"cm^3\nKorkeus: "+l.hgt+"cm\nLeveys: "+l.wdt+"cm\nSyvyys: "+l.dpt+"cm";
        return s;
    }
    public String sarky(opjekt l) {
        int sarky = l.breakability;
        String s;
        if (sarky < 2) {
            s = "erittäin kestävä";
        } else if (sarky < 4) {
            s = "melko kestävä";
        } else if (sarky < 6) {
            s = "herkkä";
        } else if (sarky < 8) {
            s = "erittäin herkkä";
        } else {
            s = "Älä lähetä esinettä TIMOTEI-miehen kautta!";
        }
        return s;
    }
}
   
