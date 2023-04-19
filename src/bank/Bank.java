package bank;
public class Bank {
    public static void main(String[] args) {
        Account account=new Account();
        //movimientos del banco con 100 cuentas
        for(int i=0;i<100;i++){
            Transfer transfer=new Transfer(account,i,3523.98);
            Thread t=new Thread(transfer);
            t.start();
        }
    }
}