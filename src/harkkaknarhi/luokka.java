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
public class luokka {
    int label;
    int sarkyvyys;
    int paino;
    float etaisyys;
    int nopeus;
    int korkeus;
    int leveys;
    int syvyys;
    
    public int getSpeed() {
        return nopeus;
    }

    public luokka(int label, int sarkyvyys, int paino, float etaisyys, int nopeus, int korkeus, int leveys, int syvyys) {
        this.label = label;
        this.sarkyvyys = sarkyvyys;
        this.paino = paino;
        this.etaisyys = etaisyys;
        this.nopeus = nopeus;
        this.korkeus = korkeus;
        this.leveys = leveys;
        this.syvyys = syvyys;
        // netbeansin generate-nappi löydetty ":D"
    }
    
    public String labelWriter(luokka l) {
        String s = "Paketin luokka: "+l.label+"\nSärkymisherkkyys: "+l.sarkyvyys+"\nPaino: "+l.paino+"g\nTilavuus: "+l.korkeus*l.leveys*l.syvyys+"cm^3\nKorkeus: "+l.korkeus+"cm\nLeveys: "+l.leveys+"cm\nSyvyys: "+l.syvyys+"\nmaksimilähetysetäisyys: "+l.etaisyys+"km";
        return s;
    }
    public String sarky(luokka l) {
        int sarky = l.sarkyvyys;
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
            s = "Älä lähetä pakettia TIMOTEI-miehen kautta!";
        }
        return s;
    }
            
        
    
}
