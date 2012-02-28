package accounts;

import java.util.Vector;

public class Accountverwaltung {
	
	private Vector<Account> accounts = new Vector<Account>();

	public void createKunde(String email, String name, String password) throws AlreadyInUseException{
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		accounts.add(new Kunde(email, name, password));
	}

	public void createAnbieter(String email, String name, String password) throws AlreadyInUseException{
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		accounts.add(new Anbieter(email, name, password));
	}
	
	public void createBetreiber(String email, String name, String password) throws AlreadyInUseException{
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		accounts.add(new Betreiber(email, name, password));
	}
	
	public void setEnableAccount(Account acc, boolean enable){
		acc.setGesperrt(enable);
	}
	
	public void delAccount(Account acc){
		boolean success = accounts.remove(acc);
		if(!success)
			return;		//TODO exception??
	}
	
	private boolean isFreeEmail(String email) {
		boolean result = true;
		for(Account a : accounts)
			if(a.getEmail().equals(email))
				result = false;
		return result;
	}

	private boolean isFreeName(String name) {
		boolean result = true;
		for(Account a : accounts)
			if(a.getName().equals(name))
				result = false;
		return result;
	}
}
