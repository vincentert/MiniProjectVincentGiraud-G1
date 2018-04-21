package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Authentication {
			
	public boolean register(Person pers) throws IOException, ClassNotFoundException {
		ArrayList<Person> listPerson = loadPersons();
		 if(listPerson==null) {
			 listPerson=new ArrayList<Person>();
		 }
		 boolean add=true;
			for(Person e:listPerson) {
				if(e.getName().equals(pers.getName())) {
					add=false;
				}
			}
			if(add) {
				listPerson.add(pers);
			}
		 FileOutputStream fichier = new FileOutputStream(getPath());
		 ObjectOutputStream oos =  new ObjectOutputStream(fichier) ;
		 oos.writeObject(listPerson) ;
		 oos.close();
		 return add;
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Person> loadPersons() throws ClassNotFoundException, IOException {
		FileInputStream fichier = new FileInputStream(getPath());
		ArrayList<Person> m=null;
		if(fichier.available()>10) {
			ObjectInputStream ois =  new ObjectInputStream(fichier) ;
			m = (ArrayList<Person>)ois.readObject() ;
			ois.close();
		}

		return m;
	}
	
	public boolean connection(Person p) throws ClassNotFoundException, IOException {
		
		ArrayList<Person> list = loadPersons();
		if(list!=null) {
			for(Person e:list) {
				if(e.getName().equals(p.getName())&&e.getPassword().equals(p.getPassword())) {
					return true;
				}
			}
		}
		return false;
	}
	public String getPath() {
		String path=this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String projectName=path.substring(0,path.indexOf("/WEB-INF"));
		projectName=projectName.substring(projectName.lastIndexOf("/"));
		return path.substring(0,path.indexOf(".metadata")).concat(projectName+"/dataBase.txt");
	}
}
