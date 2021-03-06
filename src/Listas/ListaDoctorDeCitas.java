package Listas;

import Clases.Doctor;
import Clases.Paciente;
import java.io.Serializable;
import javax.swing.JOptionPane;


public class ListaDoctorDeCitas implements Serializable {
    
    public class NodoPaciente implements Serializable {
        Paciente p ;
        NodoPaciente siguiente = null;
        NodoPaciente (Paciente pa) {
            p = pa;
        }
    }
    
    public class NodoDoctor implements Serializable {
        Doctor D;
        NodoDoctor siguiente = null;
        NodoPaciente cabpaciente = null;
        NodoDoctor (Doctor doc){
            D = doc;
        }
    }
    
    NodoDoctor P = null;
    int longitud = 0;

    
    public void InsertarDoctorCitas_Final (Doctor d){ 
        NodoDoctor Q = new NodoDoctor (d);
        if( P == null ){            
            P = Q;
        }else {
            NodoDoctor T = P;
            while (T.siguiente != null) {
                T = T.siguiente;
            }
            T.siguiente = Q;
        }
        longitud++;
    }
    
    public void InsertarPaciente (Doctor d, Paciente pa) {
        NodoDoctor Q = P;
        NodoDoctor T = null;
        while (Q!= null){
            if(Q.D.getCodigo().equals(d.getCodigo())){
                T = Q;
            }
            Q = Q.siguiente;
        }
        if(T==null){
            JOptionPane.showMessageDialog(null,"No hay doctor con ese codigo");
        }else{
            NodoPaciente nuevop = new NodoPaciente(pa);
            if(T.cabpaciente == null){
                T.cabpaciente = nuevop;
            }else{
                NodoPaciente S = T.cabpaciente;
                while(S.siguiente != null){
                    S = S.siguiente;
                }
                S.siguiente = nuevop;
            }
            T.D.setNumpacientes(T.D.getNumpacientes() + 1);
        }        
    }        
    
    public void CambiarDeDoctor (String codigoDANT, String dni, String codigoDNUE) {
        NodoDoctor Q = P;
        NodoDoctor NUE = null, ANT = null;
        NodoPaciente R, T = null;
        while (Q != null){      
            if(Q.D.getCodigo().equals(codigoDNUE)){
                NUE = Q;
            }
            if(Q.D.getCodigo().equals(codigoDANT)){
                ANT = Q;
            }
            Q = Q.siguiente;
        }        
        R = ANT.cabpaciente;
        while (R != null){
            if(R.p.getDNI().equals(dni)){                
                T = R;
            }
            R = R.siguiente;
        }
        if(NUE==null){
            JOptionPane.showMessageDialog(null,"No hay doctor con ese codigo");
        }else{
            NodoPaciente nuevop = new NodoPaciente(T.p);
            if(NUE.cabpaciente == null){
                NUE.cabpaciente = nuevop;
            }else{
                NodoPaciente S = NUE.cabpaciente;
                while(S.siguiente != null){
                    S = S.siguiente;
                }
                S.siguiente = nuevop;
            }
            NUE.D.setNumpacientes(NUE.D.getNumpacientes() + 1);
        }
        EliminarPaciente (ANT, dni);        
    }
    
    public void EliminarPaciente (NodoDoctor antiguo, String dnipaciente) {
        NodoPaciente Q = antiguo.cabpaciente;
        NodoPaciente T = null;
        int BAND = 1;     
        while(!(Q.p.getDNI().equals(dnipaciente))&&(BAND == 1)){           
            if(Q.siguiente != null){                                        
                T = Q;
                Q = Q.siguiente;
            }else{
                BAND = 0;
            }            
        }
        if(BAND == 0){
            JOptionPane.showMessageDialog(null,"No hay cita con el paciente con DNI: " + dnipaciente);
        }else{
            if(antiguo.cabpaciente == Q){
                antiguo.cabpaciente = Q.siguiente;
            }else{
                T.siguiente = Q.siguiente;
            }
        }
        
    }
    
    public void recorrerListadeListas () {
        NodoDoctor Q = P;
        while (Q != null){
            System.out.println("Dr. " + Q.D.getNombre() + " " + Q.D.getApellido());
            NodoPaciente T = Q.cabpaciente;
            while (T != null){
                System.out.println("\t" + T.p.getApellido() + " " + T.p.getNombre());
                T = T.siguiente;
            }
            Q = Q.siguiente;
        }
    }
    
    public void ObtenerCodigoDoctor (Doctor d) {
        NodoDoctor Q = P;
        while(Q != null){
            if(Q.D.getEspecialidad().equals(d.getEspecialidad())){
                if(Q.D.getApellido().equals(d.getApellido())){
                    if(Q.D.getNombre().equals(d.getNombre())){
                        d.setCodigo(Q.D.getCodigo());
                    }
                }                
            }
            Q = Q.siguiente;
        }
    }
}