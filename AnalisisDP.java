import java.util.StringTokenizer;

public class AnalisisDP{

  //Atributos: Variables de instancia o clase
	private String clavePaciente, claveDoctor, diagnostico, tipo, descripcion;
	private String fechaOrden, fechaAplicacion;

	//Constructores
	public AnalisisDP(){
		clavePaciente="";
		claveDoctor="";
		diagnostico="";
    tipo="";
		descripcion="";
		fechaOrden="";
		fechaAplicacion="";
	}

	public AnalisisDP(String datos){
		StringTokenizer st= new StringTokenizer(datos, "_");
		clavePaciente=st.nextToken();
		claveDoctor=st.nextToken();
		diagnostico=st.nextToken();
    tipo=st.nextToken();
		descripcion=st.nextToken();
		fechaOrden=st.nextToken();
		fechaAplicacion=st.nextToken();
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

  public String getTipo(){
    return this.tipo;
  }

	public String getDescripcion(){
		return this.descripcion;
	}

	public String getFechaOrden(){
		return this.fechaOrden;
	}

	public String getFechaAplicacion(){
		return this.fechaAplicacion;
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

  public void setTipo(String tip){
    this.tipo=tip;
  }

	public void setDescripcion(String des){
		this.descripcion=des;
	}

	public void setFechaOrden(String fechaO){
		this.fechaOrden= fechaO;
	}

	public void setFechaAplicacion(String fechaA){
		this.fechaAplicacion= fechaA;
	}

	public String toString(){
		return this.clavePaciente+"*"+this.claveDoctor+"*"+this.diagnostico+"*"+this.tipo+"*"+this.descripcion+"*"+this.fechaOrden+"*"+this.fechaAplicacion;
	}

	public String toStringSql(){
		return "'"+this.clavePaciente+"','"+this.claveDoctor+"','"+this.diagnostico+"','"+this.tipo+"','"+this.descripcion+"','"+this.fechaOrden+"','"+this.fechaAplicacion+"'";
	}

}
