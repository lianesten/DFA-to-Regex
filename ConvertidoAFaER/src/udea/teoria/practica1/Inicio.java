
package udea.teoria.practica1;
/**
 *
 * @author jULIAN ESTEBAN MONTOYA 
 *          JOAQUIN DAVID HERNANDEZ
 */

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.*;
import udea.teoria.practica1.ProcesarEcuaciones;
public class Inicio extends javax.swing.JFrame {
//        int estados = Integer.parseInt(JOptionPane.showInputDialog("Digite numero de estados"));
//        int simbolos = Integer.parseInt(JOptionPane.showInputDialog("Digite numero de simbolos de entrada"));

       

// JTextField [][] arreglo = new JTextField[estados+1][simbolos+2];
    private String[] args;

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        Valida();
        int iy=50;//incremento en x
        String letras = "ABCDFGHIJKLMNOPQRSTUVWXYZ-------------------------------------------------------------------------------------------";
        char[] validaEstados = letras.toCharArray();
        int indice = 1;
        for(int q=0;q<estados+1;q++){

            int ix=100;
            for(int j=0;j<simbolos+2;j++){//El +2 es el campo de aceptacion o rechazo
            pinta(ix,iy,q,j);
            ix=ix+30;//+30 pixeles
            arreglo[0][0].setText("e/s");
            arreglo[0][j].setText(Integer.toString(j-1));//aSIGNA DESDE EL SIMBOLO DE ENTRAD CERO.
            arreglo[0][j].setEditable(false);

            }
              iy=iy+30;//+30 pixeles
              indice=indice+1;
            }
            arreglo[0][simbolos+1].setText("A/R");

            for(int i=1;i<estados+1;i++) {
            char a = validaEstados[i-1];
            String estdo= String.valueOf(a);
            arreglo[i][0].setText(estdo); 
            arreglo[i][0].setEditable(false);
                }
            
    }
    
    
    void pinta(int px, int py, int indice,int indice2) {
        arreglo[indice][indice2] = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        arreglo[indice][indice2] .setText("");
        getContentPane().add(arreglo[indice][indice2] , new org.netbeans.lib.awtextra.AbsoluteConstraints(px, py, 30, -1));

        pack();
    }
    
    List construyeEcuacion( JTextField [][] tabla){
         List ecuacion;
         List ecuaciones = new ArrayList();
         int posicion = 600;

         for(int i=1;i<=estados;i++){

            boolean primeraVez=true,indice=false,flag=true;
            ecuacion= new ArrayList();
            for(int j=0;j<simbolos+1;j++){
                indice=false;
                if(primeraVez){
                    char[] value = tabla[i][j].getText().toCharArray();
                    ecuacion.add(value[0]);
                    primeraVez=false;
                    //indice=false;
                }else{
                    if(tabla[i][j].getText().equals("E")){
                        continue;
                    }else{
                        char[] value = tabla[0][j].getText().toCharArray();
                        char[] value1 = tabla[i][j].getText().toCharArray();
                        ecuacion.add(value[0]);//añade el primer simbolo de la ecuacion
                        ecuacion.add(value1[0]);//añade la primera transicion del estado
                        if(!tabla[i][j].getText().equals("E") && tabla[i][j+1].getText().equals("E") && !tabla[i][j+2].getText().equals("E") && flag == true){
                            String suma = "+";
                            char[] suma1 = suma.toCharArray(); 
                            ecuacion.add(suma1[0]); 
                            flag = false;
                        }
                        if(j+1<simbolos+1 &&  !tabla[i][j+1].getText().equals("E")){//Hay mas terminos por añadir  por lo tanto añade un +

                                String suma = "+";
                                char[] suma1 = suma.toCharArray();
                                ecuacion.add(suma1[0]);
                            }else {
                                if(tabla[i][j+1].getText().equals("E")){
                                    j=j+1;indice=true;}
                                if(tabla[i][j+1].getText().equals("1")){
                                  String suma = "+";
                                  char[] suma1 = suma.toCharArray(); 
                                  String lambda = "#";
                                  char[] lambda1 = lambda.toCharArray();
                                  if(flag != false){
                                  ecuacion.add(suma1[0]); }
                                  ecuacion.add(lambda1[0]); 
                                  if(indice==true){j=j-1;}
                                        }
                                    }
                     }
                    }//end else
                    }
              String suma = "+";
              char[] suma1 = suma.toCharArray();
              String lambda = "#";
              char[] lambda1 = lambda.toCharArray();
              char compara= (char)ecuacion.get(ecuacion.size()-2);
              char compara2=(char)ecuacion.get(ecuacion.size()-1);
             if(compara2==lambda1[0] && (compara!=suma1[0])){
                ecuacion.add(ecuacion.size()-1, suma1[0]);
             }
             if(compara2==suma1[0]){
                 ecuacion.remove(ecuacion.size()-1);
             }
             ecuaciones.add(ecuacion);
             muestraEcuacion(ecuacion,posicion);
             posicion=posicion+50;
            }

            
      return ecuaciones;
    }
    
