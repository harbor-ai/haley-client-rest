package ai.harbor.client.rest;

import ai.haley.client.rest.HaleyRestClient;

public class HarborAILeadSubmit {

	static void o(Object o) { System.out.println(o); }
	
	public final static String DEFAULT_SERVER_URL = "https://rest-testing.harbor.ai";
	
	public static void main(String[] args) throws Exception {
		
		if(args.length < 2 || args.length > 3) {
			o("Usage: <username> <password> [serverURL]");
			return;
		}
		
		String username = args[0];
		o("username: " + username);
		
		String password = args[1];
		String p = "";
		for(int i = 0 ; i < password.length(); i++) {
			if(password.length() >= 8 && ( i == 0 || i == password.length()-1) ) {
				p += password.charAt(i);
				continue;
			}
			p += "*";
		}
		o("password: " + p);
		
		String serverURL = null;
		if( args.length > 2 ) {
			serverURL = args[2];
			o("serverURL: " + serverURL);
		} else {
			serverURL = DEFAULT_SERVER_URL;
			o("serverURL: " + serverURL + " (default)");
		}

		HaleyRestClient haleyClient = new HaleyRestClient(serverURL, username, password);
		
		String jsonData = 
		"{\n" +
		"\t\"LeadID\" : \"126473722\",\n" +
		"\t\"LeadType\": \"Business Property/Casualty\",\n" +
		"\t\"DateReceived\": \"01/22/2018\",\n" +
		"\t\"TimeReceived\": \"2:50 P.M. CST\",\n" +
		"\t\n" +
		"\t\"FirstName\": \"Isaac\",\n" +
		"\t\"LastName\": \"Allen\",\n" +
		"\t\"Address\": \"79108 Mopac Hwy\",\n" +
		"\t\"ZipCode\": \"78759\",\n" +
		"\t\"City\": \"AUSTIN\",\n" +
		"\t\"County\": \"TRAVIS\",\n" +
		"\t\"State\": \"TX\",\n" +
		"\t\"PhoneDay\": \"8047528122\",\n" +
		"\t\"PhoneEve\": \"8048598397\",\n" +
		"\t\"PhoneCell\": \"8047528122\",\n" +
		"\t\"Email\": \"IsaacAllen262329296@test.org\",\n" +
		"\t\"Comment\": \"None\",\n" +
		"\t\n" +
		"\t\"CompanyName\": \"Test Company\",\n" +
		"\t\"LegalEntity\": \"Sole Proprietorship\",\n" +
		"\t\"YearsInBusiness\": 40,\n" +
		"\t\"Revenue\": \"Under $100,000\",\n" +
		"\t\"Partners\": 29,\n" +
		"\t\"FullTimeEmployees\": 6,\n" +
		"\t\"PartTimeEmployees\": 14,\n" +
		"\t\"SubContractors\": 9,\n" +
		"\t\"SIC\": \"0111\",\n" +
		"\t\"SeasonalBusiness\": false,\n" +
		"\t\"Subsidiaries\": 3,\n" +
		"\t\"GeneralLiabilityCoverage\": \"$1,000,000\",\n" +
		"\t\n" +
		"\t\"General_Liability\": false,\n" +
		"\t\"Commercial_Auto\": false,\n" +
		"\t\"Commercial_Property\": false,\n" +
		"\t\"Professional_Liability\": false,\n" +
		"\t\"Directors_and_Officers_Liability\": false,\n" +
		"\t\"Business_Owners_Package_Policy\": true,\n" +
		"\t\"Workers_Compensation\": false,\n" +
		"\t\"Commercial_Crime\": false\n" +
		"}";
		
	
		System.out.println(jsonData);

	
		String okResponse = haleyClient.postJsonData("/lead", jsonData);
		
		o("Server response: " + okResponse);
		
	}
	
}
