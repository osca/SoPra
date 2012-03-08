package accounts;

/**
 * Defaultaccount (Unangemeldet) - erbt von Account
 * 
 * @author Neumann
 */
public class Default extends Account
{

	/**
	 * Konstruktor
	 */
	public Default() 
	{
		super("", "", "");
	}

	
	//-----------------------------------------------------------------------------//
	//	LISTABLE																   //
	//-----------------------------------------------------------------------------//
	
	@Override
	public int getTyp() 
	{
		return Account.NONE;
	}

}
