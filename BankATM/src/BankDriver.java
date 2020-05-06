import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import database.SQLite;

public class BankDriver {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SQLite.init();

		new StartUpPage();
		
	}

}
