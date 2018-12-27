package GestoreLogin;

import java.io.Serializable;

public interface Comparatore<T> extends Serializable{
	
	int compareTo(T obj1, T obj2);
	
}
