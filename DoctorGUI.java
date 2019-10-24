import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class DoctorGUI extends JFrame implements ActionListener{
  private JTextField tfNombre, tfTelefono, tfClaveDoctor, tfDireccion, tfEspecialidad;
  private JButton bCapturar, bConsultar, bConsultarEspecialidad, bConsultarClave, bCancelarTransaccion, bActualizarDatos, bSalir;
  private JTextArea taDatos;
  private JPanel panel1, panel2;

  private HospitalAD hospitalad= new HospitalAD();

  public DoctorGUI(){
    super("Gestion de Doctores");

    tfClaveDoctor= new JTextField();
    tfNombre= new JTextField();
    tfEspecialidad= new JTextField();
    tfTelefono= new JTextField();
    tfDireccion= new JTextField();
    bCapturar= new JButton("Capturar");
    bConsultar= new JButton("Consultar");
    bConsultarEspecialidad= new JButton("Consultar Especialidad");
    bConsultarClave= new JButton("Consultar Clave");
    bCancelarTransaccion= new JButton("Cancelar Transaccion");
    bActualizarDatos= new JButton ("Actualizar Datos");
    bSalir= new JButton("Salir");
    taDatos= new JTextArea(10,20);
    panel1= new JPanel();
    panel2= new JPanel();

    bCapturar.addActionListener(this);
    bConsultar.addActionListener(this);
    bConsultarEspecialidad.addActionListener(this);
    bConsultarClave.addActionListener(this);
    bCancelarTransaccion.addActionListener(this);
    bActualizarDatos.addActionListener(this);
    bSalir.addActionListener(this);

    panel1.setLayout(new GridLayout(10,2));
    panel2.setLayout(new FlowLayout());

    panel1.add(new JLabel ("Clave: "));
    panel1.add(tfClaveDoctor);
    panel1.add(new JLabel ("Nombre: "));
    panel1.add(tfNombre);
    panel1.add(new JLabel ("Telefono: "));
    panel1.add(tfTelefono);
    panel1.add(new JLabel ("Direccion: "));
    panel1.add(tfDireccion);
    panel1.add(new JLabel ("Especialidad: "));
    panel1.add(tfEspecialidad);
    panel1.add(bCapturar);
    panel1.add(bConsultar);
    panel1.add(bConsultarEspecialidad);
    panel1.add(bConsultarClave);
    panel1.add(bCancelarTransaccion);
    panel1.add(bActualizarDatos);
    panel1.add(bSalir);

    panel2.add(panel1);
    panel2.add(new JScrollPane(taDatos));

    add(panel2);
    setSize(500,500);
    setVisible(false);
  }

  public String obtenerDatos(){
    String datos;
    String fecha="", hora="";

    String claveDoctor= tfClaveDoctor.getText();
    String nombreDoctor= tfNombre.getText();
    String especialidad= tfEspecialidad.getText();
    String telefonoDoctor= tfTelefono.getText();
    String direccionDoctor= tfDireccion.getText();

    if(claveDoctor.equals("") || nombreDoctor.isEmpty() || especialidad.isEmpty() || direccionDoctor.isEmpty() || telefonoDoctor.isEmpty()){
      datos="VACIO";
    }
    else{
      try{
        int n= Integer.parseInt(telefonoDoctor);
        datos=claveDoctor+"_"+nombreDoctor+"_"+especialidad+"_"+telefonoDoctor+"_"+direccionDoctor;
      }
      catch(NumberFormatException nfe){
          datos = "NO_NUMERICO";
      }
    }
    return datos;
  }

  private void inactivarBotones(){
    bCancelarTransaccion.setEnabled(true);
    bActualizarDatos.setEnabled(true);

    bCapturar.setEnabled(false);
    bConsultar.setEnabled(false);
    bConsultarClave.setEnabled(false);
    bConsultarEspecialidad.setEnabled(false);
  }

  private void activarBotones(){
    bCancelarTransaccion.setEnabled(false);
    bActualizarDatos.setEnabled(false);

    bCapturar.setEnabled(true);
    bConsultar.setEnabled(true);
    bConsultarClave.setEnabled(true);
    bConsultarEspecialidad.setEnabled(true);
  }

  private void desplegar(String datos){
    StringTokenizer st= new StringTokenizer(datos,"*");
    tfClaveDoctor.setText(st.nextToken());
    tfNombre.setText(st.nextToken());
    tfEspecialidad.setText(st.nextToken());
    tfDireccion.setText(st.nextToken());
    tfTelefono.setText(st.nextToken());
  }

  public JPanel getPanel2(){
    return this.panel2;
  }

  public void actionPerformed(ActionEvent e){

    String datos, respuesta;

    if(e.getSource()==bCapturar){
      datos=obtenerDatos();

      if(datos.equals("VACIO")){
        respuesta="Algun campo esta vacio";
      }
      else{
        if(datos.equals("NO_NUMERICO")){
          respuesta="Telefono debe ser numerico";
        }
        else{
          respuesta=hospitalad.capturarDoctor(datos);
        }
      }
      taDatos.setText(respuesta);
    }

    if(e.getSource()==bConsultar){
      datos=hospitalad.consultarDoctores();
      taDatos.setText(datos);
    }

    if(e.getSource()==bConsultarClave){
      String claveDoctor= tfClaveDoctor.getText();
      datos=hospitalad.consultarDoctorClave(claveDoctor);
      if(datos.equals("NOT_FOUND")){
        taDatos.setText("No se localizo el doctor con clave" +claveDoctor);
      }
      else{
          taDatos.setText(datos);
          desplegar(datos);
          inactivarBotones();
      }
    }

    if(e.getSource()==bCancelarTransaccion){
        activarBotones();
    }

    if(e.getSource()==bActualizarDatos){
      datos=hospitalad.actualizarDatosDoctor();
      taDatos.setText(datos);
    }

    if(e.getSource() == bSalir){
        panel2.setVisible(false);
    }


  }

  public static void main(String args[]){
    DoctorGUI doctor= new DoctorGUI();
    doctor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
