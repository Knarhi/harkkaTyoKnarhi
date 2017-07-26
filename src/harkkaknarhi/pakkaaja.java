/*
olio testaa pakettien koot
 */
package harkkaknarhi;

/**
 *
 * @author Qnaerhi
 */
public class pakkaaja {
    opjekt op;
    luokka cl;
    pakkaaja(opjekt o, luokka l) {
        op = o;
        cl = l;
    }
    public info pack(opjekt o, luokka l) {
        if (o.wgt>l.paino) {
            info i = new info(false,"Esine on liian painava!");
            return i;
        } else if (o.wdt > l.leveys || o.hgt > l.korkeus || o.dpt > l.leveys) {
            info i = new info(false,"Esineen ulottuvuudet eivät mahdu pakettiin!");
        } else {
            info i = new info(true,"Esineen fyysiset ominaisuudet eivät estä lähetystä!");
            return i;
        }
        return null;
    }
}

