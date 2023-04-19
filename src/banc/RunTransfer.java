package banc;

//Clase modelo de objeto transferencia
public class RunTransfer implements Runnable{
    private Cuenta cta;
    private int ctaOrig;
    private double max;
        
    public RunTransfer(Cuenta cta,int ctaOrig, double max){
        this.cta=cta;
        this.ctaOrig=ctaOrig;
        this.max=max;
    }
    
    @Override
    public void run() {
       try{
            while(true){
                //Generamos la cuenta receptora de transferencia
                int receptor=(int) (Math.random()*100);
                //valor que se pasara del origen al receptor
                double q=Math.random()*max;
                
                if(q!=0){
                    try {
                    cta.transfer(ctaOrig, receptor, q);
                    
                    }catch (Exception ex) {
                    System.out.println(ex+"\n");
                    }   
                }else{
                    //le asignamos nuevo valor si q==0
                    q=Math.round(Math.random()*max);
                    try {
                    cta.transfer(ctaOrig, receptor, q);
                    
                    }catch (Exception ex) {
                    System.out.println(ex+"\n");
                    }
                }
                Thread.sleep((int)(Math.random()*100));
            }
        }catch(InterruptedException e){
            System.out.println(e+"\n");
        } 
    }
    
}
