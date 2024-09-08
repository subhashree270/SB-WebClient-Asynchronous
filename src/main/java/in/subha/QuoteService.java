package in.subha;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class QuoteService {
	
	private static final String QUOTE_ENDPOINT="https://type.fit/api/quotes";
	WebClient client = WebClient.create();
	//Asynchronous
	public void getQuoteV1() {
		System.out.println("Rest API call started...");
		client.get()
		      .uri(QUOTE_ENDPOINT)
		      .header("Accept","application/json")
		      .retrieve()
		      .bodyToMono(String.class)
		      .subscribe(QuoteService :: handleResponse); 
		System.out.println("Rest API call ended...");
	}
	
	public static void handleResponse(String s) {
		System.out.println(s);
	}
	//Synchronous
	public void getQuoteV2() {
		System.out.println("Rest API call started...");
		Mono<String> bodyToMono = client.get()
		      .uri(QUOTE_ENDPOINT)
		      .retrieve()
		      .bodyToMono(String.class);
		
		System.out.println(bodyToMono.block());
		System.out.println("Rest API call ended...");
		      
	}

}
