package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

import com.sun.glass.ui.Application;

public class Reservation {
	
	public static String print(ArrayList<Book> listBook) {
		String affiche="";
		for(Book e:listBook) {
			affiche=affiche.concat(" | "+e.getName()+" | "+e.isReserved()+"\b");
		}
		return affiche;
	}
	
	
	public void register(Book book) throws IOException, ClassNotFoundException {
		ArrayList<Book> listBook = loadBooks();
		 if(listBook==null) {
			 listBook=new ArrayList<Book>();
		 }
		 boolean add=true;
		for(Book e:listBook) {
			if(e.getName().equals(book.getName())) {
				add=false;
			}
		}
		if(add) {
			listBook.add(book);
		}
		
		 for(Book p:listBook) {
			 System.out.println(p.getName());
		 }
		 
		 FileOutputStream fichier = new FileOutputStream(getPath());
		 ObjectOutputStream oos =  new ObjectOutputStream(fichier) ;
		 oos.writeObject(listBook) ;
		 oos.close();
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Book> loadBooks() throws ClassNotFoundException, IOException {
		FileInputStream fichier = new FileInputStream(getPath());
		ArrayList<Book> m=null;
		if(fichier.available()>10) {
			ObjectInputStream ois =  new ObjectInputStream(fichier) ;
			m = (ArrayList<Book>)ois.readObject() ;
			ois.close();
		}

		return m;
	}
	public static String[] getTabBook(ArrayList<Book> listBook) {
		String tab[] =new String[listBook.size()];
		int i=0;
		for(Book e:listBook) {
			tab[i]=e.getName();
			i++;
		}
		return tab;
	}
	public Book getBook(String name) throws ClassNotFoundException, IOException {
		ArrayList<Book> listBook = loadBooks();
		for(Book e:listBook) {
			if(e.getName().equals(name)) {
				return e;
			}
		}
		return null;
	}
	public boolean resBook(String name,String userName) throws ClassNotFoundException, IOException {
		boolean ret=false;
		ArrayList<Book> listBook = loadBooks();
		 if(listBook==null) {
			return false;
		 }
		for(Book e:listBook) {
			if(e.getName().equals(name)) {
				e.setReserved(true);
				e.setUser(userName);
				ret=true;
			}
		}
		
		
		 
		 FileOutputStream fichier = new FileOutputStream(getPath());
		 ObjectOutputStream oos =  new ObjectOutputStream(fichier) ;
		 oos.writeObject(listBook) ;
		 oos.close();
		 return ret;
	}
	
	public void initDataBase() throws IOException { //fonction qu'il ne s'execuse jamais sauf l'orsque l'on veut
													//reinitialiser la bdd.
		ArrayList<Book> listBook = new ArrayList<Book>();
		listBook.add(new Book("La Bible"));
		listBook.add(new Book("Don Quijote de la Mancha"));
		listBook.add(new Book("Harry Potter"));
		listBook.add(new Book("Le conte de Deux cites"));
		listBook.add(new Book("Le Seigneur des Anneaux"));
		listBook.add(new Book("Le Petit Prince"));
		listBook.add(new Book("Alice au pays des merveilles"));
		listBook.add(new Book("Da Vinci Code"));
		listBook.add(new Book("Gargantua"));
		listBook.add(new Book("Vingt mille lieues sous les mers"));
		listBook.add(new Book("Millenium"));
		listBook.add(new Book("Hamlet"));
		listBook.add(new Book("Oeudipe roi"));
		listBook.add(new Book("Le rouge et noir"));
		listBook.add(new Book("Belle merveille"));
		listBook.add(new Book("Crimes"));
		listBook.add(new Book("En douce"));
		listBook.add(new Book("Flic"));
		listBook.add(new Book("Gone baby gone"));
		listBook.add(new Book("Histoire sans issue"));
		listBook.add(new Book("Impurs"));
		listBook.add(new Book("Just la lumiere"));
		listBook.add(new Book("Kindapping"));
		listBook.add(new Book("Les voies du seigneur"));
		listBook.add(new Book("Mister orange"));
		listBook.add(new Book("Noir comme la mer"));
		listBook.add(new Book("Ou es-tu"));
		listBook.add(new Book("Pompe funebres"));
		listBook.add(new Book("Quand le requin dort"));
		listBook.add(new Book("Revolution"));
		listBook.add(new Book("Sacre bleu"));
		listBook.add(new Book("Toile de dragon"));
		listBook.add(new Book("Un amour fou"));
		listBook.add(new Book("Voyage d'hiver"));
		listBook.add(new Book("Wave"));
		listBook.add(new Book("Xenia"));
		listBook.add(new Book("Yoga des yeux"));
		listBook.add(new Book("Zen"));
		listBook.add(new Book("Je n ai plus d idee"));
		FileOutputStream fichier = new FileOutputStream(getPath());
		 ObjectOutputStream oos =  new ObjectOutputStream(fichier) ;
		 oos.writeObject(listBook) ;
		 oos.close();
	}
	
	public String getPath() {
		String path=this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		String projectName=path.substring(0,path.indexOf("/WEB-INF"));
		projectName=projectName.substring(projectName.lastIndexOf("/"));
		return path.substring(0,path.indexOf(".metadata")).concat(projectName+"/dataBook.txt");
	}
	
}
