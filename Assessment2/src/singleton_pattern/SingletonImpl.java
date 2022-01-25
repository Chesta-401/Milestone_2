/*
 *  Singleton Pattern :- if several people have same requirement then it is not recommended to 
 *  create a separate object for every requirement we can create only one object and reuse same
 *  object for every similar requirement so that performance and memory utilization will be improved. 
 *  
 */
package singleton_pattern;

import java.io.Serializable;

enum SingletonEnum{
	INSTANCE;
}



class Singleton implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	//Use volatile as double lock checking is also broken

	//(as in eager approach object is made whether it is used or not so we go for lazy instantiation)

	private static volatile Singleton singleton = null; //Lazy instantiation


	// private constructor
	private Singleton() { 
		if (singleton != null) {
			/*
			 * Reflection Issue - Someone tries to break our singleton by access our private 
			 * constructor throw exception if someone try to use REFLECTION to break singleton pattern
			 */

			throw new IllegalStateException();
		}
	}



	public static Singleton getSingleton10() {
		if (singleton == null) {
			if (singleton == null) {
				//using double lock checking with synchronized block to handle multithreading issue
				synchronized (Singleton.class) {
					singleton = new Singleton();	//Lazy instantiation
				}
			}
		}
		return singleton;
	}
	/*
	 * AVOIDING SERIALIZATION ISSUE 
	 * (as object can be made using deserialization so to avoid 
	 * that readResolve() is defined.  )
	 */


	private Object readResolve() {
		return singleton;
	}


	/*
	 * CLONING ISSUE
	 * When implementing for Cloneable, we may override the clone method and return the 
	 * singleton object instead explicitly to avoid another object creation. 
	 */


	@Override
	protected Object clone() throws CloneNotSupportedException {
		return singleton;
	}
}



public class SingletonImpl {
	public static void main(String[] args) {
		/*   Implementing Enum singleton pattern, since by default enum is singleton
				 but it is a misuse of Enum concept yet we can create a singleton object.
		 */

		SingletonEnum singleton1 = SingletonEnum.INSTANCE;
		System.out.println(singleton1.hashCode());

		SingletonEnum singleton2 = SingletonEnum.INSTANCE;
		System.out.println(singleton2.hashCode());
	}
}

