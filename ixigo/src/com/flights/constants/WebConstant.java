package com.flights.constants;

public class WebConstant {
	/**
	 * For simplicity, I have put some static json for different service
	 * providers on a different server and getting that data by restful web
	 * service calls
	 */
	public static final String WEB_SERVICE_URL = "http://localhost:8080/ixigoServiceProviders/flightService/FlightDetails/";

	// maintaining an enum of service providers along with their URLs. Since,
	// they are very few, so no need to store in DB.
	public static enum ServiceProviders {
		MAKEMYTRIP(WEB_SERVICE_URL + "mmt"), CLEARTRIP(WEB_SERVICE_URL + "ct"), EASEMYTRIP(
				WEB_SERVICE_URL + "emt"), MUSAFIR(WEB_SERVICE_URL + "mus");

		private ServiceProviders(String url) {
			this.url = url;
		}

		private String url;

		public String getUrl() {
			return url;
		}
	};

}
