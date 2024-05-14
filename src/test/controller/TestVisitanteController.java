package test.controller;

import apivisitante.entity.dto.VisitanteRequestDto;
import org.junit.Test;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertNotNull;
import static sun.security.krb5.internal.KDCOptions.with;
import static sun.security.util.KnownOIDs.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnviroment = SpringBootTest.WebEnviroment.RANDOM_PORT)

public class TestVisitanteController {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void deveEnviarRequisicaoParaCadastrarVisitanteComSucesso() {
        var endpoint = "/visitantes";
        var metodo = "POST";
        var cpf = "111111111";
        var nome = "Visitante da DIO";
        var payload = new VisitanteRequestDto(cpf, nome);

        var response = with()
                .contentType(ContentType.JSON)
                .body(payload)
                .request(metodo, endpoint)
                .then()
                .extract()
                .response();

        var json = response.jsonPath();

        assertAll("todos os testes",
                () -> assertEquals(HttpStatus.CREATED.value(), response.getStatusCode()),
                () -> assertNotNull(json.getString("id"))
        );
    }

    @Test
    void deveEnviarRequisicaoParaCadastrarVisitanteMasRetornaCpfInvalido() {
        var endpoint = "/visitantes";
        var metodo = "POST";
        var nome = "Visitante da DIO";
        var payload = new VisitanteRequestDto(null, nome);

        var response = with()
                .contentType(ContentType.JSON)
                .body(payload)
                .request(metodo, endpoint)
                .then()
                .extract()
                .response();

        var json = response.jsonPath();

        assertAll("todos os testes",
                () -> assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode()),
                () -> assertNotNull(json.getString("dataHora")),
                () -> assertEquals("cpf invalido ou não informado", json.getString("mensagem"))

        );
    }

    @Test
    void deveEnviarRequisicaoParaCadastrarVisitanteMasRetornaNomeInvalido() {
        var endpoint = "/visitantes";
        var metodo = "POST";
        var cpf = "111111111";
        var payload = new VisitanteRequestDto(cpf, null);
        var response = with()
                .contentType(ContentType.JSON)
                .body(payload)
                .request(metodo, endpoint)
                .then()
                .extract()
                .response();

        var jason = response.jsonPath();

        assertAll("todos os testes",
                () -> assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode()),
                () -> assertNotNull(json.getString("dataHora")),
                () -> assertEquals("nome não informado", json.getString("mensagem"))
        );
    }
}
