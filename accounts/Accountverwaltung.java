package accounts;

import java.util.ArrayList;

public class Accountverwaltung {

	private ArrayList<Anbieter> anbieter = new ArrayList<Anbieter>();
	private ArrayList<Betreiber> betreiber = new ArrayList<Betreiber>();
	private ArrayList<Kunde> kunden = new ArrayList<Kunde>();

	public void createKunde(String email, String name, String password) throws AlreadyInUseException{
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		kunden.add(new Kunde(email, name, password));
	}

	public void createAnbieter(String email, String name, String password) throws AlreadyInUseException{
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		anbieter.add(new Anbieter(email, name, password));
	}
	
	public void createBetreiber(String email, String name, String password) throws AlreadyInUseException{
		if (!isFreeEmail(email) || !isFreeName(name))
			throw new AlreadyInUseException();
		betreiber.add(new Betreiber(email, name, password));
	}
	
	public void setEnableAccount(Account acc, boolean enable){
		acc.setGesperrt(enable);
	}
	
	public boolean delAccount(Account acc){
		boolean success[] = new boolean[3];
		success[0] = getAnbieter().remove(acc);
		success[1] = getBetreiber().remove(acc);
		success[2] = getKunden().remove(acc);
		return (success[0] || success[1] || success[2]);
	}

	public ArrayList<Anbieter> getAnbieter(){
		return anbieter;
	}
	public ArrayList<Betreiber> getBetreiber(){
		return betreiber;
	}
	public ArrayList<Kunde> getKunden(){
		return kunden;
	}
	public ArrayList<Account> getAccounts(){
		ArrayList<Account> result = new ArrayList<Account>();
		result.addAll(getAnbieter());
		result.addAll(getBetreiber());
		result.addAll(getKunden());
		return result;
	}
	
	private boolean isFreeEmail(String email) {
		boolean result = true;
		for(Account a : getAccounts())
			if(a.getEmail().equals(email))
				result = false;
		return result;
	}

	private boolean isFreeName(String name) {
		boolean result = true;
		for(Account a : getAccounts())
			if(a.getName().equals(name))
				result = false;
		return result;
	}
}
