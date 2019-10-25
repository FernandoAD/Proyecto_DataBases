import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.util.Date;

public class RelacionPacienteGUI extends JFrame implements ActionListener{
  private JTextField tfClavePaciente;
  private JButton bBuscar, bSalir;
  private JTextArea taDatos;
  private JPanel panel1, panel2;

  private HospitalAD hospitalad= new HospitalAD();

  public RelacionPacienteGUI(){
    super("Gestion de Relacion Paciente");


    tfClavePaciente= new JTextField();
    bBuscar= new JButton("Buscar");
    bSalir= new JButton("Salir");
    taDatos= new JTextArea(10,40);
    panel1= new JPanel();
    panel2= new JPanel();

    bBuscar.addActionListener(this);
    bSalir.addActionListener(this);

    panel1.setLayout(new GridLayout(3,2));
    panel2.setLayout(new FlowLayout());

    panel1.add(new JLabel ("Clave Paciente: "));
    panel1.add(tfClavePaciente);
    panel1.add(bBuscar);
    panel1.add(bSalir);

    panel2.add(panel1);
    panel2.add(new JScrollPane(taDatos));

    add(panel2);
    setSize(500,500);
    setVisible(false);
  }

  public String obtenerDatos(){
    String datos;

    String clavePaciente= tfClavePaciente.getText();


    if(clavePaciente.equals("")){
      datos="VACIO";
    }
    else{

      datos=clavePaciente;
    }
    return datos;
  }


  public JPanel getPanel2(){
    return this.panel2;
  }

  public void actionPerformed(ActionEvent e){

    String datos, respuesta;

    if(e.getSource()==bBuscar){
      datos=obtenerDatos();

      if(datos.equals("VACIO")){
        respuesta="Algun campo esta vacio";
      }
      else{
          respuesta=hospitalad.capturarAnalisis(datos);
      }
      taDatos.setText(respuesta);
    }


    if(e.getSource() == bSalir){
        panel2.setVisible(false);
    }

  }

  public static void main(String args[]){
    RelacionPacienteGUI relacionpaciente= new RelacionPacienteGUI();
    relacionpaciente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
