
package udea.teoria.practica1;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author Joaquin David and Julian Esteban 
 * Descripcion : Clase que contiene las peraciones que se pueden hacer cobre las ecuaciones.
 */

public class Operaciones {
  /**
 *
 * Metodo que recibe un List que contiene una ecuacion para aplicarle ARDEN, retorna un List con 
 * el resulado de aplicar Arden a la ecuacion.
     * @param ecuacion
     * @return 
 */  
  public List AplicaArden(List ecuacion){
     char evaluado=(char) ecuacion.get(2);
     final String arden="*";
     final String val="1";
     final char[] r=arden.toCharArray();
     final char[] p=val.toCharArray();
     for(int i=3;i<ecuacion.size();i++){
         char x=(char) ecuacion.get(i);
         if(Objects.equals(evaluado, x)){
            ecuacion.set(i,r[0]);
            if(!(i==ecuacion.size()-1)){
             ecuacion.remove(i+1);   
            }
            ecuacion.set(1,p[0]);
            return ecuacion;
         }   
     } 
     return ecuacion;
  }
  
  
  
     /**
 *
 * Metodo que recibe un List que contiene una ecuacion de la forma 00A1C+1C+0B, es decir dos lectrar 
 * en dosterminos consecutivos, retorna un List con el resulado de agrupar la  ecuacion.
     * @param agrupalo
     * @return 
 */ 
  public List Agrupar1(List agrupalo){
      boolean termino=false;
      boolean entroUnaVez=false;
      int i=3;
      int desde=3;
       int otroDesde=0;
      final String su="+";
      final char[] sum= su.toCharArray();
      final char suma=sum[0];
      final String pa="(";
      final char[] pab= pa.toCharArray();
      final char pabr=pab[0];
      final String pc=")";
      final char[] pci= pc.toCharArray();
      final char pcie=pci[0];
      while(termino==false){
            boolean entro=false;
          for(int j=3;j<agrupalo.size();j++){           
             char recorre=(char)agrupalo.get(j);
                if(recorre==suma){
                    // aqui aumente 
                    desde=j+1;
                }
                else if((recorre >= 'a' && recorre <= 'z' ) || (recorre >= 'A' && recorre <= 'Z' ) ){
                   // Posicion de la letra 
                   int k=j ;
                   for(int p=k+1; p<agrupalo.size();p++){
                       char otro=(char)agrupalo.get(p);
                       if(otro==suma){
                           otroDesde=p+1;
                           
                       }else if (otro==recorre){
                          agrupalo.add(desde,pabr);
                          agrupalo.add(k+1,suma);
                          agrupalo.remove(k+2);
                          agrupalo.remove(k+2);
                          char ultimo=(char)agrupalo.get(agrupalo.size()-1);
                          //otroDesde=otroDesde+1;
                          while(otroDesde!=p){
                              char ag=(char)agrupalo.get(otroDesde);
                              agrupalo.remove(otroDesde);
                              agrupalo.add(k+2,ag);
                              k=k+1;
                              otroDesde=otroDesde+1;
                          }
                          try{
                          agrupalo.remove(otroDesde);
                          // siguiente linea quitarla en caso de error
                          agrupalo.remove(otroDesde);
                          }catch(Exception E){
                          //agrupalo.remove(otroDesde-1);    
                          }
                          agrupalo.add(k+2,pcie);
                          agrupalo.add(k+3, recorre);
                          agrupalo.add(k+4, suma);
                          p=agrupalo.size();
                          entroUnaVez=true;
                       }
                   } 
                }
                if(entroUnaVez==true){
                 j=agrupalo.size(); 
                 entroUnaVez=false;
                }
            }
            termino=true;
//          for(int m=3;m<agrupalo.size();m++){
//         char x=(char) agrupalo.get(m);
//         if((x >= 'a' && x <= 'z' ) || (x>= 'A' &&x <= 'Z' )){
//             for(int j=m+1;j<agrupalo.size();j++){
//                 char y=(char)agrupalo.get(j);
//                 if((y >= 'a' && y <= 'z' ) || (y>= 'A' &&y <= 'Z' )){
//                     if(x==y){
//                        termino=false;
//                        entro=true;
//                        j=agrupalo.size();
//                        m=agrupalo.size();
//                     }
//                 }
//             }
//         }
//     }
        if(entro==false){
           termino=true; 
        }  
        }
      char x =(char)agrupalo.get(agrupalo.size()-1);
      if(x==suma){
          agrupalo.remove(agrupalo.size()-1);
      }
      
      return agrupalo;
    } 
       /**
 *
 * Metodo que recibe un List que contiene una ecuacion de la forma 00A1C+1B+0C, es decir no hay dos letras
 * iguales en dos terminos consecutivos, retorna un List con el resulado de agrupar la  ecuacion.
     * @param agrupalo
     * @return 
 */ 
   public List Agrupar21(List agrupalo){
      boolean termino=false;
      boolean entroUnaVez=false;
      int i=3;
      int desde=3;
       int otroDesde=0;
      final String su="+";
      final char[] sum= su.toCharArray();
      final char suma=sum[0];
      final String pa="(";
      final char[] pab= pa.toCharArray();
      final char pabr=pab[0];
      final String pc=")";
      final char[] pci= pc.toCharArray();
      final char pcie=pci[0];
      while(termino==false){
            boolean entro=false;
          for(int j=3;j<agrupalo.size();j++){           
             char recorre=(char)agrupalo.get(j);
                if(recorre==suma){
                    // aqui aumente 
                    desde=j+1;
                }
                else if((recorre >= 'a' && recorre <= 'z' ) || (recorre >= 'A' && recorre <= 'Z' ) ){
                   // Posicion de la letra 
                   int k=j ;
                   for(int p=k+1; p<agrupalo.size();p++){
                       char otro=(char)agrupalo.get(p);
                       if(otro==suma){
                           otroDesde=p+1;
                           
                       }else if (otro==recorre){
                          agrupalo.add(desde,pabr);
                          agrupalo.add(k+1,suma);
                          agrupalo.remove(k+2);
                         // agrupalo.remove(k+2);
                          char ultimo=(char)agrupalo.get(agrupalo.size()-1);
                          //otroDesde=otroDesde+1;
                          while(otroDesde!=p){
                              char ag=(char)agrupalo.get(otroDesde+1);
                              agrupalo.remove(otroDesde+1);
                              agrupalo.add(k+2,ag);
                              k=k+1;
                              otroDesde=otroDesde+1;
                          }
                          agrupalo.remove(otroDesde+1);
                          agrupalo.add(k+2,pcie);
                          agrupalo.add(k+3, recorre);
                          p=agrupalo.size();
                          entroUnaVez=true;
                       }
                   } 
                }
                if(entroUnaVez==true){
                 j=agrupalo.size(); 
                 entroUnaVez=false;
                }
            }
          for(int m=3;m<agrupalo.size();m++){
         char x=(char) agrupalo.get(m);
         if((x >= 'a' && x <= 'z' ) || (x>= 'A' &&x <= 'Z' )){
             for(int j=m+1;j<agrupalo.size();j++){
                 char y=(char)agrupalo.get(j);
                 if((y >= 'a' && y <= 'z' ) || (y>= 'A' &&y <= 'Z' )){
                     if(x==y){
                        termino=false;
                        entro=true;
                        j=agrupalo.size();
                        m=agrupalo.size();
                     }
                 }
             }
         }
     }
        if(entro==false){
           termino=true; 
        }  
        }
      char x =(char)agrupalo.get(agrupalo.size()-1);
      if(x==suma){
          agrupalo.remove(agrupalo.size()-1);
      }
      
      return agrupalo;
    } 
  
