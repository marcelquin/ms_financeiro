package App.Service;

import App.DTO.RelatorioAnualDTO;
import App.DTO.RelatorioDTO;
import App.DTO.RelatorioDiarioDTO;
import App.DTO.RelatorioMensalDTO;
import App.Entity.PagamentoEntity;
import App.Entity.RelatorioEntity;
import App.Enum.FORMAPAGAMENTO;
import App.Exceptions.EntityNotFoundException;
import App.Exceptions.IllegalActionException;
import App.Exceptions.NullargumentsException;
import App.repository.PagamentoRepository;
import App.repository.RelatorioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Service
public class RelatorioService {

    private final PagamentoRepository pagamentoRepository;

    private final RelatorioRepository relatorioRepository;


    Locale localBrasil = new Locale("pt", "BR");

    public RelatorioService(PagamentoRepository pagamentoRepository, RelatorioRepository relatorioRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.relatorioRepository = relatorioRepository;
    }

    public ResponseEntity<List<RelatorioEntity>> ListarRelatorio()
    {
        try
        {
            return new ResponseEntity<>(relatorioRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<RelatorioDiarioDTO> BuscarRelatorioPorDia()
    {
        try {
            System.out.println(LocalDateTime.now().getDayOfMonth());
            int dia = LocalDateTime.now().getDayOfMonth();
            RelatorioEntity relatorio = relatorioRepository.findBymesReferencia(LocalDateTime.now().getMonth().getValue()).orElseThrow(
                    () -> new EntityNotFoundException()
            );
            Double totalVendidoDebito = 0.0;
            Double totalVendidoCrebito = 0.0;
            Double totalVendidodinheiro = 0.0;
            Double totalVendidoPix = 0.0;
            for (PagamentoEntity pagamento : relatorio.getPagamentos()) {
                if (pagamento.getDataPagamento().getDayOfMonth() == dia) {
                    if (pagamento.getFormaPagamento() == FORMAPAGAMENTO.PIX) {
                        totalVendidoPix += pagamento.getValor();
                    }
                    if (pagamento.getFormaPagamento() == FORMAPAGAMENTO.DINHEIRO) {
                        totalVendidodinheiro += pagamento.getValor();
                    }
                    if (pagamento.getFormaPagamento() == FORMAPAGAMENTO.DEBITO) {
                        totalVendidoDebito += pagamento.getValor();
                    }
                    if (pagamento.getFormaPagamento() == FORMAPAGAMENTO.CREDITO) {
                        totalVendidoCrebito += pagamento.getValor();
                    }
                }
                String valorPix = NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getValorVendidoPix());
                String valorDinheiro = NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getValorVendidoDinheiro());
                String valorCredito = NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getValorVendidoCredito());
                String valorDebito = NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getValorVendidoDebito());
                Double valorTotal = totalVendidodinheiro + totalVendidoCrebito + totalVendidoDebito + totalVendidoPix;
                String valottotal = NumberFormat.getCurrencyInstance(localBrasil).format(valorTotal);
                RelatorioDiarioDTO response = new RelatorioDiarioDTO(dia, valorDinheiro, valorPix, valorCredito, valorDebito, valottotal);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<RelatorioDiarioDTO> BuscarRelatorioPordiaReferencia(int diaReferencia)
    {
        try
        {
            if(diaReferencia > 0 &&
                    diaReferencia < 32 )
            {
                //localiza por mes, for nos pagamentos, pesquisa dia
                RelatorioEntity relatorio = relatorioRepository.findBymesReferencia(LocalDateTime.now().getMonth().getValue()).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                Double totalVendidoDebito = 0.0;
                Double totalVendidoCrebito = 0.0;
                Double totalVendidodinheiro = 0.0;
                Double totalVendidoPix = 0.0;
                for(PagamentoEntity pagamento : relatorio.getPagamentos())
                {
                    if(pagamento.getDataPagamento().getDayOfMonth() == diaReferencia)
                    {
                        if(pagamento.getFormaPagamento() == FORMAPAGAMENTO.PIX)
                        {
                            totalVendidoPix += pagamento.getValor();
                        }
                        if(pagamento.getFormaPagamento() == FORMAPAGAMENTO.DINHEIRO)
                        {
                            totalVendidodinheiro += pagamento.getValor();
                        }
                        if(pagamento.getFormaPagamento() == FORMAPAGAMENTO.DEBITO)
                        {
                            totalVendidoDebito += pagamento.getValor();
                        }
                        if(pagamento.getFormaPagamento() == FORMAPAGAMENTO.CREDITO)
                        {
                            totalVendidoCrebito += pagamento.getValor();
                        }
                    }
                    String valorPix = NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getValorVendidoPix());
                    String valorDinheiro = NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getValorVendidoDinheiro());
                    String valorCredito = NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getValorVendidoCredito());
                    String valorDebito = NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getValorVendidoDebito());
                    Double valorTotal = totalVendidodinheiro + totalVendidoCrebito + totalVendidoDebito + totalVendidoPix;
                    String valottotal = NumberFormat.getCurrencyInstance(localBrasil).format(valorTotal);
                    RelatorioDiarioDTO response = new RelatorioDiarioDTO(diaReferencia,valorDinheiro,valorPix,valorCredito,valorDebito,valottotal);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }

            }
            else
            {throw  new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<RelatorioMensalDTO> BuscarRelatorioPorMes()
    {
        try
        {
            RelatorioEntity relatorio = relatorioRepository.findBymesReferencia(LocalDateTime.now().getMonth().getValue()).orElseThrow(
                    ()-> new EntityNotFoundException()
            );
            RelatorioMensalDTO response = new RelatorioMensalDTO(LocalDateTime.now().getMonth().getValue(), relatorio.getValorVendidoDinheiroFront(), relatorio.getValorVendidoPixFront(), relatorio.getValorVendidoCreditoFront(), relatorio.getValorVendidoDebitoFront(), relatorio.getTotalVendasFront());
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<RelatorioMensalDTO> BuscarRelatorioPorMesReferencia(int mesReferencia)
    {
        try
        {
            if(mesReferencia > 0 &&
               mesReferencia < 13 )
            {
                RelatorioEntity relatorio = relatorioRepository.findBymesReferencia(mesReferencia).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                System.out.println(relatorio.getMesReferencia());
                RelatorioMensalDTO response = new RelatorioMensalDTO(mesReferencia, relatorio.getValorVendidoDinheiroFront(), relatorio.getValorVendidoPixFront(), relatorio.getValorVendidoCreditoFront(), relatorio.getValorVendidoDebitoFront(), relatorio.getTotalVendasFront());
                return  new ResponseEntity<>(response, HttpStatus.OK);
            }
            else
            {throw  new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<RelatorioAnualDTO> BuscarRelatorioPorAno()
    {
        try
        {
            RelatorioEntity relatorio = relatorioRepository.findByanoReferencia(LocalDateTime.now().getYear()).orElseThrow(
                    ()-> new EntityNotFoundException()
            );
            RelatorioAnualDTO response = new RelatorioAnualDTO(relatorio.getAnoReferencia(),relatorio.getValorVendidoDinheiroFront(),relatorio.getValorVendidoPixFront(),relatorio.getValorVendidoCreditoFront(),relatorio.getValorVendidoDebitoFront(),relatorio.getTotalVendasFront());
            return  new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<RelatorioAnualDTO> BuscarRelatorioPorAnoReferencia(int anoReferencia)
    {
        try
        {
            if(anoReferencia > 0)
            {
                RelatorioEntity relatorio = relatorioRepository.findByanoReferencia(anoReferencia).orElseThrow(
                        ()-> new EntityNotFoundException()
                );
                RelatorioAnualDTO response = new RelatorioAnualDTO(relatorio.getAnoReferencia(),relatorio.getValorVendidoDinheiroFront(),relatorio.getValorVendidoPixFront(),relatorio.getValorVendidoCreditoFront(),relatorio.getValorVendidoDebitoFront(),relatorio.getTotalVendasFront());
                return  new ResponseEntity<>(response, HttpStatus.OK);
            }
            else
            {throw  new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }



    public ResponseEntity<RelatorioEntity> NovoLancamento(FORMAPAGAMENTO formaPagamento,
                                                          Double parcelas,
                                                          Double valor)
    {
        try
        {
            if(formaPagamento != null &&
               valor != null)
            {
                if(formaPagamento != FORMAPAGAMENTO.CREDITO && parcelas > 1)
                {throw new IllegalActionException("Ação não Permitida");}
                if(valor < 0)
                {throw new IllegalActionException("Ação não Permitida");}
                PagamentoEntity pagamento = new PagamentoEntity();
                pagamento.setFormaPagamento(formaPagamento);
                if(parcelas == null)
                {
                    pagamento.setParcelas(1.0);
                    pagamento.setValor(valor);
                }
                else
                {
                    pagamento.setParcelas(parcelas);
                    pagamento.setValor(valor);
                    Double valorParcela = valor / parcelas;
                    pagamento.setValorParcela(valorParcela);
                }
                pagamento.setDataPagamento(LocalDateTime.now());
                pagamento.setTimeStamp(LocalDateTime.now());
                if(relatorioRepository.existsBymesReferencia(pagamento.getDataPagamento().getMonth().getValue()))
                {
                    RelatorioEntity relatorio = relatorioRepository.findBymesReferencia(pagamento.getDataPagamento().getMonth().getValue()).get();
                    relatorio.setTimeStamp(LocalDateTime.now());
                    if(formaPagamento == FORMAPAGAMENTO.CREDITO)
                    {
                        relatorio.setValorVendidoCredito(relatorio.getValorVendidoCredito() +valor);
                    }
                    if(formaPagamento == FORMAPAGAMENTO.DEBITO)
                    {
                        relatorio.setValorVendidoDebito(relatorio.getValorVendidoDebito() +valor);
                    }
                    if(formaPagamento == FORMAPAGAMENTO.PIX)
                    {
                        relatorio.setValorVendidoPix(relatorio.getValorVendidoPix() +valor);
                    }
                    if(formaPagamento == FORMAPAGAMENTO.DINHEIRO)
                    {
                        relatorio.setValorVendidoDinheiro(relatorio.getValorVendidoDinheiro() +valor);
                    }
                    relatorio.setTotalVendas(relatorio.getValorVendidoCredito() +
                            relatorio.getValorVendidoDebito() +
                            relatorio.getValorVendidoDinheiro() +
                            relatorio.getValorVendidoPix());
                    relatorio.getPagamentos().add(pagamento);
                    relatorio.setValorVendidoDebitoFront(NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getValorVendidoDebito()));
                    relatorio.setValorVendidoPixFront(NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getValorVendidoPix()));
                    relatorio.setValorVendidoCreditoFront(NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getValorVendidoCredito()));
                    relatorio.setValorVendidoDinheiroFront(NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getValorVendidoDinheiro()));
                    relatorio.setTotalVendasFront(NumberFormat.getCurrencyInstance(localBrasil).format(relatorio.getTotalVendas()));
                    pagamentoRepository.save(pagamento);
                    relatorioRepository.save(relatorio);
                    return new ResponseEntity<>(relatorio, HttpStatus.CREATED);
                }
                else
                {
                    int mesAnterior = pagamento.getDataPagamento().getMonth().minus(1).getValue();
                    System.out.println("mes anterior: "+mesAnterior);
                    RelatorioEntity relatorioAnterior = relatorioRepository.findBymesReferencia(mesAnterior).get();
                    relatorioAnterior.setDataFechamento(LocalDateTime.now());
                    relatorioAnterior.setTimeStamp(LocalDateTime.now());
                    relatorioRepository.save(relatorioAnterior);
                    RelatorioEntity relatorioEntity = new RelatorioEntity();
                    relatorioEntity.setDataAbertura(LocalDateTime.now());
                    relatorioEntity.setValorVendidoDinheiro(0.0);
                    relatorioEntity.setValorVendidoDebito(0.0);
                    relatorioEntity.setValorVendidoCredito(0.0);
                    relatorioEntity.setValorVendidoPix(0.0);
                    relatorioEntity.setAnoReferencia(pagamento.getDataPagamento().getYear());
                    relatorioEntity.setMesReferencia(pagamento.getDataPagamento().getMonth().getValue());
                    if(formaPagamento == FORMAPAGAMENTO.CREDITO)
                    {
                        Double valorCompra = relatorioEntity.getValorVendidoCredito() + pagamento.getValor();
                        relatorioEntity.setValorVendidoCredito(valorCompra);
                    }
                    if(formaPagamento == FORMAPAGAMENTO.DEBITO)
                    {
                        Double valorCompra = relatorioEntity.getValorVendidoDebito() + pagamento.getValor();
                        relatorioEntity.setValorVendidoDebito(valorCompra);
                    }
                    if(formaPagamento == FORMAPAGAMENTO.PIX)
                    {
                        Double valorCompra = relatorioEntity.getValorVendidoPix() + pagamento.getValor();
                        relatorioEntity.setValorVendidoPix(valorCompra);
                    }
                    if(pagamento.getFormaPagamento() == FORMAPAGAMENTO.DINHEIRO)
                    {
                        relatorioEntity.setValorVendidoDinheiro(relatorioEntity.getValorVendidoDinheiro() + pagamento.getValor());
                    }
                    relatorioEntity.setTotalVendas(relatorioEntity.getValorVendidoCredito() +
                                                   relatorioEntity.getValorVendidoDebito() +
                                                   relatorioEntity.getValorVendidoDinheiro() +
                                                   relatorioEntity.getValorVendidoPix());
                    relatorioEntity.setTimeStamp(LocalDateTime.now());
                    relatorioEntity.setValorVendidoDebitoFront(NumberFormat.getCurrencyInstance(localBrasil).format(relatorioEntity.getValorVendidoDebito()));
                    relatorioEntity.setValorVendidoPixFront(NumberFormat.getCurrencyInstance(localBrasil).format(relatorioEntity.getValorVendidoPix()));
                    relatorioEntity.setValorVendidoCreditoFront(NumberFormat.getCurrencyInstance(localBrasil).format(relatorioEntity.getValorVendidoCredito()));
                    relatorioEntity.setValorVendidoDinheiroFront(NumberFormat.getCurrencyInstance(localBrasil).format(relatorioEntity.getValorVendidoDinheiro()));
                    relatorioEntity.setTotalVendasFront(NumberFormat.getCurrencyInstance(localBrasil).format(relatorioEntity.getTotalVendas()));
                    relatorioEntity.setPagamentos((List<PagamentoEntity>) pagamento);
                    pagamentoRepository.save(pagamento);
                    relatorioRepository.save(relatorioEntity);
                    pagamentoRepository.save(pagamento);
                    relatorioRepository.save(relatorioEntity);
                    return new ResponseEntity<>(relatorioEntity, HttpStatus.CREATED);
                }
            }
            else{throw new NullargumentsException();}

        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

}
