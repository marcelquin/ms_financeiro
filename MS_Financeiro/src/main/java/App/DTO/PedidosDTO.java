package App.DTO;

import App.Enum.FORMAPAGAMENTO;
import App.Enum.StatusPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record PedidosDTO(

        String nomeCLiente,

        String documento,
        String codigo,
        List<String> itens,
        Double parcelas,
        Double valor,
        StatusPagamento statusPagamento,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataVenda,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataPagamento,
        FORMAPAGAMENTO formapagamento
) {

}
