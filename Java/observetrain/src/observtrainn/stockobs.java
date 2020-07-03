package observtrainn;

public interface stockobs {
	
	public void register(event ev, observer o);
	public void remove(event ev, observer o);	
	public void notifyobs();
}
