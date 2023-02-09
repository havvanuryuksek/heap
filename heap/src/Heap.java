
import java.util.Scanner;

class Eleman {
    int icerik;

    public Eleman(int icerik) {
        this.icerik = icerik;
    }
}
public class Heap {
    Eleman[] dizi;
    int adet;

    public Heap(int N) {
        this.dizi = new Eleman[N];
        this.adet = 0;
    }

    boolean bosMu() {
        return this.adet == 0;
    }

     void asagiIn(int no) {
        int altsol = 2 * no + 1;

        for(int altsag = 2 * no + 2; altsol < this.adet && this.dizi[no].icerik < this.dizi[altsol].icerik || altsag < this.adet && this.dizi[no].icerik < this.dizi[altsag].icerik; altsag = 2 * no + 2) {
            Eleman tmp;
            if (altsag < this.adet && this.dizi[altsol].icerik <= this.dizi[altsag].icerik) {
                tmp = this.dizi[no];
                this.dizi[no] = this.dizi[altsag];
                this.dizi[altsag] = tmp;
                no = altsag;
            } else {
                tmp = this.dizi[no];
                this.dizi[no] = this.dizi[altsol];
                this.dizi[altsol] = tmp;
                no = altsol;
            }

            altsol = 2 * no + 1;
        }

    
     }

    void yukariCik(int no) {
        for(int ata = (no - 1) / 2; ata >= 0 && this.dizi[ata].icerik < this.dizi[no].icerik; ata = (ata - 1) / 2) {
            Eleman tmp = this.dizi[ata];
            this.dizi[ata] = this.dizi[no];
            this.dizi[no] = tmp;
            no = ata;
        }

    }

    void ekle(Eleman yeni) {
        this.dizi[this.adet] = yeni;
        this.yukariCik(this.adet);
        ++this.adet;
    }

    Eleman sil() {
        if (!this.bosMu()) {
            Eleman tmp = this.dizi[0];
            this.dizi[0] = this.dizi[this.adet - 1];
            this.asagiIn(0);
            --this.adet;
            return tmp;
        } else {
            return null;
        }
    }

    Eleman azamiSil() { //burada0.elemanı silmek için adet-1 son elemanı onun yerine getirmesi ve sonradan sol ve sağ altlar ile karşılaştırıp düzeltmesi
        if (!this.bosMu()) {
            Eleman tmp = this.dizi[0];
            this.dizi[0] = this.dizi[this.adet - 1];
            --this.adet;
            this.asagiIn(0);
            return tmp;
        } else {
            return null;
        }
    }
    void degerDegistir(int k, int yeni){
        int eski = dizi[k].icerik;
        dizi[k].icerik=yeni;   
        if(eski>yeni)
            asagiIn(k);
        else
            yukariCik(k);
    }
    
int yiginArama(int icerik){
    int i;
    for(i=0;i<adet;i++)
        if(dizi[i].icerik==icerik)
            return i;
       return -1;
}
    void yazdir() {
        for(int i = 0; i < this.adet / 2; ++i) {
            int sol = 2 * i + 1;
            int sag = 2 * i + 2;
            Eleman var10001 = this.dizi[i];
            System.out.println("Ata dugum " + var10001.icerik);
            if (sol < this.adet) {
                var10001 = this.dizi[sol];
                System.out.println("sol cocuk " + var10001.icerik);
            }

            if (sag < this.adet) {
                var10001 = this.dizi[sag];
                System.out.println("sag cocu " + var10001.icerik);
            }

            System.out.println("");
        }

    }
    
    // ekleme 0(logN),Silme 0(logN), değer değiştirme 0(logN), arama O(N)
    // yığında bir elemanın 3 veya 4 çocuğu olabilir 3lü yığın 4lü yığın diye
    public static void main(String[] args) {
        Heap y1 = new Heap(10);
        Heap y2 = new Heap(10);
        Scanner k1 = new Scanner(System.in);
        System.out.println("Heap-1 sayıları virgül ile gir");
        String h1 = k1.nextLine();
        String[] s1 = h1.split(",");

        for(int i = 0; i < s1.length; ++i) {
            y1.ekle(new Eleman(Integer.parseInt(s1[i])));
        }

        y1.yazdir();
        System.out.println("Heap-2 sayıları virgül ile gir");
        String h2 = k1.nextLine();
        String[] s2 = h2.split(",");

        for(int i = 0; i < s2.length; ++i) {
            y2.ekle(new Eleman(Integer.parseInt(s2[i])));
        }
          y2.yazdir();
    }
}

