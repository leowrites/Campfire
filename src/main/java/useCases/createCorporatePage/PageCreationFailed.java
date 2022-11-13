package useCases.createCorporatePage;

public class PageCreationFailed extends RuntimeException{
    public PageCreationFailed(String error){super(error);}
}
