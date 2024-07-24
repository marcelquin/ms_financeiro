package App.Controller;

import App.DTO.RelatorioAnualDTO;
import App.DTO.RelatorioDTO;
import App.DTO.RelatorioDiarioDTO;
import App.DTO.RelatorioMensalDTO;
import App.Entity.RelatorioEntity;
import App.Enum.FORMAPAGAMENTO;
import App.Service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("realtorio")
@Tag(name = "realtorio",
        description = "Recebe e processa informações referente a entidade"
)
public class RelatorioController {

    private final RelatorioService service;

    public RelatorioController(RelatorioService service) {
        this.service = service;
    }

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarRelatorio")
    public ResponseEntity<List<RelatorioEntity>> ListarRelatorio()
    {return service.ListarRelatorio();}


    @Operation(summary = "Busca Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarRelatorioPorDia")
    public ResponseEntity<RelatorioDiarioDTO> BuscarRelatorioPorDia()
    {return service.BuscarRelatorioPorDia();}

    @Operation(summary = "Busca Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarRelatorioPordiaReferencia")
    public ResponseEntity<RelatorioDiarioDTO> BuscarRelatorioPordiaReferencia(int diaReferencia)
    { return service.BuscarRelatorioPordiaReferencia(diaReferencia); }

    @Operation(summary = "Busca Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarRelatorioPorMes")
    public ResponseEntity<RelatorioMensalDTO> BuscarRelatorioPorMes()
    {return service.BuscarRelatorioPorMes();}

    @Operation(summary = "Busca Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarRelatorioPorMesReferencia")
    public ResponseEntity<RelatorioMensalDTO> BuscarRelatorioPorMesReferencia(@RequestParam int mesReferencia)
    { return service.BuscarRelatorioPorMesReferencia(mesReferencia);}

    @Operation(summary = "Busca Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarRelatorioPorAno")
    public ResponseEntity<RelatorioAnualDTO> BuscarRelatorioPorAno()
    {return service.BuscarRelatorioPorAno();}

    @Operation(summary = "Busca Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarRelatorioPorAnoReferencia")
    public ResponseEntity<RelatorioAnualDTO> BuscarRelatorioPorAnoReferencia(int anoReferencia)
    { return service.BuscarRelatorioPorAnoReferencia(anoReferencia);}

    @Operation(summary = "Salva Registros da tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoLancamento")
    public ResponseEntity<RelatorioEntity> NovoLancamento(@RequestParam FORMAPAGAMENTO formaPagamento,
                                                          Double parcelas,
                                                          @RequestParam Double valor)
    { return service.NovoLancamento(formaPagamento, parcelas, valor);}
}
