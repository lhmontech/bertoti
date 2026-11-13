package com.thehecklers.sburrestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
public class SburRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SburRestDemoApplication.class, args);
	}

}
@CrossOrigin(origins = {"http://localhost:8080","http://127.0.0.1:5500"})

@RestController
@RequestMapping("/comics")
class RestApiDemoController {
	private final ComicRepository comicRepository;

	public RestApiDemoController(ComicRepository comicRepository) {
		this.comicRepository = comicRepository;
	}

	@PostMapping
	Comic postComic(@RequestBody Comic comic) {
		comicRepository.save(comic);
		return comic;
	}

	@GetMapping
	List<Comic> getComics() {
		return comicRepository.findAllComics();
	}

	@GetMapping("/{id}")
	ResponseEntity<Comic> getComicById(@PathVariable String id) {

		return comicRepository.findComicById(id)
				.map(comic -> new ResponseEntity<>(comic, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}


	@PutMapping("/{id}")
	ResponseEntity<Comic> putComic(@PathVariable String id,
	                               @RequestBody Comic comic) {
		comic.setId(id);
		int rowsUpdated = comicRepository.update(comic);

		if (rowsUpdated == 0){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(comic, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	ResponseEntity<Comic> deleteComic(@PathVariable String id) {
		int rowsDeleted = comicRepository.delete(id);

		if (rowsDeleted == 0){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}