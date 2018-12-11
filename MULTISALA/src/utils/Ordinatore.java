package utils;

import java.util.ArrayList;

import interfacce.Comparatore;

public class Ordinatore {

	public static <T> void ordina(ArrayList<T> lista, Comparatore<T> test) {
		MergeSort(lista, 0, lista.size()-1, test);
	}

	private static <T> void MergeSort(ArrayList<T> array, int begin, int end, Comparatore<T> test) {
		if (end > begin) {
			int med = (begin + end) / 2;
			MergeSort(array, begin, med, test);
			MergeSort(array, med + 1, end, test);
			merge(array, begin, med, end, test);
		}
	}

	private static <T> void merge(ArrayList<T> a, int min, int med, int max, Comparatore<T> test) {
		ArrayList<T> c = new ArrayList<T>();
		int i, j;
		for (i = min, j = med + 1; i <= med && j <= max;) {
			if (test.compara(a.get(i), a.get(j)) < 0)
				c.add(a.get(i++));
			else
				c.add(a.get(j++));
		}
		while (i <= med)
			c.add(a.get(i++));
		while (j < max)
			c.add(a.get(i++));
		for (i = min, j = 0; j < c.size(); i++, j++)
			a.set(i, c.get(j));
	}
}
