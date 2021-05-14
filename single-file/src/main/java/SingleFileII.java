public class SingleFileII {
	public static void main(String[] args) {
		AnotherClass anotherClass = new AnotherClass();
		YetAnotherClass yetAnotherClass = new YetAnotherClass();
		
		System.out.println(anotherClass.printAnotherMessage());
		System.out.println(yetAnotherClass.printYetAnotherMessage());
	}
}

class AnotherClass{
	public String printAnotherMessage() {
		return "anotherMessage";
	}
}

class YetAnotherClass{
	public String printYetAnotherMessage() {
		return "yetAnotherMessage";
	}
}