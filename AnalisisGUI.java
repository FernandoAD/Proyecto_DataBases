import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.util.Date;

public class AnalisisGUI extends JFrame implements ActionListener{
  private JTextField tfDiagnostico, tfDescripcion, tfTipo, tfClavePaciente, tfClaveDoctor;
  private JButton bCapturar, bConsultar, bConsultarTipo, bConsultarPorClavePac, bCancelarTransaccion, bActualizarDatos, bSalir;
  private JTextArea taDatos;
  private JPanel panel1, panel2;

  private HospitalAD hospitalad= new HospitalAD();

  public AnalisisGUI(){
    super("Gestion de Analisis");

    tfDiagnostico= new JTextField();
    tfDescripcion= new JTextField();
    tfTipo= new JTextField();
    tfClavePaciente= new JTextField();
    tfClaveDoctor= new JTextField();
    bCapturar= new JButton("Capturar");
    bConsultar= new JButton("Consultar");
    bConsultarTipo= new JButton("Consultar Tipo");
    bConsultarPorClavePac= new JButton("Consultar por Clave");
    bCancelarTransaccion= new JButton ("Cancelar Transaccion");
    bActualizarDatos= new JButton ("Actualizar Datos");
    bSalir= new JButton("Salir");
    taDatos= new JTextArea(10,40);
    panel1= new JPanel();
    panel2= new JPanel();

    bCapturar.addActionListener(this);
    bConsultar.addActionListener(this);
    bConsultarTipo.addActionListener(this);
    bConsultarPorClavePac.addActionListener(this);
    bCancelarTransaccion.addActionListener(this);
    bActualizarDatos.addActionListener(this);
    bSalir.addActionListener(this);

    bCancelarTransaccion.setEnabled(false);
    bActualizarDatos.setEnabled(false);


    panel1.setLayout(new GridLayout(10,2));
    panel2.setLayout(new FlowLayout());

    panel1.add(new JLabel ("Clave Paciente: "));
    panel1.add(tfClavePaciente);
    panel1.add(new JLabel ("Clave Doctor: "));
    panel1.add(tfClaveDoctor);
    panel1.add(new JLabel ("Diagnostico: "));
    panel1.add(tfDiagnostico);
    panel1.add(new JLabel ("Tipo: "));
    panel1.add(tfTipo);
    panel1.add(new JLabel ("Descripcion: "));
    panel1.add(tfDescripcion);
    panel1.add(bCapturar);
    panel1.add(bConsultar);
    panel1.add(bConsultarTipo);
    panel1.add(bConsultarPorClavePac);
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

    String clavePaciente= tfClavePaciente.getText();
    String claveDoctor= tfClaveDoctor.getText();
    String diagnostico= tfDiagnostico.getText();
    String tipo= tfTipo.getText();
    String descripcion= tfDescripcion.getText();

    if(clavePaciente.equals("") || claveDoctor.isEmpty() || diagnostico.isEmpty() || tipo.isEmpty() || descripcion.isEmpty()){
      datos="VACIO";
    }
    else{
      fecha= new Date();
      fechaOrden=fechaOrden.format("%tF", fecha);
      fechaAplicacion=fechaAplicacion.format("%tF", fecha);

      datos=clavePaciente+"_"+claveDoctor+"_"+diagnostico+"_"+tipo+"_"+diagnostico+"_"+fechaOrden+"_"+fechaAplicacion;
    }
    return datos;
  }

  private void inactivarBotones(){
    bCancelarTransaccion.setEnabled(true);
    bActualizarDatos.setEnabled(true);

    bCapturar.setEnabled(false);
    bConsultar.setEnabled(false);
    bConsultarPorClavePac.setEnabled(false);
    bConsultarTipo.setEnabled(false);
  }

  private void activarBotones(){
    bCancelarTransaccion.setEnabled(false);
    bActualizarDatos.setEnabled(false);

    bCapturar.setEnabled(true);
    bConsultar.setEnabled(true);
    bConsultarPorClavePac.setEnabled(true);
    bConsultarTipo.setEnabled(true);
  }

  private void desplegar(String datos){
    StringTokenizer st= new StringTokenizer(datos,"*");
    tfClavePaciente.setText(st.nextToken());
    tfClaveDoctor.setText(st.nextToken());
    tfDiagnostico.setText(st.nextToken());
    tfTipo.setText(st.nextToken());
    tfDescripcion.setText(st.nextToken());
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
          respuesta=hospitalad.capturarAnalisis(datos);
      }
      taDatos.setText(respuesta);
    }

    if(e.getSource()==bConsultar){
      datos=hospitalad.consultarAnalisis();
      taDatos.setText(datos);
    }

    if(e.getSource()==bConsultarTipo){
      String tipo= tfTipo.getText();
      datos=hospitalad.consultarPorTipo(tipo);
      if(datos.equals("NOT_FOUND")){
        taDatos.setText("No se localizaro el tipo: " + " " +tipo);
      }
      else{
          taDatos.setText(datos);
      }
    }

    if(e.getSource()==bConsultarPorClavePac){
      String clavePaciente= tfClavePaciente.getText();
      datos=hospitalad.consultarPorClaveDePaciente(clavePaciente);
      if(datos.equals("NOT_FOUND")){
        taDatos.setText("No se localizaron analisis del paciente con clave" +clavePaciente);
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
      datos=hospitalad.actualizarDatosAnalisis();
      taDatos.setText(datos);
    }

    if(e.getSource() == bSalir){
        panel2.setVisible(false);
    }

  }

  public static void main(String args[]){
    AnalisisGUI analisis= new AnalisisGUI();
    analisis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
