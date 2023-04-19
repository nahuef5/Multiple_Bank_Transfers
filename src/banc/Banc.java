package banc;
public class Banc {
    public static void main(String[] args) {
        Cuenta c=new Cuenta();
        //movimientos del banco con 100 cuentas
        for(int i=0;i<100;i++){
            RunTransfer rt=new RunTransfer(c,i,3523.98);
            Thread t=new Thread(rt);
            t.start();
        }
    }
}