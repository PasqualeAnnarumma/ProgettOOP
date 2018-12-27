package GestoreSconti;

import java.io.Serializable;

/**
 * Lo scontatore è un interfaccia funzionale che permette di calcolare lo sconto in base all'esigenza
 * @author MarioELT
 *
 * @param <T> elemento generico
 */
public interface Scontatore<T> extends Serializable{
	
	float calcolaSconto(T obj);
	
}
