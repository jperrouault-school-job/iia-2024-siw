package fr.formation.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.model.Hello;
import fr.formation.repo.HelloRepository;
import fr.formation.request.HelloRequest;
import fr.formation.response.HelloResponse;
import lombok.RequiredArgsConstructor;

@RestController // On dit à Spring de gérer l'instance
@RequiredArgsConstructor // Génère un constructeur avec les attributs final
public class HelloApiController {
    // @Autowired // Demande à Spring l'instance
    // private HelloRepository repository;

    private final HelloRepository repository;

    @GetMapping("/hello/find-all")
    public List<HelloResponse> findAll() {
        List<HelloResponse> responses = new ArrayList<>();

        for (Hello hello : this.repository.findAll()) {
            HelloResponse resp = HelloResponse.builder()
                .name(hello.getName())
                .age(hello.getAge())
                .build()
            ;

            responses.add(resp);
        }

        return responses;
    }

    @GetMapping("/hello/find-all-stream")
    public List<HelloResponse> findAllStream() {
        return this.repository.findAll().stream()
            .map(hello -> HelloResponse.builder()
                .name(hello.getName())
                .age(hello.getAge())
                .build()
            )
            .toList()
        ;
    }

    @GetMapping("/hello/find-all-stream-2")
    public List<HelloResponse> findAllStream2() {
        return this.repository.findAll().stream()
            // .map(hello -> this.convert(hello))
            .map(this::convert)
            .toList()
        ;
    }

    private HelloResponse convert(Hello hello) {
        return HelloResponse.builder()
            .name(hello.getName())
            .age(hello.getAge())
            .build()
        ;
    }

    // @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String hello() {
        return "Hello world!";
    }

    @GetMapping("/toto")
    public String helloToto(@RequestParam(defaultValue = "Babar", required = false) String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("/{name}")
    public HelloResponse helloPV(@PathVariable String name) {
        return HelloResponse.builder()
            .name(name)
            .email("name@org.fr")
            .age(26)
            .build()
        ;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String postHello(@RequestBody HelloRequest request) {
        Hello hello = new Hello();

        // hello.setName(request.getName());
        // hello.setAge(request.getAge());
        BeanUtils.copyProperties(request, hello);

        this.repository.save(hello);

        return hello.getId();
    }
}
