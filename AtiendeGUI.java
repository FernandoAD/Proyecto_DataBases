import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.util.Date;

public class AtiendeGUI extends JFrame implements ActionListener{
  private JTextField tfDiagnostico, tfTratamiento, tfClavePaciente, tfClaveDoctor;
  private JButton bCapturar, bConsultar, bConsultarDoctor, bCancelarTransaccion, bActualizarDatos, bSalir;
  private JTextArea taDatos;
  private JPanel panel1, panel2;

  private HospitalAD hospitalad= new HospitalAD();

  public AtiendeGUI(){
    super("Gestion de Atiende");

    tfDiagnostico= new JTextField();
    tfTratamiento= new JTextField();
    tfClavePaciente= new JTextField();
    tfClaveDoctor= new JTextField();
    bCapturar= new JButton("Capturar");
    bConsultar= new JButton("Consultar");
    bConsultarDoctor= new JButton("Consultar por Clave");
    bCancelarTransaccion= new JButton ("Cancelar Transaccion");
    bActualizarDatos= new JButton ("Actualizar Datos");
    bSalir= new JButton("Salir");
    taDatos= new JTextArea(10,40);
    panel1= new JPanel();
    panel2= new JPanel();

    bCapturar.addActionListener(this);
    bConsultar.addActionListener(this);
    bConsultarDoctor.addActionListener(this);
    bCancelarTransaccion.addActionListener(this);
    bActualizarDatos.addActionListener(this);
    bSalir.addActionListener(this);

    bCancelarTransaccion.setEnabled(false);
    bActualizarDatos.setEnabled(false);


    panel1.setLayout(new GridLayout(10,2));
    panel2.setLayout(new FlowLayout());

    panel1.add(new JLabel ("Clave Doctor: "));
    panel1.add(tfClaveDoctor);
    panel1.add(new JLabel ("Clave Paciente: "));
    panel1.add(tfClavePaciente);
    panel1.add(new JLabel ("Diagnostico: "));
    panel1.add(tfDiagnostico);
    panel1.add(new JLabel ("Tratamiento: "));
    panel1.add(tfTratamiento);
    panel1.add(bCapturar);
    panel1.add(bConsultar);
    panel1.add(bConsultarDoctor);
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
    Date fecha;
    String fechaOrden="", fechaAplicacion="";

    String claveDoctor= tfClaveDoctor.getText();
    String clavePaciente= tfClavePaciente.getText();
    String diagnostico= tfDiagnostico.getText();
    String tratamiento= tfTratamiento.getText();

    if(clavePaciente.equals("") || claveDoctor.isEmpty() || diagnostico.isEmpty() || tratamiento.isEmpty()){
      datos="VACIO";
    }
    else{
      fecha= new Date();
      fechaOrden=fechaOrden.format("%tF", fecha);

      datos=claveDoctor+"_"+clavePaciente+"_"+fechaOrden+"_"+diagnostico+"_"+tratamiento;
    }
    return datos;
  }

  private void inactivarBotones(){
    bCancelarTransaccion.setEnabled(true);
    bActualizarDatos.setEnabled(true);

    bCapturar.setEnabled(false);
    bConsultar.setEnabled(false);
    bConsultarDoctor.setEnabled(false);
  }

  private void activarBotones(){
    bCancelarTransaccion.setEnabled(false);
    bActualizarDatos.setEnabled(false);

    bCapturar.setEnabled(true);
    bConsultar.setEnabled(true);
    bConsultarDoctor.setEnabled(true);
  }

  private void desplegar(String datos){
    StringTokenizer st= new StringTokenizer(datos,"*");
    tfClavePaciente.setText(st.nextToken());
    tfClaveDoctor.setText(st.nextToken());
    String nulo= st.nextToken();
    tfDiagnostico.setText(st.nextToken());
    tfTratamiento.setText(st.nextToken());
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
          respuesta=hospitalad.capturarAtiende(datos);
      }
      taDatos.setText(respuesta);
    }

    if(e.getSource()==bConsultar){
      datos=hospitalad.consultarAtiende();
      taDatos.setText(datos);
    }

    if(e.getSource()==bConsultarDoctor){
      String claveDoctor= tfClaveDoctor.getText();
      datos=hospitalad.consultarPorClaveDoctor(claveDoctor);
      if(datos.equals("NOT_FOUND")){
        taDatos.setText("No se localizaron pacientes del doctor con clave" +claveDoctor);
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
      datos=hospitalad.actualizarDatosAtiende();
      taDatos.setText(datos);
    }

    if(e.getSource() == bSalir){
        panel2.setVisible(false);
    }

  }

  public static void main(String args[]){
    AtiendeGUI atiende= new AtiendeGUI();
    atiende.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
