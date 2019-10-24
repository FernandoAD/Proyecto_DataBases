import java.io.*;
import java.sql.*;
import java.util.StringTokenizer;
import java.util.Date;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class HospitalAD{

  private BufferedReader archivoIn;
  private PrintWriter archivoOut;
  private Connection conexion;
  private Statement statement;
  private Date date;
  private DateFormat dateFormat, horaFormat;

  private PacienteDP pacientedp;
  private DoctorDP doctordp;
  private AnalisisDP analisisdp;

  public HospitalAD(){
    try{
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conexion=DriverManager.getConnection("jdbc:mysql://localhost/Hospital?user=root");
      System.out.println("Conexón exitosa a la BD...");
    }
    catch(ClassNotFoundException cnfe){
			System.out.println("Error: "+cnfe);
		}
		catch(InstantiationException ie){
			System.out.println("Error 2: "+ie);
		}
		catch(IllegalAccessException iae){
			System.out.println("Error 3: "+iae);
		}
		catch(SQLException sqle){
			System.out.println("Error 4: "+sqle);
		}
  }

//Funciones para PacienteGUI
  public String capturarPaciente(String datos){
    String resultado="";
    String strInsert="";
    pacientedp= new PacienteDP(datos);

    strInsert="INSERT INTO Paciente VALUES("+pacientedp.toStringSql()+")";
    try{
      statement= conexion.createStatement();
      statement.executeUpdate(strInsert);
      statement.close();
      resultado="Datos capturados: "+datos;
      System.out.println(strInsert);
    }
    catch(SQLException ioe){
      resultado="Error: "+ioe;
    }
    return resultado;
  }

  public String consultarPacientes(){
    String datos="";
    String strQuery;
    ResultSet tr;

    strQuery="SELECT * FROM Paciente";
    pacientedp= new PacienteDP();

    try{
      statement=conexion.createStatement();
      tr= statement.executeQuery(strQuery);
      while(tr.next()){
        pacientedp.setClavePaciente(tr.getString(1));
        pacientedp.setNombrePaciente(tr.getString(2));
        pacientedp.setDireccion(tr.getString(3));
        pacientedp.setTelefonoPaciente(tr.getInt(4));
        datos= datos+pacientedp.toString()+ "\n";
      }
      statement.close();
    }
    catch(SQLException fnfe){
			datos="NOT_FOUND"+fnfe;
		}
    return datos;
  }

  public String consultarPacienteClave(String clavePaciente){
    String datos="";
    boolean encontrado=false;
    String strQuery;
    ResultSet tr;

    strQuery="SELECT * FROM Paciente WHERE clave_paciente='"+clavePaciente+"'";
    pacientedp= new PacienteDP();

    try{
      statement=conexion.createStatement();
      tr= statement.executeQuery(strQuery);
      while(tr.next() || encontrado==false){
        pacientedp.setClavePaciente(tr.getString(1));
        pacientedp.setNombrePaciente(tr.getString(2));
        pacientedp.setDireccion(tr.getString(3));
        pacientedp.setTelefonoPaciente(tr.getInt(4));
        datos= pacientedp.toString()+"\n";
        encontrado=true;
      }
      statement.close();

      if(!encontrado){
        datos="NOT_FOUND";
      }
    }
    catch(SQLException sqle){
			datos="Error:"+sqle;
		}
    return datos;
  }

  public String actualizarDatosPaciente(){

    return "hola";
  }

  //Funciones para DoctorGUI
  public String capturarDoctor(String datos){
    String resultado="";
    String strInsert="";
    doctordp= new DoctorDP(datos);

    strInsert="INSERT INTO Doctor VALUES("+doctordp.toStringSql()+")";
    try{
      statement= conexion.createStatement();
      statement.executeUpdate(strInsert);
      statement.close();
      resultado="Datos capturados: "+datos;
      System.out.println(strInsert);
    }
    catch(SQLException ioe){
      resultado="Error: "+ioe;
    }
    return resultado;
  }

  public String consultarDoctores(){
    String datos="";
    String strQuery;
    ResultSet tr;

    strQuery="SELECT * FROM Doctor";
    doctordp= new DoctorDP();

    try{
      statement=conexion.createStatement();
      tr= statement.executeQuery(strQuery);
      while(tr.next()){
        doctordp.setClaveDoctor(tr.getString(1));
        doctordp.setNombreDoctor(tr.getString(2));
        doctordp.setEspecialidad(tr.getString(3));
        doctordp.setTelefonoDoctor(tr.getInt(4));
        doctordp.setDireccion(tr.getString(5));
        datos= datos+doctordp.toString()+ "\n";
      }
      statement.close();
    }
    catch(SQLException fnfe){
			datos="NOT_FOUND"+fnfe;
		}
    return datos;
  }

  public String consultarDoctorClave(String claveDoctor){
    String datos="";
    boolean encontrado=false;
    String strQuery;
    ResultSet tr;

    strQuery="SELECT * FROM Doctor WHERE clave_doctor='"+claveDoctor+"'";
    doctordp= new DoctorDP();

    try{
      statement=conexion.createStatement();
      tr= statement.executeQuery(strQuery);
      while(tr.next() || encontrado==false){
        doctordp.setClaveDoctor(tr.getString(1));
        doctordp.setNombreDoctor(tr.getString(2));
        doctordp.setEspecialidad(tr.getString(3));
        doctordp.setTelefonoDoctor(tr.getInt(4));
        doctordp.setDireccion(tr.getString(5));
        datos= doctordp.toString()+"\n";
        encontrado=true;
      }
      statement.close();

      if(!encontrado){
        datos="NOT_FOUND";
      }
    }
    catch(SQLException sqle){
			datos="Error:"+sqle;
		}
    return datos;
  }

  public String actualizarDatosDoctor(){

    return "hola";
  }

  //Funciones para AnalisisGUI
  public String capturarAnalisis(String datos){
    String resultado="";
    String strInsert="";
    analisisdp= new AnalisisDP(datos);

    strInsert="INSERT INTO Analisis VALUES("+analisisdp.toStringSql()+")";
    try{
      statement= conexion.createStatement();
      statement.executeUpdate(strInsert);
      statement.close();
      resultado="Datos capturados: "+datos;
      System.out.println(strInsert);
    }
    catch(SQLException ioe){
      resultado="Error: "+ioe;
    }
    return resultado;
  }

  public String consultarAnalisis(){
    String datos="";
    String strQuery;
    ResultSet tr;

    strQuery="SELECT * FROM Analisis";
    analisisdp= new AnalisisDP();

    try{
      statement=conexion.createStatement();
      tr= statement.executeQuery(strQuery);
      while(tr.next()){
        analisisdp.setClavePaciente(tr.getString(1));
        analisisdp.setClaveDoctor(tr.getString(2));
        analisisdp.setDiagnostico(tr.getString(3));
        analisisdp.setTipo(tr.getString(4));
        analisisdp.setDescripcion(tr.getString(5));
        analisisdp.setFechaOrden(tr.getString(6));
        analisisdp.setFechaAplicacion(tr.getString(7));
        datos= datos+analisisdp.toString()+ "\n";
      }
      statement.close();
    }
    catch(SQLException fnfe){
			datos="NOT_FOUND"+fnfe;
		}
    return datos;
  }

  public String consultarAnalisisPaciente(String clavePaciente){
    String datos="";
    boolean encontrado=false;
    String strQuery;
    ResultSet tr;

    strQuery="SELECT * FROM Analisis WHERE clave_pac='"+clavePaciente+"'";
    analisisdp= new AnalisisDP();

    try{
      statement=conexion.createStatement();
      tr= statement.executeQuery(strQuery);
      while(tr.next() || encontrado==false){
        analisisdp.setClavePaciente(tr.getString(1));
        analisisdp.setClaveDoctor(tr.getString(2));
        analisisdp.setDiagnostico(tr.getString(3));
        analisisdp.setTipo(tr.getString(4));
        analisisdp.setDescripcion(tr.getString(5));
        analisisdp.setFechaOrden(tr.getString(6));
        analisisdp.setFechaAplicacion(tr.getString(7));
        datos= datos+analisisdp.toString()+ "\n";
      }
      statement.close();

      if(!encontrado){
        datos="NOT_FOUND";
      }
    }
    catch(SQLException sqle){
			datos="Error:"+sqle;
		}
    return datos;
  }

  public String actualizarDatosAnalisis(){

    return "hola";
  }

}