void  Valida(){
    
      try {
           estados = Integer.parseInt(JOptionPane.showInputDialog("Digite numero de estados"));
           simbolos = Integer.parseInt(JOptionPane.showInputDialog("Digite numero de simbolos de entrada"));
           arreglo = new JTextField [estados+1][simbolos+2];
      } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "Verifique que solo se hallan ingresado caractener numericos!!");
          Valida();
      }

  }
    
    
    
 
   void muestraEcuacion(List ecuacion,int posicion){
       String igual = "=";
       char[] igual1 = igual.toCharArray();
       ecuacion.add(1, igual1[0]);
       String mostrar =ecuacion.toString();
       StringTokenizer tokens=new StringTokenizer(mostrar,",[]");
       String ecuacionFinal="";
        while(tokens.hasMoreTokens()){
            ecuacionFinal = ecuacionFinal+(tokens.nextToken());
        }
        JLabel ec= new JLabel(ecuacionFinal);
        ec.setLayout(null);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()

                .addComponent(ec,posicion, posicion, posicion))
                
                
                
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
//                .addGap(10, 10, 10)
//                .addComponent(ec,GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                   .addComponent(ec,posicion, posicion, posicion) 
//                .addGap(33, 33, 33)

            )
        );
        pack();
     //  JOptionPane.showMessageDialog(rootPane, ecuacionFinal);
   }
   
