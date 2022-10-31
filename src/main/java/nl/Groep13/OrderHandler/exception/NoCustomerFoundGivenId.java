package nl.Groep13.OrderHandler.exception;

public class NoCustomerFoundGivenId extends Exception{
    public NoCustomerFoundGivenId(){
        super("No customer found");
    }
}
