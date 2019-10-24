import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class PacienteGUI extends JFrame implements ActionListener{
  private JTextField tfNombre, tfTelefono, tfClavePaciente, tfDireccion;
  private JButton bCapturar, bConsultar, bConsultarClave, bCancelarTransaccion, bActualizarDatos, bSalir;
  private JTextArea taDatos;
  private JPanel panel1, panel2;

  private HospitalAD hospitalad= new HospitalAD();

  public PacienteGUI(){
    super("Gestion de Pacientes");

    tfNombre= new JTextField();
    tfTelefono= new JTextField();
    tfClavePaciente= new JTextField();
    tfDireccion= new JTextField();
    bCapturar= new JButton("Capturar");
    bConsultar= new JButton("Consultar");
    bConsultarClave= new JButton("Consultar Clave");
    bCancelarTransaccion= new JButton("Cancelar transaccion");
    bActualizarDatos= new JButton ("Actualizar Datos");
    bSalir= new JButton("Salir");
    taDatos= new JTextArea(10,20);
    panel1= new JPanel();
    panel2= new JPanel();

    bCapturar.addActionListener(this);
    bConsultar.addActionListener(this);
    bConsultarClave.addActionListener(this);
    bCancelarTransaccion.addActionListener(this);
    bActualizarDatos.addActionListener(this);
    bSalir.addActionListener(this);

    bCancelarTransaccion.setEnabled(false);
    bActualizarDatos.setEnabled(false);

    panel1.setLayout(new GridLayout(10,2));
    panel2.setLayout(new FlowLayout());

    panel1.add(new JLabel ("Clave: "));
    panel1.add(tfClavePaciente);
    panel1.add(new JLabel ("Nombre: "));
    panel1.add(tfNombre);
    panel1.add(new JLabel ("Telefono: "));
    panel1.add(tfTelefono);
    panel1.add(new JLabel ("Direccion: "));
    panel1.add(tfDireccion);
    panel1.add(bCapturar);
    panel1.add(bConsultar);
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

    String clavePaciente= tfClavePaciente.getText();
    String nombrePaciente= tfNombre.getText();
    String telefonoPaciente= tfTelefono.getText();
    String direccionPaciente= tfDireccion.getText();

    if(clavePaciente.equals("") || nombrePaciente.isEmpty() || direccionPaciente.isEmpty() || telefonoPaciente.isEmpty()){
      datos="VACIO";
    }
    else{
      try{
        int n= Integer.parseInt(telefonoPaciente);
        datos=clavePaciente+"_"+nombrePaciente+"_"+direccionPaciente+"_"+telefonoPaciente;
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
  }

  private void activarBotones(){
    bCancelarTransaccion.setEnabled(false);
    bActualizarDatos.setEnabled(false);

    bCapturar.setEnabled(true);
    bConsultar.setEnabled(true);
    bConsultarClave.setEnabled(true);

  }

  private void desplegar(String datos){
    StringTokenizer st= new StringTokenizer(datos,"*");
    tfClavePaciente.setText(st.nextToken());
    tfNombre.setText(st.nextToken());
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
          respuesta=hospitalad.capturarPaciente(datos);
        }
      }
      taDatos.setText(respuesta);
    }

    if(e.getSource()==bConsultar){
      datos=hospitalad.consultarPacientes();
      taDatos.setText(datos);
    }

    if(e.getSource()==bConsultarClave){
      String clavePaciente= tfClavePaciente.getText();
      datos=hospitalad.consultarPacienteClave(clavePaciente);
      if(datos.equals("NOT_FOUND")){
        taDatos.setText("No se localizo el paciente con clave" +clavePaciente);
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
      datos=hospitalad.actualizarDatosPaciente();
      taDatos.setText(datos);
    }

    if(e.getSource() == bSalir){
        panel2.setVisible(false);
    }
  }

  public static void main(String args[]){
    PacienteGUI paciente= new PacienteGUI();
    paciente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
