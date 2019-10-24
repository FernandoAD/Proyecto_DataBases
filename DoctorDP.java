import java.util.StringTokenizer;

public class DoctorDP{

  //Atributos: Variables de instancia o clase
	private String claveDoctor, nombreDoctor, especialidad, direccionDoctor;
  private int telefonoDoctor;

	//Constructores
	public DoctorDP(){
		claveDoctor="";
		nombreDoctor="";
    especialidad="";
		telefonoDoctor=0;
		direccionDoctor="";
	}

	public DoctorDP(String datos){
		StringTokenizer st= new StringTokenizer(datos, "_");
    claveDoctor=st.nextToken();
		nombreDoctor=st.nextToken();
    especialidad=st.nextToken();
		telefonoDoctor=Integer.parseInt(st.nextToken());
		direccionDoctor=st.nextToken();
	}

  //Accesors
	public String getClaveDoctor(){
		return this.claveDoctor;
	}

	public String getNombreDoctor(){
		return this.nombreDoctor;
	}

  public String getEspecialidad(){
    return this.especialidad;
  }

	public int getTelefonoDoctor(){
		return this.telefonoDoctor;
	}

	public String getDireccionDoctor(){
		return this.direccionDoctor;
	}

	//Mutators

	public void setClaveDoctor(String clav){
		this.claveDoctor= clav;
		//Atributo y nombre. con this es el atributo y sin él, el parámetro
	}

	public void setNombreDoctor(String name){
		this.nombreDoctor=name;
	}

  public void setEspecialidad(String esp){
    this.especialidad=esp;
  }

	public void setTelefonoDoctor(int phone){
		this.telefonoDoctor=phone;
	}

	public void setDireccion(String direc){
		this.direccionDoctor=direc;
	}

	public String toString(){
		return this.claveDoctor+"*"+this.nombreDoctor+"*"+this.especialidad+"*"+this.telefonoDoctor+"*"+this.direccionDoctor;
	}

	public String toStringSql(){
		return "'"+this.claveDoctor+"','"+this.nombreDoctor+"','"+this.especialidad+"','"+this.telefonoDoctor+"','"+this.direccionDoctor+"'";
	}

}