    /**
 *
 * Metodo que recibe un List que contiene una ecuacion para aplicarle agrupar Termininos semejantes, retorna un List con 
 * el resulado de agrupar la ecuacion. evalua la ecuacion entrante para saber la forma en la que vienen
 * los terminos para asi pasarle la ecuacion a Agrupar1 o Agrupar2
     * @param agrupalo
     * @return 
 */ 
    public List Agrupar(List agrupalo){
    for(int i=3;i<agrupalo.size();i++){
         char x=(char) agrupalo.get(i);
         if((x >= 'a' && x <= 'z' ) || (x>= 'A' &&x <= 'Z' )){
             for(int j=i+1;j<agrupalo.size();j++){
                 char y=(char)agrupalo.get(j);
                 if((y >= 'a' && y <= 'z' ) || (y>= 'A' &&y <= 'Z' )){
                     if(x==y){
                         List result= Agrupar1(agrupalo);
                        return Agrupar(result);
                     }else{
                         j=agrupalo.size();
                     }
                 }
             }
         }
     }
        List result=Agrupar21(agrupalo);
        return result;
    } 
     /**
 *
 * Metodo que garantiza que al momento de aplicar arden a una ecuacion la letra del primer termino sera
 * la correspondiente a la variable que se esta evaluando, es decir, que sea de la forma a=ax+b
     * @param agrupalo
     * @return 
 */ 
    public List garantizaOrden(List evaluar){
        boolean primeraVez=true;
        boolean continuar=true;
        boolean pasoParentesis=false;
        int desde=0;
        final String su="+";
        final char[] sum= su.toCharArray();
        final char suma=sum[0];
          final String par="(";
        final char[] pare= par.toCharArray();
        final char parent=pare[0];
        char evaluado=(char)evaluar.get(2);
        for(int i=3;i<evaluar.size();i++){
            char otro=(char)evaluar.get(i);
            if((otro==suma )&&(pasoParentesis==false)){
                desde=i+1;
            }else if(otro==parent){
                pasoParentesis=true;
            }
                    else if((otro >= 'a' && otro <= 'z' ) || (otro>= 'A' &&otro <= 'Z' )){
                    if(primeraVez &&(otro==evaluado) ){
                    return evaluar; 
                    
                    }else{
                     primeraVez=false;
                     if(otro==evaluado){
                          evaluar.add(3,suma);
                          i=i+1;
                         while(continuar){
                             char x=(char) evaluar.get(i);
                            while(i!=desde){
                                 char pasalo=(char) evaluar.remove(i);
                                    evaluar.add(3,pasalo);
                                    desde=desde+1;
                         
                                }
                                // comentar esto si no funcionan bien los demas
                                evaluar.remove(i);
                                 continuar=false;
                                i=evaluar.size();
                             }
                     } 
                    
                }
            }
        }
        char x =(char)evaluar.get(evaluar.size()-1);
      if(x==suma){
          evaluar.remove(evaluar.size()-1);
      }
        return evaluar;
    }
}

