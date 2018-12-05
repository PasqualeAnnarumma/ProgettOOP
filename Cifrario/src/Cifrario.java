public class Cifrario {
	
	public String key;
	public static char[] arr = new char[27];
	public static char[] alfabeto = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	public Cifrario(String key) {
		this.key = key;
		//RIMUOVO LE OCCORRENZE
		
		//rimuoviOccorrenze(key);
		String s = rimuoviOccorrenze(key);
		cifra(s);
		
	}
	
	public void cifra(String s) {
		//CREO L'ARRAY DI CIFRAZIONE
		int i = 0;
		for (i = 0; i < s.length(); i++)
			arr[i] = s.toLowerCase().charAt(i);
		System.out.println("LA I: "+i);
		for (char c = 'a'; c != 'z'; c++)
		{
			if (s.toLowerCase().indexOf(c) < 0)
				arr[i++] = c;
		}
	}
	
	public String rimuoviOccorrenze(String s) {
		
		String s2 = s;
		
		for (int i = 0; i < s2.length(); i++)
		{
			for (int j = i+1; j < s2.length(); j++)
			{
				if (s2.toLowerCase().charAt(j) == s2.toLowerCase().charAt(i))
				{
					String s3 = s2.substring(j+1, s2.length());
					s2 = s2.substring(0, j);
					s2 = s2 + s3;
					j--;

				}
			}
		}
		System.out.println("Key : " + s2);
		return s2;
	}
	
	public String codifica(String s) {
		
		String cod = s.toLowerCase();
		String tmp = s.toLowerCase();
		
		for (int i = 0; i < cod.length(); i++)
		{
			if (tmp.charAt(i) != ' ')
			{
				int indice = (int) cod.charAt(i) - 97;
				System.out.println(indice);
				tmp = tmp.substring(0, i);
				tmp = tmp + arr[indice];
				tmp = tmp + cod.substring(i+1, cod.length());
			}
			else
				tmp = tmp + ' ';
		}
		
		return tmp;
	}

	public String deCodifica(String s) {
	
		String cod = s.toLowerCase();
		String tmp = "";
	
		for (int i = 0; i < cod.length(); i++)
		{
			char indice = s.charAt(i);
			int in = search(indice);
			
			if (in >= 0)
				tmp = tmp.substring(0, i) + alfabeto[in];
			else
				tmp = tmp.substring(0, i) + ' ';
		}
		
		return tmp;
	}
	
	static int search(char c) {
		
		if (c != ' ')
		{
			for (int i = 0; i < arr.length; i++)
			{
				if (c == arr[i])
					return i;
			}
		}
		
		return -1;
	}
}
