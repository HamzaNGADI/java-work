package bridge;

public abstract class WebApplication {

	protected Theme theme;
	
	public WebApplication(Theme t){
		theme=t;
	}
	
	public void drawTitle()
	{
		theme.drawThemeTitle();
	}
	
	//+ autres méthodes abstraites
	
}