/*
        private JLabel jLabel2;
        jLabel2.setText(i);
    
    */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BotonAceptar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        limpiarCampos = new javax.swing.JButton();
        nuevoAutomata = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BotonAceptar.setText("Aceptar");
        BotonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAceptarActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingrese los datos al AF.");

        limpiarCampos.setText("Limpiar");
        limpiarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarCamposActionPerformed(evt);
            }
        });

        nuevoAutomata.setText("Nuevo ");
        nuevoAutomata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoAutomataActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nuevoAutomata)
                    .addComponent(limpiarCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonAceptar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(BotonAceptar)
                .addGap(24, 24, 24)
                .addComponent(limpiarCampos)
                .addGap(27, 27, 27)
                .addComponent(nuevoAutomata)
                .addContainerGap(133, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAceptarActionPerformed
        // TODO add your handling code here:
         if(estados>25){
             JOptionPane.showMessageDialog(null,"El maximo de estados permitidos es 25","Alerta", JOptionPane.ERROR_MESSAGE);
             getContentPane().removeAll();
             getContentPane().revalidate();
            getContentPane().repaint();
            this.dispose();
            Inicio.main(args);
         }
          getContentPane().getComponent(3);
          for(int i=1;i<=estados;i++){
            for(int j=1;j<=simbolos;j++){
                if(arreglo[i][j].getText().compareTo("")==0){
                    JOptionPane.showMessageDialog(rootPane, "Faltan campos por llenar!");
                    return;
                }
                if(arreglo[i][j].getText().length()>1){
                    JOptionPane.showMessageDialog(rootPane, "Sólo se acepta un caracterer por campo requerido");
                    return;                    
                }
                char[] c = arreglo[i][j].getText().toCharArray();   
                char esletra = c[0];
                if(!((esletra>= 'a' && esletra <= 'z' ) || (esletra>= 'A' &&esletra<= 'Z' ))){
                    JOptionPane.showMessageDialog(rootPane, "En los campos de estados, solo se aceptan caracteres alfabeticos!");
                    return;
                }
                if(j==simbolos){
                    String valido = arreglo[i][j+1].getText().toString();
                            if(valido.equals("0") ||valido.equals("1") ){
                                continue;
                               }else{                    
                                JOptionPane.showMessageDialog(rootPane, "En los campos de aceptacion o rechazo, solo se acepta un 1 como aceptacion o 0 como rechazo!");                    
                                return;}
                     }
            }
          }
          //SE VALIDA QUE LAS TRANSICIONES QUE SE ASIGNEN A LOS ESTADOS SI ESTEN HACIA ESATDOS DEFINIDOS
          for (int i = 1; i < estados+1; i++) {
             for (int j = 1; j <= simbolos; j++) {
                    String a =arreglo[i][j].getText();
                    if(a.equals("E")){
                        continue;
                    }else{
                    boolean siTransicion=false;
                    for (int k = 1; k <= estados; k++) {
                        String b =arreglo[k][0].getText();
                        if(a.equals(b)){
                            siTransicion= true;
                        }
                    }
                        if(siTransicion==false){
                            JOptionPane.showMessageDialog(rootPane, "No esta permitido hacer transicion hacia el estado "+a+", ya que no esta definido, por favor verifique!");
                            return;
                        }
                    
                }
          }
          }
          
          
        int posicion=0;   
       List ecuaciones= construyeEcuacion(arreglo);
       for(int i=0;i<ecuaciones.size();i++){
           List recorrer=(List)ecuaciones.get(i);
           recorrer.remove(1);
       }
       List aceptados=new ArrayList();
        ProcesarEcuaciones procesa= new ProcesarEcuaciones();
         List r=procesa.organizarEcuaciones(ecuaciones);
         List recorridos=new ArrayList();
          String landa="#";
          char[] lan= landa.toCharArray();
         for(int l=0;l<r.size();l++){
             List este=(List) r.get(l);
             char comparame=(char)este.get(este.size()-1);
             if(comparame==lan[0]){
                posicion=l; 
                l= r.size();
             }
         }
         List Eregular=procesa.generarExpresionR(r, aceptados, posicion,recorridos);
         List rog;
         int posicio=900;
         for(int x=0;x<Eregular.size();x++){
              rog=(List)Eregular.get(x);
              rog.remove(0);
              rog.remove(0);
              char ultimo=(char)rog.get(rog.size()-1);
             
              String mas="+";
              char[] ms= mas.toCharArray();
              boolean continuar=true;
              while(continuar){
                  char ulti=(char)rog.get(rog.size()-1);
                  if(ulti==ms[0]||ulti==lan[0]){
                    rog.remove(rog.size()-1);
                  }else{
                      continuar=false;
                  }
              } 
            muestraEcuacion(rog,posicio);
            posicio=posicio+50;
         }
    }//GEN-LAST:event_BotonAceptarActionPerformed

    private void limpiarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarCamposActionPerformed
        int es=estados+1;
        int entran= simbolos+2;
        int total= (es*entran)+4;
        int to= getContentPane().getComponentCount();
        int compo=to;
        while( total!=compo){
        getContentPane().remove(compo-1);
        compo=compo-1;
        getContentPane().repaint();
    
    }
       
    }//GEN-LAST:event_limpiarCamposActionPerformed

    private void nuevoAutomataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoAutomataActionPerformed
        
        getContentPane().removeAll();
        getContentPane().revalidate();
        getContentPane().repaint();
        this.dispose();
       Inicio.main(args);
    }//GEN-LAST:event_nuevoAutomataActionPerformed

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
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton limpiarCampos;
    private javax.swing.JButton nuevoAutomata;
    // End of variables declaration//GEN-END:variables
  int estados; 
//    = Integer.parseInt(JOptionPane.showInputDialog("Digite numero de estados"));
    int simbolos;
//    = Integer.parseInt(JOptionPane.showInputDialog("Digite numero de simbolos de entrada"));
    JTextField [][] arreglo;
    
    //= new JTextField[estados+1][simbolos+2];
}
