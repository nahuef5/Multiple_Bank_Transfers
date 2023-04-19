package bank;

//Clase modelo de objeto transferencia
public class Transfer implements Runnable{
    private Account cta;
    private int ctaOrig;
    private double max;
        
    public Transfer(Account cta,int ctaOrig, double max){
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
                double quantity=Math.random()*max;
                
                if(quantity!=0){
                    try {
                    cta.transfer(ctaOrig, receptor, quantity);
                    
                    }catch (Exception ex) {
                    System.out.println(ex+"\n");
                    }   
                }else{
                    //le asignamos nuevo valor si q==0
                    quantity=Math.round(Math.random()*max);
                    try {
                    cta.transfer(ctaOrig, receptor, quantity);
                    
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
