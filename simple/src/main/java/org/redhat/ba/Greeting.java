package org.redhat.ba;

/**
 * Hello world!
 *
 */
public class Greeting 
{	
	private String greeting;
	
	public Greeting(){
	}
	
	public String getGreeting(){
		return this.greeting;
	}
	
	public void setGreeting(String greeting){
		this.greeting = greeting;
	}
    public static void main( String[] args )
    {
    	Greeting greeting = new Greeting();
    	greeting.setGreeting("HelloWorld!");
        System.out.println( greeting.getGreeting() );
    }
}
