
package udea.teoria.practica1;

import java.util.List;

/**
 *
 * @author Joaquin David and Julian Esteban 
 */
public class ProcesarEcuaciones {

    public ProcesarEcuaciones() {
    }
    /**
 *
 * Metodo que recibe una lista con todas las ecuaciones del AF y ingresa dos ceros al inicio de cada 
 * expresion para validar cuando se aplica arden
     * @param ecuaciones
     * @return 
 */
    public List organizarEcuaciones(List<List> ecuaciones){
        final String valores="0";
        final char[] val = valores.toCharArray();
        for(int i=0;i<ecuaciones.size(); i++){
            List actual=(List)ecuaciones.remove(i);
            actual.add(0,val[0]);
            actual.add(1,val[0]);
            ecuaciones.add(i,actual);
        }
        return ecuaciones;
        
    }
    /**
 *
 * Metodo recursivo que resive un List con todas las ecuaciones de AF, un list que le indica las letras que 
 * se permiten en la expresion que esta evaluando y un entero que le indica en que posicion del List de ecuaciones
 * se encuentra la ecuacion que debe evaluar, encargado de llamar a todos los demas metodos para simplificar,
 *agrupar y aplicar arden a las ecuaciones, al terminar retorna un List que contiene la expresion regular final 
 * del AF
     * @param ecuaciones
     * @param aceptados
     * @param posicion
     * @return 
 */
    public List generarExpresionR(List<List> ecuaciones,List aceptados,int posicion,List recorridos){
       List recorrer=ecuaciones.remove(posicion);
     //  recorridos.add(recorrer);
       aceptados.add(recorrer.get(2));
         for(int i=3;i<recorrer.size();i++){
             char encontrado=(char)recorrer.get(i);
               if((encontrado >= 'a' && encontrado <= 'z' ) || (encontrado >= 'A' && encontrado <= 'Z' ) ){
                 if(!aceptados.contains(encontrado)){
                     for(int j=0;j<ecuaciones.size();j++){
                         List buscado= ecuaciones.get(j);
                         if((char)buscado.get(2)==encontrado){
                            List resultado= generarExpresionR(ecuaciones,aceptados,j,recorridos);
                            List result=(List)resultado.get(resultado.size()-1);
                            recorrer.remove(i);
                            int k=2;
                            int p=result.size()-1;
                            while(k!=p){
                            recorrer.add(i, result.get(p));
                            p=p-1;
                            }
                         }
                    }
                 }
               }      
         }
        aceptados.remove(recorrer.get(2));
        Operaciones arden = new Operaciones();
        List  resultado = arden.Agrupar(recorrer);
        System.out.println(resultado.toString());
        List ordenada = arden.garantizaOrden(resultado);
        System.out.println(ordenada.toString());
        List ardeni = arden.AplicaArden(resultado);
        System.out.println(ardeni.toString());
        ecuaciones.add(posicion,ardeni);
        recorridos.add(ardeni);
        return recorridos;
        
    }
    
}
