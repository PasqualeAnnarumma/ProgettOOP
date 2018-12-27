package GestoreLogin;

import java.util.ArrayList;

/**
 * OrdinaLista è un oggetto che conserva una lista di elementi generici T, e li ordina secondo un comparator
 * @author MarioELT
 *
 * @param <T> elemento generico della lista, del comparator e degli elementi "primo" e "ultimo"
 */
public class OrdinaLista<T> {
	
	Comparatore<T> comp;
	ArrayList<T> lista;
	T primo;
	T ultimo;
	
	/**
	 * Costruisce l'astrazione
	 * @param comp Comparator da utilizzare
	 */
	public OrdinaLista(Comparatore<T> comp) {
		this.comp = comp;
		lista = new ArrayList<T>();
	}
	
	/**
	 * Aggiunge gli elementi alla lista mettendoli man mano in ordine
	 * @param el elemento da aggiungere
	 */
	public void add(T el) {
		if (lista.size() == 0) lista.add(el);
		else
		{
			for (int i = 0; i < lista.size(); i++)
			{
				if (comp.compareTo(el, lista.get(i)) <= 0)
				{
					lista.add(i, el);
					break;
				}
				else if (i == lista.size()-1)
				{
					lista.add(i+1, el);
					break;
				}
			}
		}
	}
	
	/**
	 * Restituisce la lista di elementi
	 * @return
	 */
	public ArrayList<T> getList() {
		return lista;
	}
}
