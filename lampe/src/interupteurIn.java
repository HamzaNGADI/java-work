
public interface interupteurIn {
	
	public void register(evenement ev, observer o);
	public void delete(evenement ev, observer o);
	public void notifyobs();
}
