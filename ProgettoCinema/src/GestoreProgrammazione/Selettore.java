package GestoreProgrammazione;

import java.io.Serializable;

/**
 * Un selettore è un'interfaccia funzionale che può essere riscritta in base alle esigenze
 * e permette di selezionare un elemento generico T
 * @author MarioELT
 *
 * @param <T> elemento
 */
public interface Selettore<T> extends Serializable{
	
	public boolean seleziona(T s);
	
}
