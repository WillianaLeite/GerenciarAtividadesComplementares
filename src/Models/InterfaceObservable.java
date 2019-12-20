package Models;

/**
 *
 * @author Williana
 */
public interface InterfaceObservable {

    public void incluir(InterfaceObserver observer);

    public void excluir(InterfaceObserver observer);

    public void avisarObservers();

}
