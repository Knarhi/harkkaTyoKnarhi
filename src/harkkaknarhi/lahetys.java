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
public class lahetys extends paketti {
        private Automaatti lahto;
        private Automaatti paate;
        private String status;
        private float matka;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getMatka() {
        return matka;
    }

    public void setMatka(float matka) {
        this.matka = matka;
    }

        public lahetys(int k, int l, int o) {
            super(k, l, o);
        }

        public void setLahto(Automaatti lahto) {
            this.lahto = lahto;
        }

        public void setPaate(Automaatti paate) {
            this.paate = paate;
        }

        public Automaatti getLahto() {
            return lahto;
        }

        public Automaatti getPaate() {
            return paate;
        }     
    }
