import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Comunes {
	 
	 //Primera línea de documentación
    public void llenartextos(java.awt.event.KeyEvent evt, int tamanio, String txt){
        char C= evt.getKeyChar();
        int car = evt.getKeyChar();
        if(Character.isDigit(C)&& !Character.isLetter(C))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo letras!!!", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        else if((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=47
                ||(int)evt.getKeyChar()>=58 && (int)evt.getKeyChar()<=64
                || (int)evt.getKeyChar()>=91 && (int)evt.getKeyChar()<=96
                || (int)evt.getKeyChar()>=123 && (int)evt.getKeyChar()<=129
                || (int)evt.getKeyChar()>=131 && (int)evt.getKeyChar()<=159
                || (int)evt.getKeyChar()>=166 && (int)evt.getKeyChar()<=191)
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo letras!!!", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        //----------------Poner limite de caracteres--------------------
        if (txt.length() == tamanio){
           evt.consume();
           JOptionPane.showMessageDialog(null,"¡Sólo puede ingresar "+ tamanio +" carácteres!","Mensaje del Sistema",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void llenarnumeros(java.awt.event.KeyEvent evt, int tamanio, String txt){
        char C= evt.getKeyChar();
     
        if(Character.isLetter(C))
        {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        else if((int)evt.getKeyChar()>32 && (int)evt.getKeyChar()<=47
                ||(int)evt.getKeyChar()>=58 && (int)evt.getKeyChar()<=64
                || (int)evt.getKeyChar()>=91 && (int)evt.getKeyChar()<=96
                || (int)evt.getKeyChar()>=123 && (int)evt.getKeyChar()<=255){
           evt.consume();
           JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
       }
        //----------------Poner limite de caracteres--------------------
        if (txt.length()== tamanio){
            evt.consume();
            JOptionPane.showMessageDialog(null,"¡Sólo puede ingresar "+ tamanio +" números!","Mensaje del Sistema",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void maximo(java.awt.event.KeyEvent evt, int tamanio, String txt){
        //----------------Poner limite de caracteres--------------------
        if (txt.length()== tamanio){
            evt.consume();
            JOptionPane.showMessageDialog(null,"¡Sólo puede ingresar "+ tamanio +" carácteres!","Mensaje del Sistema",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void llenarnumerodecimales(java.awt.event.KeyEvent evt, int tamanioEntero, int tamanioDecimal, String txt){
        //----------------Poner limite de caracteres--------------------
        char c = evt.getKeyChar();
        int bDecimal = 0, bEntero = 0;
//        if ((c >= '0') || (c <= '9') && bEntero)
//            bEntero++;
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != '.')) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "Debe ingresar sólo números!!!", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
        if (c == '.' && txt.contains(".")) {
            evt.consume();
            JOptionPane.showMessageDialog(null, "No puede ingresar más puntos!!!", "Mensaje del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void esDecimal(java.awt.event.KeyEvent evt, int tamanioE, int tamanioD, String cad)
    {
        boolean hayPunto=false;
        StringBuilder parteEntera = new StringBuilder();
        StringBuilder parteDecimal = new StringBuilder();
        int i=0, posicionDelPunto;

        for( i=0;i<cad.length(); i++ )
            if ( cad.charAt(i) == '.'){ //Detectar si hay un punto decimal en la cadena
                hayPunto=true;
            }
        
        if(hayPunto){ //Si hay punto guardar la posicion donde se encuentra el carater punto
            posicionDelPunto=cad.indexOf('.'); //(si la cadena tiene varios puntos, detecta donde esta el primero).
            
            for( i=0;i<posicionDelPunto; i++ )
                parteEntera.append(cad.charAt(i)) ; //Guardar la parte entera en una variable

            for(i = 0; i<parteEntera.length(); i++)
                if( ! Character.isDigit(parteEntera.charAt(i)) ) //Si alguno de los caracteres de la parte entera no son digitos no es decimal
                    evt.consume();

            for( i=posicionDelPunto+1;i<cad.length(); i++ )
                parteDecimal.append(cad.charAt(i)); //Guardar la parte decimal en una variable

            for(i = 0; i<parteDecimal.length(); i++)
                if( ! Character.isDigit(parteDecimal.charAt(i)) ) //Si alguno de los caracteres de la parte decimal no es un digito no es decimal
                    evt.consume(); //Incluye el caso en el que la cadena tenga dos o mas puntos

            if( parteDecimal.length() > tamanioD-1 )
                evt.consume();            
        }
    }

    
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
    public boolean validarEmail(String email) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
     }
    
    public boolean ValidarFechaInicial(Date inicio, Date fin){
        if(inicio.after(fin))
            return true;
        else
            return false;    
    }
    
    public boolean ValidarFechaFinal(Date inicio, Date fin){
        if(fin.before(inicio))
            return true;
        else
            return false;
    }
}
