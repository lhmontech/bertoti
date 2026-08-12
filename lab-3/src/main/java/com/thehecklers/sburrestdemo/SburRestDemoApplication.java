package com.thehecklers.sburrestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class SburRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SburRestDemoApplication.class, args);
	}

}
@CrossOrigin(origins = {"http://localhost:8090","http://127.0.0.1:5500"})
@RestController
@RequestMapping("/comics")


class RestApiDemoController {
	private List<Comic> comics = new ArrayList<>();

	public RestApiDemoController() {
		comics.addAll(List.of(
				new Comic("Batman: O cavaleiro das trevas", "DC", 15.90),
				new Comic("Homem Aranha", "Marvel", 12.50),
				new Comic("Vingadores: A era de Ultron", "Marvel", 17.0),
				new Comic("Invencível", "Panini", 12.50)
		));
	}

	@GetMapping
	Iterable<Comic> getComics() {
		return comics;
	}

	@GetMapping("/{id}")
	Optional<Comic> getComicById(@PathVariable String id) {
		for (Comic c: comics) {
			if (c.getId().equals(id)) {
				return Optional.of(c);
			}
		}

		return Optional.empty();
	}

	@PostMapping
	Comic postComic(@RequestBody Comic comic) {
		comics.add(comic);
		return comic;
	}

	@PutMapping("/{id}")
	ResponseEntity<Comic> putComic(@PathVariable String id,
									 @RequestBody Comic comic) {
		int comicIndex = -1;

		for (Comic c: comics) {
			if (c.getId().equals(id)) {
				comicIndex = comics.indexOf(c);
				comics.set(comicIndex, comic);
			}
		}

		return (comicIndex == -1) ?
				new ResponseEntity<>(postComic(comic), HttpStatus.CREATED) :
				new ResponseEntity<>(comic, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	void deleteComic(@PathVariable String id) {
		comics.removeIf(c -> c.getId().equals(id));
	}
}

class Comic {
	private final String id;
	private String name;
	private String publisher;
	private double price;

	public Comic() {
		this.id = UUID.randomUUID().toString();
	}

	public Comic(String id, String name, String publisher, double price) {
		this.id = id;
		this.name = name;
		this.publisher = publisher;
		this.price = price;
	}

	public Comic(String name, String publisher, double price) {
		this(UUID.randomUUID().toString(), name, publisher, price);
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}