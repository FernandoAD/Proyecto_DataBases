import java.util.StringTokenizer;

public class PacienteDP{

  //Atributos: Variables de instancia o clase
	private String clavePaciente, nombrePaciente, direccionPaciente;
  private int telefonoPaciente;

	//Constructores
	public PacienteDP(){
		clavePaciente="";
		nombrePaciente="";
		telefonoPaciente=0;
		direccionPaciente="";
	}

	public PacienteDP(String datos){
		StringTokenizer st= new StringTokenizer(datos, "_");
		clavePaciente=st.nextToken();
		nombrePaciente=st.nextToken();
    direccionPaciente=st.nextToken();
	  telefonoPaciente=Integer.parseInt(st.nextToken());
	}

  //Accesors
	public String getClavePaciente(){
		return this.clavePaciente;
	}

	public String getNombrePaciente(){
		return this.nombrePaciente;
	}

	public int getTelefonoPaciente(){
		return this.telefonoPaciente;
	}

	public String getDireccionPaciente(){
		return this.direccionPaciente;
	}

	//Mutators

	public void setClavePaciente(String clav){
		this.clavePaciente= clav;
		//Atributo y nombre. con this es el atributo y sin él, el parámetro
	}

	public void setNombrePaciente(String name){
		this.nombrePaciente=name;
	}

	public void setTelefonoPaciente(int phone){
		this.telefonoPaciente=phone;
	}

	public void setDireccion(String direc){
		this.direccionPaciente=direc;
	}

	public String toString(){
		return this.clavePaciente+"*"+this.nombrePaciente+"*"+this.direccionPaciente+"*"+this.telefonoPaciente;
	}

	public String toStringSql(){
		return "'"+this.clavePaciente+"','"+this.nombrePaciente+"','"+this.direccionPaciente+"','"+this.telefonoPaciente+"'";
	}

}
