package accounts;

/**
 * 
 * @author Neumann
 *
 */
public class Default extends Account
{

	public Default() 
	{
		super("", "", "");
	}

	@Override
	public int getTyp() 
	{
		return Account.NONE;
	}

}
