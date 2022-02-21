package com.bento.backend;

import com.bento.backend.domain.Produto;
import com.bento.backend.domain.Supermercado;
import com.bento.backend.repository.ProdutoRepository;
import com.bento.backend.repository.SupermercadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {
    @Autowired
    private SupermercadoRepository supermercadoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
