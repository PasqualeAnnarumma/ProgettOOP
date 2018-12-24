package GestoreLogin;

import java.util.ArrayList;
import java.util.Comparator;

public class OrdinaLista<T> {
	
	Comparator<T> comp;
	ArrayList<T> lista;
	T primo;
	T ultimo;
	
	public OrdinaLista(Comparator<T> comp) {
		this.comp = comp;
		lista = new ArrayList<T>();
	}
	
	public void add(T el) {
		if (lista.size() == 0) lista.add(el);
		else
		{
			for (int i = 0; i < lista.size(); i++)
			{
				if (comp.compare(el, lista.get(i)) <= 0)
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
	
	public ArrayList<T> getList() {
		return lista;
	}
}
