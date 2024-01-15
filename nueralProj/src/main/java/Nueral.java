
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class Nueral extends javax.swing.JFrame {
public static final Color red = new Color(230, 101, 105);
public static final Color blue = new Color(207,229,255);
int a, b ,jj, ep, MaxEpoch, yd, best,i=0,op=0,EpochNum=1;
float ya;
float w1,w2,w13,w14,w23,w24,w35,w45,min,max,Theta,bigX,bigX1,bigX2,y1,y2,WC1,WC13,WC14,WC23,WC24,WC35,WC45,WC2,WT1,WT13,WT14,WT23,WT24,WT35,WT45,WT2,Theta1,Theta2,GD,GD1,GD2,WCA,WC3,WC4,WTA,WT3,WT4;
float error,itr=0;
int xn1[]={0,1};
int x1[]={1,1,0,0};
int x2[]={1,0,1,0};
float actual []= new float [4];
float predicted[]= new float [4];
float Mse[];
boolean first= true;
float mse;
int Not=0, And=0, Or=0, Xor=0,Xnor=0,Nand=0,Nor=0;
int and1[]={0,0,0,1};
int or1[]={0,1,1,1};
int nand1[]={1,1,1,0};
int nor1[]={1,0,0,0};
int xor1[]={0,1,1,0};
int xnor1[]={1,0,0,1};

public static boolean onlyDigits(String e, int nm){ 
	int i;
        for (i = 0; i < nm; i++) { 
            if ((e.charAt(i) >= '0') && (e.charAt(i) <= '9')) { 
                continue; 
            }
             else
                break;
        } 
        if(i==nm)
            return true;
        else
            return false;
    } 

public Nueral() {
    initComponents();
}
     
private void  perceptron(){
    if(first)  initalization();
        
    System.out.println("---------------------------------------------------");
    System.out.println("Max # Epoch= "+MaxEpoch);
    System.out.println("initaial wight = "+"W1= "+w1+" W2="+w2);
        
    while(EpochNum <= MaxEpoch){
        if(op==7) 
            for (int j=0; j<2;j++){
            System.out.println("x= "+Integer.parseInt(A.getText()));
            bigX=0.0f;
            bigX=BigX(w1,xn1[i],Theta);
            ya=Step(bigX);
            
            if(xn1[i]==a) 
                ActualO.setText(String.valueOf(ya));
            
            yd=not(Integer.parseInt(A.getText()));
        
            actual[i]=ya;
            predicted[i]=yd;
        
            System.out.println("ya = "+ya+" yd= "+yd);
            error= Error(ya,yd);
            WC1=WeightCorrection(xn1[i],error);
            WT1=(float)Math.floor(wightTraning(w1, WC1)* 10000) / 10000;
            System.out.println("bigX ="+bigX +"\n"+"ya= "+ya+"\n"+"error= "+error+"\n"+"WC1= "+WC1);
        
            w1=WT1;
            
            System.out.println("W= "+w1);
            System.out.println("itr= "+itr);
          
            i++;
            System.out.println("*************************");
            }
        
      else
        for (int j=0; j<4;j++){
            System.out.println("x1= "+x1[i]+" x2= "+x2[i]);
            bigX=0.0f;
            bigX=BigX(w1,w2,x1[i],x2[i],Theta);
            ya=Step(bigX);
        
            if(x1[i]==a && x2[i]==b) 
                ActualO.setText(String.valueOf(ya));
          
            if(op==0)
                yd=x1[i]&x2[i];
            else if(op==1)
                yd=not(x1[i]&x2[i]);
            else if(op==2)
                yd=x1[i]|x2[i];
            else if(op==3)
                yd=not(x1[i]|x2[i]);
        
            actual[i]=ya;
            predicted[i]=yd;
        
            System.out.println("ya = "+ya+" yd= "+yd);
            error= Error(ya,yd);
            WC1=WeightCorrection(x1[i],error);
            WC2=WeightCorrection(x2[i],error);
            WT1=(float)Math.floor(wightTraning(w1, WC1)* 10000) / 10000;
            WT2=(float)Math.floor(wightTraning(w2, WC2)* 10000) / 10000;
            System.out.println("bigX ="+bigX +"\n"+"ya= "+ya+"\n"+"error= "+error+"\n"+"WC1= "+WC1+" WC2="+WC2);
        
            w1=WT1;
            w2=WT2;
            
            System.out.println("W1= "+w1+" W2="+w2);
            System.out.println("itr= "+itr);
          
            i++;
            System.out.println("*************************");
        }
            
        mse=MSE();
        Mse[EpochNum-1]=mse;
        System.out.println("Mse= "+Mse[EpochNum-1]);
        System.out.println("/*/*/*/*/*/*/*/***ep= "+EpochNum);
        itr=0;
        i=0;
        
        if(mse==0.0)
            EpochNum=MaxEpoch;
        if(EpochNum == MaxEpoch){
            float smallest = Integer.MAX_VALUE;
            int epos=0,index=0;
            while(index<Mse.length){
                if(smallest>Mse[index]){
                    smallest=Mse[index];
                    epos=index;
                }
                index++;
            }
            epos++;
            JOptionPane.showMessageDialog(this, "the smallest MSE is in epoch: "+epos+" = "+ smallest, "", JOptionPane.PLAIN_MESSAGE);
            int yi=(int)(((Theta/w2)*100));
            int xi=(int)((Theta/w1)*100);
            if (yi<0){
                yi=yi*-1;
            }
            if (xi<0){
                xi=xi*-1;
            }
            System.out.println("yi="+yi);
            System.out.println("xi="+xi);
            if(op==0){
                Draw d= new Draw();
                d.show1(and1,xi,yi);
                And=0;
            }
            if(op==1){
                Draw d= new Draw();
                d.show1(nand1,xi,yi);
                Nand=0;
            }
            if(op==2){
                Draw d= new Draw();
                d.show1(or1,xi,yi);
                Or=0;
            }
            if(op==3){
                Draw d= new Draw();
                d.show1(nor1,xi,yi);
                Nor=0;
            }
            if(op==7){
                Draw d= new Draw();
                d.show1(xn1,xi,10);
                Not=0;
            }
            System.out.println("The smallest number is : "+ smallest+" epos is: "+epos);
            BestE.setText(epos+"");
        }
        EpochNum++;  
    }
    i=0;
}

private float BigX (float w1, float w2, float x1, float x2, float thre){
    float bigX=0.0f;
    bigX = (float)Math.floor(w1*x1 * 10000) / 10000+ (float)Math.floor(w2*x2 * 10000) / 10000 - thre;
    System.out.println("w1*x1= "+(float)Math.floor(w1*x1 * 10000) / 10000+" w2*x2= "+(float)Math.floor(w2*x2 * 10000) / 10000+" bigX= "+Math.floor(bigX * 10000) / 10000);
    return (float)Math.floor(bigX * 10000) / 10000;
}
private float BigX (float w1, float x1, float thre){
    float bigX=0.0f;
    bigX = (float)Math.floor(w1*x1 * 10000) / 10000 - thre;
    System.out.println("w1*x1= "+(float)Math.floor(w1*x1 * 10000) / 10000+" bigX= "+Math.floor(bigX * 10000) / 10000);
    return (float)Math.floor(bigX * 10000) / 10000;
}
private int not(int x){
    int y=0;
    if(x==0) y=1;
    if(x==1) y=0;
    return y;
}
    
private int Step(float bigX){
    if (bigX >= 0) return 1;
    else return 0;
}

private float Error(float ya, float yd){
    return yd-ya;
}

private float WeightCorrection(float x,float e){
    float WeightC;
    float learningRate,Weight;
    learningRate=0.1f;
    Weight= learningRate * x * e;
    WeightC=(float)Math.floor(Weight * 10000) / 10000;
    return WeightC;
}

private float wightTraning(double w, float DWeight){
    return (float)w+DWeight;   
}

private void initalization(){
    min=-0.5f;
    max=0.5f;
    w1=(float)(Math.random() * (max - min)) + min;
    w2=(float)(Math.random() * (max - min)) + min;
    Theta=(float)(Math.random() * (max - min)) + min;
    MaxEpoch=ep;
    EpochNum=1;
    Mse= new float[ep];
    first= false;
}

private float MSE(){
    int n = 4; 
    float sum = 0.0f; 
    float diff;
    for (int i = 0; i < n; i++){  
        diff =  predicted[i] -  actual[i];   
        sum = sum + diff * diff;
    }
    float mse = sum / n;
    return mse;
}

private void  multilayer(){
    if(first)  initalization2();
        
    while(EpochNum<= MaxEpoch){
        for (int j=0; j<4;j++){
        System.out.println("x1= "+x1[i]+" x2= "+x2[i]);
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\");
        System.out.println("ep= "+MaxEpoch);
        System.out.println("initaial wight = "
                            +"W13= "+w13+" W23="+w23
                            +" W14= "+w14+" W24="+w24
                            +" W35= "+w35+" W45="+w45
                            +" Theta= "+Theta+" Thetae1="+Theta1
                            +" Theta2= "+Theta2);
        bigX1=BigX(w13,w23,x1[i],x2[i],Theta1);
        y1=Sigmoid(bigX1);
        System.out.println("y1= "+y1);  
        
        bigX2=BigX(w14,w24,x1[i],x2[i],Theta2);
        y2=Sigmoid(bigX2);
        System.out.println("y2= "+y2); 
        
        bigX=BigX(w35,w45,y1,y2,Theta);
        ya=Sigmoid(bigX);
        System.out.println("ya= "+ya); 
        
        error= Error(ya,yd);
        System.out.println("error= "+error);
        
        actual[i]=ya;
        predicted[i]=yd;
        
        GD=gradientDescent(ya,error);
        System.out.println("GD= "+GD); 
        
        WC35=WeightCorrection(y1,GD);
        WC45=WeightCorrection(y2,GD);
        GD1=gradientDescent(y1,GD*w35);
        GD2=gradientDescent(y2,GD*w45);
        System.out.println("GD1= "+GD1);
        System.out.println("GD2= "+GD2);
        
        WC13=WeightCorrection(x1[i],GD1);
        WC23=WeightCorrection(x2[i],GD1);
        
        WC14=WeightCorrection(x1[i],GD2);
        WC24=WeightCorrection(x2[i],GD2);
        
        WCA=WeightCorrection(-1.0f,GD);
        WC3=WeightCorrection(-1.0f,GD1);
        WC4=WeightCorrection(-1.0f,GD2);
         
        System.out.println("WC13= "+WC13);
        System.out.println("WC23= "+WC23);
        
        System.out.println("WC14= "+WC14);
        System.out.println("WC24= "+WC24);
        
        System.out.println("WC35= "+WC35);
        System.out.println("WC45= "+WC45);
        
        System.out.println("WCA= "+WCA+" with sign = "+sign(Theta));
        System.out.println("WC3= "+WC3+" with sign = "+sign(Theta1));
        System.out.println("WC4= "+WC4+" with sign = "+sign(Theta2));
        
        WT35=(float)Math.floor(wightTraning(w35, WC35)* 10000) / 10000;
        WT45=(float)Math.floor(wightTraning(w45, WC45)* 10000) / 10000;
        
        WT13=(float)Math.floor(wightTraning(w13, WC13)* 10000) / 10000;
        WT23=(float)Math.floor(wightTraning(w23, WC23)* 10000) / 10000;
        
        WT14=(float)Math.floor(wightTraning(w14, WC14)* 10000) / 10000;
        WT24=(float)Math.floor(wightTraning(w24, WC24)* 10000) / 10000;
        
        WTA=(float)Math.floor(wightTraning(Theta, WCA)* 10000) / 10000;
        WT3=(float)Math.floor(wightTraning(Theta1, WC3)* 10000) / 10000;
        WT4=(float)Math.floor(wightTraning(Theta2, WC4)* 10000) / 10000;
              
        w13=WT13;
        w23=WT23;
        
        w24=WT24;
        w14=WT14;
        
        w45=WT45;
        w35=WT35;
        
        Theta=WTA;
        Theta1=WT3;
        Theta2=WT4;
        
        System.out.println("W13= "+w13);
        System.out.println("W23= "+w23);
        
        System.out.println("W14= "+w14);
        System.out.println("W24= "+w24);
        
        System.out.println("W35= "+w35);
        System.out.println("W45= "+w45);
        
        System.out.println("Theta= "+Theta);
        System.out.println("Theta1= "+Theta1);
        System.out.println("Theta2= "+Theta2);
        
        if(x1[i]==a && x2[i]==b)
            ActualO.setText(ya+"");
        
         if(op==3)
            yd=x1[i]^x2[i];
        
        else if(op==4)
            yd=not(x1[i]^x2[i]);
        
        actual[i]=ya;        
        predicted[i]=yd;
        
        System.out.println("ya = "+ya+" yd= "+yd);
        System.out.println("*************************");
        i++;
        }
        
        mse=MSE();
        Mse[EpochNum-1]=mse;
        System.out.println("Mse= "+mse);
        System.out.println("/*/*/*/*/*/*/*/***ep= "+EpochNum);
        itr=0;
        i=0;
        if(mse==0.0) EpochNum=MaxEpoch;
        if(EpochNum == MaxEpoch){
            float smallest = Integer.MAX_VALUE;
            int epos=0;
            int index=0;
            while(index<Mse.length){
                if(smallest>Mse[index]){
                    smallest=Mse[index];
                    epos=index;
                }
                index++;
            }
            epos++;
            JOptionPane.showMessageDialog(this, "the smallest MSE is in epoch: "+epos+" = "+ smallest, "", JOptionPane.PLAIN_MESSAGE);
            int yi=(int)(((Theta1/w23)*100));
            int xi=(int)((Theta1/w13)*100);
            if (yi<0){
                yi=yi*-1;
            }
            if (xi<0){
                xi=xi*-1;
            }
            System.out.println("yi="+yi);
            System.out.println("xi="+xi);
            int yi1=(int)(((Theta2/w24)*100));
            int xi1=(int)((Theta2/w14)*100);
            if (yi1<0){
                yi1=yi1*-1;
            }
            if (xi1<0){
                xi1=xi1*-1;
            }
            System.out.println("yi1="+yi1);
            System.out.println("xi1="+xi1);
        
            if(op==4){
                Draw d= new Draw();
                d.show2(xor1,xi,yi,xi1,yi1);
                Xor=0;
            }
            if(op==5){
                Draw d= new Draw();
                d.show2(xnor1,xi,yi,xi1,yi1);
                Xnor=0;
            }
            System.out.println("The smallest number is : "+ smallest+" epos is: "+epos);
            BestE.setText(epos+"");
        }
        EpochNum++;     
    }
    i=0; 
}
      
     
private void initalization2(){
    min=-1.2f;
    max=1.2f;
    w13=((float)Math.random() * (max - min)) + min;
         w14=((float)Math.random() * (max - min)) + min;
         w23=((float)Math.random() * (max - min)) + min;
         w24=((float)Math.random() * (max - min)) + min;
         w35=((float)Math.random() * (max - min)) + min;
         w45=((float)Math.random() * (max - min)) + min;
         Theta1=((float)Math.random() * (max - min)) + min;
         Theta2=((float)Math.random() * (max - min)) + min;
         Theta=((float)Math.random() * (max - min)) + min;
         MaxEpoch=ep;
         EpochNum=1;
         Mse= new float[ep];
         first= false;
     }
      
     private float Sigmoid(float bigX)
    {
       int sigmoid=0;
        
       return (float)(1 / (1 + Math.exp(-bigX)));
    }
     
     private float gradientDescent(float ya , float error){
     
         float GD=ya*(1-ya)*error;
         
         return GD;
     }
     
     private float sign(float x){
         float sign=0.0f;
         if(x>0.0f)
             sign=1.0f;
         else if(x<0.0f)
             sign=-1.0f;
         else
             sign=0.0f;
         return sign;
     }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        NOT = new javax.swing.JButton();
        AND = new javax.swing.JButton();
        NAND = new javax.swing.JButton();
        OR = new javax.swing.JButton();
        NOR = new javax.swing.JButton();
        XOR = new javax.swing.JButton();
        XNOR = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        EPOCHS = new javax.swing.JTextField();
        A = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        B = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        DesiredO = new javax.swing.JTextField();
        ActualO = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BestE = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Training Logic gates using Neural");
        setPreferredSize(new java.awt.Dimension(900, 455));
        setSize(new java.awt.Dimension(900, 455));

        jPanel1.setBackground(new java.awt.Color(124, 16, 52));

        NOT.setBackground(new java.awt.Color(207, 229, 255));
        NOT.setFont(new java.awt.Font("Segoe Print", 0, 14)); // NOI18N
        NOT.setText("Not");
        NOT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NOTMousePressed(evt);
            }
        });
        NOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NOTActionPerformed(evt);
            }
        });

        AND.setBackground(new java.awt.Color(207, 229, 255));
        AND.setFont(new java.awt.Font("Segoe Print", 0, 14)); // NOI18N
        AND.setText("And");
        AND.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ANDMousePressed(evt);
            }
        });
        AND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ANDActionPerformed(evt);
            }
        });

        NAND.setBackground(new java.awt.Color(207, 229, 255));
        NAND.setFont(new java.awt.Font("Segoe Print", 0, 14)); // NOI18N
        NAND.setText("Nand");
        NAND.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NANDMousePressed(evt);
            }
        });
        NAND.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NANDActionPerformed(evt);
            }
        });

        OR.setBackground(new java.awt.Color(207, 229, 255));
        OR.setFont(new java.awt.Font("Segoe Print", 0, 14)); // NOI18N
        OR.setText("OR");
        OR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ORMousePressed(evt);
            }
        });
        OR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ORActionPerformed(evt);
            }
        });

        NOR.setBackground(new java.awt.Color(207, 229, 255));
        NOR.setFont(new java.awt.Font("Segoe Print", 0, 14)); // NOI18N
        NOR.setText("NOR");
        NOR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NORMousePressed(evt);
            }
        });
        NOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NORActionPerformed(evt);
            }
        });

        XOR.setBackground(new java.awt.Color(207, 229, 255));
        XOR.setFont(new java.awt.Font("Segoe Print", 0, 14)); // NOI18N
        XOR.setText("XOR");
        XOR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                XORMousePressed(evt);
            }
        });
        XOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XORActionPerformed(evt);
            }
        });

        XNOR.setBackground(new java.awt.Color(207, 229, 255));
        XNOR.setFont(new java.awt.Font("Segoe Print", 0, 14)); // NOI18N
        XNOR.setText("XNOR");
        XNOR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                XNORMousePressed(evt);
            }
        });
        XNOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XNORActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(207, 229, 255));
        jLabel2.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(207, 229, 255));
        jLabel2.setText("Choose a gate:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel2)
                .addGap(0, 33, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NOT, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AND, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NAND, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OR, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NOR, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(XOR, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(XNOR, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NOT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(AND)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NAND)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NOR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(XOR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(XNOR)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(207, 229, 255));

        jLabel3.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel3.setText("#Epochs ");

        EPOCHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EPOCHSActionPerformed(evt);
            }
        });

        A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel4.setText("Input #2 ");

        B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel5.setText("Input #1 ");

        jLabel6.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel6.setText("Desired output ");

        jLabel7.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel7.setText("Actual output");

        DesiredO.setEditable(false);
        DesiredO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesiredOActionPerformed(evt);
            }
        });

        ActualO.setEditable(false);
        ActualO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualOActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe Print", 1, 18)); // NOI18N
        jLabel1.setText("Best Epoch ");

        BestE.setEditable(false);
        BestE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BestEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(DesiredO, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(EPOCHS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(B, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(A, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BestE, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ActualO, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(A, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(B, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(EPOCHS, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DesiredO, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(ActualO, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(BestE, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon("D:\\University\\4th,1st\\AI\\project2\\project2\\Untitled.png")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EPOCHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EPOCHSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EPOCHSActionPerformed

    private void AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AActionPerformed

    private void BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BActionPerformed

    private void DesiredOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesiredOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DesiredOActionPerformed

    private void ActualOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ActualOActionPerformed

    private void BestEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BestEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BestEActionPerformed

    private void NOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NOTActionPerformed
        if(onlyDigits(A.getText(),A.getText().length()) && onlyDigits(EPOCHS.getText(),EPOCHS.getText().length()) ){
        if(!A.getText().equals("") && B.getText().equals("") && !EPOCHS.getText().equals("")){
            if(Not==0){
                first= true;
                Not++;
            }
            else
                Not++;
            
            a=Integer.parseInt(A.getText());
            ep=Integer.parseInt(EPOCHS.getText());
            int yd=not(a);
            op=7;
            String fill= Integer.toString(yd);
            DesiredO.setText(fill);
            perceptron();
        }
        else {
            JOptionPane.showMessageDialog(this, "You have to enter ONLY A & number of training epechs.", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        }
        else{
            JOptionPane.showMessageDialog(this, "Incorrect entry, you can ONLY enter integers!","WARNING", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_NOTActionPerformed

    private void ANDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ANDActionPerformed
        if(onlyDigits(A.getText(),A.getText().length()) && onlyDigits(B.getText(),B.getText().length()) && onlyDigits(EPOCHS.getText(),EPOCHS.getText().length()) ){
        if(!A.getText().equals("") && !B.getText().equals("") && !EPOCHS.getText().equals("")){
            //System.out.println("ok");
            if(And==0)
            {
                first= true;
                And++;
            }
            else
                And++;
            a=Integer.parseInt(A.getText());
            b=Integer.parseInt(B.getText());
            ep=Integer.parseInt(EPOCHS.getText());
            yd=a&b;
            op=0;
            String fill= Integer.toString(yd);
            DesiredO.setText(fill);
            perceptron();
        }
        else {
            JOptionPane.showMessageDialog(this, "You have to enter A & B values & number of training epechs.", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        }
        
        else{
            JOptionPane.showMessageDialog(this, "Incorrect entry, you can ONLY enter integers!","WARNING!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
    }//GEN-LAST:event_ANDActionPerformed

    private void NANDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NANDActionPerformed
     if(onlyDigits(A.getText(),A.getText().length()) && onlyDigits(B.getText(),B.getText().length()) && onlyDigits(EPOCHS.getText(),EPOCHS.getText().length()) ){
                if(!A.getText().equals("") && !B.getText().equals("") && !EPOCHS.getText().equals("")){
           //System.out.println("ok");
            if(Nand==0)
            {
                first= true;
                Nand++;
            }
            else
                Nand++;
           a=Integer.parseInt(A.getText());
            b=Integer.parseInt(B.getText());
            ep=Integer.parseInt(EPOCHS.getText());
            yd=not(a&b);
            op=1;
            String fill= Integer.toString(yd);
            DesiredO.setText(fill);
            perceptron();
            
        }
        else {
            JOptionPane.showMessageDialog(this, "You have to enter A & B values & number of training epechs.", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
        else{
            JOptionPane.showMessageDialog(this, "Incorrect entry, you can ONLY enter integers!","WARNING", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_NANDActionPerformed

    private void ORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ORActionPerformed
       if(onlyDigits(A.getText(),A.getText().length()) && onlyDigits(B.getText(),B.getText().length()) && onlyDigits(EPOCHS.getText(),EPOCHS.getText().length()) ){
        if(!A.getText().equals("") && !B.getText().equals("") && !EPOCHS.getText().equals("")){
            //System.out.println("ok");
            if(Or==0)
            {
                first= true;
                Or++;
            }
            else
                Or++;
            a=Integer.parseInt(A.getText());
            b=Integer.parseInt(B.getText());
            ep=Integer.parseInt(EPOCHS.getText());
            yd=a|b;
            op=2;
            String fill= Integer.toString(yd);
            DesiredO.setText(fill);
            perceptron();
        }
        else {
            JOptionPane.showMessageDialog(this, "You have to enter A & B values & number of training epechs.", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
       }
       else{
            JOptionPane.showMessageDialog(this, "Incorrect entry, you can ONLY enter integers!","WARNING", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_ORActionPerformed

    private void NORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NORActionPerformed
        if(onlyDigits(A.getText(),A.getText().length()) && onlyDigits(B.getText(),B.getText().length()) && onlyDigits(EPOCHS.getText(),EPOCHS.getText().length()) ){
        if(!A.getText().equals("") && !B.getText().equals("") && !EPOCHS.getText().equals("")){
            //System.out.println("ok");
            if(Nor==0)
            {
                first= true;
                Nor++;
            }
            else
                Nor++;
            a=Integer.parseInt(A.getText());
            b=Integer.parseInt(B.getText());
            ep=Integer.parseInt(EPOCHS.getText());
            yd=not(a|b);
            op=3;
            String fill= Integer.toString(yd);
            DesiredO.setText(fill);
            perceptron();
        }
        else {
            JOptionPane.showMessageDialog(this, "You have to enter A & B values & number of training epechs.", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        }
        else{
            JOptionPane.showMessageDialog(this, "Incorrect entry, you can ONLY enter integers!","WARNING", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_NORActionPerformed

    private void XORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XORActionPerformed
        if(onlyDigits(A.getText(),A.getText().length()) && onlyDigits(B.getText(),B.getText().length()) && onlyDigits(EPOCHS.getText(),EPOCHS.getText().length()) ){
        if(!A.getText().equals("") && !B.getText().equals("") && !EPOCHS.getText().equals("")){
            //System.out.println("ok");
            if(Xor==0)
            {
                first= true;
                Xor++;
            }
            else
                Xor++;
            a=Integer.parseInt(A.getText());
            b=Integer.parseInt(B.getText());
            ep=Integer.parseInt(EPOCHS.getText());
            int yd=a^b;
            op=4;
            String fill= Integer.toString(yd);
            DesiredO.setText(fill);
            multilayer();
        }
        else {
            JOptionPane.showMessageDialog(this, "You have to enter A & B values & number of training epechs.", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        }
        else{
            JOptionPane.showMessageDialog(this, "Incorrect entry, you can ONLY enter integers!","WARNING", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_XORActionPerformed

    private void XNORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XNORActionPerformed
        if(onlyDigits(A.getText(),A.getText().length()) && onlyDigits(B.getText(),B.getText().length()) && onlyDigits(EPOCHS.getText(),EPOCHS.getText().length()) ){
        if(!A.getText().equals("") && !B.getText().equals("") && !EPOCHS.getText().equals("")){
            if(Xnor==0)
            {
                first= true;
                Xnor++;
            }
            else
                Xnor++;
            
            a=Integer.parseInt(A.getText());
            b=Integer.parseInt(B.getText());
            ep=Integer.parseInt(EPOCHS.getText());
            int yd=not(a^b);
            op=5;
            String fill= Integer.toString(yd);
            
            DesiredO.setText(fill);
            multilayer();
        }
        else {
            JOptionPane.showMessageDialog(this, "You have to enter A & B values & number of training epechs.", "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        }
        else{
            JOptionPane.showMessageDialog(this, "Incorrect entry, you can ONLY enter integers!","WARNING", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_XNORActionPerformed

    private void ANDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ANDMousePressed
       NOT.setBackground(blue);
       AND.setBackground(red);
       NAND.setBackground(blue);
       OR.setBackground(blue);
       NOR.setBackground(blue);
       XOR.setBackground(blue);
       XNOR.setBackground(blue);
        
    }//GEN-LAST:event_ANDMousePressed

    private void NOTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NOTMousePressed
       NOT.setBackground(red);
       AND.setBackground(blue);
       NAND.setBackground(blue);
       OR.setBackground(blue);
       NOR.setBackground(blue);
       XOR.setBackground(blue);
       XNOR.setBackground(blue);
    }//GEN-LAST:event_NOTMousePressed

    private void NANDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NANDMousePressed
       NOT.setBackground(blue);
       AND.setBackground(blue);
       NAND.setBackground(red);
       OR.setBackground(blue);
       NOR.setBackground(blue);
       XOR.setBackground(blue);
       XNOR.setBackground(blue);
    }//GEN-LAST:event_NANDMousePressed

    private void ORMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ORMousePressed
       NOT.setBackground(blue);
       AND.setBackground(blue);
       NAND.setBackground(blue);
       OR.setBackground(red);
       NOR.setBackground(blue);
       XOR.setBackground(blue);
       XNOR.setBackground(blue);
    }//GEN-LAST:event_ORMousePressed

    private void NORMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NORMousePressed
       NOT.setBackground(blue);
       AND.setBackground(blue);
       NAND.setBackground(blue);
       OR.setBackground(blue);
       NOR.setBackground(red);
       XOR.setBackground(blue);
       XNOR.setBackground(blue);
    }//GEN-LAST:event_NORMousePressed

    private void XORMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XORMousePressed
       NOT.setBackground(blue);
       AND.setBackground(blue);
       NAND.setBackground(blue);
       OR.setBackground(blue);
       NOR.setBackground(blue);
       XOR.setBackground(red);
       XNOR.setBackground(blue);
    }//GEN-LAST:event_XORMousePressed

    private void XNORMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_XNORMousePressed
       NOT.setBackground(blue);
       AND.setBackground(blue);
       NAND.setBackground(blue);
       OR.setBackground(blue);
       NOR.setBackground(blue);
       XOR.setBackground(blue);
       XNOR.setBackground(red);
    }//GEN-LAST:event_XNORMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Nueral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Nueral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Nueral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Nueral.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Nueral().setVisible(true);
               
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField A;
    private javax.swing.JButton AND;
    private javax.swing.JTextField ActualO;
    private javax.swing.JTextField B;
    private javax.swing.JTextField BestE;
    private javax.swing.JTextField DesiredO;
    private javax.swing.JTextField EPOCHS;
    private javax.swing.JButton NAND;
    private javax.swing.JButton NOR;
    private javax.swing.JButton NOT;
    private javax.swing.JButton OR;
    private javax.swing.JButton XNOR;
    private javax.swing.JButton XOR;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
 class Draw extends JPanel{
    int[] coordinates={100,20};
    int mar=0;
    int mar2=150;
    int and[]=new int[4];
    int xi, yi,xi1,yi1=0;
    boolean flag=false;
    Draw(){
        
    }
    
    Draw(int A[], int xi, int yi){
       this.and=A;
       this.xi=xi;
       this.yi=yi;
        System.out.println("yi="+yi);
        System.out.println("xi="+xi);
        System.out.print("len"+and.length);
    }
    Draw(int A[], int xi, int yi,int xi1, int yi1){
       this.and=A;
       this.xi=xi;
       this.yi=yi;
       this.xi1=xi1;
       this.yi1=yi1;
       this.flag=true;
        System.out.println("yi="+yi);
        System.out.println("xi="+xi);
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g1=(Graphics2D)g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int width=getWidth();
        mar= width/2;
       
        int height=getHeight();
        mar2= height/2;
        
        g1.draw(new Line2D.Double(mar,mar2,mar+mar,mar2));
        g1.draw(new Line2D.Double(mar,mar2,mar-mar,mar2));
        g1.draw(new Line2D.Double(mar,mar2,mar,mar2+mar2));
        g1.draw(new Line2D.Double(mar,mar2,mar,mar2-mar2));
     
        g1.draw(new Line2D.Double(mar,mar2-yi,mar+xi,mar2));
        if(flag){
         g1.draw(new Line2D.Double(mar,mar2-yi1,mar+xi1,mar2));   
        }
        double x=(double)(width-2*mar)/(coordinates.length-1);
        double scale=(double)(height-2*mar)/getMax();
       for(int i=0; i<and.length;i++){
         if(i==0 && and[i]==0){
           g1.setPaint(Color.BLACK);
           g1.fill(new Ellipse2D.Double(mar,mar2,7,7));//(0,0)  
         }  
         if(i==0 && and[i]==1){
           g1.setPaint(Color.RED);
           g1.fill(new Ellipse2D.Double(mar,mar2,7,7));//(0,0)  
         }  
         if(i==1 && and[i]==0){
           g1.setPaint(Color.BLACK);
           g1.fill(new Ellipse2D.Double(mar+100,mar2,7,7));//(0,1) 
         }  
         if(i==1 && and[i]==1){
           g1.setPaint(Color.RED);
           g1.fill(new Ellipse2D.Double(mar+100,mar2,7,7));//(0,1) 
         }  
      
       if(i==2 && and[i]==0){
           g1.setPaint(Color.BLACK);
           g1.fill(new Ellipse2D.Double(mar,mar2-100,7,7));//(1,0)  
         }  
         if(i==2 && and[i]==1){
           g1.setPaint(Color.RED);
           g1.fill(new Ellipse2D.Double(mar,mar2-100,7,7));//(1,0)
         }  
         if(i==3 && and[i]==0){
           g1.setPaint(Color.BLACK);
           g1.fill(new Ellipse2D.Double(mar+100,mar2-100,7,7));//(1,1)
         }  
         if(i==3 && and[i]==1){
           g1.setPaint(Color.RED);
           g1.fill(new Ellipse2D.Double(mar+100,mar2-100,7,7));//(1,1)
         } 
       }
               
    }
    private int getMax(){
        int max=-Integer.MAX_VALUE;
        for(int i=0;i<coordinates.length;i++){
            if(coordinates[i]>max)
                max=coordinates[i];
           
        }return max;
    }       
     public void show1(int A[], int xi, int yi){
      flag=false;  
     
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //this.and=A;
    JFrame frame =new JFrame();
    frame.add(new Draw(A,xi,yi));
    frame.setSize(415,410);
    frame.setUndecorated(true);
    frame.getContentPane();
    frame.setLocation(470,35);
    frame.setVisible(true);  
    
      }
     public void show2(int A[], int xi, int yi, int xi1, int yi1){
        
    JFrame frame =new JFrame();
    frame.add(new Draw(A,xi,yi,xi1,yi1));
    frame.setSize(415,410);
    frame.setUndecorated(true);
    frame.getContentPane();
    frame.setLocation(470,35);
    frame.setVisible(true);  
     
      }
  
}