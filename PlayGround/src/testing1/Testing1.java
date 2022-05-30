package testing1;

public class Testing1 {

	public interface Foo {
		public int Too ();
	}


	class Too implements Foo{

		Too(){
			System.out.println("constructor too");	
		}
		
		@Override
		public int Too() {
			System.out.println("override too");
			return 0;
		}
		
	}
	
	public static void psvm() {
//		Too too =new Too();// error

	}
}
