import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HospitalGUI extends JFrame implements ActionListener{
  //Atributos, variables de clase o variables de instancia
  private JMenuBar mbPrincipal;
  private JMenu menuGeneral, menuRelaciones;
  private JMenuItem miPaciente, miDoctor, miAnalisis, miAtiende, miSalir;
  private JMenuItem miPacDoc, miDocPac, miAnalisisPaciente;

  //AD para los GUI's
  private HospitalAD hospitalad = new HospitalAD();

  //GUI alternos
  private PacienteGUI paciente = new PacienteGUI();
  private DoctorGUI doctor= new DoctorGUI();
  private AnalisisGUI analisis= new AnalisisGUI();
  private AtiendeGUI atiende= new AtiendeGUI();
  private RelacionPacienteGUI relacionpacientes= new RelacionPacienteGUI();
  private RelacionDoctorGUI relaciondoctor= new RelacionDoctorGUI();
  private RelacionAnalisisGUI relacionanalisis= new RelacionAnalisisGUI();

  private JPanel panel;

  public HospitalGUI(){
    mbPrincipal =   new JMenuBar();
    menuGeneral = new JMenu("General");
    menuRelaciones= new JMenu("Reportes y consultas");
    miPaciente =    new JMenuItem("Paciente");
    miDoctor =      new JMenuItem("Doctores");
    miAnalisis =    new JMenuItem("Analisis");
    miAtiende =     new JMenuItem("Atiende");
    miPacDoc=       new JMenuItem("Pacientes de Doctor");
    miDocPac=       new JMenuItem("Doctores de Paciente");
    miAnalisisPaciente= new JMenuItem("Analisis de Paciente");
    miSalir =       new JMenuItem("Salir");

    miPaciente.addActionListener(this);
    miDoctor.addActionListener(this);
    miAnalisis.addActionListener(this);
    miAtiende.addActionListener(this);
    miPacDoc.addActionListener(this);
    miDocPac.addActionListener(this);
    miAnalisisPaciente.addActionListener(this);
    miSalir.addActionListener(this);

    panel= new JPanel();

    menuGeneral.add(miPaciente);
    menuGeneral.add(miDoctor);
    menuGeneral.add(miAnalisis);
    menuGeneral.add(miAtiende);
    menuGeneral.add(miSalir);

    menuRelaciones.add(miPacDoc);
    menuRelaciones.add(miDocPac);
    menuRelaciones.add(miAnalisisPaciente);

    mbPrincipal.add(menuGeneral);
    mbPrincipal.add(menuRelaciones);

    setJMenuBar(mbPrincipal);
    setSize(500,500);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent event){

    if(event.getSource()==miPaciente){
        panel.setVisible(false);
        panel=paciente.getPanel2();
        panel.setVisible(true);
        add(panel);
        setVisible(true);
    }

    if(event.getSource()==miDoctor){
        panel.setVisible(false);
        panel=doctor.getPanel2();
        panel.setVisible(true);
        add(panel);
        setVisible(true);
    }

    if(event.getSource()==miAnalisis){
        panel.setVisible(false);
        panel=analisis.getPanel2();
        panel.setVisible(true);
        add(panel);
        setVisible(true);
    }

    if(event.getSource()==miAtiende){
        panel.setVisible(false);
        panel=atiende.getPanel2();
        panel.setVisible(true);
        add(panel);
        setVisible(true);
    }

    if(event.getSource()==miDocPac){
      panel.setVisible(false);
      panel=relacionpacientes.getPanel2();
      panel.setVisible(true);
      add(panel);
      setVisible(true);
    }

    if(event.getSource()==miPacDoc){
      panel.setVisible(false);
      panel=relaciondoctor.getPanel2();
      panel.setVisible(true);
      add(panel);
      setVisible(true);
    }

    if(event.getSource()==miAnalisisPaciente){
      panel.setVisible(false);
      panel=relacionanalisis.getPanel2();
      panel.setVisible(true);
      add(panel);
      setVisible(true);
    }

    if(event.getSource()== miSalir){
			System.exit(0);
		}

  }

  public static void main (String args[]){
    HospitalGUI hospital = new HospitalGUI();
    hospital.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}
