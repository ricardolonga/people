package br.com.ricardolonga.people;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@EnableAutoConfiguration
public class SampleController {

    private Set<Pessoa> pessoas = new HashSet<Pessoa>();

    public SampleController() {
        pessoas.add(new Pessoa("Bruna"));
        pessoas.add(new Pessoa("Ricardo"));
    }

    @ResponseBody
    @RequestMapping(value = "/pessoas")
    public Set<Pessoa> getAll() {
        return pessoas;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/pessoas", method = RequestMethod.POST, consumes = { "application/json" })
    public void salvar(@RequestBody Pessoa pessoa) {
        pessoas.add(pessoa);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/pessoas", method = RequestMethod.DELETE, consumes = { "application/json" })
    public void remover(@RequestBody Pessoa pessoa) {
        pessoas.remove(pessoa);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }

}