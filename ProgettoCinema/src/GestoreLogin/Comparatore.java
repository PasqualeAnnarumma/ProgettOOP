package GestoreLogin;

import java.io.Serializable;

/**
 * Compara due oggetti a seconda dell'implementazione
 * @author MarioELT
 *
 * @param <T> elemento generico
 */
public interface Comparatore<T> extends Serializable{
	
	/**
	 * Confronta due oggetti
	 * @param obj1 primo oggetto
	 * @param obj2 secondo oggetto
	 * @return un numero maggiore di 0 se obj1 è maggiore di obj2, 0 se obj1 == obj2, -1 se obj1 è minore di obj2
	 */
	int compare(T obj1, T obj2);
	
}
