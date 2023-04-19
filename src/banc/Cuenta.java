package banc;
import java.util.concurrent.locks.*;

//Clase modelo de objeto cuentaCorriente
public class Cuenta {
    //arrays de cuentas 
    private final double[] ctaCte;
    //objeto de turno 
    private Lock turno=new ReentrantLock();
    //objeto condicion para saldo suficiente de cada hilo
    private Condition saldoSuficiente;

    public Cuenta(){
        this.ctaCte = new double[100];
        for(int i=0;i<ctaCte.length;i++){
            ctaCte[i]=3523.98;
        }
        saldoSuficiente=turno.newCondition();
    }
    
    //metodo de transferencia
    public void transfer(int emisor, int receptor, double valor) throws Exception{
        //Generamos turno por cada hilo para que no pueda acceder otro al metodo
        this.turno.lock();
        
        try{
            while(ctaCte[emisor]<valor){
                //si el valor es menor al saldo hacemos esperar hilo hasta que la cuenta tenga ms valor
                saldoSuficiente.await();
                throw new Exception("Cuenta de id: "+Thread.currentThread()+" No se puede pasar mas dinero del que usted posee\n");
            }
            System.out.println("\n"+Thread.currentThread()+"\n");
            //acreditamos cuenta emisor
            ctaCte[emisor]-=valor;
            System.out.printf("%10.2f de %d para %d\n",valor, emisor, receptor);
            //debitamos cuenta receptor
            ctaCte[receptor]+=valor;
        
            System.out.println("\n");
            //Conocer saldo total del banco
            System.out.printf("Saldo Total: %10.2f%n\n", getSaldo());
            //despertamos hilo de condicion cuando se cumpla condicion
            saldoSuficiente.signalAll();
        }finally{
            //damos turno al siguiente hilo
            this.turno.unlock();
        }
    }
    //saldo banco
    public double getSaldo(){
        double sum=0;
        for(double d: ctaCte){
            sum+=d;
        }
        return sum;
    }
    
}
