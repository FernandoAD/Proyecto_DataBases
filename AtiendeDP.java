import java.util.StringTokenizer;

public class AtiendeDP{

  //Atributos: Variables de instancia o clase
	private String fecha;
  private String clavePaciente, claveDoctor, diagnostico, tratamiento;

	//Constructores
	public AtiendeDP(){
    claveDoctor="";
    clavePaciente="";
    fecha="";
    diagnostico="";
    tratamiento="";
	}

	public AtiendeDP(String datos){
		StringTokenizer st= new StringTokenizer(datos, "_");
    claveDoctor=st.nextToken();
    clavePaciente=st.nextToken();
    fecha=st.nextToken();
		diagnostico=st.nextToken();
    tratamiento=st.nextToken();
	}

  //Accesors
	public String getClavePaciente(){
		return this.clavePaciente;
	}

	public String getClaveDoctor(){
		return this.claveDoctor;
	}

	public String getDiagnostico(){
		return this.diagnostico;
	}

	public String getTratamiento(){
		return this.tratamiento;
	}

	public String getFecha(){
		return this.fecha;
	}


	//Mutators
	public void setClavePaciente(String clavP){
		this.clavePaciente=clavP;
	}

	public void setClaveDoctor(String clavD){
		this.claveDoctor= clavD;
		//Atributo y nombre. con this es el atributo y sin él, el parámetro
	}

	public void setDiagnostico(String diag){
		this.diagnostico=diag;
	}

  public void setTratamiento(String tra){
    this.tratamiento=tra;
  }

	public void setFecha(String fech){
		this.fecha= fech;
	}

	public String toString(){
		return this.claveDoctor+"*"+this.clavePaciente+"*"+this.fecha+"*"+this.diagnostico+"*"+this.tratamiento;
	}

	public String toStringSql(){
		return "'"+this.claveDoctor+"','"+this.clavePaciente+"','"+this.fecha+"','"+this.diagnostico+"','"+this.tratamiento+"'";
	}

}
