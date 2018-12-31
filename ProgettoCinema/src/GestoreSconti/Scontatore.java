package GestoreSconti;

import java.io.Serializable;

/**
 * Lo scontatore è un interfaccia funzionale che permette di calcolare lo sconto in base all'esigenza
 * @author MarioELT
 *
 * @param <T> elemento generico
 */
public interface Scontatore<T> extends Serializable{
	
	/**
	 * Calcola lo sconto
	 * @param obj oggetto sul quale calcolare lo sconto
	 * @return sconto
	 */
	float calcolaSconto(T obj);
	
}
