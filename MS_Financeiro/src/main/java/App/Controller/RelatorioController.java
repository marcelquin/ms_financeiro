package App.Controller;

import App.DTO.RelatorioAnualDTO;
import App.DTO.RelatorioMensalDTO;
import App.Entity.RelatorioMensalEntity;
import App.Enum.FORMAPAGAMENTO;
import App.Enum.StatusPagamento;
import App.Service.RelatorioMensalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("realtorio")
@Tag(name = "realtorio",
        description = "Recebe e processa informações referente a entidade"
)
public class RelatorioController {

    private final RelatorioMensalService service;

    public RelatorioController(RelatorioMensalService service) {
        this.service = service;
    }


    @Operation(summary = "Lista Registros da tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoLancamentoDebito")
    public void NovoLancamentoDebito(@RequestParam Double valorBoleto,
                                     Double parcelas,
                                     @RequestParam int diaVencimento,
                                     Double carenciaPagamento)
    { service.NovoLancamentoDebito(valorBoleto, parcelas, diaVencimento, carenciaPagamento);}

    @Operation(summary = "Lista Registros da tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovoLancamentoVendas")
    public ResponseEntity<RelatorioMensalEntity> NovoLancamentoVendas(@RequestParam String nomeCLiente,
                                                                      @RequestParam String documento,
                                                                      @RequestParam String codigo,
                                                                      @RequestParam List<String> itens,
                                                                      @RequestParam StatusPagamento statusPagamento,
                                                                      @RequestParam LocalDateTime dataVenda,
                                                                      @RequestParam Double valorVenda,
                                                                      Double parcelas,
                                                                      @RequestParam FORMAPAGAMENTO formapagamento)
    { return service.NovoLancamentoVendas(nomeCLiente, documento, codigo, itens, statusPagamento,dataVenda, valorVenda, parcelas, formapagamento);}

    @Operation(summary = "Lista Registros da tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/NovoPagamento")
    public void NovoPagamento(@RequestParam Long idBoleto,
                              @RequestParam FORMAPAGAMENTO formapagamento,
                              Double parcelas)
    { service.NovoPagamento(idBoleto, formapagamento, parcelas);}


    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarRelatorio")
    public ResponseEntity<List<RelatorioMensalEntity>> ListarRelatorio()
    {return service.ListarRelatorios();}

    @Operation(summary = "Busca Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarRelatorioPorMes")
    public ResponseEntity<RelatorioMensalDTO> BuscarRelatorioMensal()
    {return service.BuscarRelatorioMensal();}

    @Operation(summary = "Busca Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarRelatorioMensalComParametros")
    public ResponseEntity<RelatorioMensalDTO> BuscarRelatorioMensalComParametros(@RequestParam int mesReferencia,
                                                                                       @RequestParam int anoReferencia)
    { return service.BuscarRelatorioMensalComParametros(mesReferencia, anoReferencia);}

    @Operation(summary = "Busca Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarRelatorioPorAno")
    public ResponseEntity<RelatorioAnualDTO> BuscarRelatorioPorAno()
    {return service.BuscarRelatorioAnual();}

    @Operation(summary = "Busca Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarRelatorioAnualComParametros")
    public ResponseEntity<RelatorioAnualDTO> BuscarRelatorioAnualComParametros(@RequestParam int anoReferencia)
    { return service.BuscarRelatorioAnualComParametros(anoReferencia);}

    /*
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
    */
}

